package com.beigebigdata.bdCourt.ca.law.service.impl;


import com.beigebigdata.bdCourt.ca.law.dao.LawArtDao;
import com.beigebigdata.bdCourt.ca.law.entity.LawArt;
import com.beigebigdata.bdCourt.ca.law.service.LawArtService;
import com.septinary.common.web.basic.dao.IBaseDao;
import com.septinary.common.web.basic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("lawArtService")
public class LawArtServiceImpl extends BaseService<LawArt,Long> implements LawArtService {

    @Autowired
    private LawArtDao lawArtDao;
    
	@Override
	public IBaseDao<LawArt, Long> getBaseDao() {
		// TODO Auto-generated method stub
		return this.lawArtDao;
	}

	@Override
	public List<LawArt> queryLawArtsByLawNo(Integer lawNo, Integer catalogNo) {
		
		return this.lawArtDao.queryLawArtsByLawNo(lawNo, catalogNo);
	}

}
