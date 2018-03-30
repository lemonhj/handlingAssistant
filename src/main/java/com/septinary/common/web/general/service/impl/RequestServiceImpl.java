package com.septinary.common.web.general.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.septinary.common.type.VoidSerialization;
import com.septinary.common.util.Assert;
import com.septinary.common.web.basic.dao.IBaseDao;
import com.septinary.common.web.basic.service.BaseService;
import com.septinary.common.web.general.runtime.trace.Current;
import com.septinary.common.web.general.service.RequestService;

@Service("requestService")
public class RequestServiceImpl extends BaseService<Void,VoidSerialization> implements RequestService {

	@Override
	public IBaseDao<Void, VoidSerialization> getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Current current(HttpServletRequest request) {
		
		Assert.NotNull(request, "HttpServletRequest request must not be NULL!");
		
		Current current = (Current)request.getAttribute(configger.getString("TRACE_CURRENT_INFO"));
		
		return current;
	}

}
