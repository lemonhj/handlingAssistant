package com.septinary.common.core.util;

import java.util.List;

import com.septinary.common.core.type.ConfigPriorityLevel;
import com.septinary.common.type.Mark;
import com.septinary.common.type.Value;

/**
 * 从环境变量中读取配置
 * @Filename: com.septinary.common.core.util.ConfigEnvironmentVariable.java of the project [com.septinary.common]
 *     @Type: ConfigEnvironmentVariable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月15日下午1:45:19
 *
 */
public class ConfigFromEnvironmentVariable extends ConfigFrom {
	
	public ConfigFromEnvironmentVariable(){
		super(ConfigPriorityLevel.ENV);
	}
	
	public ConfigFromEnvironmentVariable(List<Mark<Boolean,String>> pathes){
		super(ConfigPriorityLevel.ENV, pathes);
	}

	@Override
	public Value<Object> get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
