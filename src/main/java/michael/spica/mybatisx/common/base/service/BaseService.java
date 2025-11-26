package michael.spica.mybatisx.common.base.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import michael.spica.mybatisx.common.BasePageRequest;

import java.io.Serializable;

/**
 * Created by michael on 2025-11-25.
 */
public interface BaseService<T, ID extends Serializable> extends IService<T> {

    <R extends BasePageRequest> Page<T> page(R request, Wrapper<T> wrapper);

    Page<T> pageDefault(BasePageRequest request);
}
