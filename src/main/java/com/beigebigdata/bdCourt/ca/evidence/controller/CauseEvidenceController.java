package com.beigebigdata.bdCourt.ca.evidence.controller;

import com.beigebigdata.bdCourt.ca.cases.entity.CaseBase;
import com.beigebigdata.bdCourt.ca.cases.service.CaseBaseService;
import com.beigebigdata.bdCourt.ca.evidence.entity.CaseEvidence;
import com.beigebigdata.bdCourt.ca.evidence.entity.CauseEvidence;
import com.beigebigdata.bdCourt.ca.evidence.service.CaseEvidenceService;
import com.beigebigdata.bdCourt.ca.evidence.service.CauseEvidenceService;
import com.septinary.api.annotation.SysTraceLog;
import com.septinary.common.util.VerifyUtils;
import com.septinary.common.util.ViewJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.*;


/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CauseEvidenceController
 * @Description:提供的案件访问接口
 * @date 16/10/21
 */
@Controller
@RequestMapping("v1.0/evidences")
public class CauseEvidenceController {

    @Autowired
    private CauseEvidenceService causeEvidenceService;

    @Autowired
    private CaseEvidenceService caseEvidenceService;

    @Autowired
    private CaseBaseService caseBaseService;

    /**
     * 获取指定证据类别下的的证据
     * @param causeCode
     * @return
     */
    @RequestMapping(value = "/{causeCode}/evidences",method = RequestMethod.GET)
    @ResponseBody
    @SysTraceLog(description = "获取指定证据类别下的的证据")
    public ViewJSON<List<Map<String,Object>>> fetchCauseEvidence(@PathVariable(value = "causeCode")Integer causeCode,String caseCode){
        VerifyUtils.verifyReviseInfo(causeCode, caseCode);
        CaseBase caseBase = new CaseBase();
        caseBase.setCaseCode(caseCode);
        caseBase = caseBaseService.selectOne(caseBase);
        if (caseBase == null) return new ViewJSON<>("404","CASE NOT FOUND","案件不存在！");
        List<CauseEvidence> causeEvidences = causeEvidenceService.fetchChildEvidence(causeCode,caseBase.getCaseType());

        List<CaseEvidence> caseEvidences = caseEvidenceService.fetchExistEvidence(causeCode,caseCode);

        List<Map<String,Object>> evidences = new ArrayList<>();
        //垃圾的实现方法，暂时先这样，后面优化
        for (Iterator<CauseEvidence> iterator = causeEvidences.iterator();iterator.hasNext();){
            CauseEvidence evidence = iterator.next();
            Map<String,Object> evi = new HashMap<>();
            evi.put("causeEvidence",evidence);
            evi.put("isSupply",false);
            for (Iterator<CaseEvidence> caseEvidenceIterator = caseEvidences.iterator();caseEvidenceIterator.hasNext();){
                CaseEvidence caseEvidence = caseEvidenceIterator.next();
                if (evidence.getEvidNo().intValue() == caseEvidence.getCeEvidNo().intValue()){
                    evi.put("isSupply",caseEvidence.getCeIssupply());
                    evi.put("caseEvidCode",caseEvidence.getCaseEvidCode());
                }
            }
            evidences.add(evi);
        }
        return new ViewJSON<>("200","success",evidences);

    }


    /**
     * 获取证据状态
     * @param evidNos
     * @return
     */
    @RequestMapping(value = "/{caseCode}/status",method = RequestMethod.GET)
    @ResponseBody
    @SysTraceLog(description = "获取证据状态")
    public ViewJSON<List<CaseStatus>> fetchCauseEvidencesStatus(@PathVariable(value = "caseCode")String caseCode,Integer[] evidNos){
        VerifyUtils.verifyReviseInfo(caseCode, evidNos);
        List<CaseStatus> resultList = fetchCaseStatus(caseCode, evidNos);
        return new ViewJSON<>("200","success",resultList);
    }


    /**
     * 获取证据状态
     * @param caseCode
     * @param evidNos
     * @return
     */
    private List<CaseStatus> fetchCaseStatus(String caseCode, Integer[] evidNos) {
        List<CaseStatus> resultList = new ArrayList<>();
        CaseBase caseBase = new CaseBase();
        caseBase.setCaseCode(caseCode);
        caseBase = caseBaseService.selectOne(caseBase);
        if (caseBase == null) return new ArrayList<>();

        for (Integer evidNo : evidNos){
            boolean needEssential = causeEvidenceService.needEssential(evidNo,caseBase.getCaseType());
            List<CaseEvidence> caseEvidences = caseEvidenceService.fetchExistEvidence(evidNo,caseCode);
            int supplyNum = 0;
            boolean isEssentialSupply = false;
            for (Iterator<CaseEvidence> iterator = caseEvidences.iterator();iterator.hasNext();){
                CaseEvidence caseEvidence = iterator.next();
                if (caseEvidence.getCeIssupply()) {
                    supplyNum++;
                    CauseEvidence causeEvidence = new CauseEvidence();
                    causeEvidence.setCauseNo(caseBase.getCaseType());
                    causeEvidence.setpEvidNo(caseEvidence.getCePEvidNo());
                    causeEvidence.setEvidNo(caseEvidence.getCeEvidNo());
                    causeEvidence = causeEvidenceService.selectOne(causeEvidence);
                    if (causeEvidence.getIsEssential() >= 1) isEssentialSupply = true;
                }
            }
            CaseStatus caseStatus = new CaseStatus();
            caseStatus.supplyNum = supplyNum;
            caseStatus.isEssentialSupply = isEssentialSupply;
            caseStatus.evidNo = evidNo;
            caseStatus.needEssential = needEssential;
            resultList.add(caseStatus);
        }
        return resultList;
    }

    class CaseStatus implements Serializable{
        private int evidNo;
        private int supplyNum;
        private boolean isEssentialSupply;
        private boolean needEssential;

        public boolean isNeedEssential() {
            return needEssential;
        }

        public void setNeedEssential(boolean needEssential) {
            this.needEssential = needEssential;
        }

        public int getEvidNo() {
            return evidNo;
        }

        public void setEvidNo(int evidNo) {
            this.evidNo = evidNo;
        }

        public boolean isEssentialSupply() {
            return isEssentialSupply;
        }

        public void setIsEssentialSupply(boolean isEssentialSupply) {
            this.isEssentialSupply = isEssentialSupply;
        }

        public int getSupplyNum() {
            return supplyNum;
        }

        public void setSupplyNum(int supplyNum) {
            this.supplyNum = supplyNum;
        }
    }











}
