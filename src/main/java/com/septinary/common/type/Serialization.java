package com.septinary.common.type;

/**
 * 序列化抽象基类（to String & from String）
 * @Filename: com.septinary.common.type.Serializition.java of the project [com.septinary.common]
 *     @Type: Serializition
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月24日下午4:25:56
 *
 */
abstract public class Serialization implements ISerializable {
	private static final long serialVersionUID = 1L;

	@Override
	public String serialize(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object deserialize(String string) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

}
