package com.beigebigdata.bdCourt.ca.cases.controller;

import com.beigebigdata.bdCourt.ca.cases.service.CaseBaseService;
import com.septinary.api.annotation.SysTraceLog;
import com.septinary.common.util.VerifyUtils;
import com.septinary.common.util.ViewJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * 提供的案件访问接口
 * @author lihuay@chinabigdata.com 2016-11-22
 *
 */
@Controller
@RequestMapping("v1.1/cases")
public class CaseBaseControllerV11 {
	
    @Autowired
    private CaseBaseService caseBaseService;
    
    /**
     * 根据筛选条件来获取相似案件
     * @param causeNo
     * @return
     */
    @RequestMapping(value = "/{causeNo}/similarCases",method = RequestMethod.GET)
    @ResponseBody
    @SysTraceLog(description = "根据筛选条件来获取历史案件")
    public ViewJSON<List<HashMap<String,Object>>> fetchCasesByConditions(@PathVariable(value = "causeNo")String causeNo,Integer startNo,Integer size){
        VerifyUtils.verifyReviseInfo(causeNo);
        List<HashMap<String,Object>> caseBases = caseBaseService.fetchSimilarByCauseNo(causeNo,startNo,size);

        return new ViewJSON<>("200","success",caseBases);
    }
}
