package com.septinary.common.core.util;

import java.util.List;

import com.septinary.common.core.type.ConfigPriorityLevel;
import com.septinary.common.type.Mark;
import com.septinary.common.type.Value;
import com.septinary.common.util.IGetter;

/**
 * 配置来源抽象基类
 * @Filename: com.septinary.common.core.util.ConfigFrom.java of the project [com.septinary.common]
 *     @Type: ConfigFrom
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月25日上午11:43:15
 *
 */
abstract public class ConfigFrom implements IConfigFrom, IGetter<String,Value<Object>> {

	protected ILoggingable logger = Loggerable.GetLogger(this.getClass());
	
	private ConfigPriorityLevel configLevel = null;

	private List<Mark<Boolean,String>> pathes = null;

	public ConfigFrom() {
		this.setConfigLevel(ConfigPriorityLevel.UNDEFINED);
	}

	public ConfigFrom(ConfigPriorityLevel configLevel) {
		this();
		this.setConfigLevel(configLevel);
	}
	
	public ConfigFrom(ConfigPriorityLevel configLevel, List<Mark<Boolean,String>> pathes) {
		this(configLevel);
		this.setPathes(pathes);
	}
	
	public ConfigPriorityLevel getConfigLevel() {
		return configLevel;
	}

	public void setConfigLevel(ConfigPriorityLevel configLevel) {
		this.configLevel = configLevel;
	}

	public List<Mark<Boolean,String>> getPathes() {
		return pathes;
	}

	public void setPathes(List<Mark<Boolean,String>> pathes) {
		this.pathes = pathes;
	}
	
}
