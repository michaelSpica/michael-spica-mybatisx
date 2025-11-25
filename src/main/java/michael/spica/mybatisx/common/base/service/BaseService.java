package michael.spica.mybatisx.common.base.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;

/**
 * Created by michael on 2025-11-25.
 */
public interface BaseService<T, ID extends Serializable> extends IService<T> {

}
