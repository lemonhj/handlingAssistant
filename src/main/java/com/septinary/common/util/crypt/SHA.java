package com.septinary.common.util.crypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * 非对称加密
 * @Filename: com.septinary.common.util.SHA.java of the project [com.septinary.common]
 *     @Type: SHA
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月22日下午5:56:00
 *
 */
public class SHA implements IEncryptable<String> {

	static private byte[] java_security_MessageDigest_sha(byte[] source) {

        try {
            // 获得SHA-1摘要算法的 MessageDigest 对象
            MessageDigest mdInstance = MessageDigest.getInstance("SHA");
            // 使用指定的字节更新摘要
            mdInstance.update(source);
            // 获得密文
            return mdInstance.digest();
        } catch (NoSuchAlgorithmException nsae) {
        	nsae.printStackTrace();
        }
        
        return null;
	}


	/**
	 * 字符串SHA加密
	 @method encrypt()
	 @see com.septinary.common.util.crypt.IEncryptable#encrypt(Object, HashMap)
	 @memo TODO
	 @param source	加密源字符串
	 @param options 加密选项		{"key":"xxx", "format":"${key}${source}", "case":"upper|lower"}
	 @return String
	 */
	@Override
	public String encrypt(String source, HashMap<Object, Object> options) {
		// TODO Auto-generated method stub
		// return null;

		String casive = null;
		//加密选项
		if(null!=options) {
			Object value = null;
			value = options.get("key");
			String key = null==value ? "" : value.toString();
			value = options.get("format");
			String format = null==value ? "" : value.toString();
			source = format.replace("${key}", key).replace("${source}", source);
			value = options.get("case");
			casive = null==value ? null : value.toString().matches("^(?i)(upper|lower)$") ? value.toString() : null;
		}

		byte[] sha = SHA.java_security_MessageDigest_sha(source.getBytes());
		if(null==sha) return null;
		
		StringBuilder sb = new StringBuilder();
		for (byte b : sha) {
			sb.append(String.format("%02X", b)); // 10进制转16进制: X表示以十六进制形式输出，02表示不足两位前面补0输出
		}

		String tmp = sb.toString();
		
        //密文字符串
        return (null==casive ? tmp : (casive.equalsIgnoreCase("upper")?tmp.toUpperCase() : (casive.equalsIgnoreCase("lower")?tmp.toLowerCase():tmp)));
	
	}

}
