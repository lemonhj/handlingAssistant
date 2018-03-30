package com.septinary.common.web.general.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.septinary.common.web.basic.listener.Listener;

/**
 * 
 * @Filename: com.septinary.common.web.general.listener.CommonListenerSession.java of the project [com.septinary.common.web]
 *     @Type: CommonListenerSession
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月28日下午11:19:15
 *
 *	注意：
 *		感知型监听器HttpSessionBindingListener、HttpSessionActivationListener不需要注册。
 *		所谓对session进行数据绑定，就是调用session.setAttribute()把HttpSessionBindingListener
 *	保存进session中。例如，我们在LoginServlet.java中进行这一步。
 *	//把用户名放入在线列表：
 *	session.setAttribute("onlineUserBindingListener", new OnlineUserBindingListener(username));
 *	这就是HttpSessionBindingListener和HttpSessionListener之间的最大区别： HttpSessionListener
 *	只需要设置到web.xml中就可以监听整个应用中的所有session。 HttpSessionBindingListener必须实
 *	例化后放入某一个session中，才可以进行监听。
 *		HttpSessionBindingListener必须实例化后放入某一个session中，才可以进行监听。从监听范围
 *	上比较，HttpSessionListener设置一次就可以监听所有session，HttpSessionBindingListener
 *	通常都是一对一的。 也就是说将绑定的对象类实现HttpSessionBindingListener接口。正是这种区别成
 *	就了HttpSessionBindingListener的优势，我们可以让每个listener对应一个username，这样就不需
 *	要每次再去session中读取username，进一步可以将所有操作在线列表的代码都移入listener，更容易维护。
 */
public class CommonListenerSession extends Listener implements HttpSessionListener, HttpSessionAttributeListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}

}
