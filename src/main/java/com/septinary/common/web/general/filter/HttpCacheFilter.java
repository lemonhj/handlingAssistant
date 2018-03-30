package com.septinary.common.web.general.filter;

import java.io.IOException;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制浏览器缓存页面中的静态资源
 * @Filename: com.septinary.common.web.general.filter.HttpCacheFilter.java of the project [com.septinary.common.web]
 *     @Type: HttpCacheFilter
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月30日下午4:46:35
 *	有些动态页面中引用了一些图片或css文件以修饰页面效果，这些图片和css文件经常是不变化的，所以为减轻服务器的压力，可以使用filter控制浏览器缓存这些文件，以提升服务器的性能。
 *	web.xml文件中的配置如下：
<!-- 配置缓存过滤器 -->
<filter>
	<filter-name>CacheFilter</filter-name>
	<filter-class>com.septinary.common.web.general.filter.HttpCacheFilter</filter-class>
	<!-- 配置要缓存的web资源以及缓存时间，以小时为单位 -->
	<init-param>
		<param-name>css</param-name>
		<param-value>4</param-value>
	</init-param>
	<init-param>
		<param-name>jpg</param-name>
		<param-value>1</param-value>
	</init-param>
	<init-param>
		<param-name>js</param-name>
		<param-value>4</param-value>
	</init-param>
	<init-param>
		<param-name>png</param-name>
		<param-value>4</param-value>
	</init-param>
</filter>
<!-- 配置要缓存的web资源的后缀-->
<filter-mapping>
	<filter-name>CacheFilter</filter-name>
	<url-pattern>*.jpg</url-pattern>
</filter-mapping>
<filter-mapping>
	<filter-name>CacheFilter</filter-name>
	<url-pattern>*.css</url-pattern>
</filter-mapping>
<filter-mapping>
	<filter-name>CacheFilter</filter-name>
	<url-pattern>*.js</url-pattern>
</filter-mapping>
<filter-mapping>
	<filter-name>CacheFilter</filter-name>
	<url-pattern>*.png</url-pattern>
</filter-mapping>
 */
public class HttpCacheFilter extends CommonFilter {

	//过滤器配置信息
	private FilterConfig filterConfig = null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		super.init(filterConfig);
		
		this.filterConfig = filterConfig;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public boolean preDo(ServletRequest request, ServletResponse response, Object chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		return super.preDo(request, response, chain);
	}

	@Override
	public void postDo(ServletRequest request, ServletResponse response, Object chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// super.postDo(request, response);

		//只能处理HTTP请求
		if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
			throw new ServletException(this.getClass().getName()+" just supports HTTP requests");
		}
	    HttpServletRequest req = (HttpServletRequest) request; // 把ServletRequest强转成HttpServletRequest
	    HttpServletResponse res = (HttpServletResponse) response; // 把ServletResponse强转成HttpServletResponse
	     
	    //1.获取用户想访问的资源
	    String uri = req.getRequestURI(); 
	     
	    //2.得到用户想访问的资源的后缀名
	    String ext = uri.substring(uri.lastIndexOf(".")+1);
	     
	    //得到资源需要缓存的时间
	    String time = filterConfig.getInitParameter(ext);
	    if(time!=null){
	    	long t = Long.parseLong(time)*3600*1000;
	    	//设置缓存
	    	res.setDateHeader("expires", System.currentTimeMillis() + t);
	    }
	    
	    super.postDo(request, response, chain);
	}

}
