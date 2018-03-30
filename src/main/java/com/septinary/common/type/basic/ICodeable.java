package com.septinary.common.type.basic;

/**
 * 编号接口
 * @Filename: com.septinary.common.type.ICodeable.java of the project [com.septinary.common]
 *     @Type: ICodeable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日下午9:57:26
 * 
 * @param <PK>
 * @param <SK>
 */
public interface ICodeable<PK, SK> extends IIdable<PK> {

	public SK getCode();

	public void setCode(SK code);
}
