package michael.spica.mybatisx.common.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Created by michael on 2025-11-25.
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint COMMENT '主键ID'")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 租户ID
     */
    @Column(columnDefinition = "bigint COMMENT '租户ID'")
    private Long tenantId;

    /**
     * 创建时间
     */
    @Column(columnDefinition = "datetime COMMENT '创建时间'")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @Column(columnDefinition = "datetime COMMENT '更新时间'")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
