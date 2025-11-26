package michael.spica.mybatisx.common.base.service.support;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
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

}
