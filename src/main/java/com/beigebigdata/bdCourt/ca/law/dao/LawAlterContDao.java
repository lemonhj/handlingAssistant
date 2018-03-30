package com.beigebigdata.bdCourt.ca.law.dao;

import com.beigebigdata.bdCourt.ca.law.entity.LawAlterCont;
import com.septinary.common.web.basic.dao.IBaseDao;

import java.util.List;

public interface LawAlterContDao extends IBaseDao<LawAlterCont,Long> {

	/**
	 * 查询法律典籍目录结构-根据法律编号
	 * @param lawNo
	 * @return
	 */
	public List<LawAlterCont> queryLawAlterContByLawNo(Integer lawNo);
}
