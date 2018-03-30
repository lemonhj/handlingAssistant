package com.septinary.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author lin.tb lin.tb@septinary.com
 * @ClassName: SysExceptionLog
 * @Description:调用异常日志注解
 * @date 16/8/4
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SysExceptionLog {
    String description()  default "";
}
