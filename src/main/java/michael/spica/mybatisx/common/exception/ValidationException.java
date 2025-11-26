package michael.spica.mybatisx.common.exception;

import lombok.Getter;
import michael.spica.mybatisx.common.enums.ResultCode;

import java.util.Map;

/**
 * 业务异常
 * <p>
 * Created by michael on 2025-11-21.
 */
@Getter
public class ValidationException extends BizException {

    private final Map<String, String> errors;

    public ValidationException(Map<String, String> errors) {
        super(ResultCode.VALIDATE_FAILED.getCode(), "参数校验失败");
        this.errors = errors;
    }

}
