package com.septinary.common.util.smart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 只能HashMap类
 * @Filename: com.septinary.common.util.HashMapSmart.java of the project [com.septinary.common]
 * @Type: HashMapSmart
 * @Desc: TODO
 * @Author: macbook[weide<weide001@gmail.com>]
 * @Created: 2016-03-04 13:29:00
 */
public class HashMapSmart<K,T> extends HashMap<K,T> {
    private static final long serialVersionUID = -8748605197089953268L;

    public HashMapSmart() {
        super();
    }

    public HashMapSmart(int capacity) {
        super(capacity);
    }

    public HashMapSmart(int capacity, float loadFactor) {
        super(capacity, loadFactor);
    }

    public HashMapSmart(Map<? extends K, ? extends T> map) {
        super(map);
    }

    /**
     * 线性put
     * @param key
     * @param value
     * @return
     */
    public HashMapSmart<K,T> add(K key, T value) {
        super.put(key, value);
        return this;
    }

    /**
     * 线性putAll
     * @param map
     * @return
     */
    public HashMapSmart<K,T> addAll(Map<? extends K, ? extends T> map) {
        super.putAll(map);
        return this;
    }

    /**
     * 线性remove
     * @param key
     * @return
     */
    public HashMapSmart<K,T> rmv(Object key) {
        super.remove(key);
        return this;
    }

    /**
     * 线性clear
     * @return
     */
    public HashMapSmart<K,T> clr() {
        super.clear();
        return this;
    }

    /**
     * 线性sort
     * @param comparator
     * @return
     */
    public HashMapSmart<K,T> sort(Comparator<Entry<K, T>> comparator) {
        if (null==comparator) comparator = new Comparator<Entry<K,T>>() {
            public int compare(Entry<K,T> l, Entry<K,T> r) {
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
        List<Entry<K,T>> list = new ArrayList<Entry<K,T>>(this.entrySet());
        Collections.sort(list, comparator);
        this.clr();
        for (Entry<K,T> item: list) this.put(item.getKey(),item.getValue());
        return this;
    }
    public HashMap<K,T> sort() {
        return this.sort(null);
    }
}
