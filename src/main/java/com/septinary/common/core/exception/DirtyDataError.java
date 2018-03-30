package com.septinary.common.core.exception;

/**
 * 脏数据错误
 * @Filename: com.septinary.common.core.exception.DirtyDataError.java of the project [com.septinary.common]
 *     @Type: DirtyDataError
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年8月17日 上午10:52:49
 */
public class DirtyDataError extends Error {
	private static final long serialVersionUID = 2497767061146339060L;

	public DirtyDataError() {
		super();
	}
	
	public DirtyDataError(String message) {
		super(message);
	}
	
    public DirtyDataError(String message, Throwable cause) {
        super(message, cause);
    }
    
    public DirtyDataError(Throwable cause) {
        super(cause);
    }
    
    protected DirtyDataError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    	super(message, cause, enableSuppression, writableStackTrace);
    }
}
