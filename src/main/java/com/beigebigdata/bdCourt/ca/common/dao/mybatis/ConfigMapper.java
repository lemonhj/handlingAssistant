package com.beigebigdata.bdCourt.ca.common.dao.mybatis;

import com.beigebigdata.bdCourt.ca.common.dao.ConfigDao;
import com.beigebigdata.bdCourt.ca.common.entity.Config;
import com.septinary.common.web.general.dao.mybatis.CommonMapperMySQL;
import org.springframework.stereotype.Repository;

@Repository("configDao")
public interface ConfigMapper extends ConfigDao, CommonMapperMySQL<Config> {

}
