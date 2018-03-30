package com.septinary.common.core.some;

import com.septinary.common.type.basic.IExtrable;

/**
 * 某个行为动作
 * @Filename: com.septinary.common.core.some.ISomeactionable.java of the project [com.septinary.common]
 *     @Type: ISomeactionable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月23日下午10:31:10
 *
 */
public interface ISomeactionable<PK> extends ISomeable<PK>, IExtrable {
	
	//标志
	public String getTag();
	public void setTag(String tag);
	
	//图标
	public String getIcon();
	public void setIcon(String icon);
	
}
