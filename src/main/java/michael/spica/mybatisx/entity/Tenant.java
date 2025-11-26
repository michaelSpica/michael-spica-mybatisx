package michael.spica.mybatisx.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import michael.spica.mybatisx.common.base.entity.BaseEntity;
import michael.spica.mybatisx.common.enums.CommonStatus;
import michael.spica.mybatisx.common.enums.TenantType;
import org.hibernate.annotations.Comment;

/**
 * 租户表
 * <p>
 * Created by michael on 2025-11-26.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table
@Comment(value = "租户表")
public class Tenant extends BaseEntity {

    /**
     * 租户名称
     */
    @Column(nullable = false, unique = true, length = 100, columnDefinition = "varchar(100) COMMENT '租户名称'")
    private String name;

    /**
     * 租户标识
     */
    @Column(nullable = false, unique = true, length = 50, columnDefinition = "varchar(50) COMMENT '租户标识'")
    private String code;

    /**
     * 租户状态
     */
    @Column(columnDefinition = "varchar(100) COMMENT '租户状态'")
    @Enumerated(value = EnumType.STRING)
    private CommonStatus status;

    /**
     * 租户类型
     */
    @Column(columnDefinition = "varchar(100) COMMENT '租户类型'")
    @Enumerated(value = EnumType.STRING)
    private TenantType type;

    /**
     * 租户logo
     */
    @Column(columnDefinition = "varchar(500) COMMENT '租户logo'")
    private String logo;

    /**
     * 租户描述
     */
    @Column(columnDefinition = "varchar(500) COMMENT '租户描述'")
    private String description;

    /**
     * 租户联系人
     */
    @Column(columnDefinition = "varchar(50) COMMENT '租户联系人'")
    private String contact;

    /**
     * 租户联系人电话
     */
    @Column(columnDefinition = "varchar(20) COMMENT '租户联系人电话'")
    private String contactPhone;

    /**
     * 租户联系人邮箱
     */
    @Column(columnDefinition = "varchar(100) COMMENT '租户联系人邮箱'")
    private String contactEmail;

    /**
     * 租户联系人地址
     */
    @Column(columnDefinition = "varchar(200) COMMENT '租户联系人地址'")
    private String contactAddress;

    /**
     * 租户联系人备注
     */
    @Column(columnDefinition = "varchar(500) COMMENT '租户联系人备注'")
    private String contactRemark;
}
