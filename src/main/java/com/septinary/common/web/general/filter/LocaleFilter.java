package com.septinary.common.web.general.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 国际化消息过滤器
 * @Filename: com.septinary.common.web.general.filter.LocaleFilter.java of the project [com.septinary.common.web]
 *     @Type: LocaleFilter
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月9日下午8:06:51
 *
 */
public class LocaleFilter extends CommonFilter {

	@Override
	public boolean preDo(ServletRequest request, ServletResponse response, Object chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		return super.preDo(request, response, chain);
	}

	@Override
	public void postDo(ServletRequest request, ServletResponse response, Object chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.postDo(request, response, chain);
	}

}
