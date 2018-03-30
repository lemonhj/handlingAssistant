package com.septinary.common.web.general.messageresource.springmvc;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import com.septinary.common.core.util.ResourceMessagerable;

/**
 * 数据库消息资源读取
 * @Filename: com.septinary.common.web.general.messageresource.springmvc.DatabaseResourceBundleMessageSource.java of the project [com.septinary.common.web]
 *     @Type: DatabaseResourceBundleMessageSource
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月20日下午4:37:34
 *
 */
public class DatabaseResourceBundleMessageSource extends AbstractMessageSource implements ResourceLoaderAware {

	private ResourceMessagerable messager = new ResourceMessagerable(){};

	private ResourceLoader resourceLoader = null;
    
    public DatabaseResourceBundleMessageSource() {}
    
	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = (null!=resourceLoader ? resourceLoader : new DefaultResourceLoader());
	}
	public ResourceLoader getResourceLoader() {
		return this.resourceLoader;
	}

	@Override
	protected MessageFormat resolveCode(String code, Locale locale) {
		String msg = this.getText(code, locale);
        MessageFormat result = this.createMessageFormat(msg, locale);
        return result;
	}
    
    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        String result = this.getText(code, locale);
        return result;
    }
	
    @Override
	protected Object[] resolveArguments(Object[] args, Locale locale) {
		return super.resolveArguments(args, locale);
	}
	
	/**
     * 实际读取逻辑
     @method DatabaseResourceBundleMessageSource: getText()
     @memo TODO
     @param code
     @param locale
     @return String
     */
    private String getText(String code, Object[] args, Locale locale) {
    	//首先，从数据库中读取
    	String message = this.messager.getMessage(code, locale);
    	if( null!=message ) return message;
    	
    	//假如数据库中不存在，则从（默认，取决于spring-mvc.xml中的配置）properties资源文件中读取
    	MessageSource messageSource = this.getParentMessageSource();
    	if( null!=messageSource ) {
    		try {
                message = messageSource.getMessage(code, args, locale);
            } catch (NoSuchMessageException | MissingResourceException e) {
            	if( this.isUseCodeAsDefaultMessage() ) {
            		message = code;
            	}else {
            		throw e;
            	}
            }
    		return message;
    	}
    	
    	//最后，如果均未发现相关配置的处理策略
    	if( this.isUseCodeAsDefaultMessage() ) {
    		message = code;
    		return message;
    	}
    	throw new NoSuchMessageException("Cannot find resource message with code: "+code, locale);
    }
    private String getText(String code, Locale locale) {
    	return this.getText(code, null, locale);
    }
    
}
