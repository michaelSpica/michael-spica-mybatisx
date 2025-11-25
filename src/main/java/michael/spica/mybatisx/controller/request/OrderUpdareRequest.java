package michael.spica.mybatisx.controller.request;

import lombok.Data;

/**
 * Created by michael on 2025-11-25.
 */
@Data
public class OrderUpdareRequest {

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品描述
     */
    private String productDesc;

}
