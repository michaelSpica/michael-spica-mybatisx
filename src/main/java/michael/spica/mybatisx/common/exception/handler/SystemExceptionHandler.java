package michael.spica.mybatisx.common.exception.handler;

import lombok.extern.slf4j.Slf4j;
import michael.spica.mybatisx.common.R;
import michael.spica.mybatisx.common.constant.HeaderConstants;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by michael on 2025-11-21.
 */
@Slf4j
@RestControllerAdvice
@Order(3)
public class SystemExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R<Void> handleException(Exception e) {
        log.error("系统异常, traceId={}", MDC.get(HeaderConstants.TRACE_ID), e);
        return R.fail(500, "系统错误，请提供 traceId 给运维排查");
    }
}
