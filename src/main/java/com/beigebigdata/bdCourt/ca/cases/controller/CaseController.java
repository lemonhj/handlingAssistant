package com.beigebigdata.bdCourt.ca.cases.controller;

import com.beigebigdata.bdCourt.ca.cases.entity.Case;
import com.beigebigdata.bdCourt.ca.cases.entity.CaseAnalysisResult;
import com.beigebigdata.bdCourt.ca.cases.entity.CaseBase;
import com.beigebigdata.bdCourt.ca.cases.entity.CaseDefendant;
import com.beigebigdata.bdCourt.ca.cases.service.CaseAnalysisResultService;
import com.beigebigdata.bdCourt.ca.cases.service.CaseBaseService;
import com.beigebigdata.bdCourt.ca.cases.service.CaseDefendantService;
import com.beigebigdata.bdCourt.ca.cases.service.CaseService;
import com.beigebigdata.bdCourt.ca.cause.entity.CauseConfig;
import com.beigebigdata.bdCourt.ca.cause.entity.CauseFctrQueryConf;
import com.beigebigdata.bdCourt.ca.cause.service.CauseConfigService;
import com.beigebigdata.bdCourt.ca.cause.service.CauseFactorService;
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

import java.io.Serializable;
import java.util.*;


/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CaseController
 * @Description:提供的历史案件访问接口
 * @date 16/10/21
 */
@Controller
@RequestMapping("v1.0/historicalCases")
public class CaseController {

    @Autowired
    private CaseAnalysisResultService caseAnalysisResultService;

    @Autowired
    private CaseService caseService;

    @Autowired
    private CaseDefendantService defendantService;

    @Autowired
    private CauseFactorService causeFactorService;

    @Autowired
    private CauseConfigService causeConfigService;

    @Autowired
    private CaseBaseService caseBaseService;

    @Autowired
    private CauseFctrQueryConfService causeFctrQueryConfService;

    /**
     * 获取案件的偏离度
     * @param caseNo
     * @return
     */
    @RequestMapping(value = "/{caseNo}/caseDeviation",method = RequestMethod.GET)
    @ResponseBody
    @SysTraceLog(description = "获取案件的偏离度")
    public ViewJSON<String> fetchCaseDeviation(@PathVariable(value = "caseNo")Long caseNo){
        VerifyUtils.verifyReviseInfo(caseNo);

        CaseAnalysisResult caseAnalysisResult = new CaseAnalysisResult();
        caseAnalysisResult.setCaseNo(caseNo);
        caseAnalysisResult = caseAnalysisResultService.selectOne(caseAnalysisResult);

        if (caseAnalysisResult == null) new ViewJSON<>("404","CASE NOT FOUND","案件不存在！");

        String result = caseAnalysisResult.getCaseDeviation()+"%";

        return new ViewJSON<>("200","success","","",result);

    }

    /**
     * 查询历史案件要素
     * @param caseNo
     * @return
     */
    @RequestMapping(value = "/{caseNo}/causes",method = RequestMethod.GET)
    @ResponseBody
    @SysTraceLog(description = "查询历史案件要素")
    public ViewJSON<List<Map<String,Object>>> fetchCaseCause(@PathVariable(value = "caseNo")Long caseNo){
        VerifyUtils.verifyReviseInfo(caseNo);
        List<Map<String,Object>> causeFactorList= caseService.fetchCaseCause(caseNo);

        return new ViewJSON<>("200","success","","",causeFactorList);
    }

    /**
     * 查询历史案件的被告人信息
     * @param caseNo
     * @return
     */
    @RequestMapping(value = "/{caseNo}/defendantInfo",method = RequestMethod.GET)
    @ResponseBody
    @SysTraceLog(description = "查询历史案件的被告人信息")
    public ViewJSON<List<CaseDefendant>> fetchDefendantInfo(@PathVariable(value = "caseNo")Long caseNo){
        VerifyUtils.verifyReviseInfo(caseNo);
        List<CaseDefendant> causeFactorList= defendantService.fetchDefendantInfo(caseNo);

        return new ViewJSON<>("200","success","","",causeFactorList);
    }

    /**
     * 查询案件的审判依据
     * @param caseNo
     * @return
     */
    @RequestMapping(value = "/{caseNo}/judgementBasis",method = RequestMethod.GET)
    @ResponseBody
    @SysTraceLog(description = "查询案件的审判依据")
    public ViewJSON<Map<String,Object>> fetchJudgementBasis(@PathVariable(value = "caseNo")Long caseNo){
        VerifyUtils.verifyReviseInfo(caseNo);
        List<String> causeFactorList= causeFactorService.fetchJudgementBasis(caseNo);
        CaseDefendant defendant = new CaseDefendant();
        defendant.setCaseNo(caseNo);
        List<CaseDefendant> defendants = defendantService.select(defendant);
        List<String> summaryInfos = new ArrayList<>();

        for(Iterator<CaseDefendant> iterator = defendants.iterator();iterator.hasNext();){
            CaseDefendant cd = iterator.next();
            summaryInfos.add(cd.getJudgmentResult());
        }

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("judgementBasis",causeFactorList);
        resultMap.put("summaryInfo",summaryInfos);

        return new ViewJSON<>("200","success",resultMap);
    }

    /**
     * 查询案件的法官信息
     * @param caseNo
     * @return
     */
    @RequestMapping(value = "/{caseNo}/judgeInfo",method = RequestMethod.GET)
    @ResponseBody
    @SysTraceLog(description = "查询案件的法官信息")
    public ViewJSON<Map<String,Object>> fetchJudgeInfo(@PathVariable(value = "caseNo")Long caseNo){
        VerifyUtils.verifyReviseInfo(caseNo);
        CauseConfig causeConfig = new CauseConfig();
        causeConfig.setCauseCategory(1);
        List<CauseConfig> causeConfigs = causeConfigService.select(causeConfig);
        List<TrialCases> trialCasesList = new ArrayList<>();
        Case caseInfo = new Case();
        caseInfo.setCaseNo(caseNo);
        caseInfo = caseService.selectOne(caseInfo);
        if (caseInfo == null) new ViewJSON<>("404","CASE NOT FOUND","案件不存在！");
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("judgeName",caseInfo.getJudgeName());

        for (Iterator<CauseConfig> iterator = causeConfigs.iterator();iterator.hasNext();){
            CauseConfig cc = iterator.next();
            Integer caseNum = caseService.fetchCaseNum(caseNo,cc.getCauseNo());
            TrialCases trialCases = new TrialCases();
            trialCases.setCauseName(cc.getCauseName());
            trialCases.setCaseNum(caseNum);
            trialCasesList.add(trialCases);
        }
        resultMap.put("historicalCases",trialCasesList);
        return new ViewJSON<>("200","success",resultMap);
    }


    class TrialCases implements Serializable{
        private String causeName;
        private Integer caseNum;

        public String getCauseName() {
            return causeName;
        }

        public void setCauseName(String causeName) {
            this.causeName = causeName;
        }

        public Integer getCaseNum() {
            return caseNum;
        }

        public void setCaseNum(Integer caseNum) {
            this.caseNum = caseNum;
        }
    }


    /**
     * 获取相似历史案件的筛选条件
     * @param caseCode
     * @return
     */
    @RequestMapping(value = "/{caseCode}/screeningConditions",method = RequestMethod.GET)
    @ResponseBody
    @SysTraceLog(description = "获取历史案件的筛选条件")
    public ViewJSON<List<CauseFctrQueryConf>> casescreeningConditions(@PathVariable(value = "caseCode")String caseCode){
        VerifyUtils.verifyReviseInfo(caseCode);
        CaseBase caseInfo = new CaseBase();
        caseInfo.setCaseCode(caseCode);
        caseInfo = caseBaseService.selectOne(caseInfo);
        if (caseInfo == null) new ViewJSON<>("404","CASE NOT FOUND","案件不存在！");

        CauseFctrQueryConf causeFctrQueryConf = new CauseFctrQueryConf();
        causeFctrQueryConf.setCauseNo(caseInfo.getCaseType());
        List<CauseFctrQueryConf> causeFctrQueryConfs = causeFctrQueryConfService.select(causeFctrQueryConf);

        return new ViewJSON<>("200","success",causeFctrQueryConfs);
    }


    /**
     * 获取相似历史案件的统计信息
     * @param caseCode
     * @return
     */
    @RequestMapping(value = "/{caseCode}/statisticsInfo",method = RequestMethod.GET)
    @ResponseBody
    @SysTraceLog(description = "获取相似历史案件的统计信息")
    public ViewJSON<HashMap<String,HashMap<String,Integer>>> fetchStatisticsInfo(@PathVariable(value = "caseCode")String caseCode){
        HashMap<String,HashMap<String,Integer>> statisticsInfo = caseService.fetchStatisticsInfo(caseCode);
        return new ViewJSON<>("200","success",statisticsInfo);
    }

    /**
     * 通过案由号获取统计信息
     * @param causeNo
     * @return
     */
    @RequestMapping(value = "/{causeNo}/statisticsInfo/byCauseNo",method = RequestMethod.GET)
    @ResponseBody
    @SysTraceLog(description = "通过案由获取统计信息")
    public ViewJSON<HashMap<String,HashMap<String,Integer>>> fetchStatisticsInfoByCauseNo(@PathVariable(value = "causeNo")String causeNo){
        HashMap<String,HashMap<String,Integer>> statisticsInfo = caseService.fetchStatisticsInfoByCauseNo(causeNo);
        return new ViewJSON<>("200","success",statisticsInfo);
    }


}
