package com.beigebigdata.bdCourt.ca.api.view;

/**
 * 会员实体视图
 * 
 * @author lihuayang
 *
 */
public class MemberView {
	private Long memId;
	private String memUsername;
	private String memPassword;
	private String memType;
	private String memState;
	private String memTel;

	public Long getMemId() {
		return memId;
	}

	public void setMemId(Long memId) {
		this.memId = memId;
	}

	public String getMemUsername() {
		return memUsername;
	}

	public void setMemUsername(String memUsername) {
		this.memUsername = memUsername;
	}

	public String getMemPassword() {
		return memPassword;
	}

	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}

	public String getMemType() {
		return memType;
	}

	public void setMemType(String memType) {
		this.memType = memType;
	}

	public String getMemState() {
		return memState;
	}

	public void setMemState(String memState) {
		this.memState = memState;
	}

	public String getMemTel() {
		return memTel;
	}

	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}

}
