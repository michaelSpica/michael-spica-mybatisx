package michael.spica.mybatisx.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import michael.spica.mybatisx.common.base.BaseEntity;

/**
 * 产品表
 * <p>
 * Created by michael on 2025-11-25.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("mic_product") // 👈 指定表名（根据实际表名调整）
public class Product extends BaseEntity {

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品描述
     */
    private String description;
}
