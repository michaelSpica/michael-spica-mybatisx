package michael.spica.mybatisx.controller;

import jakarta.annotation.Resource;
import michael.spica.mybatisx.common.R;
import michael.spica.mybatisx.common.base.BaseController;
import michael.spica.mybatisx.controller.request.tenant.TenantCreateRequest;
import michael.spica.mybatisx.entity.Tenant;
import michael.spica.mybatisx.service.TenantService;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 切换租户状态
     * <p>
     * 使用方式：
     * http://localhost:8080/api/tenant/switchStatus/ENABLED/1
     * http://localhost:8080/api/tenant/switchStatus/DISABLED/1
     *
     * @param operate 状态（不区分大小写）
     * @param id
     * @return
     */
    @PutMapping("/switchStatus/{operate}/{id}")
    public R<Tenant> switchStatus(@PathVariable String operate, @PathVariable Long id) {
        return tenantService.switchStatus(operate, id);
    }
}
