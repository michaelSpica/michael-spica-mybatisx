package michael.spica.mybatisx.controller;

import jakarta.annotation.Resource;
import michael.spica.mybatisx.common.R;
import michael.spica.mybatisx.common.base.BaseController;
import michael.spica.mybatisx.controller.request.tenant.TenantCreateRequest;
import michael.spica.mybatisx.entity.Tenant;
import michael.spica.mybatisx.service.TenantService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by michael on 2025-11-26.
 */
@RestController
@RequestMapping("/tenant")
public class TenantController extends BaseController {

    @Resource
    private TenantService tenantService;

    @PostMapping
    public R<Tenant> create(@RequestBody TenantCreateRequest request) {
        return tenantService.create(request);
    }
}
