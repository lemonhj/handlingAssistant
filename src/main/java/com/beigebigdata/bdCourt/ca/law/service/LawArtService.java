package com.beigebigdata.bdCourt.ca.law.service;

import com.beigebigdata.bdCourt.ca.law.entity.LawArt;
import com.septinary.common.web.basic.service.IBaseService;

import java.util.List;

public interface LawArtService extends IBaseService<LawArt,Long> {
	
	/**
	 * 查询法律条文-根据法律编号
	 * @param lawNo
	 * @param catalogNo
	 * @return
	 */
	List<LawArt> queryLawArtsByLawNo(Integer lawNo, Integer catalogNo);


}
