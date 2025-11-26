package michael.spica.mybatisx.service;

import michael.spica.mybatisx.common.base.service.BaseService;
import michael.spica.mybatisx.entity.Tenant;
import michael.spica.mybatisx.entity.TenantDbConfig;

/**
 * Created by michael on 2025-11-26.
 */
public interface TenantDbConfigService extends BaseService<TenantDbConfig, Long> {

    void create(Tenant tenant);
}
