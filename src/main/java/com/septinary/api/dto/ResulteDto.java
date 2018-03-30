package com.septinary.api.dto;

/**
 * 
* @ClassName: ResulteDto 
* @Description: 接口统一返回数据格式 
* @author lin.tb lin.tb@septinary.com
* @date 2015年4月17日 上午10:53:44 
*
 */
public class ResulteDto {
	private Object data = new Object();
	private Integer code = 0;
	private String hint = "";
	
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	
}
