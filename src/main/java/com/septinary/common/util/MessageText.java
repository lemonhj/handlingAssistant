package com.septinary.common.util;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.Collection;

/**
 * 文本消息
 * @Filename: com.septinary.common.web.general.util.springmvc.MessageText.java of the project [com.septinary.common.web.general]
 *     @Type: MessageText
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年7月28日 上午11:26:15
 */
public class MessageText extends Format implements com.septinary.common.util.IMessageText {
	private static final long serialVersionUID = 1L;
	
	private String code = null;
	private Object[] args = null;

	public MessageText(String code) {
		this.code = code;
	}

	public MessageText(String code, Object... arguments) {
		this.code = code;
		this.args = arguments;
	}
	
	/**
	 * 构造函数
	 * @param code
	 * @param args
	 * @param whatever	占位参数，无实际意义！！
	 */
	public MessageText(String code, Object[] args, Object whatever) {
		this.code = code;
		this.args = args;
	}
	
	public MessageText(String code, Collection<?> args) {
		this(code,args.toArray());
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	@Override
	public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "MessageText [code=" + code + ", args=" + Arrays.toString(args) + "]";
	}
}
