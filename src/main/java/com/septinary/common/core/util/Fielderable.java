package com.septinary.common.core.util;

import java.util.HashMap;

import com.septinary.common.core.util.IFieldingable.EnumFields;
import com.septinary.common.manager.FielderManager;
import com.septinary.common.type.IField;
import com.septinary.common.type.IFieldValue;

/**
 * 字段配置读取适配器
 * @Filename: com.septinary.common.core.util.Fielderable.java of the project [com.septinary.common]
 *     @Type: Fielderable
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年7月26日 下午3:42:33
 */
abstract public class Fielderable {

    private IFieldingable fielder = null;

    public Fielderable() {
    	System.out.println("Getting fielder from sigleton:fielderManager ...");
        this.fielder = FielderManager.Instance().getFielder();
    }
    
    //静态读取
    static private Fielderable fielderable = new Fielderable(){};
    static public Fielderable GetFielder() {
    	return fielderable;
    }
    
    //字段配置读取适配
    //获取字段配置
	public IField field(String name) {
		return this.fielder.field(name);
	}
	
	//获取字段集合
	public EnumFields fields(Class<? extends Enum<?>> clazz) {
		return this.fielder.fields(clazz);
	}
	public EnumFields fields(String className) {
		return this.fielder.fields(className);
	}
	
	//获取取值键值Map
	public HashMap<String,IFieldValue> values(String name) {
		IField field = this.field(name);
		return null==field?null:field.values();
	}
	
	//获取字段取值
	public IFieldValue value(String name, String key_or_value) {
		return this.fielder.value(name, key_or_value);
	}
	public IFieldValue value(String name_dot_keyOrValue) {
		return this.fielder.value(name_dot_keyOrValue);
	}
	
	//获取字段枚举
	public Enum<?> getEnum(String name, String key_or_value) {
		return this.fielder.getEnum(name, key_or_value);
	}
	public Enum<?> getEnum(String name_dot_keyOrValue) {
		return this.fielder.getEnum(name_dot_keyOrValue);
	}
}
