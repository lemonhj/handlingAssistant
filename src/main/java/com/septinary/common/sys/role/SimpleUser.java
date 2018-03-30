package com.septinary.common.sys.role;

/**
 * 简单用户实体
 * @Filename: com.septinary.common.sys.role.SimpleUser.java of the project [com.septinary.common]
 *     @Type: SimpleUser
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年6月6日 下午4:55:10
 */
@SuppressWarnings("serial")
public class SimpleUser extends User<Long,String,Integer,Integer> implements ISimpleUser {

	@Override
	public ISimpleUser toUser() {
		return this;
	}

}

