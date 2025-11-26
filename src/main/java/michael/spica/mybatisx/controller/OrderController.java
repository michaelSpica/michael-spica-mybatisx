package michael.spica.mybatisx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import michael.spica.mybatisx.common.R;
import michael.spica.mybatisx.common.annotation.Log;
import michael.spica.mybatisx.common.base.BaseController;
import michael.spica.mybatisx.controller.request.order.OrderCreateRequest;
import michael.spica.mybatisx.controller.request.order.OrderPageRequest;
import michael.spica.mybatisx.controller.request.order.OrderUpdareRequest;
import michael.spica.mybatisx.entity.Order;
import michael.spica.mybatisx.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by michael on 2025-11-25.
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Resource
    private OrderService orderService;

    @Log("创建订单")
    @PostMapping
    public R<Order> create(@RequestBody OrderCreateRequest request) {
        return orderService.create(request);
    }

    @Log("更新订单")
    @PutMapping
    public R<Order> update(@RequestBody OrderUpdareRequest request) {
        return orderService.update(request);
    }

    @Log("删除订单")
    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable Long id) {
        return orderService.delete(id);
    }

    @Log("查看订单")
    @GetMapping("/{id}")
    public R<Order> view(@PathVariable Long id) {
        return orderService.view(id);
    }

    @Log("查询所有订单")
    @GetMapping
    public R<List<Order>> all() {
        return orderService.all();
    }

    @Log("分页查询订单")
    @GetMapping("/page")
    public R<Page<Order>> page(@Valid @RequestBody OrderPageRequest request) {
        return orderService.page(request);
    }

    @Log("分页查询订单-v2")
    @GetMapping("/page/v2")
    public R<Page<Order>> pageDefault() {
        return orderService.pageDefault();
    }
}
