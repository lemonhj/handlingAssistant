package com.septinary.common.core.type;

/**
 * 日志级别
 * @Filename: com.septinary.common.core.type.LogLevel.java of the project [com.septinary.common]
 *     @Type: LogLevel
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月1日下午12:32:30
 *
 */
public enum LogLevel {
	UNDEFINED(	-2	){ public String key(){return this.name();} },
	ALL(		-1	){ public String key(){return this.name();} },
	VERBOSE(	1	){ public String key(){return this.name();} },
	DEBUG(		2	){ public String key(){return this.name();} },
	INFO(		4	){ public String key(){return this.name();} },
	NOTICE(		8	){ public String key(){return this.name();} },
	TRACE(		16	){ public String key(){return this.name();} },
	LOG(		32	){ public String key(){return this.name();} },
	WARN(		64	){ public String key(){return this.name();} },
	FAIL(		128	){ public String key(){return this.name();} },
	ERROR(		256	){ public String key(){return this.name();} },
	EXCEPTION(	512	){ public String key(){return this.name();} },
	ASSERT(		1024){ public String key(){return this.name();} },
	FATAL(		2048){ public String key(){return this.name();} },
	NONE(		0	){ public String key(){return this.name();} };
	
	//枚举取值
	private final Integer value;
	
	//构造函数
	LogLevel(Integer value) {
		this.value = value;
	}
	LogLevel(String value) {
		this.value = LogLevel.valueOf(value).value();
	}
	
	//抽象接口：获取key值
	abstract public String key();
	
	//获取取值
	public Integer value() {
		return this.value;
	}
	
	//获取释义
	public String parse(){
		return this.name();
	};
	
	//字串化
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		// return super.toString();
		return this.key();
	}
}
