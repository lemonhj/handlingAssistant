package com.septinary.common.type;

/**
 * 资源消息接口
 * @Filename: com.septinary.common.type.IResourceMessage.java of the project [com.septinary.common]
 *     @Type: IResourceMessage
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年7月27日 下午6:10:20
 */
public interface IResourceMessage {

	public String getLang();
	public void setLang(String lang);
	
	public String getKey();
	public void setKey(String key);
	
	public String getMessage();
	public void setMessage(String message);
	
	public String getMemo();
	public void setMemo(String memo);
}
