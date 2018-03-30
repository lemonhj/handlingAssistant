package com.septinary.common.web.general.interceptor.struts2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.septinary.common.web.basic.interceptor.IInterceptor;
import com.septinary.common.web.basic.interceptor.Interceptor;

/**
 * 
 * @Filename: com.septinary.common.web.general.interceptor.CommonInterceptorStruts2.java of the project [com.septinary.common.web]
 *     @Type: CommonInterceptorStruts2
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月29日下午3:01:19
 *
 */
public class CommonInterceptorStruts2 extends Interceptor implements IInterceptor {

	@Override
	public boolean preDo(ServletRequest request, ServletResponse response, Object object) throws IOException, ServletException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doing(ServletRequest request, ServletResponse response, Object object) throws IOException, ServletException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void postDo(ServletRequest request, ServletResponse response, Object object) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

}
