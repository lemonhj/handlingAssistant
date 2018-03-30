package com.septinary.common.util;

import com.septinary.common.type.Result;

/**
 * 响应接口
 * @Filename: com.septinary.common.util.IOnable.java of the project [com.septinary.common]
 *     @Type: IOnable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年5月19日下午2:07:21
 * 
 * @param <TAG>
 */
public interface IOnable<TAG> {

	public Result on(TAG tag, Object... arguments);
}
