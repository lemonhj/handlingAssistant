package com.septinary.common.web.general.context.springmvc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;



/**
 * @author lin.tb lin.tb@septinary.com
 * @ClassName: Springfactory
 * @Description:
 * @date 15/12/2
 */
public class Springfactory implements BeanFactoryAware {

    private static BeanFactory beanFactory;

    // private static ApplicationContext context;

    @SuppressWarnings("static-access")
	public void setBeanFactory(BeanFactory factory) throws BeansException {
        this.beanFactory = factory;
    }

    /**
     * 根据beanName名字取得bean
     *
     * @param beanName
     * @return
     */
    @SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
        if (null != beanFactory) {
            return (T) beanFactory.getBean(beanName);
        }
        return null;
    }

}
