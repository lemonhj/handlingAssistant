package com.septinary.common.web.basic.business;

import com.septinary.common.core.util.Fielderable;
import com.septinary.common.web.basic.bean.LogicWebBean;

/**
 * 业务层 Bean 抽象基类
 * @Filename: com.septinary.common.web.basic.business.Business.java of the project [com.septinary.common.web]
 *     @Type: Business
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月12日下午7:15:09
 *
 */
public abstract class Business extends LogicWebBean implements IBusiness {

	//字段定义配置器
	static protected Fielderable fielder = Fielderable.GetFielder();
}
