package michael.spica.mybatisx.common.exception.handler;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import michael.spica.mybatisx.common.R;
import michael.spica.mybatisx.common.constant.HeaderConstants;
import michael.spica.mybatisx.common.enums.ResultCode;
import michael.spica.mybatisx.common.exception.AuthException;
import michael.spica.mybatisx.common.exception.BizException;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by michael on 2025-11-21.
 */
@Slf4j
@RestControllerAdvice
@Order(1)
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    public R<Void> handleBusiness(BizException e) {
        log.warn("业务异常，traceId={}, msg={}", MDC.get(HeaderConstants.TRACE_ID), e.getMessage());
        return R.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(AuthException.class)
    public R<Void> handleAuth(AuthException e) {
        log.warn("鉴权异常, traceId={}, type={}, msg={}", MDC.get(HeaderConstants.TRACE_ID), e.getType(), e.getMessage());
        return switch (e.getType()) {
            case TOKEN_EXPIRED -> R.fail(ResultCode.UNAUTHORIZED.getCode(), "登录过期，请重新登录");
            case TOKEN_INVALID -> R.fail(ResultCode.UNAUTHORIZED);
            case FORBIDDEN -> R.fail(ResultCode.FORBIDDEN);
            case USER_NOT_EXIST -> R.fail(ResultCode.USER_NOT_EXIST);
            case USER_DISABLED -> R.fail(ResultCode.USER_DISABLED);
            case USER_LOCKED -> R.fail(ResultCode.USER_LOCKED);
            case USER_NOT_LOGIN -> R.fail(ResultCode.USER_NOT_LOGIN);
            case TENANT_NOT_SET -> R.fail(ResultCode.TENANT_NOT_SET);
            default -> R.fail(ResultCode.UNAUTHORIZED.getCode(), "鉴权失败");
        };
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public R<Void> handleDB(Exception e) {
        log.error("数据库异常, traceId={}", MDC.get(HeaderConstants.TRACE_ID), e);
        return R.fail("数据库操作失败，请检查输入或联系管理员");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public R<Void> handleDuplicateKey(DuplicateKeyException e) {
        log.error("唯一键冲突", e);
        return R.fail(409, "数据已存在");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public R<Void> handleConstraintViolation(ConstraintViolationException e) {
        String msg = e.getConstraintViolations().iterator().next().getMessage();
        log.error("参数验证失败", e);
        return R.fail(400, msg);
    }
}
