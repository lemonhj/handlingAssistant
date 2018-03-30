package com.septinary.common.core.util;

import com.septinary.common.type.Result;

/**
 * 错误代码读取接口
 * @Filename: com.septinary.common.core.util.IErroringable.java of the project [com.septinary.common]
 *     @Type: IErroringable
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年7月15日 下午6:17:03
 */
public interface IErroringable {
	
	public Result error(String errorcode);

}
