package com.septinary.common.core.util;

import java.util.Locale;

/**
 * 资源读取接口
 * @Filename: com.septinary.common.core.util.IResourceMessagingable.java of the project [com.septinary.common]
 *     @Type: IResourceMessagingable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月15日下午4:57:39
 *
 */
public interface IResourceMessagingable {

	/**
	 * 读取消息
	 * @param key
	 * @param locale
	 * @return
	 */
	public String getMessage(String key, Locale locale);
}
