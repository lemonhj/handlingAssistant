package com.beigebigdata.bdCourt.ca.common.service;

import com.beigebigdata.bdCourt.ca.common.entity.Config;
import com.septinary.common.web.basic.service.IBaseService;

public interface ConfigService extends IBaseService<Config, Long> {

	void save(String key, Config value);
	
	void rmv(String key);

	Config read(String key);
}
