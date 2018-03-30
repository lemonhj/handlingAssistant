package com.septinary.common.general.runtime;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.septinary.common.core.util.ILoggingable;
import com.septinary.common.util.FormatUtil;

/**
 * 七进制通用日志输出类
 * @Filename: com.septinary.common.general.runtime.SeptinaryLog4j.java of the project [com.septinary.common.general]
 *     @Type: SeptinaryLog4j
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月24日下午10:56:36
 *
 *	依赖log4j包。
 */
public class SeptinaryLog4j implements ILoggingable {
	//参数占位符（正则表达式）
	private static final String PLACEHOLDER_PATTERN = "\\{\\s*\\}";

	/**
	 * log4j levels:
	 * 	TRACE
	 * 	DEBUG
	 * 	INFO
	 * 	WARN
	 * 	ERROR
	 * 	FATAL
	 */
	private Logger logger = null;
	
	protected SeptinaryLog4j(String name) {
		this.logger = Logger.getLogger(name);
	}
	
	static public SeptinaryLog4j GetLogger(Class<?> clazz) {
		return new SeptinaryLog4j(clazz.getName());
	}
	
	/**
	 * 是否屏蔽指定级别的日志？
	 * @param level
	 * @return
	 */
	private boolean shield(Level printLevel) {
		Level configLevel = this.logger.getLevel();
		configLevel = null==configLevel ? Logger.getRootLogger().getLevel() : configLevel;
		return configLevel.toInt() > printLevel.toInt();
	}

	//log4j没有VERBOSE级别：将其代理到DEBUG级别
	@Override
	public void verbose(Object message) {
		// TODO Auto-generated method stub
		this.debug(message);
	}

	@Override
	public void verbose(Object message, Throwable t) {
		// TODO Auto-generated method stub
		this.debug(message, t);
	}

	@Override
	public void verbose(String format, Object... params) {
		// TODO Auto-generated method stub
		this.debug(format, params);
	}

	@Override
	public void verbose(Throwable t, String format, Object... params) {
		// TODO Auto-generated method stub
		this.debug(t, format, params);
	}

	@Override
	public void debug(Object message) {
		// TODO Auto-generated method stub
		this.logger.debug(message);
	}

	@Override
	public void debug(Object message, Throwable t) {
		// TODO Auto-generated method stub
		this.logger.debug(message, t);
	}

	//log4j没有可变参数输出接口：自行判断日志输出配置级别
	@Override
	public void debug(String format, Object... params) {
		// TODO Auto-generated method stub
		
		if(shield(Level.DEBUG)) return;
		this.debug(FormatUtil.Format(PLACEHOLDER_PATTERN, format, params));
	}

	@Override
	public void debug(Throwable t, String format, Object... params) {
		// TODO Auto-generated method stub
		
		if(shield(Level.DEBUG)) return;
		this.debug(FormatUtil.Format(PLACEHOLDER_PATTERN, format, params), t);
	}

	//log4j没有NOTICE级别：将其代理到INFO级别
	@Override
	public void notice(Object message) {
		// TODO Auto-generated method stub
		this.info(message);
	}

	@Override
	public void notice(Object message, Throwable t) {
		// TODO Auto-generated method stub
		this.info(message, t);
	}
	
	@Override
	public void notice(String format, Object... params) {
		// TODO Auto-generated method stub
		this.info(format, params);
	}

	@Override
	public void notice(Throwable t, String format, Object... params) {
		// TODO Auto-generated method stub
		this.info(t, format, params);
	}

	@Override
	public void info(Object message) {
		// TODO Auto-generated method stub
		this.logger.info(message);
	}

	@Override
	public void info(Object message, Throwable t) {
		// TODO Auto-generated method stub
		this.logger.info(message, t);
	}

	//log4j没有可变参数输出接口：自行判断日志输出配置级别
	@Override
	public void info(String format, Object... params) {
		// TODO Auto-generated method stub

		if(shield(Level.INFO)) return;
		this.info(FormatUtil.Format(PLACEHOLDER_PATTERN, format, params));
	}

	@Override
	public void info(Throwable t, String format, Object... params) {
		// TODO Auto-generated method stub

		if(shield(Level.INFO)) return;
		this.info(FormatUtil.Format(PLACEHOLDER_PATTERN, format, params), t);
	}

	@Override
	public void trace(Object message) {
		// TODO Auto-generated method stub
		
		//this.logger.trace(message);
		this.info(message);
	}

	@Override
	public void trace(Object message, Throwable t) {
		// TODO Auto-generated method stub
		
		//this.logger.trace(message, t);
		this.info(message, t);
	}

	//log4j没有可变参数输出接口：自行判断日志输出配置级别
	@Override
	public void trace(String format, Object... params) {
		// TODO Auto-generated method stub

		//if(shield(Level.TRACE)) return;
		//this.trace(FormatUtil.Format(PLACEHOLDER_PATTERN, format, params));
		this.info(format, params);
	}

	@Override
	public void trace(Throwable t, String format, Object... params) {
		// TODO Auto-generated method stub

		//if(shield(Level.TRACE)) return;
		//this.trace(FormatUtil.Format(PLACEHOLDER_PATTERN, format, params), t);
		this.info(t, format, params);
	}

	//log4j没有LOG级别：将其代理到INFO级别
	@Override
	public void log(Object message) {
		// TODO Auto-generated method stub
		this.info(message);
	}

	@Override
	public void log(Object message, Throwable t) {
		// TODO Auto-generated method stub
		this.info(message, t);
	}

	@Override
	public void log(String format, Object... params) {
		// TODO Auto-generated method stub
		this.info(format, params);
	}

	@Override
	public void log(Throwable t, String format, Object... params) {
		// TODO Auto-generated method stub
		this.info(t, format, params);
	}

	@Override
	public void warn(Object message) {
		// TODO Auto-generated method stub
		this.logger.warn(message);
	}

	@Override
	public void warn(Object message, Throwable warnable) {
		// TODO Auto-generated method stub
		this.logger.warn(message, warnable);
	}

	//log4j没有可变参数输出接口：自行判断日志输出配置级别
	@Override
	public void warn(String format, Object... params) {
		// TODO Auto-generated method stub

		if(shield(Level.WARN)) return;
		this.warn(FormatUtil.Format(PLACEHOLDER_PATTERN, format, params));
	}

	@Override
	public void warn(Throwable t, String format, Object... params) {
		// TODO Auto-generated method stub

		if(shield(Level.WARN)) return;
		this.warn(FormatUtil.Format(PLACEHOLDER_PATTERN, format, params), t);
	}

	//log4j没有FAIL级别：将其代理到ERROR级别
	@Override
	public void fail(Object message) {
		// TODO Auto-generated method stub
		this.error(message);
	}

	@Override
	public void fail(Object message, Throwable failable) {
		// TODO Auto-generated method stub
		this.error(message, failable);
	}

	@Override
	public void fail(String format, Object... params) {
		// TODO Auto-generated method stub
		this.error(format, params);
	}

	@Override
	public void fail(Throwable failable, String format, Object... params) {
		// TODO Auto-generated method stub
		this.error(failable, format, params);
	}

	@Override
	public void error(Object message) {
		// TODO Auto-generated method stub
		this.logger.error(message);
	}

	@Override
	public void error(Object message, Throwable errorable) {
		// TODO Auto-generated method stub
		this.logger.error(message, errorable);
	}

	//log4j没有可变参数输出接口：自行判断日志输出配置级别
	@Override
	public void error(String format, Object... params) {
		// TODO Auto-generated method stub

		if(shield(Level.ERROR)) return;
		this.error(FormatUtil.Format(PLACEHOLDER_PATTERN, format, params));
	}

	@Override
	public void error(Throwable errorable, String format, Object... params) {
		// TODO Auto-generated method stub

		if(shield(Level.ERROR)) return;
		this.error(FormatUtil.Format(PLACEHOLDER_PATTERN, format, params), errorable);
	}

	//log4j没有EXCEPTION级别：将其代理到ERROR级别
	@Override
	public void exception(Object message, Exception e) {
		// TODO Auto-generated method stub
		this.error(message, e);
	}

	@Override
	public void exception(Exception e, String format, Object... params) {
		// TODO Auto-generated method stub
		this.error(e, format, params);
	}

	//log4j没有ASSERT级别：将其代理到ERROR级别
	@Override
	public void assertion(Object message, AssertionError ae) {
		// TODO Auto-generated method stub
		this.error(message, ae);
	}

	@Override
	public void assertion(AssertionError ae, String format, Object... params) {
		// TODO Auto-generated method stub
		this.error(ae, format, params);
	}

	@Override
	public void fatal(Object message, Error e) {
		// TODO Auto-generated method stub
		this.logger.fatal(message, e);
	}

	//log4j没有可变参数输出接口：自行判断日志输出配置级别
	@Override
	public void fatal(Error e, String format, Object... params) {
		// TODO Auto-generated method stub

		if(shield(Level.FATAL)) return;
		this.fatal(FormatUtil.Format(PLACEHOLDER_PATTERN, format, params), e);
	}

	public static void main(String[] args) {
		 // TODO ... test ...
	}

}
