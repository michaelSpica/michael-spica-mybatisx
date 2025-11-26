package michael.spica.mybatisx.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by michael on 2025-11-21.
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "mybatis-plus.tenant")
public class TenantIgnoreProperties {

    /**
     * 是否启用多租户模式（默认：false:不开启；true:开启）
     */
    private boolean enabled = false;

    /**
     * 不需要多租户隔离的表名集合（不区分大小写）
     */
    private Set<String> ignoreTables = Set.of();

    public void setIgnoreTables(Set<String> ignoreTables) {
        this.ignoreTables = ignoreTables != null ? ignoreTables : Set.of();
    }
}
