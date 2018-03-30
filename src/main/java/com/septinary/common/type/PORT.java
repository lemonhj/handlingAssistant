package com.septinary.common.type;

/**
 * 端口
 * @Filename: com.septinary.common.type.PORT.java of the project [com.septinary.common]
 *     @Type: PORT
 *     @Desc: 端口取值
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年2月23日下午3:49:27
 *
 */
public class PORT {

	/**
	 * 端口值
	 */
	private int port;

	public PORT(Integer value) {
		this.setPort(value);
	}

	public PORT(String port) {
		this.setPort(port);
	}
	
	public int getPort() {
		return this.port;
	}
	
	public void setPort(int value) throws IllegalArgumentException {
		if(0>value || 65535<value) throw new IllegalArgumentException(value + " is invalid port value[0~65535]");
		this.port = value;
	}
	
	public void setPort(String port) throws IllegalArgumentException {
		try {
			this.setPort(Integer.valueOf(port));
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentException(port + " is invalid port string");
		}
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.port);
	}
}
