package michael.spica.mybatisx.common.annotation;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import michael.spica.mybatisx.common.util.MaskUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 日志切面（记录业务日志并脱敏）
 * <p>
 * Created by michael on 2025-11-21.
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(michael.spica.mybatisx.common.annotation.Log)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long begin = System.currentTimeMillis();

        MethodSignature sig = (MethodSignature) point.getSignature();
        Method method = sig.getMethod();
        Log logAnn = method.getAnnotation(Log.class);
        String className = point.getTarget().getClass().getName();
        String methodName = sig.getName();
        Object[] args = point.getArgs();
        String params = JSONUtil.toJsonStr(args);   // 脱敏
        params = MaskUtils.maskSensitive(params);

        try {
            log.info("[{}] 开始 => {}#{} params: {}", logAnn.value(), className, methodName, params);
            Object result = point.proceed();
            long time = System.currentTimeMillis() - begin;
            log.info("[{}] 结束 => {}#{} 耗时:{}ms result: {}", logAnn.value(), className, methodName, time, JSONUtil.toJsonStr(result));
            return result;

        } catch (Exception e) {
            long time = System.currentTimeMillis() - begin;
            log.error("[{}] 异常 => {}#{} 耗时:{}ms, error: {}", logAnn.value(), className, methodName, time, e.getMessage(), e);
            throw e;
        }
    }
}
