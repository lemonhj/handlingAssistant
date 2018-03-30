package com.septinary.common.core.util;

import java.util.HashMap;
import java.util.Locale;

import com.septinary.common.manager.ResourceMessagerManager;
import com.septinary.common.util.StringUtil;

/**
 * 资源消息读取适配器
 * @Filename: com.septinary.common.core.util.ResourceMessagerable.java of the project [com.septinary.common]
 *     @Type: ResourceMessagerable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月15日下午11:56:18
 *
 */
abstract public class ResourceMessagerable {
	private HashMap<Locale,HashMap<String,String>> resouces = new HashMap<Locale,HashMap<String,String>>();

	private IResourceMessagingable messager = null;
	
	public ResourceMessagerable() {

        System.out.println("Getting messager from sigleton:ResourceMessagerManager ...");
        this.messager = ResourceMessagerManager.Instance().getMessager();
	}
	
	//静态读取器
	private static ResourceMessagerable resourcemessager = new ResourceMessagerable(){};
	public static ResourceMessagerable GetMessager() {
		return resourcemessager;
	}
	
	//消息读取调用适配
    public String getMessage(String key, Locale locale) {
		//从缓存中读取:
		HashMap<String,String> messages = this.resouces.get(locale);
		if(null==messages) {
			messages = new HashMap<String,String>();
			this.resouces.put(locale, messages);
		}
		String message = null;
		if(messages.containsKey(key)) {
			message = messages.get(key);
			return message;
		}
		
		//从数据库读取:
		message = this.messager.getMessage(key, locale);
		messages.put(key, message);
		return message;
    }
    public String getMessage(String key, Object[] arguments, Locale locale) {
    	String message = this.getMessage(key, locale);
    	return StringUtil.Replace(message, arguments, "{", "}");
    }
}
