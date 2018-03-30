package com.septinary.common.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.septinary.common.core.type.AccessRestrictionLevel;
import com.septinary.common.core.type.AccessSecurityRule;
import com.septinary.common.core.type.AccessTraceType;

/**
 * Controller.action()访问的权限限制
 * @Filename: com.septinary.common.core.annotation.AccessPermissionRestriction.java of the project [com.septinary.common]
 *     @Type: AccessPermissionRestriction
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月30日下午6:05:13
 *
注解@Target的源码：【@target】这个注解来指定给哪一类java成员注解，指定注解目标该是什么样的东西
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE) //这不是在作弊，这确实是自己注解自己，所以说注解也可以被字节给注解
public @interface Target {
    ElementType[] value(); //值可以使数组 value={...}
}

public enum ElementType {
    TYPE,  //给类（型）注解
    FIELD, //给字段注解，不要忘了，字段可以是对象
    METHOD, //给方法注解
    PARAMETER, //给参数注解
    CONSTRUCTOR, //给构造方法注解
    LOCAL_VARIABLE, //给局部变量注解
    ANNOTATION_TYPE,//给注解注解（这貌似把自己不当类来看）
    PACKAGE, //给包注解
    TYPE_PARAMETER, //不知道，等知道了我再写在这里
    TYPE_USE //这个也不知道
}
 *
注解@Retention的源码：【@Retention】表示注解运行的状态，换句话说，注解改在什么样的状态下才能运行
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Retention {
    RetentionPolicy value();
}

public enum RetentionPolicy {
    SOURCE, //源码状态运行，
    CLASS, //编译类文件时运行
    RUNTIME //运行时运行
}
 *说明：一般来说，源码状态运行和编译状态运行的注解往往和编译器相关，比如报错，警告，类编译参数等，这2类状态一般和编辑器插件关系密切，这里不再讨论在日常开发中RUNTIME用的最多，这是开发人员可以控制的一个状态。
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessPermissionRestriction {
	/*
	 * 限制级别
	 * 关于注解的方法说明，注解只有方法，没有字段，因为注解也是一个interface,只不过前面加了一个@符号，还有一点是这些方法无法实现，写法和C++的纯虚函数相似
	 */
	public AccessRestrictionLevel value() default AccessRestrictionLevel.PUBLIC;
	
	/*
	 * 安全规则
	 * 可配置多条规则同时生效
	 */
	public AccessSecurityRule[] rules() default {AccessSecurityRule.NONE};

	/*
	 * 权限制定
	 * 绑定接口的访问权限标识（模块-子模块-...-子孙模块，操作）
	 */
	public String module() default "";
	public String operate() default "";
	
	/*
	 * 跟踪类型
	 * 可配置多种跟踪类型同时生效
	 */
	public AccessTraceType[] trace() default {AccessTraceType.NONE};
	
	/*
	 * 允许跨域？
	 */
	public boolean cors() default false;
}
