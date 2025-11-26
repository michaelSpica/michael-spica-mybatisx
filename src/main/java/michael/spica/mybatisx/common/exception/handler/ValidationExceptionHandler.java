package michael.spica.mybatisx.common.exception.handler;

import lombok.extern.slf4j.Slf4j;
import michael.spica.mybatisx.common.R;
import michael.spica.mybatisx.common.constant.HeaderConstants;
import michael.spica.mybatisx.common.enums.ResultCode;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by michael on 2025-11-21.
 */
@Slf4j
@RestControllerAdvice
@Order(2)
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<Map<String, String>> handleValid(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        log.warn("参数校验异常, traceId={}, errors={}", MDC.get(HeaderConstants.TRACE_ID), errors);
        R<Map<String, String>> r = R.fail(ResultCode.VALIDATE_FAILED.getCode(), "参数校验失败");
        r.setData(errors);
        return r;
    }

    @ExceptionHandler(BindException.class)
    public R<Void> handleBindException(BindException e) {
        String msg = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        log.error("参数验证失败", e);
        return R.fail(ResultCode.VALIDATE_FAILED.getCode(), msg);
    }
}
