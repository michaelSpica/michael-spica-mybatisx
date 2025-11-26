package michael.spica.mybatisx.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import michael.spica.mybatisx.common.R;
import michael.spica.mybatisx.common.base.service.impl.BaseServiceImpl;
import michael.spica.mybatisx.common.enums.CommonStatus;
import michael.spica.mybatisx.common.exception.BizException;
import michael.spica.mybatisx.common.util.ChineseNameGenerator;
import michael.spica.mybatisx.common.util.RandomUtils;
import michael.spica.mybatisx.config.TenantConfig;
import michael.spica.mybatisx.controller.request.tenant.TenantCreateRequest;
import michael.spica.mybatisx.entity.Tenant;
import michael.spica.mybatisx.mapper.TenantMapper;
import michael.spica.mybatisx.service.DbOperateService;
import michael.spica.mybatisx.service.TenantDbConfigService;
import michael.spica.mybatisx.service.TenantService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * Created by michael on 2025-11-26.
 */
@Slf4j
@Service
public class TenantServiceimpl extends BaseServiceImpl<TenantMapper, Tenant, Long> implements TenantService {

    @Resource
    private TenantConfig tenantConfig;

    @Resource
    private DbOperateService dbOperateService;

    @Resource
    private TenantDbConfigService tenantDbConfigService;

    @Override
    public R<Tenant> create(TenantCreateRequest request) {
        log.debug("创建租户：{}", JSONUtil.toJsonStr(request));

        // 1. 创建租户实体并保存
        Tenant tenant = new Tenant();
        BeanUtils.copyProperties(request, tenant);
        tenant.setName(RandomUtils.randomFakeNamePlus());
        tenant.setCode(StrUtil.toLowerCase(RandomUtil.randomString(12)));// 租户标识 随机生成
        tenant.setStatus(CommonStatus.ENABLED);// 默认 启用
        tenant.setContact(ChineseNameGenerator.randomName());
        tenant.setContactPhone(RandomUtils.generateMobile());
        tenant.setContactEmail(RandomUtils.generateEmailPlus());
        boolean saved = super.save(tenant);

        if (saved) {
            if (tenantConfig.getDbConfig().getInit().equals(true)) {
                // 2. 创建数据库
                dbOperateService.createTenantDatabase(tenant);

                // 3. 初始化表结构
                dbOperateService.initializeTenantTablesFromTemplate(tenant);

                // 4. 初始化租户配置信息
                tenantDbConfigService.create(tenant);
            }
            return R.ok(tenant);
        }
        return R.fail("创建租户失败");
    }

    @Override
    public R<Tenant> switchStatus(String operate, Long id) {
        CommonStatus commonStatus = CommonStatus.from(operate);

        Tenant tenant = super.getById(id);
        if (null == tenant) {
            throw new BizException("租户不存在");
        }
        if (tenant.getStatus().equals(commonStatus)) {
            throw new BizException("租户已处于“" + commonStatus.getLabel() + "”状态");
        }

//        boolean updated = this.update(
//                new LambdaUpdateWrapper<Tenant>()
//                        .eq(Tenant::getId, id)
//                        .set(Tenant::getStatus, commonStatus)
//                        .set(Tenant::getUpdateTime, LocalDateTime.now())// 手动更新时间
//        );

//        boolean updated = this.lambdaUpdate()
//                .eq(Tenant::getId, id)
//                .set(Tenant::getStatus, commonStatus)
//                .set(Tenant::getUpdateTime, LocalDateTime.now()) // 手动更新时间
//                .update();

        Tenant tenant_ = new Tenant();
        tenant_.setStatus(commonStatus);
        boolean updated = super.update(tenant_, new LambdaUpdateWrapper<Tenant>().eq(Tenant::getId, id));
        if (updated) {
            return R.ok(tenant);
        }
        return R.fail("租户注销失败");
    }
}
