package com.septinary.common.type.basic;

/**
 * 通用图像接口
 * @Filename: com.septinary.common.type.IImageable.java of the project [com.septinary.common]
 *     @Type: IImageable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日上午11:32:31
 * 
 * @param <URI>
 */
public interface IImageable<URI> extends IThumbable<URI>, IPictureable<URI> {

	//原图
	public URI getImg();
	
	public void setImg(URI img);
}
