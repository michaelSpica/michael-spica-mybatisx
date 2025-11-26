package michael.spica.mybatisx.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import michael.spica.mybatisx.common.base.service.impl.BaseServiceImpl;
import michael.spica.mybatisx.config.TenantConfig;
import michael.spica.mybatisx.entity.Tenant;
import michael.spica.mybatisx.entity.TenantDbConfig;
import michael.spica.mybatisx.mapper.TenantDbConfigMapper;
import michael.spica.mybatisx.service.DbOperateService;
import michael.spica.mybatisx.service.TenantDbConfigService;
import org.springframework.stereotype.Service;

/**
 * Created by michael on 2025-11-26.
 */
@Slf4j
@Service
public class TenantDbConfigServiceImpl extends BaseServiceImpl<TenantDbConfigMapper, TenantDbConfig, Long> implements TenantDbConfigService {

    @Resource
    private TenantConfig tenantConfig;

    @Resource
    private DbOperateService dbOperateService;

    @Override
    public void create(Tenant tenant) {
        TenantConfig.DbConfig dbConfig = tenantConfig.getDbConfig();

        TenantDbConfig tenantDbConfig = new TenantDbConfig();
        tenantDbConfig.setTenantId(tenant.getId());
        tenantDbConfig.setDbUrl(String.format(dbConfig.getDbUrl(), dbOperateService.getDbName(tenant)));
        tenantDbConfig.setDbUsername(dbConfig.getDbUsername());
        tenantDbConfig.setDbPassword(dbConfig.getDbPassword());
        tenantDbConfig.setDbType(DbType.MYSQL);
        tenantDbConfig.setDbDriver(dbConfig.getDbDriver());
        boolean saved = super.save(tenantDbConfig);
        if (saved) {
            log.debug("租户数据源配置成功: {}", JSONUtil.toJsonStr(tenantDbConfig));
        }
    }
}
