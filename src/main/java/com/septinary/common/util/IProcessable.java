package com.septinary.common.util;

/**
 * 处理接口
 * @Filename: com.septinary.common.util.IProcessable.java of the project [com.septinary.common]
 *     @Type: IProcessable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日上午12:41:00
 *
 */
public interface IProcessable {

	public <T> T process(Object first, Object... arguments);
	
}
