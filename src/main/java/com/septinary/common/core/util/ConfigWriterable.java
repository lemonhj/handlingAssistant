package com.septinary.common.core.util;

import com.septinary.common.manager.ConfiggerManager;

/**
 * 配置写入适配器
 * @Filename: com.septinary.common.core.util.ConfigWriterable.java of the project [com.septinary.common]
 *     @Type: ConfigWriterable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日下午9:38:27
 *
 */
public abstract class ConfigWriterable {
    protected IConfigSetter configger = null;

    public ConfigWriterable() {}

    //懒惰加载
    protected IConfigSetter getConfigger() {
    	if(null==this.configger) {
    		System.out.println("Getting configWriter from sigleton:ConfiggerManager ...");
            this.configger = ConfiggerManager.Instance().getWriter();
    	}
    	return this.configger;
    }
    
    //写入适配
    public ConfigWriterable set(String key, ConfigItem value) {
        this.getConfigger().set(key, value);
        return this;
    }

    //----------------------------写入-----------------------------
    //友好写入
    // TODO ...
}
