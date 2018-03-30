package com.beigebigdata.bdCourt.ca.common.service.impl;

import com.beigebigdata.bdCourt.ca.common.dao.ConfigDao;
import com.beigebigdata.bdCourt.ca.common.entity.Config;
import com.beigebigdata.bdCourt.ca.common.service.ConfigService;
import com.septinary.common.core.util.ILoadable;
import com.septinary.common.type.Configuration;
import com.septinary.common.type.Mark;
import com.septinary.common.util.Assert;
import com.septinary.common.web.basic.dao.IBaseDao;
import com.septinary.common.web.basic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("configService")
public class ConfigServiceImpl extends BaseService<Config, Long> implements ConfigService, ILoadable<Configuration, Mark<Boolean,String>> {
	
	@Autowired
	private ConfigDao configDao;

	@Override
	public IBaseDao<Config, Long> getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(String key, Config value) {
		// TODO Auto-generated method stub
	}

	@Override
	public void rmv(String key) {
		// TODO Auto-generated method stub
	}

	@Override
	public Config read(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Configuration> load(Mark<Boolean,String>... params) {
		// TODO Auto-generated method stub
		// return null;
		
		Assert.NotEmpty(params, "必须指定加载配置的路径范围！");
		
		return this.configDao.loadConfigItemsByPath(params[0].getMark(), params[0].getValue());
	}

}
