package com.beigebigdata.bdCourt.ca.law.service;

import com.beigebigdata.bdCourt.ca.law.entity.LawAlterCont;
import com.septinary.common.web.basic.service.IBaseService;

import java.util.List;

public interface LawAlterContService extends IBaseService<LawAlterCont,Long> {

	/**
	 * 查询法律典籍目录结构-根据法律编号
	 * @param lawNo
	 * @return
	 */
	public List<LawAlterCont> queryLawAlterContByLawNo(Integer lawNo);
}
