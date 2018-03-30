package com.septinary.common.type.basic;

/**
 * 删除接口
 * @Filename: com.septinary.common.type.IDeletedable.java of the project [com.septinary.common]
 *     @Type: IDeletedable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月22日下午11:30:44
 *
 */
public interface IDeletedable {

	public Boolean getDeleted();
	public Boolean isDeleted();
	
	public void setDeleted(Boolean deleted);
}
