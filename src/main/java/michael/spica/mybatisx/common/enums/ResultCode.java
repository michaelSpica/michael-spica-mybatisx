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

    FORBIDDEN(403, "没有相关权限");

    private final int code;

    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
