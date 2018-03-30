package com.septinary.common.util;

/**
 * 获取
 * @Filename: com.septinary.common.system.util.IGetter.java of the project [com.septinary.common]
 * @Type: IGetter
 * @Desc: TODO
 * @Author: macbook[weide<weide001@gmail.com>]
 * @Created: 2016-03-03 13:33:00
 */
public interface IGetter<K,T> {
    public T get(K key);
}
