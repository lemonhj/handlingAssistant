package com.septinary.common.web.general.runtime.springmvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.septinary.common.core.exception.ConfigError;
import com.septinary.common.core.util.IFieldGetter;
import com.septinary.common.core.util.IFieldingable;
import com.septinary.common.core.util.ILoggingable;
import com.septinary.common.core.util.Loggerable;
import com.septinary.common.type.IField;
import com.septinary.common.type.IFieldValue;
import com.septinary.common.type.Serialization;
import com.septinary.common.util.Assert;
import com.septinary.common.util.StringUtil;

/**
 * 七进制通用字段定义配置读取类
 * @Filename: com.septinary.common.web.general.runtime.springmvc.SeptinaryField.java of the project [com.septinary.common.web]
 *     @Type: SeptinaryField
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年7月26日 下午5:50:15
 */
public class SeptinaryField {

	private static ILoggingable logger = Loggerable.GetLogger(SeptinaryField.class);
	
	//读取器
	private IFieldGetter getter = null;
	//读取缓存
	private HashMap<String,IField> fields = new HashMap<String,IField>();
	private HashMap<Class<?>,HashMap<String,IField>> clazzs = new HashMap<Class<?>,HashMap<String,IField>>();
	
	//单件
	private SeptinaryField() {}
	private static SeptinaryField septinaryField = new SeptinaryField();

	private IFieldGetter getGetter() {
		if( null==this.getter ) {
			this.setGetter( (IFieldGetter)ContextLoader.getCurrentWebApplicationContext().getBean("fieldService") );
		}
		Assert.NotNull(this.getter, "Dose not found the bean: fieldService on the current context: " + ContextLoader.getCurrentWebApplicationContext().getApplicationName());
		return getter;
	}

	private void setGetter(IFieldGetter getter) {
		this.getter = getter;
	}
	
	@SuppressWarnings("serial")
	private void put(IField field) {
		if(null==field) return;
		logger.trace("Cache the field [" + field.getParse() + "] - " + field.getName() + (null==field.getClazz()?"":"("+field.getClazz()+")") + ": {"
				+ "\r\n" + StringUtil.Implode(field.getValues(), "\r\n", new Serialization(){
					@Override public String serialize(Object object) {
						return "\t" + ((IFieldValue)object).getKey() + " (" + ((IFieldValue)object).getValue() + ": " + ((IFieldValue)object).getParse() + ")";
					}})
				+ "\r\n}"
		);
		this.fields.put(field.getName(), field);
	}

	private IField get(String name) {
		IField field = this.fields.get(name);
		
		if(null==field) {
			field = this.getGetter().get(name);
			if(null==field) throw new ConfigError("Has not found the field:[" + name + "] definition, please check up the configuration!");
			this.put(field);
		}
		
		return field;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<IField> get(Class<? extends Enum<?>> clazz) {
		List<IField> fields = null;
		HashMap<String,IField> hm = this.clazzs.get(clazz);
		if(null==hm) {
			hm = new HashMap<String,IField>();
			fields = this.getGetter().get(clazz);
			for(IField field: fields) {
				//保持一致性：即先前读取的将会一直生效，直到程序重新加载，而不是被后面读取的覆盖掉
				if(this.fields.containsKey(field.getName())) {
					field = this.fields.get(field.getName());
				} else {
					this.put(field);
				}
				hm.put(field.getName(), field);
			}
			this.clazzs.put(clazz, hm);
		} else {
			fields = new ArrayList(hm.values());
		}
		return fields;
	}
	
	static public IFieldingable GetFielder() {
		return new IFieldingable() {
			@Override public IField field(String name) {
				return septinaryField.get(name);
			}
			@Override public EnumFields fields(Class<? extends Enum<?>> clazz) {
				return new EnumFields( clazz, septinaryField.get(clazz) );
			}
			@SuppressWarnings("unchecked")
			@Override public EnumFields fields(String className) {
				try {
		    		return this.fields( (Class<? extends Enum<?>>)Class.forName(className) );
		    	} catch (ClassCastException | ClassNotFoundException e) {
		    		e.printStackTrace();
		    		return new EnumFields();
		    	}
			}
			@Override public IFieldValue value(String name, String key_or_value) {
				IField field = this.field(name);
				Assert.NotNull(field, "Not found the definition of field: " + name);
				IFieldValue fv = field.value(key_or_value);
				Assert.NotNull(fv, "Not found the definition of fieldvalue: " + name + "." + key_or_value);
				return fv;
			}
			@Override public IFieldValue value(String name_dot_keyOrValue) {
				if( null==name_dot_keyOrValue ) return null;
		    	String[] arr = name_dot_keyOrValue.split("\\.");
		    	Assert.IsTrue(2==arr.length, "String argument must be like {FileName}.{FieldValue:(key|value)}");
		    	return this.value(arr[0], arr[1]);
			}
			@Override public Enum<?> getEnum(String name, String key_or_value) {
				// TODO Auto-generated method stub
				return null;
			}
			@Override public Enum<?> getEnum(String name_dot_keyOrValue) {
				String[] arr = name_dot_keyOrValue.split("\\.");
				Assert.IsTrue(2==arr.length, "String argument must be like {FileName}.{FieldValue:(key|value)}");
		    	return this.getEnum(arr[0], arr[1]);
			}
		}; 
	}
}
