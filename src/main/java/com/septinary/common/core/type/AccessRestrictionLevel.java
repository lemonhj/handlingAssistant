package com.septinary.common.core.type;

/**
 * 访问限制级别
 * @Filename: com.septinary.common.core.type.AccessRestrictionLevel.java of the project [com.septinary.common]
 *     @Type: AccessRestrictionLevel
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日下午9:33:10
 *
 */
public enum AccessRestrictionLevel {
	UNDEFINED,	//未定义，与public一致
	PUBLIC,		//公开级别：均可访问，不作限制
	SECURITY,	//安全级别：访问受限，安全控制
	SESSION,	//会话级别：会话访问，不是入口
	LOGIN,		//认证级别：登录访问，认证通过
	PERMISSION	//授权级别：授权访问，权限控制
}
