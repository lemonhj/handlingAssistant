package com.septinary.common.type;

/**
 * IP
 * @Filename: com.septinary.common.type.IP.java of the project [com.septinary.common]
 *     @Type: IP
 *     @Desc: IP地址类型
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2015年5月13日下午9:42:28
 * 
 */
public class IP {

	/**
	 * IP v4值
	 */
	private int value;

	public IP(Integer value) {
		this.setValue(value);
	}

	public IP(String ip) {
		this.setValue(ip);
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public void setValue(String ip) throws IllegalArgumentException {
        int ipv4 = 0;
        
        try {
            String[] arr = ip.split("\\.");
    		byte[] bytes = new byte[4];
            bytes[0] = (byte) (Integer.parseInt(arr[0]) & 0xff);
            bytes[1] = (byte) (Integer.parseInt(arr[1]) & 0xff);
            bytes[2] = (byte) (Integer.parseInt(arr[2]) & 0xff);
            bytes[3] = (byte) (Integer.parseInt(arr[3]) & 0xff);
            
            ipv4 |= ((bytes[3] <<  0) & 0x000000ff);
            ipv4 |= ((bytes[2] <<  8) & 0x0000ff00);
            ipv4 |= ((bytes[1] << 16) & 0x00ff0000);
            ipv4 |= ((bytes[0] << 24) & 0xff000000);
        } catch (Exception e) {
            throw new IllegalArgumentException(ip + " is invalid IPv4");
        }
        
        this.value = ipv4;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append((this.value >> 24) & 0xff)
			.append('.')
			.append((this.value >> 16) & 0xff)
			.append('.')
			.append((this.value >>  8) & 0xff)
			.append('.')
			.append((this.value >>  0) & 0xff)
			.toString();
	}
}
