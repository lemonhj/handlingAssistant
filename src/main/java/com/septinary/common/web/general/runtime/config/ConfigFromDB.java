package com.septinary.common.web.general.runtime.config;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.septinary.common.core.exception.ConfigError;
import com.septinary.common.core.type.ConfigPriorityLevel;
import com.septinary.common.core.util.ConfigFrom;
import com.septinary.common.core.util.ILoadable;
import com.septinary.common.type.Configuration;
import com.septinary.common.type.Mark;
import com.septinary.common.type.Value;
import com.septinary.common.util.Assert;

/**
 * 从数据库读取配置
 * @Filename: com.septinary.common.core.util.ConfigFromDB.java of the project [com.septinary.common]
 *     @Type: ConfigFromDB
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月25日上午11:42:52
 *
 */
public class ConfigFromDB extends ConfigFrom {
	private HashMap<String, Value<Object>> configItems = null;
	
	private ILoadable<Configuration, Mark<Boolean,String>> loader = null;
	
	public ConfigFromDB() {
		super(ConfigPriorityLevel.DB);
	}
	
	public ConfigFromDB(List<Mark<Boolean,String>> pathes) {
		super(ConfigPriorityLevel.DB,pathes);
	}

	@SuppressWarnings("unchecked")
	public ILoadable<Configuration, Mark<Boolean,String>> getLoader() {
		if(null==this.loader) {
			this.setLoader( (ILoadable<Configuration, Mark<Boolean,String>>)ContextLoader.getCurrentWebApplicationContext().getBean("configService") );
		}
		Assert.NotNull(this.loader, "Dose not found the bean: configService on the current context: " + ContextLoader.getCurrentWebApplicationContext().getApplicationName());
		return loader;
	}

	public void setLoader(ILoadable<Configuration, Mark<Boolean,String>> loader) {
		this.loader = loader;
	}

	@Override
	public Value<Object> get(String key) throws ConfigError {
		if( null==this.configItems ) this.configItems = this.load_config_items();
		
		if(!this.configItems.containsKey(key)) throw new ConfigError("Has not found the config item:[" + key + "], please check up the configuration!");
		
		return this.configItems.get(key);
	}
	
	@SuppressWarnings("unchecked")
	private HashMap<String, Value<Object>> load_config_items() {
		HashMap<String, Value<Object>> result = new HashMap<String, Value<Object>>();
		if( null==this.getLoader() ) return result;
		
		for(Mark<Boolean,String> path: this.getPathes()) {
			logger.trace("Loading the path: " + path.getValue() + " configurations as Mode: " + (path.getMark()?"inherited":"none-inherited") + " ...");
			List<Configuration> items = this.getLoader().load(path);
			for(Configuration config: items) {
				logger.trace(config.getKey() + "=" + (config.getHidden()?"***{hidden}***":config.getValue()) + (null==config.getMemo()?"":"\t# "+config.getMemo()));
				result.put(config.getKey(), new Value<Object>(config.getValue(), config.getDatatype()));
			}
		}
		
		return result;
	}
}
