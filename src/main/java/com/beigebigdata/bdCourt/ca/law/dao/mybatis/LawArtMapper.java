package com.beigebigdata.bdCourt.ca.law.dao.mybatis;

import com.beigebigdata.bdCourt.ca.law.dao.LawArtDao;
import com.beigebigdata.bdCourt.ca.law.entity.LawArt;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository("lawArtDao")
public interface LawArtMapper extends LawArtDao, Mapper<LawArt> {
	
}