package com.septinary.common.web.general.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.septinary.common.web.basic.filter.Filter;
import com.septinary.common.web.basic.filter.IFilter;

/**
 * 		Filter也称之为过滤器，它是Servlet技术中比较激动人心的技术，WEB开发人员通过Filter技术，对web
 *	服务器管理的所有web资源：例如Jsp, Servlet, 静态图片文件或静态 html 文件等进行拦截，从而实现一些
 *	特殊的功能。例如实现URL级别的权限访问控制、过滤敏感词汇、压缩响应信息等一些高级功能。
 *
 * @Filename: com.septinary.common.web.general.filter.CommonFilter.java of the project [com.septinary.common.web]
 *     @Type: CommonFilter
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月29日上午12:12:46
 *
 *		Filter不像Servlet，它不能产生一个请求或者响应，它只是修改对某一资源的请求，或者修改从某一的响应。Servlet
 *	中的过滤器Filter是实现了javax.servlet.Filter接口的服务器端程序，主要的用途是过滤字符编码、做
 *	一些业务逻辑判断等。其工作原理是，只要你在web.xml文件配置好要拦截的客户端请求，它都会帮你拦截到请
 *	求，此时你就可以对请求或响应(Request、Response)统一设置编码，简化操作；同时还可进行逻辑判断，如
 *	用户是否已经登陆、有没有权限访问该页面等等工作。它是随你的web应用启动而启动的，只初始化一次，以后就
 *	可以拦截相关请求，只有当你的web应用停止或重新部署的时候才销毁。Filter可认为是Servlet的一种“变种”，
 *	它主要用于对用户请求进行预处理，也可以对HttpServletResponse进行后处理，是个典型的处理链。它与Servlet
 *	的区别在于：它不能直接向用户生成响应。完整的流程是：Filter对用户请求进行预处理，接着将请求交给Servlet
 *	进行处理并生成响应，最后Filter再对服务器响应进行后处理。
 *		Filter有如下几个用处:
 *	1)在HttpServletRequest到达Servlet之前，拦截客户的HttpServletRequest。
 *	2)根据需要检查HttpServletRequest，也可以修改HttpServletRequest头和数据。
 *	3)在HttpServletResponse到达客户端之前，拦截HttpServletResponse。
 *	4)根据需要检查HttpServletResponse，也可以修改HttpServletResponse头和数据。
 *		通过对filter过滤器的了解，可以得知在以下三种情况下可以做些处理：
 *	1> 通过控制对chain.doFilter的方法的调用，来决定是否需要访问目标资源。比如，可以在用户权限验证等等。判
 *断用户是否有访问某些资源的权限，有权限放行，没权限不执行chain.doFilter方法。
 *	2> 通过在调用chain.doFilter方法之前，做些处理来达到某些目的。比如，解决中文乱码的问题等等。可以在doFilter
 *方法前，执行设置请求编码与响应的编码。甚至可以对request接口进行封装装饰来处理get请求方式的中文乱码问题(重
 *写相应的request.getParameter方法)。
 *	3> 通过在调用chain.doFilter方法之后，做些处理来达到某些目的。比如对整个web网站进行压缩。在调用chain.doFilter
 *方法之前用类A对response对象进行封装装饰，重写getOutputStream和重写getWriter方法。在类A内部中，将输
 *出内容缓存进ByteArrayOutputStream流中，然后在chain.doFilter方法执行后，获取类A中ByteArrayOutputStream
 *流缓存数据，用GZIPOutputStream流进行压缩下。
 *		Filter有如下几个种类:
 *	1)用户授权的Filter：Filter负责检查用户请求，根据请求过滤用户非法请求。
 *	2)日志Filter：详细记录某些特殊的用户请求。
 *	3)负责解码的Filter：包括对非标准编码的请求解码。
 *	4)能改变XML内容的XSLT Filter等。
 *	5)Filter可负责拦截多个请求或响应，一个请求或响应也可被多个Filter拦截。
 *		创建一个Filter只需两个步骤：
 *	1)建Filter处理类；
 *	2)web.xml文件中配置Filter。
 *
<filter>
 	<filter-name>Demo1Filter</filter-name>
 	<filter-class>com.itheima.filter.Demo1Filter</filter-class>
 	<init-param>
 		<param-name>param1</param-name>
 		<param-value>value在这里呢</param-value>
 	</init-param>
</filter>
<filter-mapping>
 	<filter-name>Demo1Filter</filter-name>
 	<url-pattern>/*</url-pattern>
 	<dispatcher>REQUEST</dispatcher> <!-- 没有配置dispatcher就是默认request方式的 -->
 	<dispatcher>FORWARD</dispatcher>
 	<dispatcher>ERROR</dispatcher>
 	<dispatcher>INCLUDE</dispatcher>
</filter-mapping>
 * 说明：
 * 	1> filter-mapping标签中servlet-name与url-pattern。
 * 		Filter不仅可以通过url-pattern来指定拦截哪些url匹配的资源。而且还可以通过servlet-name来指定拦截哪个指定的servlet(专门为某个servlet服务了,servlet-name对应Servlet的相关配置)。
 * 	2> filter-mapping标签中dispatcher。
 * 		指定过滤器所拦截的资源被 Servlet 容器调用的方式，可以是REQUEST,INCLUDE,FORWARD和ERROR之一，默认REQUEST。用户可以设置多个<dispatcher> 子元素用来指定 Filter 对资源的多种调用方式进行拦截。
 * REQUEST：当用户直接访问页面时，Web容器将会调用过滤器。如果目标资源是通过RequestDispatcher的include()或forward()方法访问或ERROR情况时，那么该过滤器就不会被调用。
 * INCLUDE：如果目标资源是通过RequestDispatcher的include()方法访问时，那么该过滤器将被调用。除此之外，该过滤器不会被调用。
 * FORWARD：如果目标资源是通过RequestDispatcher的forward()方法访问时，那么该过滤器将被调用，除此之外，该过滤器不会被调用。
 * ERROR：如若在A.jsp页面page指令中指定了error属性=examError.jsp，那么A.jsp中若出现了异常，会跳转到examError.jsp中处理。而在跳转到examError.jsp时，若过滤器配置了ERROR的dispather那么则会拦截，否则不会拦截。
 */
public class CommonFilter extends Filter implements javax.servlet.Filter, IFilter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 		程序实现了doFilter()方法，实现该方法就可实现对用户请求进行预处理，也可实现对服务器响应进行后处
	 * 理——它们的分界线为是否调用了chain.doFilter()，执行该方法之前，即对用户请求进行预处理；执行该方法之
	 * 后，即对服务器响应进行后处理。在请求Filter中，仅在日志中记录请求的URL，对所有的请求都执行chain.doFilter(request,reponse)
	 * 方法，当Filter对请求过滤后，依然将请求发送到目的地址。如果需要检查权限，可以在Filter中根据用户请求的
	 * HttpSession，判断用户权限是否足够。如果权限不够，直接调用重定向即可，无须调用chain.doFilter(request,reponse)
	 * 方法。
	 * 		其实struts2本身就依托于一个总过滤器：
	<filter>
        <filter-name>struts2</filter-name>
        <filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>struts2</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
     *		多个匹配的Filter，是按照其在web.xml中配置的顺序来执行的。所以这也就是，把自己的Filter或者其他的Filter
     *（比如UrlRewrite的Filter）放在Struts的DispatcherFilter的前面的原因。因为，它们需要在请求被Struts2
     *框架处理之前，做一些前置的工作。当Filter被调用，并且进入了Struts2的DispatcherFilter中后，Struts2会
     *按照在Action中配置的Interceptor Stack中的Interceptor的顺序，来调用Interceptor。 
	 */
	@Override
	final public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		if( !this.preDo(request, response, chain) ) return;
		
		if( !this.doing(request, response, chain) ) return;
		
		this.postDo(request, response, chain);
	}

	@Override
	public boolean preDo(ServletRequest request, ServletResponse response, Object chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// return false;
		
		return true;
	}

	@Override
	final public boolean doing(ServletRequest request, ServletResponse response, Object chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// return false;
		return this.doing(request, response, (FilterChain)chain);
	}
	public boolean doing(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//激活下一个过滤器
		chain.doFilter(request, response);
		
		return true;
	}

	@Override
	public void postDo(ServletRequest request, ServletResponse response, Object chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
