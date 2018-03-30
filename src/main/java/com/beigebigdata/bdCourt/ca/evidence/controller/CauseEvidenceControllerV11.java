package com.beigebigdata.bdCourt.ca.evidence.controller;

import com.beigebigdata.bdCourt.ca.cases.entity.CaseBase;
import com.beigebigdata.bdCourt.ca.evidence.entity.CauseEvidence;
import com.beigebigdata.bdCourt.ca.evidence.service.CauseEvidenceService;
import com.septinary.api.annotation.SysTraceLog;
import com.septinary.common.util.ViewJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 页卡首页证据相关V1.1版本
 * @author lihuay@chinabigdata.com 2016-11-21
 *
 */
@Controller
@RequestMapping("v1.1/evidences")
public class CauseEvidenceControllerV11 {


    @Autowired
    private CauseEvidenceService causeEvidenceService;
    
    /**
     * 获取案由下的所列的证据要素
     * @param causeNo
     * @return
     */
    @RequestMapping(value = "/{causeNo}/evidenceCategory",method = RequestMethod.GET)
    @ResponseBody
    @SysTraceLog(description = "获取指定案由下的所列证据分类")
    public ViewJSON<List<CaseBase>> fetchCauseEvidenceAll(@PathVariable(value = "causeNo")Integer causeNo) {
    	List<CaseBase> caseBases = causeEvidenceService.fetchCaseEvidenceCategory(causeNo);
    	return new ViewJSON<>("200","success",caseBases);
    }
    
    /**
     * 获取指定证据下的子证据
     * @param causeNo
     * @param evidNo
     * @return
     */
    @RequestMapping(value = "/{causeNo}/childs/{evidNo}",method = RequestMethod.GET)
    @ResponseBody
    @SysTraceLog(description = "获取指定案由下证据的子证据分类")
    public ViewJSON<List<Map<String,Object>>> fetchCauseEvidencdChildsByEvidNo(@PathVariable(value = "causeNo")Integer causeNo, @PathVariable(value = "evidNo")Integer evidNo) {
    	List<CauseEvidence> list = causeEvidenceService.fetchChildEvidence(evidNo,causeNo);
    	List<Map<String,Object>> listResult = new ArrayList<Map<String,Object>>();
    	if(list.size() > 0) {
    		for(int i=0; i < list.size(); i++) {
    			Map<String,Object> evi = new HashMap<>();
    			CauseEvidence obj = list.get(i);
    			evi.put("causeEvidence",obj);
    			evi.put("isSupply",false);
    			listResult.add(evi);
    		}
    	}
    	return new ViewJSON<List<Map<String,Object>>>("200", "success",listResult);
    }

}
