package com.septinary.common.web.general.runtime.trace;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.septinary.common.sys.op.IOperation;

/**
 * 当前访问的各种信息
 * @Filename: com.septinary.common.web.general.runtime.trace.Current.java of the project [com.septinary.common.web.general]
 *     @Type: Current
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年8月8日 下午5:18:57
 */
public class Current extends CurrentSessionInformation {
	
	//当前访问处理
	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	//当前操作行为
	private IOperation operation;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public IOperation getOperation() {
		return operation;
	}

	public void setOperation(IOperation operation) {
		this.operation = operation;
	}
}
