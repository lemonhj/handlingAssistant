package com.septinary.common.type.basic;

/**
 * 通用排序接口
 * @Filename: com.septinary.common.type.IOrderable.java of the project [com.septinary.common]
 *     @Type: IOrderable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月22日下午11:30:19
 * 
 * @param <O>
 */
public interface IOrderable<O extends Comparable<?>> {

	public O getOrder();
	
	public void setOrder(O order);
}
