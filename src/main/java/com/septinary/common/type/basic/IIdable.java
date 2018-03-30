package com.septinary.common.type.basic;

/**
 * 通用ID接口
 * @Filename: com.septinary.common.type.IIdable.java of the project [com.septinary.common]
 *     @Type: IIdable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日上午11:46:30
 * 
 * @param <PK>
 */
public interface IIdable<PK> {

	public PK getId();

	public void setId(PK id);
}
