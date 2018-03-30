package com.septinary.common.web.general.dao.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.SqlServerMapper;

/**
 * 通用 SQLServer Mapper 类
 * @Filename: com.septinary.common.web.general.dao.mybatis.CommonMapperSQLServer.java of the project [com.septinary.common.web]
 *     @Type: CommonMapperSQLServer
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月12日下午4:39:55
 * 
 * @param <T>
 */
public interface CommonMapperSQLServer<T> extends Mapper<T>, SqlServerMapper<T> {

}
