package com.septinary.common.type.basic;

import com.septinary.common.util.IProcessable;

/**
 * 通用缩略图接口
 * @Filename: com.septinary.common.type.IThumbable.java of the project [com.septinary.common]
 *     @Type: IThumbable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日上午11:25:03
 * 
 * @param <URI>
 */
public interface IThumbable<URI> {

	//缩略图
	public URI getThumb();
	
	public URI getThumb(IProcessable process);
	
	public void setThumb(URI thumb);
}
