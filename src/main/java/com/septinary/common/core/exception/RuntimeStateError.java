package com.septinary.common.core.exception;

/**
 * 运行时状态错误
 * @Filename: com.septinary.common.core.exception.RuntimeStateError.java of the project [com.septinary.common]
 *     @Type: RuntimeStateError
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月3日上午11:47:31
 *
 */
public class RuntimeStateError extends Error {
	private static final long serialVersionUID = 4224617109556884217L;

	public RuntimeStateError() {
		super();
	}
	
	public RuntimeStateError(String message) {
		super(message);
	}
	
    public RuntimeStateError(String message, Throwable cause) {
        super(message, cause);
    }
    
    public RuntimeStateError(Throwable cause) {
        super(cause);
    }
    
    protected RuntimeStateError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    	super(message, cause, enableSuppression, writableStackTrace);
    }
}
