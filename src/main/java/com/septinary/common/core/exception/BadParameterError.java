package com.septinary.common.core.exception;

/**
 * 参数错误
 * @Filename: com.septinary.common.core.exception.BadParameterError.java of the project [com.septinary.common]
 *     @Type: BadParameterError
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年8月17日 上午10:53:19
 */
public class BadParameterError extends Error {
	private static final long serialVersionUID = 6909224278610110781L;

	public BadParameterError() {
		super();
	}
	
	public BadParameterError(String message) {
		super(message);
	}
	
    public BadParameterError(String message, Throwable cause) {
        super(message, cause);
    }
    
    public BadParameterError(Throwable cause) {
        super(cause);
    }
    
    protected BadParameterError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    	super(message, cause, enableSuppression, writableStackTrace);
    }
}
