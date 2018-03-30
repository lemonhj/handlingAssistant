package com.beigebigdata.bdCourt.ca.law.dao.mybatis;

import com.beigebigdata.bdCourt.ca.law.dao.LawAlterContDao;
import com.beigebigdata.bdCourt.ca.law.entity.LawAlterCont;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository("lawAlterContDao")
public interface LawAlterContMapper extends LawAlterContDao, Mapper<LawAlterCont> {
	
}