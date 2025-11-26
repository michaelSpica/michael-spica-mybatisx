package michael.spica.mybatisx.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 租户类型
 * <p>
 * Created by michael on 2025-10-23.
 */
@Getter
@AllArgsConstructor
public enum TenantType {

    FREEMIUM("免费租户", "特点：提供基础功能的免费版本，通常有限制，如用户数上限、功能模块限制、存储空间小、包含平台品牌标识、不提供技术支持等"),

    PREMIUM("付费租户", "特点：提供高级功能和定制服务，通常没有限制，如用户数无限制、功能模块无限制、存储空间大、不包含平台品牌标识、提供技术支持等"),

    ENTERPRISE("企业租户", "特点：提供企业级功能和定制服务，通常没有限制，如用户数无限制、功能模块无限制、存储空间大、不包含平台品牌标识、提供技术支持等"),

    TRIAL("试用租户", "特点：提供试用功能和定制服务，通常没有限制，如用户数无限制、功能模块无限制、存储空间大、不包含平台品牌标识、提供技术支持等"),

    INTERNAL("内部租户", "特点：提供内部功能和定制服务，通常没有限制，如用户数无限制、功能模块无限制、存储空间大、不包含平台品牌标识、提供技术支持等");

    private final String label;

    private final String description;
}
