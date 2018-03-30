package com.septinary.common.util;

/**
 * 设置
 * @Filename: com.septinary.common.system.util.ISetter.java of the project [com.septinary.common]
 * @Type: ISetter
 * @Desc: TODO
 * @Author: macbook[weide<weide001@gmail.com>]
 * @Created: 2016-03-03 13:37:00
 */
public interface ISetter<K,T> {
    public ISetter<K,T> set(K key, T value);
}
