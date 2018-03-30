package com.septinary.common.type;

/**
 * 序列化接口（to String & from String）
 * @Filename: com.septinary.common.type.ISerializable.java of the project [com.septinary.common]
 *     @Type: Serializable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月24日下午4:26:07
 *
 */
public interface ISerializable extends java.io.Serializable {
	
	public String serialize(Object object);
	
	public Object deserialize(String string) throws RuntimeException;
}
