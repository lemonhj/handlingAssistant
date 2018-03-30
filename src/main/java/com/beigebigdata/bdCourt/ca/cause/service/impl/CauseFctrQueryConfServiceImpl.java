package com.beigebigdata.bdCourt.ca.cause.service.impl;

import com.beigebigdata.bdCourt.ca.cause.dao.CauseFctrQueryConfDao;
import com.beigebigdata.bdCourt.ca.cause.entity.CauseFctrQueryConf;
import com.beigebigdata.bdCourt.ca.cause.service.CauseFctrQueryConfService;
import com.septinary.common.web.basic.dao.IBaseDao;
import com.septinary.common.web.basic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CauseFctrQueryConfServiceImpl
 * @Description:
 * @date 16/11/8
 */
@Service("causeFctrQueryConfService")
public class CauseFctrQueryConfServiceImpl extends BaseService<CauseFctrQueryConf,Long> implements CauseFctrQueryConfService {

    @Autowired
    private CauseFctrQueryConfDao causeFctrQueryConfDao;

    @Override
    public IBaseDao<CauseFctrQueryConf, Long> getBaseDao() {
        return causeFctrQueryConfDao;
    }
}
