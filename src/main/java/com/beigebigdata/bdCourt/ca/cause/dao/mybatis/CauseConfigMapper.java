package com.beigebigdata.bdCourt.ca.cause.dao.mybatis;

import com.beigebigdata.bdCourt.ca.cause.dao.CauseConfigDao;
import com.beigebigdata.bdCourt.ca.cause.entity.CauseConfig;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository("causeConfigDao")
public interface CauseConfigMapper extends CauseConfigDao, Mapper<CauseConfig> {
}