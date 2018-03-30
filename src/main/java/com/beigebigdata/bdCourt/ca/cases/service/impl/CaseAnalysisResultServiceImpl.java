package com.beigebigdata.bdCourt.ca.cases.service.impl;

import com.beigebigdata.bdCourt.ca.cases.dao.CaseAnalysisResultDao;
import com.beigebigdata.bdCourt.ca.cases.entity.CaseAnalysisResult;
import com.beigebigdata.bdCourt.ca.cases.service.CaseAnalysisResultService;
import com.septinary.common.web.basic.dao.IBaseDao;
import com.septinary.common.web.basic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CaseServiceImpl
 * @Description:
 * @date 16/11/2
 */
@Service("caseAnalysisResultService")
public class CaseAnalysisResultServiceImpl extends BaseService<CaseAnalysisResult,Long> implements CaseAnalysisResultService {

    @Autowired
    private CaseAnalysisResultDao caseAnalysisResultDao;

    @Override
    public IBaseDao<CaseAnalysisResult, Long> getBaseDao() {
        return caseAnalysisResultDao;
    }


}
