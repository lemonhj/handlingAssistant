package com.septinary.common.core.util;

import java.util.List;

/**
 * 加载接口
 * @Filename: com.septinary.common.core.util.ILoadable.java of the project [com.septinary.common]
 *     @Type: ILoadable
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年7月5日 下午8:49:22
 */
public interface ILoadable<Return, Param> {
	
	@SuppressWarnings("unchecked")
	public List<Return> load(Param... params);
}
