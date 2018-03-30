package com.septinary.common.core.util;

import com.septinary.common.manager.ConfiggerManager;
import com.septinary.common.type.Value;

/**
 * 配置读取写入适配器
 * @Filename: com.septinary.common.core.util.Configerable.java of the project [com.septinary.common]
 * @Type: Configgerable
 * @Desc: TODO
 * @Author: macbook[weide<weide001@gmail.com>]
 * @Created: 2016-03-03 13:45:00
 */
abstract public class Configgerable {
    protected IConfiggingable configger = null;

    public Configgerable() {}

    //懒惰加载
    protected IConfiggingable getConfigger() {
    	if(null==this.configger) {
            System.out.println("Getting configger from sigleton:ConfiggerManager ...");
            this.configger = ConfiggerManager.Instance().getConfigger();
    	}
    	return this.configger;
    }

    //读取、写入适配
    public String get(String key) {
    	Value<Object> value = this.getConfigger().get(key);
    	if( byte[].class.equals(value.getType()) ) return null;
        return value.getValue().toString();
    }
    public byte[] getBytes(String key) {
    	Value<Object> value = this.getConfigger().get(key);
    	if( byte[].class.equals(value.getType()) ) return (byte[])value.getValue();
        return null;
    }
    public Configgerable set(String key, ConfigItem value) {
        this.getConfigger().set(key, value);
        return this;
    }
    
    //----------------------------读取-----------------------------
    //友好读取
    // TODO ...
    
    //----------------------------写入-----------------------------
    //友好写入
    // TODO ...
}
