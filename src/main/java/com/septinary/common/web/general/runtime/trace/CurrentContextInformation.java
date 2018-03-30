package com.septinary.common.web.general.runtime.trace;

import javax.servlet.ServletContext;

/**
 * 当前上下文信息
 * @Filename: com.septinary.common.web.general.runtime.trace.CurrentContextInformation.java of the project [com.septinary.common.web.general]
 *     @Type: CurrentContextInformation
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年8月10日 下午8:01:37
 */
public class CurrentContextInformation extends CurrentApplicationInformation {

	private ServletContext servlet;

	public ServletContext getServlet() {
		return servlet;
	}

	public void setServlet(ServletContext servlet) {
		this.servlet = servlet;
	}
}
