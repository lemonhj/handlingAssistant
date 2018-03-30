package com.septinary.common.util;

/**
 * 回调接口
 * @Filename: com.septinary.common.util.ICallbackable.java of the project [com.septinary.common]
 *     @Type: ICallbackable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月25日下午3:28:13
 *
 */
public interface ICallbackable {

	public Object callback(Object first, Object... arguments);
	
}
