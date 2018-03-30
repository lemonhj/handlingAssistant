package com.septinary.common.type;

import java.util.Map;

/**
 * 简单证书对象
 * @Filename: com.septinary.common.type.TokenSimple.java of the project [com.septinary.common]
 *     @Type: TokenSimple
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年8月11日 下午7:05:31
 */
public class TokenSimple extends Token<String> {

	public TokenSimple(){}
	public TokenSimple(String tokendata){
		super(tokendata);
	}
	public TokenSimple(TokenData<String> token) {
		super(token);
	}
	public TokenSimple(Token<String> token) {
		super(token);
		this.setOptions(token.getOptions());
	}
	public TokenSimple(String tokendata, Map<String,Object> options) {
		super(tokendata);
		this.setOptions(options);
	}
	
	public TokenSimple add(String key, Object value) {
		super.add(key, value);
		return this;
	}
}
