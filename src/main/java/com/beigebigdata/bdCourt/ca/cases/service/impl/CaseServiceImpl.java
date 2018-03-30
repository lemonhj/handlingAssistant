package com.beigebigdata.bdCourt.ca.cases.service.impl;

import com.beigebigdata.bdCourt.ca.cases.dao.CaseDao;
import com.beigebigdata.bdCourt.ca.cases.entity.Case;
import com.beigebigdata.bdCourt.ca.cases.service.CaseService;
import com.septinary.common.web.basic.dao.IBaseDao;
import com.septinary.common.web.basic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CaseServiceImpl
 * @Description:
 * @date 16/11/2
 */
@Service("caseService")
public class CaseServiceImpl extends BaseService<Case,Long> implements CaseService {

    @Autowired
    private CaseDao caseDao;


    @Override
    public IBaseDao<Case, Long> getBaseDao() {
        return caseDao;
    }


    @Override
    public List<Map<String,Object>> fetchCaseCause(Long caseNo) {
        Case caseInfo = new Case();
        caseInfo.setCaseNo(caseNo);
        caseInfo = selectOne(caseInfo);

        return caseDao.fetchCaseCause(caseNo,caseInfo.getCauseNo());
    }

    @Override
    public Integer fetchCaseNum(Long caseNo, Integer causeNo) {
        return caseDao.fetchCaseNum(caseNo,causeNo);
    }


    @Override
    public HashMap<String,HashMap<String,Integer>> fetchStatisticsInfo(String caseCode) {

        HashMap<String,HashMap<String,Integer>> returnMap = new HashMap<>();
        HashMap<String,Integer> intervalSentence = fetchSentenceCaseNum(caseCode);
        returnMap.put("intervalSentence",intervalSentence);
        HashMap<String,Integer> probation = fetchProbationCaseNum(caseCode);
        returnMap.put("probation",probation);

        return returnMap;
    }

    @Override
    public HashMap<String, HashMap<String, Integer>> fetchStatisticsInfoByCauseNo(String causeNo) {

        HashMap<String,HashMap<String,Integer>> returnMap = new HashMap<>();
        HashMap<String,Integer> intervalSentence = fetchSentenceCaseNumByCauseNo(causeNo);
        returnMap.put("intervalSentence",intervalSentence);
        HashMap<String,Integer> probation = fetchProbationCaseNumByCauseNo(causeNo);
        returnMap.put("probation",probation);

        return returnMap;
    }

    private HashMap<String, Integer> fetchProbationCaseNumByCauseNo(String causeNo) {
        HashMap<String,Integer> probationCaseNum = new HashMap<>();
        probationCaseNum.put("reprieve",caseDao.fetchCaseNumByCauseNo(causeNo, "cr.susp_term is not null"));
        probationCaseNum.put("notReprieve",caseDao.fetchCaseNumByCauseNo(causeNo, "cr.susp_term is null"));

        return probationCaseNum;
    }

    private HashMap<String, Integer> fetchSentenceCaseNumByCauseNo(String causeNo) {

        String conditions[] = {"lt4Month","mt4lt14","mt14"};
        HashMap<String,Integer> sentenceCaseNum = new HashMap<>();
        for (String condition : conditions){
            switch (condition){
                case "lt4Month" : sentenceCaseNum.put("lt4Month", caseDao.fetchCaseNumByCauseNo(causeNo, "cr.impris_term < 4"));
                case "mt4lt14" : sentenceCaseNum.put("mt4lt14",caseDao.fetchCaseNumByCauseNo(causeNo, "cr.impris_term > 4 AND cr.impris_term < 14"));
                default: sentenceCaseNum.put("mt14", caseDao.fetchCaseNumByCauseNo(causeNo, "cr.impris_term > 14"));
            }
        }
        return sentenceCaseNum;
    }

    /**
     * 获取缓刑统计信息
     * @param caseCode 案件编码
     * @return
     */
    private HashMap<String,Integer> fetchProbationCaseNum(String caseCode) {
        HashMap<String,Integer> probationCaseNum = new HashMap<>();
        probationCaseNum.put("reprieve",caseDao.fetchCaseNumByCondition(caseCode, "cr.susp_term is not null"));
        probationCaseNum.put("notReprieve",caseDao.fetchCaseNumByCondition(caseCode, "cr.susp_term is null"));

        return probationCaseNum;
    }

    /**
     * 获取刑期统计信息
     * @param caseCode 案件编码
     * @return
     */
    private HashMap<String,Integer> fetchSentenceCaseNum(String caseCode) {
        String conditions[] = {"lt4Month","mt4lt14","mt14"};
        HashMap<String,Integer> sentenceCaseNum = new HashMap<>();
        for (String condition : conditions){
            switch (condition){
                case "lt4Month" : sentenceCaseNum.put("lt4Month", caseDao.fetchCaseNumByCondition(caseCode, "cr.impris_term < 4"));
                case "mt4lt14" : sentenceCaseNum.put("mt4lt14",caseDao.fetchCaseNumByCondition(caseCode,"cr.impris_term > 4 AND cr.impris_term < 14"));
                default: sentenceCaseNum.put("mt14", caseDao.fetchCaseNumByCondition(caseCode, "cr.impris_term > 14"));
            }
        }
        return sentenceCaseNum;
    }


}
