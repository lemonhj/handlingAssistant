package com.septinary.common.type;

/**
 * 资源消息定义
 * @Filename: com.septinary.common.type.ResourceMessage.java of the project [api.free-car.cn]
 *     @Type: ResourceMessage
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年7月27日 下午6:17:06
 */
public class ResourceMessage implements IResourceMessage {

	private String lang;
	
	private String key;
	
	private String message;
	
	private String memo;

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
