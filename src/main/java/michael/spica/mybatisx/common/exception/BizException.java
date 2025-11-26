package michael.spica.mybatisx.common.exception;

import lombok.Getter;
import michael.spica.mybatisx.common.enums.ResultCode;

/**
 * 业务异常
 * <p>
 * Created by michael on 2025-11-21.
 */
@Getter
public class BizException extends RuntimeException {

    private final int code;

    public BizException(String message) {
        super(message);
        this.code = 400;
    }

    public BizException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }
}
