package com.septinary.common.web.general.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.septinary.common.web.basic.listener.Listener;

/**
 * 
 * @Filename: com.septinary.common.web.general.listener.CommonListenerContext.java of the project [com.septinary.common.web]
 *     @Type: CommonListenerContext
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月28日下午11:20:07
 *
 *		Listener：监听器
 *从字面上可以看出listener主要用来监听只用。通过listener可以监听web服务器中某一个执行动作，并根据其要求作
 *出相应的响应。通俗的语言说就是在application，session，request三个对象创建消亡或者往其中添加修改删除属
 *性时自动执行代码的功能组件。比如spring 的总监听器 会在服务器启动的时候实例化我们配置的bean对象、 hibernate
 *的 session 的监听器会监听session的活动和生命周期，负责创建，关闭session等活动。
 *		Servlet的监听器Listener，它是实现了javax.servlet.ServletContextListener接口的服务器端程
 *序，它也是随web应用的启动而启动，只初始化一次，随web应用的停止而销毁。主要作用是： 做一些初始化的内容添加工
 *作、设置一些基本的内容、比如一些参数或者是一些固定的对象等等。
 */
public class CommonListenerContext extends Listener implements ServletContextListener, ServletContextAttributeListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
