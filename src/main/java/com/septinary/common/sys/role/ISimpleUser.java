package com.septinary.common.sys.role;

/**
 * 简单用户接口
 * @Filename: com.septinary.common.sys.role.ISimpleUser.java of the project [com.septinary.common]
 *     @Type: ISimpleUser
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年6月6日 下午4:53:17
 */
public interface ISimpleUser extends IUserable<Long,String,Integer,Integer> {

	public ISimpleUser toUser();
}
