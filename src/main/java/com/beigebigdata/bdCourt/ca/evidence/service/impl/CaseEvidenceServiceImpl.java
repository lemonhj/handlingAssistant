package com.beigebigdata.bdCourt.ca.evidence.service.impl;

import com.beigebigdata.bdCourt.ca.evidence.dao.CaseEvidenceDao;
import com.beigebigdata.bdCourt.ca.evidence.entity.CaseEvidence;
import com.beigebigdata.bdCourt.ca.evidence.service.CaseEvidenceService;
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
@Service("caseEvidenceService")
public class CaseEvidenceServiceImpl extends BaseService<CaseEvidence, Long> implements CaseEvidenceService {

    @Autowired
    private CaseEvidenceDao caseEvidenceDao;

    @Override
    public IBaseDao<CaseEvidence, Long> getBaseDao() {
        return caseEvidenceDao;
    }

    @Override
    public List<CaseEvidence> fetchExistEvidence(Integer parentCauseCode,String caseCode) {
        return caseEvidenceDao.fetchExistEvidence(parentCauseCode+"",caseCode);
    }

    @Override
    public int revokeCaseEvidence(String caseEvidCode) {
        return caseEvidenceDao.revokeCaseEvidence(caseEvidCode);
    }
}
