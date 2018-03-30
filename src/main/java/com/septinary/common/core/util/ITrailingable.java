package com.septinary.common.core.util;

import com.septinary.common.sys.op.Action;

/**
 * 跟踪操作接口
 * @Filename: com.septinary.common.core.util.ITrailingable.java of the project [com.septinary.common]
 *     @Type: ITrailingable
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年5月30日 下午6:01:13
 */
public interface ITrailingable {

	public void trail(Action<String> action, ITrail trail);
}
