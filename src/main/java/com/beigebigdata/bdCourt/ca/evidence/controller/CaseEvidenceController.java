package com.beigebigdata.bdCourt.ca.evidence.controller;

import com.beigebigdata.bdCourt.ca.common.service.MakerCodeService;
import com.beigebigdata.bdCourt.ca.evidence.entity.CaseEvidence;
import com.beigebigdata.bdCourt.ca.evidence.service.CaseEvidenceService;
import com.septinary.api.annotation.SysTraceLog;
import com.septinary.common.util.VerifyUtils;
import com.septinary.common.util.ViewJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CaseEvidenceController
 * @Description:提供的案件访问接口
 * @date 16/10/21
 */
@Controller
@RequestMapping("v1.0/caseEvidences")
public class CaseEvidenceController {

    @Autowired
    private CaseEvidenceService caseEvidenceService;

    @Autowired
    private MakerCodeService makerCodeService;


    /**
     * 提供案件证据
     * @param caseEvidence 案件证据
     * @return
     */
    @RequestMapping(value = "/caseEvidence",method = RequestMethod.POST)
    @ResponseBody
    @SysTraceLog(description = "提供案件证据")
    public ViewJSON<CaseEvidence> fetchCauseEvidence(CaseEvidence caseEvidence){
        VerifyUtils.verifyReviseInfo(caseEvidence.getCaseCode(), caseEvidence.getCeEvidNo(), caseEvidence.getCePEvidNo());

        CaseEvidence caseEvidenceDb = caseEvidenceService.selectOne(caseEvidence);

        if (caseEvidenceDb != null){
            caseEvidenceDb.setCeIssupply(true);
            caseEvidenceDb.setCeUpdate(new Date());
            caseEvidenceDb = caseEvidenceService.updateByPrimaryKeySelective(caseEvidenceDb);
            if (caseEvidenceDb == null) return new ViewJSON<>("500","SERVER ERROR","证据保存异常");
        }else{
            caseEvidence.setCeIssupply(true);
            caseEvidence.setCeSupplyTime(new Date());
            caseEvidence.setCaseEvidCode(makerCodeService.makeCode("CaseEvidence", null));

            caseEvidence = caseEvidenceService.insertSelective(caseEvidence);
            if (caseEvidence == null) return new ViewJSON<>("500","SERVER ERROR","证据保存异常");
        }
        return new ViewJSON<>("200","success",caseEvidenceDb != null ? caseEvidenceDb : caseEvidence);
    }


    /**
     * 撤销案件证据
     * @param caseEvidCode 案件证据code
     * @return
     */
    @RequestMapping(value = "/revokeCaseEvidence/{caseEvidCode}",method = RequestMethod.POST)
    @ResponseBody
    @SysTraceLog(description = "撤销案件证据")
    public ViewJSON<Object> revokeCaseEvidence(@PathVariable(value = "caseEvidCode")String caseEvidCode){
        VerifyUtils.verifyReviseInfo(caseEvidCode);

        CaseEvidence caseEvidence = new CaseEvidence();
        caseEvidence.setCaseEvidCode(caseEvidCode);
        caseEvidence.setCeUpdate(new Date());
        caseEvidence.setCeIssupply(false);

        int result = caseEvidenceService.revokeCaseEvidence(caseEvidCode);

        if (result <= 0) return new ViewJSON<>("500","SERVER ERROR","证据更新异常");

        return new ViewJSON<>("200","success");
    }









}
