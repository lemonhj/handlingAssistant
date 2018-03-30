package com.septinary.common.type.basic;

/**
 * 通用图标接口
 * @Filename: com.septinary.common.type.IIconable.java of the project [com.septinary.common]
 *     @Type: IIconable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日上午11:23:47
 * 
 * @param <URI>
 */
public interface IIconable<URI> {

	//图标
	public URI getIcon();
	
	public void setIcon(URI icon);
}
