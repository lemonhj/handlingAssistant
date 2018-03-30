package com.septinary.common.core.type;

/**
 * 运行模式
 * @Filename: com.septinary.common.core.type.RunningMode.java of the project [com.septinary.common]
 *     @Type: RunningMode
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月1日下午12:34:42
 *
 */
public enum RunningMode {
	UNDEFINED(	-2){},		//未定义的运行模式
	ALL(		-1){},		//所有
	DEBUG(		1){},		//调试模式
	TEST(		2){},		//测试模式
	RELEASE(	4){},		//发布模式
	NONE(		0){};		//全不
	
	//枚举取值
	private final Integer value;
	//构造函数
	RunningMode(Integer value) {
        this.value = value;
    }
	RunningMode(String value) {
        this.value = RunningMode.valueOf(value).value();
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
