package com.septinary.common.util;

import java.util.List;
import java.util.Map;

/**
 * 
 * @Filename: com.septinary.common.util.MultiValueMap.java of the project [com.septinary.common]
 *     @Type: MultiValueMap
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月12日下午4:41:16
 * 
 * @param <K>
 * @param <V>
 */
public interface MultiValueMap<K, V> extends Map<K, List<V>> {

	/**
	 * Return the first value for the given key.
	 * @param key the key
	 * @return the first value for the specified key, or {@code null}
	 */
	V getFirst(K key);

	/**
	 * Add the given single value to the current list of values for the given key.
	 * @param key the key
	 * @param value the value to be added
	 */
	void add(K key, V value);

	/**
	 * Set the given single value under the given key.
	 * @param key the key
	 * @param value the value to set
	 */
	void set(K key, V value);

	/**
	 * Set the given values under.
	 * @param values the values.
	 */
	void setAll(Map<K, V> values);

	/**
	 * Returns the first values contained in this {@code MultiValueMap}.
	 * @return a single value representation of this map
	 */
	Map<K, V> toSingleValueMap();

}
