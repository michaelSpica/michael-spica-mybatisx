package michael.spica.mybatisx.entity;

import com.baomidou.mybatisplus.annotation.DbType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import michael.spica.mybatisx.common.base.entity.BaseEntity;
import org.hibernate.annotations.Comment;

/**
 * Created by michael on 2025-11-26.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table
@Comment(value = "租户数据源配置表")
public class TenantDbConfig extends BaseEntity {

    @Column(columnDefinition = "bigint COMMENT '租户ID'")
    private Long tenantId;

    @Column(columnDefinition = "varchar(500) COMMENT '数据库连接'")
    private String dbUrl;

    @Column(columnDefinition = "varchar(100) COMMENT '数据库用户名'")
    private String dbUsername;

    @Column(columnDefinition = "varchar(50) COMMENT '数据库密码'")
    private String dbPassword;

    @Column(columnDefinition = "varchar(50) COMMENT '数据库类型'")
    @Enumerated(value = EnumType.STRING)
    private DbType dbType;

    @Column(columnDefinition = "varchar(200) COMMENT '数据库驱动'")
    private String dbDriver;
}
