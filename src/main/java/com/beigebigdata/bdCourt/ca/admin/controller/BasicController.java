package com.beigebigdata.bdCourt.ca.admin.controller;


import com.beigebigdata.bdCourt.ca.admin.common.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public abstract class BasicController {

	
	@Autowired
	protected HttpSession session;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	protected String getParameter(HttpServletRequest request,String param){
		String value = Utils.trim(request.getParameter(param));
		return value;
	}
	public String redirect(String path) {
		return "redirect:" + path;
	}

	public String forward(String path){
		return "forward:" + path;
	}

	public WebMessage saveError(Object message) {
		return new WebMessage(WebMessage.ERROR, message);
	}

	public WebMessage saveSuccess() {
		return saveSuccess("");
	}

	public WebMessage saveSuccess(Object message) {
		return new WebMessage(WebMessage.SUCCESS, message);
	}

	public WebMessage saveSuccess(int status,Object message) {
		return new WebMessage(status, message);
	}
	
}
