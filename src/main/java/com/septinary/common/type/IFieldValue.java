package com.septinary.common.type;

/**
 * 字段取值接口
 * @Filename: com.septinary.common.type.IFieldValue.java of the project [com.septinary.common]
 *     @Type: IFieldValue
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日下午10:36:41
 *
 */
public interface IFieldValue {

	public IField getField();

	public void setField(IField field);
	
	public String getKey();
	
	public void setKey(String key);
	
	/**
	 * 尝试转为数值
	 * @param clazz
	 * @return
	 */
	public Integer getValue(Class<Integer> clazz);
	public String getValue();
	
	public void setValue(String value);

	public String getParse();

	public void setParse(String parse);
	
	public boolean equals(FieldValue fv);
	public boolean equals(String key_or_value);
}
