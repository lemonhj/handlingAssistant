package com.septinary.common.type;

/**
 * 来源-去向对应接口
 * @Filename: com.septinary.common.type.IFromTo.java of the project [com.septinary.common]
 *     @Type: IFromTo
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年8月19日 上午11:30:13
 *  @param <FromType>
 *  @param <ToType>
 */
public interface IFromTo<FromType, ToType> {
	public FromType getFrom();
	
	public IFromTo<FromType,ToType> setFrom(FromType from);
	
	public ToType getTo();
	
	public IFromTo<FromType,ToType> setTo(ToType to);
}
