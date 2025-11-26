package michael.spica.mybatisx.common.base.service.support;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import michael.spica.mybatisx.common.BasePageRequest;
import michael.spica.mybatisx.common.constant.BizConstants;

import java.io.Serializable;
import java.util.Optional;

/**
 * Created by michael on 2025-11-25.
 */
@Slf4j
public abstract class BaseServiceImplSupport<M extends BaseMapper<T>, T, ID extends Serializable> extends ServiceImpl<M, T> {

    /**
     * 自动识别实体主键字段（column 名）
     */
    protected String getPrimaryKeyColumn() {
        return Optional.ofNullable(TableInfoHelper.getTableInfo(getEntityClass()))
                .map(TableInfo::getKeyColumn)// 数据库字段名（推荐使用）
                .filter(StringUtils::isNotBlank)
                .orElse(BizConstants.ENTITY_ID);// 兜底
    }

    public class PageBuilder {
        private final BasePageRequest request;
        private Wrapper<T> wrapper;
        private String customOrderBy;
        private Boolean customAsc;

        public PageBuilder(BasePageRequest request) {
            this.request = request;
        }

        // 设置 wrapper
        public PageBuilder with(Wrapper<T> wrapper) {
            this.wrapper = wrapper;
            return this;
        }

        // 自定义排序
        public PageBuilder orderBy(String field, boolean asc) {
            this.customOrderBy = field;
            this.customAsc = asc;
            return this;
        }

        public PageBuilder orderByAsc(String field) {
            return orderBy(field, true);
        }

        public PageBuilder orderByDesc(String field) {
            return orderBy(field, false);
        }

        // 调用执行
        public Page<T> exec() {
            Page<T> page = Page.of(request.getCurrent(), request.getSize());

            // 先使用链式的排序, 若未设置，则使用 request 中的排序
            Optional.ofNullable(customOrderBy)
                    .filter(StringUtils::isNotBlank)
                    .map(orderBy -> Boolean.TRUE.equals(customAsc) ? OrderItem.asc(orderBy) : OrderItem.desc(orderBy))
                    .ifPresentOrElse(
                            page::addOrder,
                            () -> Optional.ofNullable(request.getOrderBy())
                                    .filter(StringUtils::isNotBlank)
                                    .map(orderBy -> request.isAsc() ? OrderItem.asc(orderBy) : OrderItem.desc(orderBy))
                                    .ifPresentOrElse(
                                            page::addOrder,
                                            () -> page.addOrder(OrderItem.desc(getPrimaryKeyColumn()))
                                    )
                    );

            return BaseServiceImplSupport.super.getBaseMapper().selectPage(page, wrapper);
        }
    }
}
