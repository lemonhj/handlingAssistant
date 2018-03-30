package com.septinary.common.core.exception;

/**
 * 配置错误
 * @Filename: com.septinary.common.core.exception.ConfigError.java of the project [com.septinary.common]
 *     @Type: ConfigError
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月3日上午11:47:16
 *
 */
public class ConfigError extends Error {
	private static final long serialVersionUID = -2341465397066901351L;

	public ConfigError() {
		super();
	}
	
	public ConfigError(String message) {
		super(message);
	}
	
    public ConfigError(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ConfigError(Throwable cause) {
        super(cause);
    }
    
    protected ConfigError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    	super(message, cause, enableSuppression, writableStackTrace);
    }
}
