package com.septinary.common.type;

import java.util.HashMap;
import java.util.Map;

/**
 * 证书对象
 * @Filename: com.septinary.common.type.Token.java of the project [com.septinary.common]
 *     @Type: Token
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年8月2日 下午1:10:19
 */
public class Token<T> extends TokenData<T> {

	private Map<String,Object> options;
	
	public Token(){}
	public Token(T tokendata){
		super(tokendata);
	}
	public Token(TokenData<T> token) {
		super(token);
	}
	public Token(Token<T> token) {
		super(token);
		this.setOptions(token.getOptions());
	}
	public Token(T tokendata, Map<String,Object> options) {
		super(tokendata);
		this.setOptions(options);
	}

	public Map<String, Object> getOptions() {
		return options;
	}

	public Token<T> setOptions(Map<String, Object> options) {
		this.options = options;
		return this;
	}
	
	public Token<T> add(String key, Object value) {
		if(null==this.options) this.options = new HashMap<String, Object>();
		this.options.put(key, value);
		return this;
	}
}
