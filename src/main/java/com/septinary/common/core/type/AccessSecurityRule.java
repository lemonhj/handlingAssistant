package com.septinary.common.core.type;

/**
 * 访问安全规则
 * @Filename: com.septinary.common.core.type.AccessSecurityRule.java of the project [com.septinary.common]
 *     @Type: AccessSecurityRule
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日下午9:33:40
 *
 */
public enum AccessSecurityRule {
	UNDEFINED(		-2),	//未定义
	ALL(			-1),	//所有
	DIRTY_REQUEST(	1),		//错误请求，调用参数错误，携带脏数据
	WRONG_STATUS(	2),		//错误状态，调用时机不当
	TOOMUCH_CALL(	4),		//过频调用，调用次数过频
	TOOBRIEF_CALL(	8),		//间隔太短，调用间隔太短
	FORGE_REQUEST(	16),	//伪造请求，调用疑似伪造
	NONE(			0);		//全不

	//枚举取值
	private final Integer value;
	
	//构造函数
	AccessSecurityRule(Integer value) {
		this.value = value;
	}
	AccessSecurityRule(String value) {
		this.value = LogLevel.valueOf(value).value();
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
