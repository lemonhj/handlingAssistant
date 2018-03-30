package com.beigebigdata.bdCourt.ca.cause.controller;

import com.beigebigdata.bdCourt.ca.cause.entity.CauseFactor;
import com.beigebigdata.bdCourt.ca.cause.service.CauseFactorService;
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
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CauseFactorController
 * @Description:罪种要素信息API
 * @date 16/10/21
 */
@Controller
@RequestMapping("v1.0/causeFacs")
public class CauseFctrQueryConfController {

    @Autowired
    private CauseFactorService causeFactorService;

    /**
     * 通过案件类型来查询此案件类型下所涉及到的要素
     * @return
     */
    @RequestMapping(value = "/{causeType}/causeFactors",method = RequestMethod.GET)
    @ResponseBody
    @SysTraceLog(description = "通过案件类型来查询此案件类型下所涉及到的要素")
    public ViewJSON<List<CauseFactor>> causeFactorsByCauseType(@PathVariable(value = "causeType")Integer causeType){
        VerifyUtils.verifyReviseInfo(causeType);
        List<CauseFactor> causeFactors = causeFactorService.causeFactorsByCt(causeType);

        return new ViewJSON<>("200","success",causeFactors);
    }


    /**
     * 获取指定案件所具备的要素
     * @param caseCode 案件编号
     * @return
     */
    @RequestMapping(value = "/{caseCode}/byCaseCode/causeFactors",method = RequestMethod.GET)
    @ResponseBody
    @SysTraceLog(description = "获取指定案件所具备的要素")
    public ViewJSON<List<CauseFactor>> causeFactorsByCaseCode(@PathVariable(value = "caseCode")String caseCode) {
        VerifyUtils.verifyReviseInfo(caseCode);
        List<CauseFactor> resultList = causeFactorService.causeFactorsByCaseCode(caseCode);

        return new ViewJSON<>("200","success",resultList);
    }

}
