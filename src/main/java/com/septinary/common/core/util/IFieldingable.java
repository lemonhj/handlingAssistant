package com.septinary.common.core.util;

import java.util.List;

import com.septinary.common.type.IField;
import com.septinary.common.type.IFieldValue;

/**
 * 字段配置读取接口
 * @Filename: com.septinary.common.core.util.IFieldingable.java of the project [com.septinary.common]
 *     @Type: IFieldingable
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年7月26日 下午3:46:37
 */
public interface IFieldingable {
	
	/**
     * 根据字段名称获取字段对象
     * @param name
     * @return
     */
	public IField field(String name);
	
	/**
     * 根据枚举类获取字段对象集合
     * @param clazz
     * @return
     */
	public EnumFields fields(Class<? extends Enum<?>> clazz);
	public EnumFields fields(String className);
	
	/**
     * 根据字段名称、取值Key|Value获取取值对象
     * @param name
     * @param key_or_value
     * @return
     */
    public IFieldValue value(String name, String key_or_value);
    public IFieldValue value(String name_dot_keyOrValue);
    
    /**
     * 根据字段名称、取值Key|Value获取取值枚举值
     * @param name
     * @param key_or_value
     * @return
     */
    public Enum<?> getEnum(String name, String key_or_value);
    public Enum<?> getEnum(String name_dot_keyOrValue);
	

	/**
	 * 枚举类型对应的字段配置对象集合
	 * @Filename: com.septinary.common.system.util.IFieldingable.java of the project [com.septinary.common]
	 *     @Type: IFieldingable.EnumFields
	 *     @Desc: TODO
	 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
	 *  @Created: 2016年7月26日15:50:01
	 */
	public static class EnumFields {
		private Class<? extends Enum<?>> clazz = null;
		private List<IField> fields = null;
		
		public EnumFields() {}
		
		public EnumFields(List<IField> fields) {
			this.setFields(fields);
		}
		
		public EnumFields(Class<? extends Enum<?>> clazz, List<IField> fields) {
			this.setClazz(clazz);
			this.setFields(fields);
		}
		
		public EnumFields(Class<? extends Enum<?>> clazz) {
			this.setClazz(clazz);
		}

		public Class<? extends Enum<?>> getClazz() {
			return clazz;
		}

		public void setClazz(Class<? extends Enum<?>> clazz) {
			this.clazz = clazz;
		}

		public List<IField> getFields() {
			return fields;
		}

		public void setFields(List<IField> fields) {
			this.fields = fields;
		}
		
		/**
		 * 根据字段名称获取字段对象
		 * @param name
		 * @return
		 */
		public IField get(String name) {
			if(null==this.fields || 0>=this.fields.size()) return null;
			return this.fields.get( this.fields.indexOf(name) );
		}

		/**
		 * 获取默认的一个字段对象：即字段名称就是枚举类名称的简短部分，若不存在对应的，那么取第一个元素
		 * @return
		 */
		public IField get() {
			IField field = null;
			if(null!=this.clazz) this.get( this.clazz.getSimpleName() );
			if(null==field && null!=this.fields) field = this.get(0);
			return field;
		}
		
		/**
		 * 获取指定小标的一个字段对象
		 * @param index
		 * @return
		 */
		public IField get(int index) {
			if(null==this.fields) return null;
			return this.fields.get(index);
		}
	}
}
