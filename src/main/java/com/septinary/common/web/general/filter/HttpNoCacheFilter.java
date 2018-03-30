package com.septinary.common.web.general.filter;

import java.io.IOException;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 禁止浏览器缓存所有动态页面
 * @Filename: com.septinary.common.web.general.filter.HttpCacheFilter.java of the project [com.septinary.common.web]
 *     @Type: HttpCacheFilter
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月30日下午4:34:57
 *  有3 个HTTP 响应头字段都可以禁止浏览器缓存当前页面，它们在 Servlet 中的示例代码如下：
 *  	response.setDateHeader("Expires",-1);
 *  	response.setHeader("Cache-Control","no-cache");
 *  	response.setHeader("Pragma","no-cache"); 
 *  并不是所有的浏览器都能完全支持上面的三个响应头，因此最好是同时使用上面的三个响应头。
 *  	Expires数据头：值为GMT时间值，为-1指浏览器不要缓存页面
 *  	Cache-Control响应头有两个常用值：
 *  	no-cache指浏览器不要缓存当前页面。
 *  	max-age:xxx指浏览器缓存页面xxx秒。
 *  
 *	web.xml文件中的配置如下：
<filter>
   <filter-name>NoCacheFilter</filter-name>
   <filter-class>com.septinary.common.web.general.filter.HttpNoCacheFilter</filter-class>
</filter>
  
<filter-mapping>
   <filter-name>NoCacheFilter</filter-name>
    <!--只拦截Jsp请求-->
   <servlet-name>*.jsp</servlet-name>
</filter-mapping>
*/
public class HttpNoCacheFilter extends CommonFilter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		super.init(filterConfig);
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
	    
	    //禁止浏览器缓存所有动态页面
	    res.setDateHeader("Expires", -1);
	    res.setHeader("Cache-Control", "no-cache");
	    res.setHeader("Pragma", "no-cache");
	    
	    super.postDo(req, res, chain);
	}

}
