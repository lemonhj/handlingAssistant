package com.septinary.common.type;

import com.septinary.common.util.NumericUtil;

/**
 * 字段取值
 * @Filename: com.septinary.common.type.FieldValue.java of the project [com.septinary.common]
 *     @Type: FieldValue
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月22日下午3:40:20
 *
 */
public class FieldValue extends Pair<String,String> implements IFieldValue {
	private IField field;

	private String parse;
	
	public FieldValue(IField field) {
		super(null, null);
		this.field = field;
		this.parse = null;
	}
	
	public FieldValue(IField field, String key) {
		super(key, null);
		this.field = field;
		this.parse = null;
	}
	
	public FieldValue(IField field, String key, String value) {
		super(key, value);
		this.field = field;
		this.parse = null;
	}
	
	public FieldValue(IField field, String key, String value, String parse) {
		super(key, value);
		this.field = field;
		this.parse = parse;
	}
	
	public FieldValue(String key, String value, String parse) {
		this(null, key, value, parse);
	}
	
	public FieldValue(String key, String value) {
		this(null, key, value, null);
	}
	
	public FieldValue(String key) {
		this(null, key, null, null);
	}
	
	public FieldValue() {
		this(null, null, null, null);
	}

	public IField getField() {
		return field;
	}

	public void setField(IField field) {
		this.field = field;
	}

	public String getParse() {
		return parse;
	}

	public void setParse(String parse) {
		this.parse = parse;
	}

	@Override
	public Integer getValue(Class<Integer> clazz) {
		return NumericUtil.ToInteger(this.getValue());
	}
	
	/**
	 * 相等判断？
	 * @param fv
	 * @return
	 */
	public boolean equals(FieldValue fv) {
		if(null==fv) return false;
		if(null==this.field&&null!=fv.field) return false;
		if(null==this.field || !this.field.equals(fv.field)) return false;
		if(null==this.getKey() || null==this.getValue()) return false;
		return ( this.getKey().equals(fv.getKey()) && this.getValue().equals(fv.getValue()) );
	}
	
	/**
	 * 相等判断？
	 * @param key_or_value Key 或者 Value值
	 * @return
	 */
	public boolean equals(String key_or_value) {
		if(null==key_or_value) return false;
		return ( key_or_value.equals(this.getValue()) || key_or_value.equals(this.getKey()) );
	}
}
