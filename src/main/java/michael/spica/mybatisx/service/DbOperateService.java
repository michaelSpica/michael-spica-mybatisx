package michael.spica.mybatisx.service;

import michael.spica.mybatisx.entity.Tenant;

/**
 * Created by michael on 2025-11-26.
 */
public interface DbOperateService {

    /**
     * 执行SQL
     *
     * @param sql
     */
    void executeSql(String sql);

    /**
     * 获取数据库名称
     *
     * @param tenant
     * @return
     */
    String getDbName(Tenant tenant);

    /**
     * 创建租户数据库
     *
     * @param tenant
     */
    void createTenantDatabase(Tenant tenant);

    /**
     * 初始化租户表结构
     *
     * @param tenant
     */
    void initializeTenantTablesFromTemplate(Tenant tenant);
}
