package com.septinary.common.type;

/**
 * 证书数据
 * @Filename: com.septinary.common.type.TokenData.java of the project [com.septinary.common]
 *     @Type: TokenData
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年8月11日 下午7:08:23
 *  @param <T>
 */
public class TokenData<T> {

	private T token;
	
	public TokenData(){}
	public TokenData(T tokendata) {
		this.setToken(tokendata);
	}
	public TokenData(TokenData<T> token) {
		this(token.getToken());
	}

	public T getToken() {
		return token;
	}

	public TokenData<T> setToken(T token) {
		this.token = token;
		return this;
	}
}
