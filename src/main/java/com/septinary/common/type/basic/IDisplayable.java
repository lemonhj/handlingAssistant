package com.septinary.common.type.basic;

import com.septinary.common.type.IOnClickable;

/**
 * 展示接口
 * @Filename: com.septinary.common.type.IListDisplayable.java of the project [com.septinary.common]
 *     @Type: IListDisplayable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日下午9:58:07
 * 
 * @param <URI>
 * @param <Type>
 */
public interface IDisplayable<URI, Type> extends IImageable<URI>, ITypeable<Type>, IOnClickable {

}
