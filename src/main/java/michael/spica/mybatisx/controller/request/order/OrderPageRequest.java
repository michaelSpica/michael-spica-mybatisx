package michael.spica.mybatisx.controller.request.order;

import lombok.Data;
import lombok.EqualsAndHashCode;
import michael.spica.mybatisx.common.PageRequest;

/**
 * Created by michael on 2025-11-25.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderPageRequest extends PageRequest {

    /**
     * 订单编号
     */
    private String orderNo;
}
