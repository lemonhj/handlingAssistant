package com.septinary.common.type.basic;

/**
 * 通用类型接口
 * @Filename: com.septinary.common.type.ITypeable.java of the project [com.septinary.common]
 *     @Type: ITypeable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日上午11:35:02
 * 
 * @param <Type>
 */
public interface ITypeable<Type> {

	public Type getType();
	
	public void setType(Type type);
}
