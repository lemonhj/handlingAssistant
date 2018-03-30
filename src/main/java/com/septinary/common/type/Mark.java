package com.septinary.common.type;

/**
 * 标记
 * @Filename: com.septinary.common.type.Mark.java of the project [com.septinary.common]
 *     @Type: Mark
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年7月8日 下午2:58:11
 *  @param <MarkType>
 *  @param <ValueType>
 */
public class Mark<MarkType, ValueType> {

	private MarkType mark;
	
	private ValueType value;
	
	public Mark(){}
	
	public Mark(MarkType mark, ValueType value){
		this.mark = mark;
		this.value = value;
	}

	public MarkType getMark() {
		return mark;
	}

	public void setMark(MarkType mark) {
		this.mark = mark;
	}

	public ValueType getValue() {
		return value;
	}

	public void setValue(ValueType value) {
		this.value = value;
	}
}
