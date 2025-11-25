package michael.spica.mybatisx.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import michael.spica.mybatisx.common.R;
import michael.spica.mybatisx.common.base.service.impl.BaseServiceImpl;
import michael.spica.mybatisx.controller.request.OrderCreateRequest;
import michael.spica.mybatisx.controller.request.OrderPageRequest;
import michael.spica.mybatisx.controller.request.OrderUpdareRequest;
import michael.spica.mybatisx.entity.Order;
import michael.spica.mybatisx.mapper.OrderMapper;
import michael.spica.mybatisx.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by michael on 2025-11-25.
 */
@Slf4j
@Service
public class OrderServiceImpl extends BaseServiceImpl<OrderMapper, Order, Long> implements OrderService {

    @Override
    public R<Order> create(OrderCreateRequest request) {
        Order order = new Order();
        BeanUtils.copyProperties(request, order);
        order.setOrderNo("TB" + System.currentTimeMillis() + RandomUtil.randomNumbers(6));
        order.setUserId(1L);
        order.setTenantId(1L);
        int i = super.getBaseMapper().insert(order);
        if (i > 0) {
            log.info("创建订单成功，订单编号：{}", order.getOrderNo());
            return R.ok(order);
        }
        return R.fail("创建订单失败");
    }

    @Override
    public R<Order> update(OrderUpdareRequest request) {
        LambdaQueryWrapper<Order> lambdaQueryWrapper = new LambdaQueryWrapper<Order>().eq(Order::getOrderNo, request.getOrderNo());
        Order order_ = super.getBaseMapper().selectOne(lambdaQueryWrapper);
        if (null == order_) {
            return R.ok("订单不存在");
        }

        Order order = new Order();
        BeanUtils.copyProperties(request, order);
        int i = super.getBaseMapper().update(order, lambdaQueryWrapper);
        if (i > 0) {
            order = super.getBaseMapper().selectOne(lambdaQueryWrapper);
            log.info("更新订单成功，订单编号：{}", order.getOrderNo());
            return R.ok(order);
        }
        return R.fail("更新订单失败");
    }

    @Override
    public R<Void> delete(Long id) {
        Order order = super.getBaseMapper().selectOne(new LambdaQueryWrapper<Order>().eq(Order::getId, id));
        if (null != order) {
            super.getBaseMapper().deleteById(id);
            log.info("删除订单成功，订单编号：{}", order.getOrderNo());
            return R.ok();
        }
        return R.ok("订单不存在");
    }

    @Override
    public R<Order> view(Long id) {
        Order order = super.getBaseMapper().selectById(id);
        if (null != order) {
            log.info("查看订单成功，订单编号：{}", order.getOrderNo());
            return R.ok(order);
        }
        return R.ok("订单不存在");
    }

    @Override
    public R<List<Order>> all() {
        List<Order> list = super.getBaseMapper().selectList(null);
        if (null != list && !list.isEmpty()) {
            log.info("查看所有订单成功，订单数量：{}", list.size());
            return R.ok(list);
        }
        return R.ok("订单不存在");
    }

    @Override
    public R<Page<Order>> page(OrderPageRequest request) {
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(request.getOrderNo()), Order::getOrderNo, request.getOrderNo());
        return R.ok(super.getBaseMapper().selectPage(Page.of(request.getCurrent(), request.getSize()), wrapper));
    }
}
