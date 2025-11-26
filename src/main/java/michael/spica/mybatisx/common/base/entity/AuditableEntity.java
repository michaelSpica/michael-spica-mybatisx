package michael.spica.mybatisx.common.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Created by michael on 2025-09-28.
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity extends BaseEntity {

    @Column(columnDefinition = "bigint COMMENT '创建者'")
    private Long creator;

    @Column(columnDefinition = "bigint COMMENT '更新者'")
    private Long updater;
}
