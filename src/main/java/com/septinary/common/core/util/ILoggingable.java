package com.septinary.common.core.util;

/**
 * 日志输出接口
 * @Filename: com.septinary.common.core.system.ILoggingable.java of the project [com.septinary.common]
 *     @Type: ILoggingable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年2月24日下午12:30:26
 * 
 */
public interface ILoggingable {
	//繁复输出
	public void verbose(Object message);
	public void verbose(String format, Object... params);
	public void verbose(Object message, Throwable t);
	public void verbose(Throwable t, String format, Object... params);
	//调试输出
	public void debug(Object message);
	public void debug(String format, Object... params);
	public void debug(Object message, Throwable t);
	public void debug(Throwable t, String format, Object... params);
	//信息输出
	public void info(Object message);
	public void info(String format, Object... params);
	public void info(Object message, Throwable t);
	public void info(Throwable t, String format, Object... params);
	//提示输出
	public void notice(Object message);
	public void notice(String format, Object... params);
	public void notice(Object message, Throwable t);
	public void notice(Throwable t, String format, Object... params);
	//跟踪输出
	public void trace(Object message);
	public void trace(String format, Object... params);
	public void trace(Object message, Throwable t);
	public void trace(Throwable t, String format, Object... params);
	//日志输出
	public void log(Object message);
	public void log(String format, Object... params);
	public void log(Object message, Throwable t);
	public void log(Throwable t, String format, Object... params);
	//警告输出
	public void warn(Object message);
	public void warn(String format, Object... params);
	public void warn(Object message, Throwable warnable);
	public void warn(Throwable t, String format, Object... params);
	//失败输出
	public void fail(Object message);
	public void fail(String format, Object... params);
	public void fail(Object message, Throwable failable);
	public void fail(Throwable t, String format, Object... params);
	//错误输出
	public void error(Object message);
	public void error(String format, Object... params);
	public void error(Object message, Throwable errorable);
	public void error(Throwable errorable, String format, Object... params);
	//异常输出
	public void exception(Object message, Exception e);
	public void exception(Exception e, String format, Object... params);
	//断言输出
	public void assertion(Object message, AssertionError ae);
	public void assertion(AssertionError ae, String format, Object... params);
	//致命输出
	public void fatal(Object message, Error e);
	public void fatal(Error e, String format, Object... params);
}
