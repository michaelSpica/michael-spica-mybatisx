package michael.spica.mybatisx.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import jakarta.annotation.Resource;
import michael.spica.mybatisx.common.context.TenantContext;
import michael.spica.mybatisx.common.enums.AuthErrorType;
import michael.spica.mybatisx.common.exception.AuthException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

/**
 * Created by michael on 2025-11-25.
 */
@Configuration
@MapperScan("michael.spica.mybatisx.mapper")
public class MybatisPlusConfig {

    @Resource
    private TenantConfig tenantConfig;

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); // 根据数据库类型调整

        // 多租户
        buildTenantInterceptor().ifPresent(interceptor::addInnerInterceptor);

        return interceptor;
    }

    private Optional<TenantLineInnerInterceptor> buildTenantInterceptor() {
        if (!tenantConfig.isEnabled()) {
            return Optional.empty();
        }

        return Optional.of(
                new TenantLineInnerInterceptor(new TenantLineHandler() {
                    @Override
                    public Expression getTenantId() {
                        // 从上下文获取当前租户ID（例如: 从 ThreadLocal 或 SecurityContext）
                        Long tenantId = TenantContext.getCurrentTenantId();
                        if (null == tenantId) {
                            throw new AuthException(AuthErrorType.TENANT_NOT_SET, "未设置租户ID");
                        }
                        return new LongValue(tenantId);
                    }

                    @Override
                    public boolean ignoreTable(String tableName) {
                        return tenantConfig.getIgnoreTables()
                                .stream()
                                .anyMatch(tableName::equalsIgnoreCase);
                    }
                })
        );
    }
}
