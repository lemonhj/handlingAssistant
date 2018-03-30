package com.beigebigdata.bdCourt.ca.cases.service.impl;

import com.beigebigdata.bdCourt.ca.cases.dao.CaseDefendantDao;
import com.beigebigdata.bdCourt.ca.cases.entity.CaseDefendant;
import com.beigebigdata.bdCourt.ca.cases.service.CaseDefendantService;
import com.septinary.common.web.basic.dao.IBaseDao;
import com.septinary.common.web.basic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CaseDefendantServiceImpl
 * @Description:
 * @date 16/11/3
 */
@Service("caseDefendantService")
public class CaseDefendantServiceImpl extends BaseService<CaseDefendant,Long> implements CaseDefendantService {

    @Autowired
    private CaseDefendantDao caseDefendantDao;

    @Override
    public IBaseDao<CaseDefendant, Long> getBaseDao() {
        return caseDefendantDao;
    }

    @Override
    public List<CaseDefendant> fetchDefendantInfo(Long caseNo) {
        return caseDefendantDao.fetchDefendantInfo(caseNo);
    }

}
