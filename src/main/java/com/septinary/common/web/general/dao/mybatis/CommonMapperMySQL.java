package com.septinary.common.web.general.dao.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用 MySQL Mapper 类
 * @Filename: com.septinary.common.web.general.dao.mybatis.CommonMapperMySQL.java of the project [com.septinary.common.web]
 *     @Type: CommonMapper
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月12日下午4:35:23
 * 
 * @param <T>
 */
public interface CommonMapperMySQL<T> extends Mapper<T>, MySqlMapper<T> {

}
