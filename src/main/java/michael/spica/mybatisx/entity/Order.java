package michael.spica.mybatisx.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import michael.spica.mybatisx.common.base.BaseEntity;
import org.hibernate.annotations.Comment;

/**
 * 订单表
 * <p>
 * Created by michael on 2025-11-20.
 */
@EqualsAndHashCode(callSuper = true)
@Data
//@TableName("mic_order") // 👈 指定表名（根据实际表名调整）
@Entity
@Table
@Comment(value = "订单表")
public class Order extends BaseEntity {

    /**
     * 订单编号
     */
    @Column(unique = true, nullable = false, columnDefinition = "varchar(100) COMMENT '订单编号'")
//    @TableField(insertStrategy = FieldStrategy.NOT_NULL)
    private String orderNo;

    /**
     * 产品名称
     */
    @Column(columnDefinition = "varchar(500) COMMENT '产品名称'")
    private String productName;

    /**
     * 产品描述
     */
    @Column(columnDefinition = "varchar(500) COMMENT '产品描述'")
    private String productDesc;

    /**
     * 用户ID
     */
    @Column(columnDefinition = "varchar(100) COMMENT '用户ID'")
    private Long userId;
}
