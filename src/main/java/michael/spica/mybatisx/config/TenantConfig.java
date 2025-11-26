package michael.spica.mybatisx.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * Created by michael on 2025-11-21.
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "tenant")
public class TenantConfig {

    /**
     * 是否启用多租户模式（默认：false:不开启；true:开启）
     */
    private boolean enabled = false;

    /**
     * 不需要多租户隔离的表名集合（不区分大小写）
     */
    private Set<String> ignoreTables = Set.of();

    /**
     * 多租户数据库配置
     */
    private DbConfig dbConfig;

    @Data
    public static class DbConfig {

        /**
         * 是否初始化数据库
         */
        private Boolean init;

        /**
         * 数据库前缀
         */
        private String prefix;

        private String dbUrl;

        private String dbDriver;

        private String dbUsername;

        private String dbPassword;

        private List<DbInfos> dbInfos;

        @Data
        public static class DbInfos {

            private String dbName;

            private List<String> tables;
        }
    }

    public void setIgnoreTables(Set<String> ignoreTables) {
        this.ignoreTables = ignoreTables != null ? ignoreTables : Set.of();
    }

}
