package michael.spica.mybatisx.controller.request.tenant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import michael.spica.mybatisx.common.enums.TenantType;

/**
 * Created by michael on 2025-10-29.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TenantCreateRequest {

    /**
     * 租户名称
     */
    private String name;

    /**
     * 租户类型
     */
    private TenantType type;

    /**
     * 租户logo
     */
    private String logo;

    /**
     * 租户描述
     */
    private String description;

    /**
     * 租户联系人
     */
    private String contact;

    /**
     * 租户联系人电话
     */
    private String contactPhone;

    /**
     * 租户联系人邮箱
     */
    private String contactEmail;

    /**
     * 租户联系人地址
     */
    private String contactAddress;

    /**
     * 租户联系人备注
     */
    private String contactRemark;
}
