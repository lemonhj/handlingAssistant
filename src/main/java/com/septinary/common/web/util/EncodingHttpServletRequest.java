package com.septinary.common.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 编码处理的HttpServletRequest
 * @Filename: com.septinary.common.web.util.EncodingHttpServletRequest.java of the project [com.septinary.common.web]
 *     @Type: EncodingHttpServletRequest
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日下午10:22:33
 *
 */
public class EncodingHttpServletRequest extends HttpServletRequestWrapper {

	private HttpServletRequest request = null;
	
	public EncodingHttpServletRequest(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
		
		this.request = request;
	}

	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		// return super.getParameter(name);
		
		try{
			//获取参数的值
			String value= this.request.getParameter(name);
			if(value==null) return null;

			if( !this.request.getMethod().equalsIgnoreCase("get") ) {
				//如果不是以get方式提交数据的，就直接返回获取到的值
				return value;
			} else {
				//如果是以get方式提交数据的，就对获取到的值进行转码处理
		        value = new String(value.getBytes("ISO8859-1"),this.request.getCharacterEncoding());
		        return value;
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
