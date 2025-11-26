package michael.spica.mybatisx.common.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Created by michael on 2025-09-30.
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TenantBaseEntity extends BaseEntity {

    /**
     * 租户ID
     */
    @Column(nullable = false, columnDefinition = "bigint COMMENT '租户ID'")
    private Long tenantId;
}
