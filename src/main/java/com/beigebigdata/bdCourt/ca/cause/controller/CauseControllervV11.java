package com.beigebigdata.bdCourt.ca.cause.controller;

import com.beigebigdata.bdCourt.ca.common.entity.FieldValue;
import com.beigebigdata.bdCourt.ca.common.service.FieldService;
import com.septinary.api.annotation.SysTraceLog;
import com.septinary.common.util.ViewJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 版本V1.1,获取案由列表
 * @author lihuay@chainbigdata.com 2016-11-21
 *
 */
@Controller
@RequestMapping("v1.1/causes")
public class CauseControllervV11 {
	
	    @Autowired
	    private FieldService fieldService;
	    /**
	     * 获取首页页卡信息
	     * @return
	     */
	    @RequestMapping(value = "/caseTab", method =  RequestMethod.GET)
	    @ResponseBody
	    @SysTraceLog(description = "获取首页页卡")
	    public ViewJSON<List<Map<String,String>>> getCaseHomeTab() {

	        List<FieldValue> fieldValues = fieldService.fetchFieldValue("CaseHomeTab");
	        List<Map<String,String>> roles = new ArrayList<>();
	        for (Iterator<FieldValue> iterator= fieldValues.iterator();iterator.hasNext();){
	            FieldValue fieldValue = iterator.next();
	            if (!fieldValue.getValue().equals("0")){
	                Map<String,String> role = new HashMap<>();
	                role.put("type",fieldValue.getValue());
	                role.put("name",fieldValue.getParse());
	                roles.add(role);
	            }
	        }
	        return new ViewJSON<>("200","OK",roles);
	    }
}
