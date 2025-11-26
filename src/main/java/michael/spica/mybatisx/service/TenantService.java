package michael.spica.mybatisx.service;

import michael.spica.mybatisx.common.R;
import michael.spica.mybatisx.common.base.service.BaseService;
import michael.spica.mybatisx.controller.request.tenant.TenantCreateRequest;
import michael.spica.mybatisx.entity.Tenant;

/**
 * Created by michael on 2025-11-26.
 */
public interface TenantService extends BaseService<Tenant, Long> {

    R<Tenant> create(TenantCreateRequest request);

    R<Tenant> switchStatus(String operate, Long id);
}
