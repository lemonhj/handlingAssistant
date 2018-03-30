package com.septinary.common.web.general.runtime.config;

import java.util.List;

import com.septinary.common.core.type.ConfigPriorityLevel;
import com.septinary.common.core.util.ConfigFrom;
import com.septinary.common.type.Mark;
import com.septinary.common.type.Value;

/**
 * 从xml配置文件读取配置
 * @Filename: com.septinary.common.core.util.ConfigXml.java of the project [com.septinary.common]
 *     @Type: ConfigWebXml
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月15日下午1:45:42
 *
 */
public class ConfigFromXml extends ConfigFrom {

	public ConfigFromXml(){
		super(ConfigPriorityLevel.XML);
	}
	
	public ConfigFromXml(List<Mark<Boolean,String>> pathes){
		super(ConfigPriorityLevel.XML, pathes);
	}
	
	@Override
	public Value<Object> get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
