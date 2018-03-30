package com.septinary.common.type;

/**
 * 名称-取值对
 * @Filename: com.septinary.common.type.IPairNameValueable.java of the project [com.septinary.common]
 *     @Type: IPairNameValueable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日下午9:51:47
 * 
 * @param <N>
 * @param <V>
 */
public interface IPairNameValueable<N,V> {

	public N getName();

	public void setKey(N name);

	public V getValue();

	public void setValue(V value);
}
