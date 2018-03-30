package com.septinary.common.web.general.runtime.trace;

import javax.servlet.jsp.PageContext;

/**
 * 当前页面上下文信息
 * @Filename: com.septinary.common.web.general.runtime.trace.CurrentPageInformation.java of the project [com.septinary.common.web.general]
 *     @Type: CurrentPageInformation
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年8月10日 下午8:06:28
 */
public class CurrentPageInformation extends Current {

	private PageContext page;

	public PageContext getPage() {
		return page;
	}

	public void setPage(PageContext page) {
		this.page = page;
	}
}
