package michael.spica.mybatisx.service.impl;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import michael.spica.mybatisx.config.TenantConfig;
import michael.spica.mybatisx.entity.Tenant;
import michael.spica.mybatisx.service.DbOperateService;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by michael on 2025-11-26.
 */
@Slf4j
@Component
public class DbOperateServiceImpl implements DbOperateService {

    private static final Object lock = new Object(); // 锁对象

    @Resource
    private DataSource dataSource;

    @Resource
    private TenantConfig tenantConfig;

    @Override
    public void executeSql(String sql) {
        // 使用同步锁，确保同一时间只有一个线程执行数据库创建操作
        synchronized (lock) {
            try (Connection connection = dataSource.getConnection();
                 Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                log.error("执行 SQL 失败：{}", sql, e);
                throw new RuntimeException("执行 SQL 失败", e);
            }
        }
    }

    @Override
    public String getDbName(Tenant tenant) {
        return Optional.ofNullable(tenantConfig.getDbConfig().getPrefix())
                .filter(StrUtil::isNotBlank)
                .map(prefix -> prefix + tenant.getCode())
                .orElse(tenant.getCode());
    }

    @Override
    public void createTenantDatabase(Tenant tenant) {
        try {
            // 数据库名（避免重复）
            String dbName = tenantConfig.getDbConfig().getPrefix() + tenant.getCode();

            // 创建数据库（指定字符集和排序规则）
            String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS " + dbName + " CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci";
            log.debug("创建数据库SQL：{}", createDatabaseSQL);

            this.executeSql(createDatabaseSQL);

        } catch (Exception e) {
            log.error("创建租户数据库失败", e);
            throw new RuntimeException("创建租户数据库失败");
        }
    }

    @Override
    public void initializeTenantTablesFromTemplate(Tenant tenant) {
        log.debug("初始化租户数据库表结构：{}", tenant.getName());

        try {
            String dbName = this.getDbName(tenant);

            List<TenantConfig.DbConfig.DbInfos> dbInfos = Optional.ofNullable(tenantConfig.getDbConfig().getDbInfos())
                    .orElse(Collections.emptyList());

            dbInfos.stream()
                    .filter(Objects::nonNull)
                    .flatMap(dbInfo -> Optional.ofNullable(dbInfo.getTables())
                            .orElse(Collections.emptyList())
                            .stream()
                            .filter(Objects::nonNull)
                            .filter(StrUtil::isNotBlank)
                            .map(table -> Map.entry(dbInfo, table)))
                    .forEach(e -> {
                        String sql = this.getCreateTableSQL(e.getKey().getDbName(), e.getValue(), dbName);
                        this.executeSql(sql);
                        log.debug("初始化表 `{}`.`{}` 成功", dbName, e.getValue());
                    });

        } catch (Exception e) {
            log.error("租户数据库表初始化失败", e);
            throw new RuntimeException("租户数据库表初始化失败");
        }
    }

    /**
     * 获取表结构 SQL
     *
     * @param templateDb 模板数据库名
     * @param tableName  表名
     * @param targetDb   目标数据库名
     * @return SQL
     */
    private String getCreateTableSQL(String templateDb, String tableName, String targetDb) {
        String sql = null;
        String query = "SHOW CREATE TABLE " + templateDb + "." + tableName;
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                sql = rs.getString("Create Table");
                // 替换数据库名
                sql = sql.replaceFirst("CREATE TABLE `.*?`", "CREATE TABLE `" + targetDb + "`.`" + tableName + "`");
            }
            log.debug("获取表结构成功：\n{}", sql);

        } catch (SQLException e) {
            log.error("获取表结构失败：{}", tableName, e);
            throw new RuntimeException("获取表结构失败：" + tableName, e);
        }
        return sql;
    }
}
