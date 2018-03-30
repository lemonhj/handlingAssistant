package com.septinary.common.web.basic.bean;

import com.septinary.common.core.util.ConfigReaderable;
import com.septinary.common.core.util.Loggerable;

/**
 * Logic Bean 抽象基类
 * @Filename: com.septinary.common.web.basic.bean.LogicWebBean.java of the project [com.septinary.common.web]
 *     @Type: LogicBean
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月28日下午10:42:04
 *
 */
abstract public class LogicWebBean extends WebBean implements ILogicWebBean {

	//日志输出器
	protected Loggerable logger = new Loggerable(this){};
	
	//配置读取器
	static protected ConfigReaderable configger = ConfigReaderable.GetReader();

}
