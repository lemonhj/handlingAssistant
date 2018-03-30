package com.septinary.common.web.general.runtime.springmvc;

import java.util.Locale;

import org.springframework.context.ApplicationContext;

import com.septinary.common.core.util.IResourceMessageGetter;
import com.septinary.common.core.util.IResourceMessagingable;
import com.septinary.common.type.IResourceMessage;
import com.septinary.common.type.Mark;
import com.septinary.common.web.general.context.springmvc.ApplicationContextSpringMVC;

/**
 * 
 * @Filename: com.septinary.common.web.general.runtime.springmvc.SeptinaryMessageResource.java of the project [com.septinary.common.web]
 *     @Type: SeptinaryMessageResource
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月20日下午4:39:12
 *
 */
public class SeptinaryMessageResource implements IResourceMessagingable {
	//单件
	protected SeptinaryMessageResource() {}
	
	static private SeptinaryMessageResource messageresource = null;
	static public SeptinaryMessageResource GetMessager() {
		if(null==messageresource) messageresource = new SeptinaryMessageResource();
		return messageresource;
	}

	//消息获取器
	private IResourceMessageGetter getter = null;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String getMessage(String key, Locale locale) {
		if(null==this.getter) {
			ApplicationContext applicationContext = ApplicationContextSpringMVC.getApplicationContext();
			this.getter = (null!=this.getter) ? this.getter : (IResourceMessageGetter)applicationContext.getBean("resourceMessageService");
		}
		IResourceMessage messageObj = this.getter.get(new Mark(locale.toString(),key));
		return null==messageObj ? null : messageObj.getMessage();
	}

}
