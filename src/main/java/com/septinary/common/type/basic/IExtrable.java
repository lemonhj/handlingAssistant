package com.septinary.common.type.basic;

import com.septinary.common.util.IProcessable;

/**
 * 额外信息接口
 * @Filename: com.septinary.common.type.IExtrable.java of the project [com.septinary.common]
 *     @Type: IExtrable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日上午12:41:36
 *
 */
public interface IExtrable {

	public String getExtra();
	
	public void setExtra(String extra);
	
	public <T> T getExtra(IProcessable process);
}
