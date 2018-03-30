package com.septinary.common.web.general.runtime.trace;

import javax.servlet.http.HttpSession;

/**
 * 当前会话信息
 * @Filename: com.septinary.common.web.general.runtime.trace.CurrentSessionInformation.java of the project [com.septinary.common.web.general]
 *     @Type: CurrentSessionInformation
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年8月10日 下午8:02:49
 */
public class CurrentSessionInformation extends CurrentContextInformation {

	private HttpSession session;

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
}
