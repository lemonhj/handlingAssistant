package com.septinary.common.type.basic;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 更新接口
 * @Filename: com.septinary.common.type.IUpdateable.java of the project [com.septinary.common]
 *     @Type: IUpdateable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年5月19日上午11:57:10
 *
 */
public interface IUpdateable {

	public Date getCreate();

	public void setCreate(Date create);

	public Timestamp getUpdate();

	public void setUpdate(Timestamp update);
}
