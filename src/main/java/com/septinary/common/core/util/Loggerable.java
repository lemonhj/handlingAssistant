package com.septinary.common.core.util;

import com.septinary.common.manager.LoggerManager;
import com.septinary.common.util.Assert;

/**
 * 日志输出适配器
 * @Filename: com.septinary.common.core.util.Loggerable.java of the project [com.septinary.common]
 *     @Type: Loggerable
 *     @Desc: 只要扩展此类即可拥有日志输出器
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月3日上午11:41:02
 *
 */
abstract public class Loggerable {

    private ILoggingable logger = null;

    public Loggerable(Object holder) {
    	Assert.NotNull(holder, "The holder of the logger must not be null!");
    	
        System.out.println("Getting logger from sigleton:LoggerManager for '"+holder.getClass().getName()+"' ...");
        this.logger = LoggerManager.Instance().getLogger(holder.getClass());
    }
    
    //静态日志
    public static ILoggingable GetLogger(Class<?> clazz) {
    	return LoggerManager.Instance().getLogger(clazz);
    }

    //日志输出调用适配
	//繁复输出
	public void verbose(Object message) {
		this.logger.verbose(message);
	}
	public void verbose(Object message, Throwable t) {
		this.logger.verbose(message, t);
	}
	public void verbose(String format, Object... params) {
		this.logger.verbose(format, params);
	}
	public void verbose(Throwable t, String format, Object... params) {
		this.logger.verbose(t, format, params);
	}
	
	//调试输出
	public void debug(Object message) {
		this.logger.debug(message);
	}
	public void debug(Object message, Throwable t) {
		this.logger.debug(message, t);
	}
	public void debug(String format, Object... params) {
		this.logger.debug(format, params);
	}
	public void debug(Throwable t, String format, Object... params) {
		this.logger.debug(t, format, params);
	}
	
	//信息输出
	public void info(Object message) {
		this.logger.info(message);
	}
	public void info(Object message, Throwable t) {
		this.logger.info(message, t);
	}
	public void info(String format, Object... params) {
		this.logger.info(format, params);
	}
	public void info(Throwable t, String format, Object... params) {
		this.logger.info(t, format, params);
	}
	
	//提示输出
	public void notice(Object message) {
		this.logger.notice(message);
	}
	public void notice(Object message, Throwable t) {
		this.logger.notice(message, t);
	}
	public void notice(String format, Object... params) {
		this.logger.notice(format, params);
	}
	public void notice(Throwable t, String format, Object... params) {
		this.logger.notice(t, format, params);
	}
	
	//跟踪输出
	public void trace(Object message) {
		this.logger.trace(message);
	}
	public void trace(Object message, Throwable t) {
		this.logger.trace(message, t);
	}
	public void trace(String format, Object... params) {
		this.logger.trace(format, params);
	}
	public void trace(Throwable t, String format, Object... params) {
		this.logger.trace(t, format, params);
	}
	
	//日志输出
	public void log(Object message) {
		this.logger.log(message);
	}
	public void log(Object message, Throwable t) {
		this.logger.log(message, t);
	}
	public void log(String format, Object... params) {
		this.logger.log(format, params);
	}
	public void log(Throwable t, String format, Object... params) {
		this.logger.log(t, format, params);
	}
	
	//警告输出
	public void warn(Object message) {
		this.logger.warn(message);
	}
	public void warn(Object message, Throwable warnable) {
		this.logger.warn(message, warnable);
	}
	public void warn(String format, Object... params) {
		this.logger.warn(format, params);
	}
	public void warn(Throwable t, String format, Object... params) {
		this.logger.warn(t, format, params);
	}
	
	//失败输出
	public void fail(Object message) {
		this.logger.fail(message);
	}
	public void fail(Object message, Throwable failable) {
		this.logger.fail(message, failable);
	}
	public void fail(String format, Object... params) {
		this.logger.fail(format, params);
	}
	public void fail(Throwable t, String format, Object... params) {
		this.logger.fail(t, format, params);
	}
	
	//错误输出
	public void error(Object message) {
		this.logger.error(message);
	}
	public void error(Object message, Throwable errorable) {
		this.logger.error(message, errorable);
	}
	public void error(String format, Object... params) {
		this.logger.error(format, params);
	}
	public void error(Throwable errorable, String format, Object... params) {
		this.logger.error(errorable, format, params);
	}
	
	//异常输出
	public void exception(Object message, Exception e) {
		this.logger.exception(message, e);
	}
	public void exception(Exception e, String format, Object... params) {
		this.logger.exception(e, format, params);
	}
	
	//断言输出
	public void assertion(Object message, AssertionError ae) {
		this.logger.assertion(message, ae);
	}
	public void assertion(AssertionError ae, String format, Object... params) {
		this.logger.assertion(ae, format, params);
	}
	
	//致命输出
	public void fatal(Object message, Error e) {
		this.logger.fatal(message, e);
	}
	public void fatal(Error e, String format, Object... params) {
		this.logger.fatal(e, format, params);
	}
}
