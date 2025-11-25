package michael.spica.mybatisx.controller.request.order;

import lombok.Data;

/**
 * Created by michael on 2025-11-25.
 */
@Data
public class OrderCreateRequest {

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
