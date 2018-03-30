package com.septinary.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列/反序列操作
 * @Filename: com.septinary.common.util.SerializationUtil.java of the project [com.septinary.common]
 *     @Type: SerializationUtil
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月13日上午1:08:06
 *
 */
public abstract class SerializationUtil {

	/**
	 * 序列化
	 * Serialize the given object to a byte array.
	 * @param object the object to serialize
	 * @return an array of bytes representing the object in a portable fashion
	 */
	public static byte[] Serialize(Object object) {
		if (object == null) {
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			oos.flush();
		} catch (IOException ex) {
			throw new IllegalArgumentException("Failed to serialize object of type: " + object.getClass(), ex);
		}
		return baos.toByteArray();
	}

	/**
	 * 反序列化
	 * Deserialize the byte array into an object.
	 * @param bytes a serialized object
	 * @return the result of deserializing the bytes
	 */
	public static Object Deserialize(byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
			return ois.readObject();
		} catch (IOException ex) {
			throw new IllegalArgumentException("Failed to deserialize object", ex);
		} catch (ClassNotFoundException ex) {
			throw new IllegalStateException("Failed to deserialize object type", ex);
		}
	}

}
