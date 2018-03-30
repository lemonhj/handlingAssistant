package com.septinary.common.sys.role;

import java.io.Serializable;

import com.septinary.common.core.basic.dto.BaseEntity;


/**
 * 平台用户
 * @Filename: com.septinary.common.sys.role.User.java of the project [com.septinary.common]
 *     @Type: User
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016-5-27 13:34:08
 *
 */
@SuppressWarnings("serial")
abstract public class User<PK extends Serializable, SK extends Serializable, Type extends Serializable, State extends Serializable> extends BaseEntity implements IUserable<PK, SK, Type, State> {
	
	private PK id;
	
	private SK code;
	
	private String host;

	private String username;
	
	private String password;
	
	private Type type;
	
	private State state;

	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}

	public SK getCode() {
		return code;
	}

	public void setCode(SK code) {
		this.code = code;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
