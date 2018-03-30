package com.septinary.common.type;

import java.util.HashMap;
import java.util.List;

import com.septinary.common.util.CollectionUtil;
import com.septinary.common.util.ICallbackable;

/**
 * 枚举字段定义
 * @Filename: com.septinary.common.type.Field.java of the project [com.septinary.common]
 *     @Type: Field
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月22日下午3:42:29
 *
 */
public class Field implements IField {
	private String clazz;

	private String name;
	
	private String parse;
	
	private IFieldValue datatype;
	
	private IFieldValue dataunit;
	
	private List<IFieldValue> values;
	
	public Field(List<IFieldValue> values) {
		this(values, null);
	}
	
	public Field(List<IFieldValue> values, String clazz) {
		this(values, clazz, null);
	}
	
	public Field(List<IFieldValue> values, String clazz, String name) {
		this(values, clazz, name, null);
	}
	
	public Field(List<IFieldValue> values, String clazz, String name, String parse) {
		this(values, clazz, name, parse, null);
	}
	
	public Field(List<IFieldValue> values, String clazz, String name, String parse, IFieldValue datatype) {
		this(values, clazz, name, parse, datatype, null);
	}
	
	public Field(List<IFieldValue> values, String clazz, String name, String parse, IFieldValue datatype, IFieldValue dataunit) {
		this(clazz, name, parse, datatype, dataunit, values);
	}
	
	public Field(String clazz, String name, String parse, IFieldValue datatype, IFieldValue dataunit, List<IFieldValue> values) {
		this.setClazz(clazz);
		this.setName(name);
		this.setParse(parse);
		this.setDatatype(datatype);
		this.setDataunit(dataunit);
		this.setValues(values);
	}
	
	public Field(String clazz, String name, String parse, IFieldValue datatype, IFieldValue dataunit) {
		this(clazz, name, parse, datatype, dataunit, null);
	}
	
	public Field(String clazz, String name, String parse, IFieldValue datatype) {
		this(clazz, name, parse, datatype, null);
	}
	
	public Field(String clazz, String name, String parse) {
		this(clazz, name, parse, null);
	}
	
	public Field(String clazz, String name) {
		this(clazz, name, null);
	}
	
	public Field(String clazz) {
		this(clazz, null);
	}
	
	public Field() {}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParse() {
		return parse;
	}

	public void setParse(String parse) {
		this.parse = parse;
	}

	public IFieldValue getDatatype() {
		if(null!=datatype) datatype.setField(this);
		return datatype;
	}

	public void setDatatype(IFieldValue datatype) {
		if(null!=datatype) datatype.setField(this);
		this.datatype = datatype;
	}

	public IFieldValue getDataunit() {
		if(null!=dataunit) dataunit.setField(this);
		return dataunit;
	}

	public void setDataunit(IFieldValue dataunit) {
		if(null!=dataunit) dataunit.setField(this);
		this.dataunit = dataunit;
	}

	public List<IFieldValue> getValues() {
		final Field self = this;
		CollectionUtil.EachDo(values, new ICallbackable(){
			@Override public Object callback(Object first, Object... arguments) { ((IFieldValue)first).setField(self); return first; }}
		);
		return values;
	}

	public void setValues(List<IFieldValue> values) {
		final Field self = this;
		CollectionUtil.EachDo(values, new ICallbackable(){
			@Override public Object callback(Object first, Object... arguments) { ((IFieldValue)first).setField(self); return first; }}
		);
		this.values = values;
	}
	
	/**
	 * 根据key|value获取取值对象
	 * @param key_or_value
	 * @return
	 */
	public IFieldValue value(String key_or_value){
		if(null==key_or_value || null==this.getValues()) return null;
		for(IFieldValue fv: this.getValues()) if(fv.equals(key_or_value)) return fv;
		return null;
	}

	/**
	 * 根据键名获取取值对象
	 * @param key
	 * @return
	 */
	public IFieldValue byKey(String key){
		return this.value(key);
	}

	/**
	 * 根据键值获取取值对象
	 * @param value
	 * @return
	 */
	public IFieldValue byValue(String value){
		return this.value(value);
	}
	
	/**
	 * 获取键值Map集合
	 */
	public HashMap<String,IFieldValue> values() {
		HashMap<String,IFieldValue> hm = new HashMap<String,IFieldValue>();
		for(IFieldValue fv: values) hm.put(fv.getKey(), fv);
		return hm;
	}

	/**
	 * 相等判断？
	 * @param field
	 * @return
	 */
	public boolean equals(Field field) {
		return this.equals(field.getName());
	}
	
	/**
	 * 相等判断？
	 * @param name 字段名称 或者 枚举类名称
	 * @return
	 */
	public boolean equals(String name) {
		if( null==name ) return false;
		return ( name.equals(this.getName()) || name.equals(this.getClazz()) );
	}
	
}
