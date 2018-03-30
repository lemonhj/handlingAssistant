package com.septinary.common.web.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 请求处理接口
 * @Filename: com.septinary.common.web.util.IRequestProcessable.java of the project [com.septinary.common.web]
 *     @Type: IOnRequestDoing
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月29日下午3:15:29
 *
 */
public interface IRequestProcessable {

	public boolean preDo(ServletRequest request, ServletResponse response, Object object) throws IOException, ServletException;
	
	public boolean doing(ServletRequest request, ServletResponse response, Object object) throws IOException, ServletException;
	
	public void postDo(ServletRequest request, ServletResponse response, Object object) throws IOException, ServletException;

}
