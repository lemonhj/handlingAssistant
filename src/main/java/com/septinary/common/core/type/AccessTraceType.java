package com.septinary.common.core.type;

/**
 * 访问跟踪类型
 * @Filename: com.septinary.common.core.type.AccessTraceType.java of the project [com.septinary.common]
 *     @Type: AccessTraceType
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年8月6日 上午10:47:16
 */
public enum AccessTraceType {
	UNDEFINED(	-2){},		//未定义的运行模式
	ALL(		-1){},		//所有
	LOG(		1){},		//跟踪日志
	TRAIL(		2){},		//跟踪轨迹
	NONE(		0){};		//全不

	//枚举取值
	private final Integer value;
	//构造函数
	AccessTraceType(Integer value) {
        this.value = value;
    }
	AccessTraceType(String value) {
        this.value = AccessTraceType.valueOf(value).value();
    }
	//抽象接口：获取key值
	public String key(){
		return this.name();
	};
	//获取取值
	public Integer value() {
		return this.value;
	}
	//获取释义
	public String parse(){
		return this.key();
	};
	//字串化
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		// return super.toString();
		return this.key();
	}
}
