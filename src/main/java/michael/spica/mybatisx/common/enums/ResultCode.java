package michael.spica.mybatisx.common.enums;

import lombok.Getter;

/**
 * Created by michael on 2025-11-21.
 */
@Getter
public enum ResultCode {

    SUCCESS(200, "操作成功"),

    FAILED(500, "操作失败"),

    VALIDATE_FAILED(400, "参数校验失败"),

    UNAUTHORIZED(401, "未认证或Token已失效"),

    FORBIDDEN(403, "没有相关权限"),

    // ========== 用户 ==========
    USER_NOT_EXIST(1301, "用户不存在"),
    USER_NOT_LOGIN(1302, "用户未登录"),
    USER_DISABLED(1303, "用户被禁用"),
    USER_LOCKED(1304, "用户被锁"),

    // ========== 租户 ==========
    TENANT_NOT_SET(1401, "当前请求未包含有效的租户信息"),

    ;

    private final int code;

    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
