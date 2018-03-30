package com.septinary.common.type;

import java.util.HashMap;
import java.util.List;

/**
 * 枚举字段接口
 * @Filename: com.septinary.common.type.IField.java of the project [com.septinary.common]
 *     @Type: IField
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日下午10:36:20
 *
 */
public interface IField {
	public String getClazz();

	public void setClazz(String clazz);
	
	public String getName();

	public void setName(String name);

	public String getParse();

	public void setParse(String parse);

	public IFieldValue getDatatype();

	public void setDatatype(IFieldValue datatype);

	public IFieldValue getDataunit();

	public void setDataunit(IFieldValue dataunit);

	public List<IFieldValue> getValues();

	public void setValues(List<IFieldValue> values);
	
	public IFieldValue value(String key_or_value);
	public IFieldValue byKey(String key);
	public IFieldValue byValue(String value);
	
	public HashMap<String,IFieldValue> values();
	
	public boolean equals(Field field);
	public boolean equals(String name);
}
