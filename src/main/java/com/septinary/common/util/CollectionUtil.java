package com.septinary.common.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 集合操作
 * @Filename: com.septinary.common.util.CollectionUtil.java of the project [com.septinary.common]
 *     @Type: CollectionUtil
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月12日下午3:21:53
 *
 *	@see org.springframework.util.CollectionUtils
 */
abstract public class CollectionUtil {

	/**
	 * 是否为空？
	 @method CollectionUtil: IsEmpty()
	 @memo TODO
	 @param collection
	 @return boolean
	 */
	public static boolean IsEmpty(Collection<?> collection) {
		return (collection == null || collection.isEmpty());
	}

	/**
	 * 是否为空？
	 @method CollectionUtil: IsEmpty()
	 @memo TODO
	 @param map
	 @return boolean
	 */
	public static boolean IsEmpty(Map<?, ?> map) {
		return (map == null || map.isEmpty());
	}
	
	/**
	 * 对集合中每一个元素依次执行指定的回调方法
	 @method ObjectUtil: EachDo()
	 @memo TODO
	 @param collection
	 @param callback
	 @param abortReturnFilter 终止返回值条件
	 @return int 成功执行的次数
	 */
	static public int EachDo(Collection<?> collection, ICallbackable callback, Object abortReturnFilter) {
		if(null==collection || null==callback) return 0;
		
		int count = 0;
		for(Object item: collection) {
			Object ret = callback.callback(item);
			count ++;
			if(null!=abortReturnFilter && abortReturnFilter.equals(ret)) return count;
		}
		
		return count;
	}
	static public int EachDo(Collection<?> collection, ICallbackable callback) {
		return EachDo(collection, callback, null);
	}

	/**
	 * 数组转List
	 @method CollectionUtil: ArrayToList()
	 @memo TODO
	 @param source
	 @return List
	 */
	@SuppressWarnings("rawtypes")
	public static List ArrayToList(Object source) {
		return Arrays.asList(ObjectUtil.ToArray(source));
	}

	/**
	 * 合并数组进入集合
	 @method CollectionUtil: MergeArrayIntoCollection()
	 @memo TODO
	 @param array
	 @param collection void
	 */
	@SuppressWarnings("unchecked")
	public static <E> void MergeArrayIntoCollection(Object array, Collection<E> collection) {
		if (collection == null) {
			throw new IllegalArgumentException("Collection must not be null");
		}
		Object[] arr = ObjectUtil.ToArray(array);
		for (Object elem : arr) {
			collection.add((E) elem);
		}
	}

	/**
	 * 合并Properties进入Map
	 @method CollectionUtil: MergePropertiesIntoMap()
	 @memo TODO
	 @param props
	 @param map void
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> void MergePropertiesIntoMap(Properties props, Map<K, V> map) {
		if (map == null) {
			throw new IllegalArgumentException("Map must not be null");
		}
		if (props != null) {
			for (Enumeration<?> en = props.propertyNames(); en.hasMoreElements();) {
				String key = (String) en.nextElement();
				Object value = props.getProperty(key);
				if (value == null) {
					// Potentially a non-String value...
					value = props.get(key);
				}
				map.put((K) key, (V) value);
			}
		}
	}

	/**
	 * 迭代中是否包含指定元素？
	 @method CollectionUtil: Contains()
	 @memo TODO
	 @param iterator
	 @param element
	 @return boolean
	 */
	public static boolean Contains(Iterator<?> iterator, Object element) {
		if (iterator != null) {
			while (iterator.hasNext()) {
				Object candidate = iterator.next();
				if (ObjectUtil.EqualNullSafe(candidate, element)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 枚举中是否包含指定元素？
	 @method CollectionUtil: Contains()
	 @memo TODO
	 @param enumeration
	 @param element
	 @return boolean
	 */
	public static boolean Contains(Enumeration<?> enumeration, Object element) {
		if (enumeration != null) {
			while (enumeration.hasMoreElements()) {
				Object candidate = enumeration.nextElement();
				if (ObjectUtil.EqualNullSafe(candidate, element)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 集合中是否包含指定元素的实例？
	 @method CollectionUtil: ContainsInstance()
	 @memo TODO
	 @param collection
	 @param element
	 @return boolean
	 */
	public static boolean ContainsInstance(Collection<?> collection, Object element) {
		if (collection != null) {
			for (Object candidate : collection) {
				if (candidate == element) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 集合中是否包含指定集合中的某个元素？
	 @method CollectionUtil: ContainsAny()
	 @memo TODO
	 @param source
	 @param candidates
	 @return boolean
	 */
	public static boolean ContainsAny(Collection<?> source, Collection<?> candidates) {
		if (IsEmpty(source) || IsEmpty(candidates)) {
			return false;
		}
		for (Object candidate : candidates) {
			if (source.contains(candidate)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 查找集合中第一个出现在指定集合中的元素
	 @method CollectionUtil: FindFirst()
	 @memo TODO
	 @param source
	 @param candidates
	 @return E
	 */
	@SuppressWarnings("unchecked")
	public static <E> E FindFirst(Collection<?> source, Collection<E> candidates) {
		if (IsEmpty(source) || IsEmpty(candidates)) {
			return null;
		}
		for (Object candidate : candidates) {
			if (source.contains(candidate)) {
				return (E) candidate;
			}
		}
		return null;
	}

	/**
	 * 查找集合中是指定类型实例的第一个元素
	 @method CollectionUtil: FindValueOfType()
	 @memo TODO
	 @param collection
	 @param type
	 @return T
	 */
	@SuppressWarnings("unchecked")
	public static <T> T FindFirstValueOfType(Collection<?> collection, Class<T> type) {
		if (IsEmpty(collection) || null==type) {
			return null;
		}
		T value = null;
		for (Object element : collection) {
			if (type.isInstance(element)) {
				value = (T) element;
				break;
			}
		}
		return value;
	}

	/**
	 * 查找集合中是指定类型集合中的任一类型实例的第一个元素
	 @method CollectionUtil: FindValueOfType()
	 @memo TODO
	 @param collection
	 @param types
	 @return Object
	 */
	public static Object FindFirstValueOfType(Collection<?> collection, Class<?>[] types) {
		if (IsEmpty(collection) || ObjectUtil.IsEmpty(types)) {
			return null;
		}
		for (Class<?> type : types) {
			Object value = FindFirstValueOfType(collection, type);
			if (value != null) {
				return value;
			}
		}
		return null;
	}

	/**
	 * 判断集合中是否有且都是同一个对象？
	 @method CollectionUtil: HasUniqueObject()
	 @memo TODO
	 @param collection
	 @return boolean
	 */
	public static boolean HasUniqueObject(Collection<?> collection) {
		if (IsEmpty(collection)) {
			return false;
		}
		boolean hasCandidate = false;
		Object candidate = null;
		for (Object elem : collection) {
			if (!hasCandidate) {
				hasCandidate = true;
				candidate = elem;
			}
			else if (candidate != elem) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取集合中个元素的通用类型
	 @method CollectionUtil: FindCommonElementType()
	 @memo TODO
	 @param collection
	 @return Class<?>
	 */
	public static Class<?> FindCommonElementType(Collection<?> collection) {
		if (IsEmpty(collection)) {
			return null;
		}
		Class<?> candidate = null;
		for (Object val : collection) {
			if (val != null) {
				if (candidate == null) {
					candidate = val.getClass();
				}
				else if (candidate != val.getClass()) {
					return null;
				}
			}
		}
		return candidate;
	}

	/**
	 * 枚举转数组
	 @method CollectionUtil: ToArray()
	 @memo TODO
	 @param enumeration
	 @param array
	 @return A[]
	 */
	public static <A, E extends A> A[] ToArray(Enumeration<E> enumeration, A[] array) {
		ArrayList<A> elements = new ArrayList<A>();
		while (enumeration.hasMoreElements()) {
			elements.add(enumeration.nextElement());
		}
		return elements.toArray(array);
	}

	/**
	 * 枚举转迭代
	 @method CollectionUtil: ToIterator()
	 @memo TODO
	 @param enumeration
	 @return Iterator<E>
	 */
	public static <E> Iterator<E> ToIterator(Enumeration<E> enumeration) {
		return new EnumerationIterator<E>(enumeration);
	}

	/**
	 * Map转MultiValueMap
	 @method CollectionUtil: ToMultiValueMap()
	 @memo TODO
	 @param map
	 @return MultiValueMap<K,V>
	 */
	public static <K, V> MultiValueMap<K, V> ToMultiValueMap(Map<K, List<V>> map) {
		return new MultiValueMapAdapter<K, V>(map);
	}

	/**
	 * MultiValueMap转换UnmodifiableMultiValueMap
	 @method CollectionUtil: UnmodifiableMultiValueMap()
	 @memo TODO
	 @param map
	 @return MultiValueMap<K,V>
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> MultiValueMap<K, V> UnmodifiableMultiValueMap(MultiValueMap<? extends K, ? extends V> map) {
		Assert.NotNull(map, "'map' must not be null");
		Map<K, List<V>> result = new LinkedHashMap<K, List<V>>(map.size());
		for (Map.Entry<? extends K, ? extends List<? extends V>> entry : map.entrySet()) {
			List<? extends V> values = Collections.unmodifiableList(entry.getValue());
			result.put(entry.getKey(), (List<V>) values);
		}
		Map<K, List<V>> unmodifiableMap = Collections.unmodifiableMap(result);
		return ToMultiValueMap(unmodifiableMap);
	}


	/**
	 * Iterator wrapping an Enumeration.
	 */
	private static class EnumerationIterator<E> implements Iterator<E> {

		private final Enumeration<E> enumeration;

		public EnumerationIterator(Enumeration<E> enumeration) {
			this.enumeration = enumeration;
		}

		@Override
		public boolean hasNext() {
			return this.enumeration.hasMoreElements();
		}

		@Override
		public E next() {
			return this.enumeration.nextElement();
		}

		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Not supported");
		}
	}


	/**
	 * Adapts a Map to the MultiValueMap contract.
	 */
	@SuppressWarnings("serial")
	private static class MultiValueMapAdapter<K, V> implements MultiValueMap<K, V>, Serializable {

		private final Map<K, List<V>> map;

		public MultiValueMapAdapter(Map<K, List<V>> map) {
			Assert.NotNull(map, "'map' must not be null");
			this.map = map;
		}

		@Override
		public void add(K key, V value) {
			List<V> values = this.map.get(key);
			if (values == null) {
				values = new LinkedList<V>();
				this.map.put(key, values);
			}
			values.add(value);
		}

		@Override
		public V getFirst(K key) {
			List<V> values = this.map.get(key);
			return (values != null ? values.get(0) : null);
		}

		@Override
		public void set(K key, V value) {
			List<V> values = new LinkedList<V>();
			values.add(value);
			this.map.put(key, values);
		}

		@Override
		public void setAll(Map<K, V> values) {
			for (Entry<K, V> entry : values.entrySet()) {
				set(entry.getKey(), entry.getValue());
			}
		}

		@Override
		public Map<K, V> toSingleValueMap() {
			LinkedHashMap<K, V> singleValueMap = new LinkedHashMap<K,V>(this.map.size());
			for (Entry<K, List<V>> entry : map.entrySet()) {
				singleValueMap.put(entry.getKey(), entry.getValue().get(0));
			}
			return singleValueMap;
		}

		@Override
		public int size() {
			return this.map.size();
		}

		@Override
		public boolean isEmpty() {
			return this.map.isEmpty();
		}

		@Override
		public boolean containsKey(Object key) {
			return this.map.containsKey(key);
		}

		@Override
		public boolean containsValue(Object value) {
			return this.map.containsValue(value);
		}

		@Override
		public List<V> get(Object key) {
			return this.map.get(key);
		}

		@Override
		public List<V> put(K key, List<V> value) {
			return this.map.put(key, value);
		}

		@Override
		public List<V> remove(Object key) {
			return this.map.remove(key);
		}

		@Override
		public void putAll(Map<? extends K, ? extends List<V>> map) {
			this.map.putAll(map);
		}

		@Override
		public void clear() {
			this.map.clear();
		}

		@Override
		public Set<K> keySet() {
			return this.map.keySet();
		}

		@Override
		public Collection<List<V>> values() {
			return this.map.values();
		}

		@Override
		public Set<Entry<K, List<V>>> entrySet() {
			return this.map.entrySet();
		}

		@Override
		public boolean equals(Object other) {
			if (this == other) {
				return true;
			}
			return map.equals(other);
		}

		@Override
		public int hashCode() {
			return this.map.hashCode();
		}

		@Override
		public String toString() {
			return this.map.toString();
		}
	}
}
