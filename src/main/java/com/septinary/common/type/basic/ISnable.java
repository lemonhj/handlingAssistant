package com.septinary.common.type.basic;

/**
 * 通用SN接口
 * @Filename: com.septinary.common.type.ISnable.java of the project [com.septinary.common]
 *     @Type: ISnable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日下午10:00:45
 * 
 * @param <PK>
 * @param <SK>
 */
public interface ISnable<PK, SK> extends IIdable<PK> {

	public SK getSn();

	public void setSn(SK sn);
}
