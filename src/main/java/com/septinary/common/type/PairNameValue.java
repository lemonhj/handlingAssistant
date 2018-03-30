package com.septinary.common.type;

/**
 * 名称-取值对
 * @Filename: com.septinary.common.type.PairNameValue.java of the project [com.septinary.common]
 *     @Type: PairNameValue
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日下午9:54:47
 * 
 * @param <N>
 * @param <V>
 */
public class PairNameValue<N,V> implements IPairNameValueable<N,V> {

	private N name;
	
	private V value;
	
	public PairNameValue(N name, V value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public PairNameValue(N key) {
		this(key, null);
	}
	
	public PairNameValue() {
		this(null, null);
	}

	public N getName() {
		return name;
	}

	public void setKey(N name) {
		this.name = name;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
}
