package michael.spica.mybatisx.common.base.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.extern.slf4j.Slf4j;
import michael.spica.mybatisx.common.base.service.BaseService;
import michael.spica.mybatisx.common.base.service.support.BaseServiceImplSupport;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by michael on 2025-11-25.
 */
@Slf4j
@Service
public class BaseServiceImpl<M extends BaseMapper<T>, T, ID extends Serializable>
        extends BaseServiceImplSupport<M, T, ID>
        implements BaseService<T, ID> {

}
