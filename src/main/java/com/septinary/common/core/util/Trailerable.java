package com.septinary.common.core.util;

import com.septinary.common.manager.TrailerManager;
import com.septinary.common.sys.op.Action;
import com.septinary.common.util.Assert;

/**
 * 跟踪操作适配器
 * @Filename: com.septinary.common.core.util.Trailerable.java of the project [com.septinary.common]
 *     @Type: Trailerable
 *     @Desc: TODO
 *   @Author: boofee's macbook pro 13[Administrator<weide001@gmail.com>]
 *  @Created: 2016年5月30日 下午5:54:04
 */
abstract public class Trailerable {

	private ITrailingable trailer = null;
	
	public Trailerable(Object holder) {
		Assert.NotNull(holder, "The holder of the trailer must not be null!");
    	
        System.out.println("Getting trailer from sigleton:TrailerManager for '"+holder.getClass().getName()+"' ...");
        this.trailer = TrailerManager.Instance().getTrailer(holder);
	}
	
	//跟踪轨迹（追加方式）
	public void trail(Action<String> action, ITrail trail) {
		this.trailer.trail(action, trail);
	}
}
