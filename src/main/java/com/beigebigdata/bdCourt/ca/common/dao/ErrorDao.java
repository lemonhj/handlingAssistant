package com.beigebigdata.bdCourt.ca.common.dao;

import com.septinary.common.type.IFieldValue;
import com.septinary.common.type.Result;
import com.septinary.common.web.basic.dao.IBaseDao;

import java.util.List;

public interface ErrorDao extends IBaseDao<Error,Long> {

	public List<Result> loadReturnCodesByType(IFieldValue type);
}
