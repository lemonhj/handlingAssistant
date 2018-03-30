package com.septinary.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

/**
* UUID操作
* @Filename: com.septinary.common.util.UUIDUtil.java of the project [com.septinary.common]
*     @Type: GUIDUtil
*     @Desc: TODO
*   @Author: macbook[weide<weide001@gmail.com>]
*  @Created: 2016年5月11日下午10:11:44
*
*/
public abstract class UUIDUtil {

	private static Random myRand;
	private static SecureRandom mySecureRand;

	private static String s_id;
	private static final int PAD_BELOW = 0x10;
	private static final int TWO_BYTES = 0xFF;
	
	static {
		mySecureRand = new SecureRandom();
		long secureInitializer = mySecureRand.nextLong();
		myRand = new Random(secureInitializer);
		try {
			s_id = InetAddress.getLocalHost().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 利用jdk1.5以后的java.util.UUID直接生成
	 @method UUIDUtil: Uuid()
	 @memo TODO
	 @return String
	 */
	public static String Uuid() {
		return Uuid(true);
	}
	public static String Uuid(boolean upper) {
		String result = UUID.randomUUID().toString();
		return upper ? result.toUpperCase() : result.toLowerCase();
	}
	
	/**
	 * 模拟生成GUID
	 @method UUIDUtil: Guid()
	 @memo TODO
	 @return String
	 */
	public static String Guid() {
		return Guid(false, true);
	}
	public static String Guid(boolean secure) {
		return Guid(secure, true);
	}
	public static String Guid(boolean secure, boolean upper) {
		MessageDigest md5 = null;
		StringBuffer sbValueBeforeMD5 = new StringBuffer(128);
		
		String valueBeforeMD5 = "";
		String valueAfterMD5 = "";
	
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error: " + e);
		}

		try {
			long time = System.currentTimeMillis();
			long rand = 0;

			if (secure) {
				rand = mySecureRand.nextLong();
			} else {
				rand = myRand.nextLong();
			}
			
			sbValueBeforeMD5.append(s_id);
			sbValueBeforeMD5.append(":");
			sbValueBeforeMD5.append(Long.toString(time));
			sbValueBeforeMD5.append(":");
			sbValueBeforeMD5.append(Long.toString(rand));
	
			valueBeforeMD5 = sbValueBeforeMD5.toString();
			md5.update(valueBeforeMD5.getBytes());
			
			byte[] array = md5.digest();
			StringBuffer sb = new StringBuffer(32);
			for (int j = 0; j < array.length; ++j) {
				int b = array[j] & TWO_BYTES;
				if (b < PAD_BELOW)
				sb.append('0');
				sb.append(Integer.toHexString(b));
			}
			
			valueAfterMD5 = sb.toString();
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}
		
		//转换成标准格式的GUID
		String raw = upper ? valueAfterMD5.toUpperCase() : valueAfterMD5.toLowerCase();
		StringBuffer sb = new StringBuffer(64);
		sb.append(raw.substring(0, 8));
		sb.append("-");
		sb.append(raw.substring(8, 12));
		sb.append("-");
		sb.append(raw.substring(12, 16));
		sb.append("-");
		sb.append(raw.substring(16, 20));
		sb.append("-");
		sb.append(raw.substring(20));
		
		return sb.toString();
	}
}
