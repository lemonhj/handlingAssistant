package com.septinary.common.type;

/**
 * 键值对
 * @Filename: com.septinary.common.type.Pair.java of the project [com.septinary.common]
 *     @Type: Pair
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月22日下午3:29:53
 * 
 * @param <K>
 * @param <V>
 */
public class Pair<K,V> implements IPairable<K,V> {

	private K key;
	
	private V value;
	
	public Pair(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	public Pair(K key) {
		this(key, null);
	}
	
	public Pair() {
		this(null, null);
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
}
