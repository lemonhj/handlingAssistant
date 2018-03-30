package com.septinary.common.type;

/**
 * 键值对
 * @Filename: com.septinary.common.type.IPairable.java of the project [com.septinary.common]
 *     @Type: IPairable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日下午9:49:40
 * 
 * @param <K>
 * @param <V>
 */
public interface IPairable<K,V> {

	public K getKey();

	public void setKey(K key);

	public V getValue();

	public void setValue(V value);
}
