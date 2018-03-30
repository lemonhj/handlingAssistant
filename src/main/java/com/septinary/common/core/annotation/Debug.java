package com.septinary.common.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
* @ClassName: Debug 
* @Description: 调试注解 
* @author lin.tb lin.tb@septinary.com
* @date 2015年4月27日 下午12:12:56 
*
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Debug {
	boolean value() default true;  
}
