package com.septinary.common.web.general.interceptor.springmvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.septinary.common.web.basic.interceptor.IInterceptor;
import com.septinary.common.web.basic.interceptor.Interceptor;

/**
 * 
 * @Filename: com.septinary.common.web.general.interceptor.CommonSpringMVCInterceptor.java of the project [com.septinary.common.web]
 *     @Type: CommonInterceptor
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月29日上午12:43:52
 *
 *		Interceptor：
 *是在面向切面编程的，就是在你的service或者一个方法，前调用一个方法，或者在方法后调用一个方法，是基于JAVA的反射机
 *制。比如动态代理就是拦截器的简单实现，在你调用方法前打印出字符串（或者做其它业务逻辑的操作），也可以在你调用方法后
 *打印出字符串，甚至在你抛出异常的时候做业务逻辑的操作。
 *		servlet、filter、listener是配置到web.xml中（web.xml的加载顺序是：context-param -> listener -> filter -> servlet ），
 *而interceptor不配置到web.xml中，如：struts的拦截器配置到struts.xml中。spring的拦截器配置到spring.xml中。 
 *
 *		拦截器与过滤器的区别： 
 *1. 拦截器是基于java的反射机制的，而过滤器是基于函数回调。
 *2. 拦截器不依赖与servlet容器，过滤器依赖与servlet容器。 
 *3. 拦截器只能对action请求起作用，而过滤器则可以对几乎所有的请求起作用。
 *4. 拦截器可以访问action上下文、值栈里的对象，而过滤器不能访问。 
 *5. 在action的生命周期中，拦截器可以多次被调用，而过滤器只能在容器初始化时被调用一次。
 *
 *		配置支持：
第一种配置方法：
<bean id="handlerInterceptor1" class="cn.javass.chapter5.web.interceptor.HandlerInterceptor1" />
<bean id="handlerInterceptor2" class="cn.javass.chapter5.web.interceptor.HandlerInterceptor2" />

<bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping">
    <property name="interceptors">
        <list>
          <ref bean="handlerInterceptor1"/>
          <ref bean="handlerInterceptor2"/>
        </list>
    </property>
</bean>
第二种配置方法：
<mvc:interceptors>  
      <bean class="cn.javass.chapter5.web.interceptor.HandlerInterceptor1" />
      <bean class="cn.javass.chapter5.web.interceptor.HandlerInterceptor2" />  
</mvc:interceptors>
 *	具体示例：
1.在SpringMVC的配置文件中加上支持MVC的schema
<beans xmlns="http://www.springframework.org/schema/beans"  
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"  
    xmlns:mvc="http://www.springframework.org/schema/mvc"  
    xsi:schemaLocation="http://www.springframework.org/schema/beans  
     http://www.springframework.org/schema/beans/spring-beans-3.0.xsd  
     http://www.springframework.org/schema/context  
     http://www.springframework.org/schema/context/spring-context-3.0.xsd  
     http://www.springframework.org/schema/mvc  
     http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd">  
这样在SpringMVC的配置文件中就可以使用mvc标签了，mvc标签中有一个mvc:interceptors是用于声明SpringMVC的拦截器的。
2.使用mvc:interceptors标签来声明需要加入到SpringMVC拦截器链中的拦截器
<mvc:interceptors>  
    <!-- 使用bean定义一个Interceptor，直接定义在mvc:interceptors根下面的Interceptor将拦截所有的请求 -->  
    <bean class="com.septinary.common.web.general.interceptor.springmvc.AllInterceptor"/>  
    <mvc:interceptor>  
        <mvc:mapping path="/test/member.do"/>  
        <!-- 定义在mvc:interceptor下面的表示是对特定的请求才进行拦截的 -->  
        <bean class="com.septinary.common.web.general.interceptor.springmvc.CommonInterceptorSpringMVC"/>  
    </mvc:interceptor>  
</mvc:interceptors>  
由上面的示例可以看出可以利用mvc:interceptors标签声明一系列的拦截器，然后它们就可以形成一个拦截器链，拦截器的执行顺序是按声明的先后顺序执行的，先声明的拦截器中的preHandle方法会先执行，然而它的postHandle方法和afterCompletion方法却会后执行。
在mvc:interceptors标签下声明interceptor主要有两种方式：
	（1）直接定义一个Interceptor实现类的bean对象。使用这种方式声明的Interceptor拦截器将会对所有的请求进行拦截。
	（2）使用mvc:interceptor标签进行声明。使用这种方式进行声明的Interceptor可以通过mvc:mapping子标签来定义需要进行拦截的请求路径。
经过上述两步之后，定义的拦截器就会发生作用对特定的请求进行拦截了。
 */
public class CommonSpringMVCInterceptor extends Interceptor implements AsyncHandlerInterceptor, IInterceptor {

	/**
	 * preHandle (HttpServletRequest request, HttpServletResponse response, Object handle) 
	 * 方法，顾名思义，该方法将在请求处理之前进行调用。SpringMVC 中的Interceptor 是链式的调用的，
	 * 在一个应用中或者说是在一个请求中可以同时存在多个Interceptor 。每个Interceptor 的调用会依
	 * 据它的声明顺序依次执行，而且最先执行的都是Interceptor 中的preHandle 方法，所以可以在这个
	 * 方法中进行一些前置初始化操作或者是对当前请求的一个预处理，也可以在这个方法中进行一些判断来决定
	 * 请求是否要继续进行下去。该方法的返回值是布尔值Boolean 类型的，当它返回为false 时，表示请求
	 * 结束，后续的Interceptor 和Controller 都不会再执行；当返回值为true 时就会继续调用下一个Interceptor
	 * 的preHandle 方法，如果已经是最后一个Interceptor 的时候就会是调用当前请求的Controller
	 * 方法。
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		// return false;
		
		return this.preDo(request, response, handler);
	}

	/**
	 * postHandle (HttpServletRequest request, HttpServletResponse response, Object handle, ModelAndView modelAndView)
	 * 方法，由preHandle 方法的解释我们知道这个方法包括后面要说到的afterCompletion 方法都只能
	 * 是在当前所属的Interceptor 的preHandle 方法的返回值为true 时才能被调用。postHandle方
	 * 法，顾名思义就是在当前请求进行处理之后，也就是Controller 方法调用之后执行，但是它会在DispatcherServlet
	 * 进行视图返回渲染之前被调用，所以我们可以在这个方法中对Controller 处理之后的ModelAndView
	 * 对象进行操作。postHandle 方法被调用的方向跟preHandle 是相反的，也就是说先声明的Interceptor
	 * 的postHandle 方法反而会后执行，这和Struts2 里面的Interceptor 的执行过程有点类型。Struts2
	 * 里面的Interceptor 的执行过程也是链式的，只是在Struts2 里面需要手动调用ActionInvocation
	 * 的invoke 方法来触发对下一个Interceptor 或者是Action 的调用，然后每一个Interceptor中
	 * 在invoke 方法调用之前的内容都是按照声明顺序执行的，而invoke 方法之后的内容就是反向的。
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
		this.postDo(request, response, handler);
	}

	/**
	 * afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handle, Exception ex)
	 * 方法，该方法也是需要当前对应的Interceptor 的preHandle 方法的返回值为true 时才会执行。
	 * 顾名思义，该方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执
	 * 行。这个方法的主要作用是用于进行资源清理工作的。
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**
	 * AsyncHandlerInterceptor提供了一个afterConcurrentHandlingStarted()方法, 这个方法会在Controller
	 * 方法异步执行时开始执行, 而Interceptor的postHandle方法则是需要等到Controller的异
	 * 步执行完才能执行。提供了afterConcurrentHandlingStarted方法，该方法是用来处理异步
	 * 请求。当Controller中有异步请求方法的时候会触发该方法。 经测试，异步请求先支持preHandle、
	 * 然后执行afterConcurrentHandlingStarted。异步线程完成之后执行preHandle、postHandle、afterCompletion。
	 */
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preDo(ServletRequest request, ServletResponse response, Object handler) throws IOException, ServletException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	final public boolean doing(ServletRequest request, ServletResponse response, Object handler) throws IOException, ServletException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void postDo(ServletRequest request, ServletResponse response, Object handler) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

}
