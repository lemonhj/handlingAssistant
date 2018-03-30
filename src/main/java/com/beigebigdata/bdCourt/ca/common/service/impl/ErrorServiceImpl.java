package com.beigebigdata.bdCourt.ca.common.service.impl;

import com.beigebigdata.bdCourt.ca.common.dao.ErrorDao;
import com.beigebigdata.bdCourt.ca.common.service.ErrorService;
import com.septinary.common.core.util.IErrorGetter;
import com.septinary.common.type.IFieldValue;
import com.septinary.common.type.Result;
import com.septinary.common.web.basic.dao.IBaseDao;
import com.septinary.common.web.basic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("errorService")
public class ErrorServiceImpl extends BaseService<Error, Long> implements ErrorService, IErrorGetter {
	
	@Autowired
	private ErrorDao errorDao = null;

	@Override
	public IBaseDao<Error, Long> getBaseDao() {
		// TODO Auto-generated method stub
		return this.errorDao;
	}

	@Override
	public List<Result> get(IFieldValue key) {
		// TODO Auto-generated method stub
		return this.errorDao.loadReturnCodesByType(key);
	}

}
