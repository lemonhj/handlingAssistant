package com.septinary.common.core.type;

/**
 * 配置读取优先级
 * @Filename: com.septinary.common.core.type.ConfigPriorityLevel.java of the project [com.septinary.common]
 *     @Type: ConfigPriorityLevel
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日下午9:34:13
 *
 */
public enum ConfigPriorityLevel {
	UNDEFINED,		//未定义的配置优先级
	CONST,			//从const常量读取
	DB,				//从db数据读取
	XML,			//从xml配置文件读取
	PROPERTIES,		//从properties配置文件读取
	ENV				//从env变量读取
}
