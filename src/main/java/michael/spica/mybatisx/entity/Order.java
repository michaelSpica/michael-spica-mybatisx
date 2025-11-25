package michael.spica.mybatisx.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import michael.spica.mybatisx.common.base.BaseEntity;

/**
 * 订单表
 * <p>
 * Created by michael on 2025-11-20.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("mic_order") // 👈 指定表名（根据实际表名调整）
public class Order extends BaseEntity {

    /**
     * 订单编号
     */
    @TableField(insertStrategy = FieldStrategy.NOT_NULL)
    private String orderNo;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品描述
     */
    private String productDesc;

    /**
     * 用户ID
     */
    private Long userId;
}
