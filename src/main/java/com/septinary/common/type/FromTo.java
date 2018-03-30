package com.septinary.common.type;

/**
 * 来源-去向对应类
 * @Filename: com.septinary.common.type.FromTo.java of the project [com.septinary.common]
 *     @Type: FromTo
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年8月19日 上午11:33:53
 *  @param <FromType>
 *  @param <ToType>
 */
public class FromTo<FromType, ToType> implements IFromTo<FromType, ToType> {
	private FromType from;
	
	private ToType to;

	@Override
	public FromType getFrom() {
		// TODO Auto-generated method stub
		return from;
	}

	@Override
	public FromTo<FromType, ToType> setFrom(FromType from) {
		// TODO Auto-generated method stub
		this.from = from;
		return this;
	}

	@Override
	public ToType getTo() {
		// TODO Auto-generated method stub
		return to;
	}

	@Override
	public FromTo<FromType, ToType> setTo(ToType to) {
		// TODO Auto-generated method stub
		this.to = to;
		return this;
	}

}
