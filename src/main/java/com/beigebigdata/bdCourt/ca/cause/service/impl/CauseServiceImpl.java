package com.beigebigdata.bdCourt.ca.cause.service.impl;

import com.beigebigdata.bdCourt.ca.cause.dao.CauseDao;
import com.beigebigdata.bdCourt.ca.cause.entity.Cause;
import com.beigebigdata.bdCourt.ca.cause.service.CauseService;
import com.septinary.common.web.basic.dao.IBaseDao;
import com.septinary.common.web.basic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CauseServiceImpl
 * @Description:
 * @date 16/10/26
 */
@Service("causeService")
public class CauseServiceImpl extends BaseService<Cause,Long> implements CauseService {

    @Autowired
    private CauseDao causeDao;


    @Override
    public IBaseDao<Cause, Long> getBaseDao() {
        return causeDao;
    }
}
