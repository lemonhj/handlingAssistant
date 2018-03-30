package com.septinary.common.web.general.service;

import javax.servlet.http.HttpServletRequest;

import com.septinary.common.type.VoidSerialization;
import com.septinary.common.web.basic.service.IBaseService;
import com.septinary.common.web.general.runtime.trace.Current;

public interface RequestService extends IBaseService<Void,VoidSerialization> {
	
	/**
	 * 当前请求信息
	 * @param request
	 * @return
	 */
	public Current current(HttpServletRequest request);
}
