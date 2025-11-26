package michael.spica.mybatisx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import michael.spica.mybatisx.common.R;
import michael.spica.mybatisx.common.base.service.BaseService;
import michael.spica.mybatisx.controller.request.order.OrderCreateRequest;
import michael.spica.mybatisx.controller.request.order.OrderPageRequest;
import michael.spica.mybatisx.controller.request.order.OrderUpdareRequest;
import michael.spica.mybatisx.entity.Order;

import java.util.List;

/**
 * Created by michael on 2025-11-25.
 */
public interface OrderService extends BaseService<Order, Long> {

    R<Order> create(OrderCreateRequest request);

    R<Order> update(OrderUpdareRequest request);

    R<Void> delete(Long id);

    R<Order> view(Long id);

    R<List<Order>> all();

    R<Page<Order>> page(OrderPageRequest request);

    R<Page<Order>> page();
}
