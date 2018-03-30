package com.beigebigdata.bdCourt.ca.cause.dao.mybatis;


import com.beigebigdata.bdCourt.ca.cause.dao.CauseFctrQueryConfDao;
import com.beigebigdata.bdCourt.ca.cause.entity.CauseFctrQueryConf;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository("causeFctrQueryConfDao")
public interface CauseFctrQueryConfMapper extends CauseFctrQueryConfDao, Mapper<CauseFctrQueryConf> {
}