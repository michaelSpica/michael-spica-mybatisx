package michael.spica.mybatisx.common.annotation;

import michael.spica.mybatisx.common.enums.LogType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by michael on 2025-11-21.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    String value() default "";

    LogType type() default LogType.OPERATION;
}
