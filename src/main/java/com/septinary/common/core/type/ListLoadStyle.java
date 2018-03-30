package com.septinary.common.core.type;

/**
 * 列表数据加载方式
 * @Filename: com.septinary.common.core.type.ListLoadStyle.java of the project [com.septinary.common]
 *     @Type: ListLoadStyle
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年8月22日 下午2:47:02
 */
public enum ListLoadStyle {
	UNDEFINED(0),
	REFRESH(1),
	MORE(2),
	SINGLE(3),
	ALL(4);
	
	//枚举取值
	private final Integer value;
	
	//构造函数
	ListLoadStyle(Integer value) {
		this.value = value;
	}
	ListLoadStyle(String value) {
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
