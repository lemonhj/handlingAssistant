package com.septinary.common.web.general.runtime.config;

import java.util.List;

import com.septinary.common.core.type.ConfigPriorityLevel;
import com.septinary.common.core.util.ConfigFrom;
import com.septinary.common.type.Mark;
import com.septinary.common.type.Value;

/**
 * 从properties配置文件读取配置
 * @Filename: com.septinary.common.core.util.ConfigProperties.java of the project [com.septinary.common]
 *     @Type: ConfigProperties
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月15日下午1:47:16
 *
 */
public class ConfigFromProperties extends ConfigFrom {

	public ConfigFromProperties(){
		super(ConfigPriorityLevel.PROPERTIES);
	}
	
	public ConfigFromProperties(List<Mark<Boolean,String>> pathes){
		super(ConfigPriorityLevel.PROPERTIES, pathes);
	}
	
	@Override
	public Value<Object> get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
