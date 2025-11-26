package michael.spica.mybatisx.common.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import michael.spica.mybatisx.common.BasePageRequest;
import michael.spica.mybatisx.common.base.service.BaseService;
import michael.spica.mybatisx.common.base.service.support.BaseServiceImplSupport;

import java.io.Serializable;
import java.util.Optional;

/**
 * Created by michael on 2025-11-25.
 */
@Slf4j
public class BaseServiceImpl<M extends BaseMapper<T>, T, ID extends Serializable>
        extends BaseServiceImplSupport<M, T, ID>
        implements BaseService<T, ID> {

    @Override
    public <R extends BasePageRequest> Page<T> page(R request, Wrapper<T> wrapper) {

        Page<T> page = Page.of(request.getCurrent(), request.getSize());

        Optional.ofNullable(request.getOrderBy()).filter(StringUtils::isNotBlank).map(orderBy -> request.isAsc() ? OrderItem.asc(orderBy) : OrderItem.desc(orderBy)).ifPresentOrElse(
                // 指定了排序字段
                page::addOrder,
                // 未指定排序字段（默认：按主键降序）
                () -> page.addOrder(OrderItem.desc(super.getPrimaryKeyColumn())));

        return super.getBaseMapper().selectPage(page, wrapper);
    }

    @Override
    public Page<T> page(BasePageRequest request) {
        return this.page(request, null);
    }

}
