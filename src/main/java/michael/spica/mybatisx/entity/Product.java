package michael.spica.mybatisx.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import michael.spica.mybatisx.common.base.entity.TenantBaseEntity;
import org.hibernate.annotations.Comment;

/**
 * 产品表
 * <p>
 * Created by michael on 2025-11-25.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table
@Comment(value = "产品表")
public class Product extends TenantBaseEntity {

    /**
     * 产品名称
     */
    @Column(columnDefinition = "varchar(500) COMMENT '产品名称'")
    private String name;

    /**
     * 产品描述
     */
    @Column(columnDefinition = "varchar(500) COMMENT '产品描述'")
    private String description;
}
