package com.beigebigdata.bdCourt.ca.evidence.service.impl;


import com.beigebigdata.bdCourt.ca.cases.entity.CaseBase;
import com.beigebigdata.bdCourt.ca.cases.service.CaseBaseService;
import com.beigebigdata.bdCourt.ca.evidence.dao.CauseEvidenceDao;
import com.beigebigdata.bdCourt.ca.evidence.entity.CauseEvidence;
import com.beigebigdata.bdCourt.ca.evidence.service.CauseEvidenceService;
import com.septinary.common.web.basic.dao.IBaseDao;
import com.septinary.common.web.basic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CauseEvidenceServiceImpl
 * @Description:
 * @date 16/10/26
 */
@Service("causeEvidenceService")
public class CauseEvidenceServiceImpl extends BaseService<CauseEvidence,Long> implements CauseEvidenceService {

    @Autowired
    private CauseEvidenceDao causeEvidenceDao;

    @Autowired
    private CaseBaseService caseBaseService;

    @Override
    public IBaseDao<CauseEvidence, Long> getBaseDao() {
        return causeEvidenceDao;
    }

    @Override
    public List<CaseBase> fetchCaseEvidenceCategory(Integer caseType) {
        return causeEvidenceDao.fetchCaseEvidenceCategory(caseType);
    }

    @Override
    public List<CauseEvidence> fetchChildEvidence(Integer causeCode, Integer caseType) {
        return causeEvidenceDao.fetchChildEvidence(causeCode,caseType);
    }

    @Override
    public boolean needEssential(Integer evidNo, Integer caseType) {
        List<Integer> essentials = causeEvidenceDao.essential(evidNo,caseType);
        if (essentials.contains(1) || essentials.contains(2)) return true;
        return false;
    }

}
