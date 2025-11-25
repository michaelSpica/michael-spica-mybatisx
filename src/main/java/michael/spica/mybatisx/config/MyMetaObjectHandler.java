package michael.spica.mybatisx.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import michael.spica.mybatisx.common.constant.BizConstants;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by michael on 2025-11-25.
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, BizConstants.ENTITY_CREATE_TIME, LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, BizConstants.ENTITY_UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, BizConstants.ENTITY_UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
    }
}
