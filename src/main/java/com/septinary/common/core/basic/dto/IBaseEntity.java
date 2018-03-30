package com.septinary.common.core.basic.dto;

/**
 * 数据实体基类接口
 * @Filename: com.septinary.common.core.basic.dto.IBaseEntity.java of the project [com.septinary.common]
 *     @Type: IBaseEntity
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月12日下午12:17:56
 *
 */
public interface IBaseEntity extends IDto {

    public interface CloneCallback {
    	public Object clone(String fieldname, Object originalvalue);
    }

}
