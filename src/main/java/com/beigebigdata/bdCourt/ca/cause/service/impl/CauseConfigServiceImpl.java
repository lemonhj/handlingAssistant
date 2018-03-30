package com.beigebigdata.bdCourt.ca.cause.service.impl;


import com.beigebigdata.bdCourt.ca.cause.dao.CauseConfigDao;
import com.beigebigdata.bdCourt.ca.cause.entity.CauseConfig;
import com.beigebigdata.bdCourt.ca.cause.service.CauseConfigService;
import com.septinary.common.web.basic.dao.IBaseDao;
import com.septinary.common.web.basic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CauseConfigServiceImpl
 * @Description:
 * @date 16/11/3
 */
@Service("causeConfigService")
public class CauseConfigServiceImpl extends BaseService<CauseConfig,Long> implements CauseConfigService {

    @Autowired
    private CauseConfigDao causeConfigDao;

    @Override
    public IBaseDao<CauseConfig, Long> getBaseDao() {
        return causeConfigDao;
    }
}
