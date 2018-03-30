package com.septinary.common.type;

/**
 * 取值
 * @Filename: com.septinary.common.type.Value.java of the project [com.septinary.common]
 *     @Type: Value
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年7月8日 下午1:17:45
 */
public class Value<T> {

	private T value;

	private IFieldValue type;
	
	public Value(){}
	
	public Value(T value, IFieldValue type){
		this.value = value;
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public IFieldValue getType() {
		return type;
	}

	public void setType(IFieldValue type) {
		this.type = type;
	}
}
