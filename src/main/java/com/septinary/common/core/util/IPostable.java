package com.septinary.common.core.util;

import java.util.List;

/**
 * 提交接口
 * @Filename: com.septinary.common.core.util.IPostable.java of the project [com.septinary.common]
 *     @Type: IPostable
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年7月8日 下午6:01:21
 *  @param <Return>
 *  @param <Param>
 */
public interface IPostable<Return, Param> {

	public Return post(List<Param> params);
}
