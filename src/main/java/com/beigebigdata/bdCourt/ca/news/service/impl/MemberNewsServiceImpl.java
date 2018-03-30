package com.beigebigdata.bdCourt.ca.news.service.impl;

import com.beigebigdata.bdCourt.ca.news.dao.MemberNewsDao;
import com.beigebigdata.bdCourt.ca.news.entity.MemberNews;
import com.beigebigdata.bdCourt.ca.news.service.MemberNewsService;
import com.septinary.common.web.basic.dao.IBaseDao;
import com.septinary.common.web.basic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: MemberNewsServiceImpl
 * @Description:
 * @date 17/6/26
 */
@Service("memberNewsService")
public class MemberNewsServiceImpl extends BaseService<MemberNews,Long> implements MemberNewsService {

    @Autowired
    private MemberNewsDao memberNewsDao;


    @Override
    public IBaseDao<MemberNews, Long> getBaseDao() {
        return memberNewsDao;
    }

}
