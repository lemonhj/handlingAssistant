package com.septinary.push.entity;

/**
 * 
* @ClassName: AppAttr 
* @Description: 推送目的App参数 
* @author 林波涛
* @date
*
 */
public class AppAttr {
	private String appId;
	
	private String appKey;
	
	private String master;
	
	private String host;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
}
