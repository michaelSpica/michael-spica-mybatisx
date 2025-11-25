package michael.spica.mybatisx.common.base.service.support;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by michael on 2025-11-25.
 */
@Slf4j
@Component
public abstract class BaseServiceImplSupport<M extends BaseMapper<T>, T, ID extends Serializable>
        extends ServiceImpl<M, T> {

}
