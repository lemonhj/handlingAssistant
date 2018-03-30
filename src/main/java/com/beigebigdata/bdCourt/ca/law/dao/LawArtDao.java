package com.beigebigdata.bdCourt.ca.law.dao;

import com.beigebigdata.bdCourt.ca.law.entity.LawArt;
import com.septinary.common.web.basic.dao.IBaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LawArtDao extends IBaseDao<LawArt,Long> {

	/**
	 * 查询法律条文-根据法律编号
	 * @param lawNo
	 * @return
	 */
	public List<LawArt> queryLawArtsByLawNo(@Param("lawNo") Integer lawNo, @Param("catalogNo") Integer catalogNo);
}
