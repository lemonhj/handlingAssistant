package com.septinary.common.core.exception;

/**
 * 数值超过边界值异常
 * @Filename: com.septinary.common.core.exception.NumericOutOfBoundaryException.java of the project [com.septinary.common]
 *     @Type: NumericOutOfBoundaryException
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日下午9:27:12
 *
 */
public class NumericOutOfBoundaryException extends RuntimeException {
    static final long serialVersionUID = 1L;

    public NumericOutOfBoundaryException() {
        super();
    }

    public NumericOutOfBoundaryException(String message) {
        super(message);
    }

    public NumericOutOfBoundaryException(String message, Throwable cause) {
        super(message, cause);
    }

    public NumericOutOfBoundaryException(Throwable cause) {
        super(cause);
    }

    protected NumericOutOfBoundaryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
