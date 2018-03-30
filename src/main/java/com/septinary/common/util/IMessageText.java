package com.septinary.common.util;

/**
 * 文本消息接口
 * @Filename: com.septinary.common.util.IMessageText.java of the project [com.septinary.common]
 *     @Type: IMessageText
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年7月28日 上午11:22:53
 */
public interface IMessageText {

	public String getCode();

	public void setCode(String code);

	public Object[] getArgs();

	public void setArgs(Object[] args);
}
