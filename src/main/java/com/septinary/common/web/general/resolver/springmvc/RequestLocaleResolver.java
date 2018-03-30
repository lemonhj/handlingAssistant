package com.septinary.common.web.general.resolver.springmvc;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

/**
 * 
 * @Filename: com.septinary.common.web.general.resolver.springmvc.RequestLocaleResolver.java of the project [com.septinary.common.web.general]
 *     @Type: RequestLocaleResolver
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月21日下午9:52:55
 *
 */
public class RequestLocaleResolver extends AcceptHeaderLocaleResolver {
	
	private Locale locale;

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		// TODO Auto-generated method stub
		// return super.resolveLocale(request);
		
		return this.locale;
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		// TODO Auto-generated method stub
		// super.setLocale(request, response, locale);
		
		this.locale = locale;
	}

}
