package com.septinary.common.web.general.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 访问轨迹跟踪过滤器
 * @Filename: com.septinary.common.web.general.filter.AccessTrailFilter.java of the project [com.septinary.common.web]
 *     @Type: AccessTrailFilter
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月1日下午4:36:41
 *
 */
public class AccessTrailFilter extends CommonFilter {

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
