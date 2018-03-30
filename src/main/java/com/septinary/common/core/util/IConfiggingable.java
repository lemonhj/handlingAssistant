package com.septinary.common.core.util;

/**
 * 配置读取/写入接口
 * @Filename: com.septinary.common.core.util.IConfiggingable.java of the project [com.septinary.common]
 * @Type: IConfiggingable
 * @Desc: TODO
 * @Author: macbook[weide<weide001@gmail.com>]
 * @Created: 2016-03-03 13:23:00
 */
public interface IConfiggingable extends IConfigGetter, IConfigSetter {

	
	public byte[] getBytes(String key);
	
	public IConfigSetter setBytes(String key, byte[] value);

}
