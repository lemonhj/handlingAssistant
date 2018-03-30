package com.beigebigdata.bdCourt.ca.admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelpController extends BasicController {

	
	@RequestMapping(value = "help")
	public @ResponseBody
	WebMessage helpHandler(){
		return  saveSuccess();
	}
	
	@RequestMapping(value = "index")
	public String indexHandler(){
		return  "index";
	}
	
}
