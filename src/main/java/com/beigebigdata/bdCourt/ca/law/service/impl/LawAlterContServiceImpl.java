package com.beigebigdata.bdCourt.ca.law.service.impl;


import com.beigebigdata.bdCourt.ca.law.dao.LawAlterContDao;
import com.beigebigdata.bdCourt.ca.law.entity.LawAlterCont;
import com.beigebigdata.bdCourt.ca.law.service.LawAlterContService;
import com.septinary.common.web.basic.dao.IBaseDao;
import com.septinary.common.web.basic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("lawAlterContService")
public class LawAlterContServiceImpl extends BaseService<LawAlterCont,Long> implements LawAlterContService {
	
	@Autowired
	private LawAlterContDao lawAlterContDao;

	@Override
	public IBaseDao<LawAlterCont, Long> getBaseDao() {
		// TODO Auto-generated method stub
		return this.lawAlterContDao;
	}

	@Override
	public List<LawAlterCont> queryLawAlterContByLawNo(Integer lawNo) {
		return this.lawAlterContDao.queryLawAlterContByLawNo(lawNo);
	}

}
