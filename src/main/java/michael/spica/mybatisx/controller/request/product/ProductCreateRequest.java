package michael.spica.mybatisx.controller.request.product;

import lombok.Data;

/**
 * Created by michael on 2025-11-25.
 */
@Data
public class ProductCreateRequest {

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品描述
     */
    private String description;
}
