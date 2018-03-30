package com.septinary.common.type.basic;


/**
 * 名称对象接口
 * @Filename: com.septinary.common.type.INameable.java of the project [com.septinary.common]
 *     @Type: INameable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日下午9:58:55
 * 
 */
public interface INameable extends ITagable, IFullnameable {

	public String getName();
	public void setName(String name);
}
