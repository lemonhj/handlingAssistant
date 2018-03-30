package com.septinary.api.annotation;


import java.lang.annotation.*;

/**
 * @author lin.tb lin.tb@septinary.com
 * @ClassName: SystemControllerLog
 * @Description:调用日志跟踪
 * @date 16/8/4
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysTraceLog {
    String description()  default "";
}
