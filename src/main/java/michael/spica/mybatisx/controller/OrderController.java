package michael.spica.mybatisx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import michael.spica.mybatisx.common.R;
import michael.spica.mybatisx.common.base.BaseController;
import michael.spica.mybatisx.controller.request.OrderCreateRequest;
import michael.spica.mybatisx.controller.request.OrderPageRequest;
import michael.spica.mybatisx.controller.request.OrderUpdareRequest;
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

    @PostMapping
    public R<Order> create(@RequestBody OrderCreateRequest request) {
        return orderService.create(request);
    }

    @PutMapping
    public R<Order> update(@RequestBody OrderUpdareRequest request) {
        return orderService.update(request);
    }

    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable Long id) {
        return orderService.delete(id);
    }

    @GetMapping("/{id}")
    public R<Order> view(@PathVariable Long id) {
        return orderService.view(id);
    }

    @GetMapping
    public R<List<Order>> all() {
        return orderService.all();
    }

    @GetMapping("/page")
    public R<Page<Order>> page(@Valid @RequestBody OrderPageRequest request) {
        return orderService.page(request);
    }
}
