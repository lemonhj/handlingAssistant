package com.septinary.common.sys.role;

import java.io.Serializable;

import com.septinary.common.type.basic.ICodeable;

/**
 * 用户实体接口
 * @Filename: com.septinary.common.sys.role.IUserable.java of the project [com.septinary.common]
 *     @Type: IUserable
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年5月30日 下午8:33:29
 *  @param <PK>
 *  @param <SK>
 *  @param <Type>
 *  @param <State>
 */
public interface IUserable<PK extends Serializable, SK extends Serializable, Type extends Serializable, State extends Serializable> extends ICodeable<PK, SK>, Serializable {

	public String getUsername();

	public void setUsername(String username);

	public String getPassword();

	public void setPassword(String password);

	public Type getType();

	public void setType(Type type);

	public State getState();

	public void setState(State state);
}
