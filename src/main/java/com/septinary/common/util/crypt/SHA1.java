package com.septinary.common.util.crypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import org.apache.commons.codec.binary.Hex;

/**
 * 非对称加密
 * 由于MD5 与SHA-1均是从MD4 发展而来，它们的结构和强度等特性有很多相似之处
 * SHA-1与MD5 的最大区别在于其摘要比MD5 摘要长 32 比特（1byte=8bit，相当于长4byte，转换16进制后比MD5多8个字符）。 
 * 对于强行攻击，：MD5 是2128 数量级的操作，SHA-1 是2160数量级的操作。
 * 对于相同摘要的两个报文的难度：MD5是 264 是数量级的操作，SHA-1 是280 数量级的操作。
 * 因而，SHA-1 对强行攻击的强度更大。 但由于SHA-1 的循环步骤比MD5 多（80:64）且要处理的缓存大（160 比特:128 比特），SHA-1 的运行速度比MD5 慢。
 * 
 * @Filename: com.septinary.common.util.SHA1.java of the project [com.septinary.common]
 *     @Type: SHA1
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月22日下午5:56:09
 *
 */
public class SHA1 implements IEncryptable<String> {
	
	static private byte[] java_security_MessageDigest_sha(byte[] source) {

        try {
            // 获得SHA-1摘要算法的 MessageDigest 对象
            MessageDigest mdInstance = MessageDigest.getInstance("SHA-1");
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
	 * 字符串SHA1加密
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

		//密文字节流
		byte[] sha = SHA1.java_security_MessageDigest_sha(source.getBytes());
		if(null==sha) return null;

        // 把密文字节流转换成十六进制的字符流形式
		String tmp = String.valueOf(Hex.encodeHex(sha));
		
        //密文字符串
        return (null==casive ? tmp : (casive.equalsIgnoreCase("upper")?tmp.toUpperCase() : (casive.equalsIgnoreCase("lower")?tmp.toLowerCase():tmp)));
	
	}

}
