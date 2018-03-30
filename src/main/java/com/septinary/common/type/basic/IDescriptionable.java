package com.septinary.common.type.basic;

/**
 * 可描述对象接口
 * @Filename: com.septinary.common.type.IDescriptionable.java of the project [com.septinary.common]
 *     @Type: IDescriptionable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月26日下午4:43:15
 * 
 */
public interface IDescriptionable extends IExtrable, IKeywordsable, IMemoable, IUpdateable, IDeletedable {

	public String getDesc();
	public void setDesc(String desc);

}
