package com.septinary.common.web.general.context.springmvc;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.septinary.common.core.util.ConfigFrom;
import com.septinary.common.core.util.ILoadable;
import com.septinary.common.type.Mark;
import com.septinary.common.util.CollectionUtil;
import com.septinary.common.util.ICallbackable;

public class ApplicationContextSpringMVC implements ApplicationContextAware, ILoadable<ConfigFrom,List<Mark<Boolean,String>>> {
	
	private List<ConfigFrom> configFromList = null;
	
	public List<ConfigFrom> getConfigFromList() {
		return configFromList;
	}

	public void setConfigFromList(List<ConfigFrom> configFromList) {
		this.configFromList = configFromList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConfigFrom> load(final List<Mark<Boolean,String>>... pathes) {
		
		//设置读取的配置路径
		if(null!=pathes && 0<pathes.length) CollectionUtil.EachDo(this.configFromList, new ICallbackable(){
			@Override public Object callback(Object first, Object... arguments) {
				((ConfigFrom)first).setPathes(pathes[0]);
				return first;
			}}
		);
		
		return this.getConfigFromList();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		// return null;
		
		ApplicationContextSpringMVC.applicationContext = applicationContext;
	}

	
	//Spring应用上下文环境
	private static ApplicationContext applicationContext;

	/**
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 获取对象
	 * 
	 * @param name
	 * @return Object 一个以所给名字注册的bean的实例
	 * @throws BeansException
	 */
	public static Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}

	/**
	 * 获取类型为requiredType的对象
	 * 如果bean不能被类型转换，相应的异常将会被抛出（BeanNotOfRequiredTypeException）
	 * 
	 * @param name bean注册名
	 * @param requiredType 返回对象类型
	 * @return Object 返回requiredType类型对象
	 * @throws BeansException
	 */
	public static Object getBean(String name, Class<?> requiredType) throws BeansException {
		return applicationContext.getBean(name, requiredType);
	}

	/**
	 * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
	 * 
	 * @param name
	 * @return boolean
	 */
	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}

	/**
	 * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
	 * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
	 * 
	 * @param name
	 * @return boolean
	 * @throws NoSuchBeanDefinitionException
	 */
	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.isSingleton(name);
	}

	/**
	 * @param name
	 * @return Class 注册对象的类型
	 * @throws NoSuchBeanDefinitionException
	 */
	public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.getType(name);
	}

	/**
	 * 如果给定的bean名字在bean定义中有别名，则返回这些别名
	 * 
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.getAliases(name);
	}

}
