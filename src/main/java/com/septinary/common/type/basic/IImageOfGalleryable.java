package com.septinary.common.type.basic;

/**
 * 图集图片接口
 * @Filename: com.septinary.common.type.ISomethingable.java of the project [com.septinary.common]
 *     @Type: IImageOfGallerieable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月26日下午4:57:46
 * 
 * @param <URI>
 * @param <O>
 */
public interface IImageOfGalleryable<PK, URI, O extends Comparable<?>> extends IIdable<PK>, IImageable<URI>, IOrderable<O> {

	public String getText();
	
	public void setText(String text);
}
