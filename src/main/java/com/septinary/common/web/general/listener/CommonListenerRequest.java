package com.septinary.common.web.general.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

import com.septinary.common.core.util.Loggerable;
import com.septinary.common.util.ICallbackable;
import com.septinary.common.web.basic.listener.Listener;
import com.septinary.common.web.util.HTTPUtil;

/**
 * 
 * @Filename: com.septinary.common.web.general.listener.CommonListenerRequest.java of the project [com.septinary.common.web]
 *     @Type: CommonListenerRequest
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月28日下午11:20:19
 *
 */
@WebListener
public class CommonListenerRequest extends Listener implements ServletRequestListener, ServletRequestAttributeListener {

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		
		ServletRequest req = sre.getServletRequest();
		if( !(req instanceof HttpServletRequest) ) return;
		
		HttpServletRequest request = (HttpServletRequest)req;
		
		//输出请求参数：
		final Loggerable logger = this.logger;
		logger.debug("请求参数：");
		HTTPUtil.DumpParameters(request, new ICallbackable(){
			@Override
			public Object callback(Object first, Object... arguments) {
				logger.debug(first);
				return first;
			}}
		);
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		
	}

}
