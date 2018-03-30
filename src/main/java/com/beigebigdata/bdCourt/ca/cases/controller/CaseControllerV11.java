package com.beigebigdata.bdCourt.ca.cases.controller;

import com.beigebigdata.bdCourt.ca.cases.service.CaseService;
import com.beigebigdata.bdCourt.ca.cause.entity.CauseFctrQueryConf;
import com.beigebigdata.bdCourt.ca.cause.service.CauseFctrQueryConfService;
import com.septinary.api.annotation.SysTraceLog;
import com.septinary.common.util.VerifyUtils;
import com.septinary.common.util.ViewJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 与案由要素相关的同类案例
 * @author lihuay@chinabigdata.com 2016-11-22
 *
 */
@Controller
@RequestMapping("v1.1/historicalCases")
public class CaseControllerV11 {
	
	@Autowired
	private CauseFctrQueryConfService causeFctrQueryConfService;
	
    @Autowired
    private CaseService caseService;
	
	 /**
     * 获取选择案由所办案件的历史案件的筛选条件
     * @param causeNo
     * @return
     */
    @RequestMapping(value = "/{causeNo}/screeningConditions",method = RequestMethod.GET)
    @ResponseBody
    @SysTraceLog(description = "获取历史案件的筛选条件")
    public ViewJSON<List<CauseFctrQueryConf>> casescreeningConditions(@PathVariable(value = "causeNo")Integer causeNo){
        VerifyUtils.verifyReviseInfo(causeNo);
        CauseFctrQueryConf causeFctrQueryConf = new CauseFctrQueryConf();
        causeFctrQueryConf.setCauseNo(causeNo);
        List<CauseFctrQueryConf> causeFctrQueryConfs = causeFctrQueryConfService.select(causeFctrQueryConf);

        return new ViewJSON<>("200","success",causeFctrQueryConfs);
    }


}
