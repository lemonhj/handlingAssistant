package com.septinary.common.util.smart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LinkedHashMapSmart<K,T> extends LinkedHashMap<K,T> {
    private static final long serialVersionUID = 0L;
	

    public LinkedHashMapSmart() {
        super();
    }

    public LinkedHashMapSmart(int capacity) {
        super(capacity);
    }

    public LinkedHashMapSmart(int capacity, float loadFactor) {
        super(capacity, loadFactor);
    }

    public LinkedHashMapSmart(Map<? extends K, ? extends T> map) {
        super(map);
    }

    /**
     * 线性put
     * @param key
     * @param value
     * @return
     */
    public LinkedHashMapSmart<K,T> add(K key, T value) {
        super.put(key, value);
        return this;
    }

    /**
     * 线性putAll
     * @param map
     * @return
     */
    public LinkedHashMapSmart<K,T> addAll(Map<? extends K, ? extends T> map) {
        super.putAll(map);
        return this;
    }

    /**
     * 线性remove
     * @param key
     * @return
     */
    public LinkedHashMapSmart<K,T> rmv(Object key) {
        super.remove(key);
        return this;
    }

    /**
     * 线性clear
     * @return
     */
    public LinkedHashMapSmart<K,T> clr() {
        super.clear();
        return this;
    }

    /**
     * 线性sort
     * @param comparator
     * @return
     */
    public LinkedHashMapSmart<K,T> sort(Comparator<Map.Entry<K, T>> comparator) {
        if (null==comparator) comparator = new Comparator<Map.Entry<K,T>>() {
            public int compare(Map.Entry<K,T> l, Map.Entry<K,T> r) {
                int result = (l.getKey()).toString().compareTo(r.getKey().toString());
                if (0==result) {
                    result = l.getValue().toString().compareTo(r.getValue().toString());
                }
                return result;
            }
        };
        //List与Set互转：
        //List list = new ArrayList(set);
        //Set set = new HashSet(list);
        List<Map.Entry<K,T>> list = new ArrayList<Map.Entry<K,T>>(this.entrySet());
        Collections.sort(list, comparator);
        this.clr();
        for (Map.Entry<K,T> item: list) this.put(item.getKey(),item.getValue());
        return this;
    }
    public LinkedHashMapSmart<K,T> sort() {
        return this.sort(null);
    }
}
