package com.beigebigdata.bdCourt.ca.common.dao;

import com.beigebigdata.bdCourt.ca.common.entity.Config;
import com.septinary.common.type.Configuration;
import com.septinary.common.web.basic.dao.IBaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConfigDao extends IBaseDao<Config,Long> {

	public List<Configuration> loadConfigItemsByPath(@Param("indicated") Boolean indicated, @Param("path") String path);
}
