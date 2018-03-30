package com.septinary.common.type.basic;

/**
 * 大图接口
 * @Filename: com.septinary.common.type.IPictureable.java of the project [com.septinary.common]
 *     @Type: IPictureable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年5月19日上午11:47:52
 * 
 * @param <URI>
 */
public interface IPictureable<URI> {
	
	//大图
	public URI getPicture();
	
	public void setPicture(URI picture);

}
