package com.beigebigdata.bdCourt.ca.cases.service.impl;

import com.beigebigdata.bdCourt.ca.cases.dao.CaseAnaDealedFileDao;
import com.beigebigdata.bdCourt.ca.cases.entity.CaseAnaDealedFile;
import com.beigebigdata.bdCourt.ca.cases.service.CaseAnaDealedFileService;
import com.septinary.common.web.basic.dao.IBaseDao;
import com.septinary.common.web.basic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CaseAnaDealedFileServiceImpl
 * @Description:
 * @date 16/11/2
 */
@Service("caseAnaDealedFileService")
public class CaseAnaDealedFileServiceImpl extends BaseService<CaseAnaDealedFile,Long> implements CaseAnaDealedFileService {
    @Autowired
    private CaseAnaDealedFileDao caseAnaDealedFileDao;

    @Override
    public IBaseDao<CaseAnaDealedFile, Long> getBaseDao() {
        return caseAnaDealedFileDao;
    }
}
