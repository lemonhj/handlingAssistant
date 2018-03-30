package com.septinary.common.web.general.listener;

import com.septinary.common.core.util.IConfigGetter;
import com.septinary.common.core.util.IErroringable;
import com.septinary.common.core.util.IFieldingable;
import com.septinary.common.core.util.ILoggingable;
import com.septinary.common.core.util.IResourceMessagingable;
import com.septinary.common.manager.ConfiggerManager;
import com.septinary.common.manager.ErrorManager;
import com.septinary.common.manager.FielderManager;
import com.septinary.common.manager.LoggerManager;
import com.septinary.common.manager.ResourceMessagerManager;
import com.septinary.common.type.IFieldValue;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;

@Component
@Order(1)
public class SystemManagerStartupListener extends CommonListenerContext {
	
	static {
		//设置系统日志工厂。注意：在此之前，使用 LoggerManager.Instance().getLogger() 就会抛出RuntimeStateError异常
		System.out.println("Setting LoggerManager.loggerFactory ...");
		LoggerManager.Instance().setLoggerFactory(new LoggerManager.ILoggerFactory() {
			@Override public ILoggingable getLogger(Class<?> clazz) {
				// TODO Auto-generated method stub
				// 暂时直接使用自封装的log4j类，后期可以从配置文件中读取...
				return com.septinary.common.general.runtime.SeptinaryLog4j.GetLogger(clazz);
			}
		});

		//设置字段定义读取器。注意：在此之前，使用 FielderManager.Instance().getFielder() 就会抛出RuntimeStateError异常
    	System.out.println("Setting FielderManager.fielderFactory ...");
    	FielderManager.Instance().setFielderFactory(new FielderManager.IFielderFactory() {
			@Override public IFieldingable getFielder() {
				// TODO Auto-generated method stub
				return com.septinary.common.web.general.runtime.springmvc.SeptinaryField.GetFielder();
			}
		});

		//设置错误定义读取器。注意：在此之前，使用 ErrorManager.Instance().getErrorer() 就会抛出RuntimeStateError异常
    	System.out.println("Setting ErrorManager.errorerFactory ...");
    	ErrorManager.Instance().setErrorerFactory(new ErrorManager.IErrorerFactory() {
			@Override public IErroringable getErrorer(IFieldValue type) {
				// TODO Auto-generated method stub
				return com.septinary.common.web.general.runtime.springmvc.SeptinaryErrorer.GetErrorer(type);
			}
		});

		//设置系统配置工厂。注意：在此之前，使用 ConfiggerManager.Instance().getConfigger() 就会抛出RuntimeStateError异常
    	System.out.println("Setting ConfiggerManager.configReaderFactory ...");
        ConfiggerManager.Instance().setConfigReaderFactory(new ConfiggerManager.IConfigReaderFactory() {
            @Override public IConfigGetter getReader() {
				// TODO Auto-generated method stub
            	// 每个应用程序必须指定一系列配置路径
                return com.septinary.common.web.general.runtime.springmvc.SeptinaryConfig.GetReader("com.septinary.campustime.api");
            }
        });

		//设置资源消息工厂。注意：在此之前，使用 ResourceMessagerManager.Instance().getMessager() 就会抛出RuntimeStateError异常
    	System.out.println("Setting ResourceMessagerManager.messagerFactory ...");
    	ResourceMessagerManager.Instance().setMessagerFactory(new ResourceMessagerManager.IMessagerFactory() {
			@Override public IResourceMessagingable getMessager() {
				// TODO Auto-generated method stub
				return com.septinary.common.web.general.runtime.springmvc.SeptinaryMessageResource.GetMessager();
			}
		});
	}

}
