package com.beigebigdata.bdCourt.ca.cause.service.impl;

import com.beigebigdata.bdCourt.ca.cases.entity.CaseBase;
import com.beigebigdata.bdCourt.ca.cases.service.CaseBaseService;
import com.beigebigdata.bdCourt.ca.cause.dao.CauseFactorDao;
import com.beigebigdata.bdCourt.ca.cause.entity.CauseFactor;
import com.beigebigdata.bdCourt.ca.cause.service.CauseFactorService;
import com.septinary.common.web.basic.dao.IBaseDao;
import com.septinary.common.web.basic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CauseFactorServiceImpl
 * @Description:罪种要素信息操作
 * @date 16/11/1
 */
@Service("causeFactorService")
public class CauseFactorServiceImpl extends BaseService<CauseFactor,Long> implements CauseFactorService {

    @Autowired
    private CauseFactorDao causeFactorDao;

    @Autowired
    private CaseBaseService caseBaseService;

    @Override
    public IBaseDao<CauseFactor, Long> getBaseDao() {
        return causeFactorDao;
    }

    @Override
    public List<CauseFactor> causeFactorsByCt(Integer causeType) {
        return causeFactorDao.causeFactorsByCt(causeType);
    }

    @Override
    public List<CauseFactor> causeFactorsByCaseCode(String caseCode) {

        CaseBase caseBase = new CaseBase();
        caseBase.setCaseCode(caseCode);
        caseBase = caseBaseService.selectOne(caseBase);
        if (caseBase == null)return new ArrayList<>();

        return causeFactorDao.causeFactorsByCaseCode(caseCode,caseBase.getCaseType()+"");
    }

    @Override
    public List<String> fetchJudgementBasis(Long caseNo) {
        return causeFactorDao.fetchJudgementBasis(caseNo);
    }

    @Override
    public List<CauseFactor> fetchConditionsCause(Integer caseTyp, Integer pFactorNo) {
        return causeFactorDao.fetchConditionsCause(caseTyp,pFactorNo);
    }
}
