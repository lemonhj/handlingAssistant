package com.septinary.common.web.general.filter.springmvc;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * 统一全站字符编码
 * 此过滤器用来解决全站中文乱码问题
 * @Filename: com.septinary.common.web.general.filter.springmvc.EncodingFilterSpringMVC.java of the project [com.septinary.common.web]
 *     @Type: EncodingFilterSpringMVC
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月30日下午2:17:06
 *
 *	通过配置参数charset指明使用何种字符编码,以处理Html Form请求参数的中文问题
 */
public class EncodingFilterSpringMVC extends CharacterEncodingFilter {

	//过滤器配置信息
	//private FilterConfig filterConfig = null;

	//设置默认的字符编码
	//private String defaultCharset = "UTF-8";
	//private boolean forceCharset = false;
  
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		// TODO Auto-generated method stub

		/*
		//只能处理HTTP请求
		if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
			throw new ServletException(this.getClass().getName()+" just supports HTTP requests");
		}
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;
	    */
		/*
	    String charset = this.getFilterConfig().getInitParameter("encoding");
	    Boolean forceEncoding = Boolean.valueOf(this.getFilterConfig().getInitParameter("forceEncoding"));
	    if(charset==null) charset = defaultCharset;
	    if(forceEncoding==null) forceEncoding = forceCharset;
	    */
	    /*
	    //重写字符集编码
	    request.setCharacterEncoding(charset);
	    response.setCharacterEncoding(charset);
	    response.setContentType("text/html;charset="+charset);
	    */
	    /*
	    //激活下一过滤器
	    EncodingHttpServletRequest requestWrapper = new EncodingHttpServletRequest(request);
	    filterChain.doFilter(requestWrapper, response);
		*/
	    
		super.doFilterInternal(request, response, filterChain);
	}

}
