package com.septinary.common.web.general.service.impl;

import org.springframework.stereotype.Service;

import com.septinary.common.type.VoidSerialization;
import com.septinary.common.web.basic.dao.IBaseDao;
import com.septinary.common.web.basic.service.BaseService;
import com.septinary.common.web.general.service.ApplicationService;

@Service("applicationService")
public class ApplicationServiceImpl extends BaseService<Void,VoidSerialization> implements ApplicationService {

	@Override
	public IBaseDao<Void, VoidSerialization> getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
