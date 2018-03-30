package com.septinary.common.util;

import java.util.Random;

/**
 * 随机操作
 * @Filename: com.septinary.common.util.RandomUtil.java of the project [com.septinary.common]
 *     @Type: RandomUtil
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年2月17日下午11:10:09
 *
 */
abstract public class RandomUtil {
	
	/**
	 * 获取随机数字
	 @method RandomUtil: Number()
	 @memo TODO
	 @param length
	 @return String
	 */
	public static String Number(int length){
		String chars = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		
		for(int i=0 ; i<length; ++i){
			int number = random.nextInt(10);//[0,10]
			sb.append(chars.charAt(number));
		}
		
		return sb.toString();
	}
	
	/**
	 * 获取随机小写
	 @method RandomUtil: Lower()
	 @memo TODO
	 @param length
	 @return String
	 */
	public static String Lower(int length){
		String chars = "abcdefghijklmnopqrstuvwxyz";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		
		for(int i=0 ; i<length; ++i){
			int number = random.nextInt(26);//[0,26]
			sb.append(chars.charAt(number));
		}
		
		return sb.toString();
	}
	
	/**
	 * 获取随机大写
	 @method RandomUtil: Upper()
	 @memo TODO
	 @param length
	 @return String
	 */
	public static String Upper(int length){
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		
		for(int i=0 ; i<length; ++i){
			int number = random.nextInt(26);//[0,26]
			sb.append(chars.charAt(number));
		}
		
		return sb.toString();
	}
	
	/**
	 * 获取随机字母
	 @method RandomUtil: Letter()
	 @memo TODO
	 @param length
	 @return String
	 */
	public static String Letter(int length){
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		
		for(int i=0 ; i<length; ++i){
			int number = random.nextInt(52);//[0,52]
			sb.append(chars.charAt(number));
		}
		
		return sb.toString();
	}
	
	/**
	 * 获取随机词汇字串
	 @method RandomUtil: Word()
	 @memo TODO
	 @param length
	 @return String
	 */
	public static String Word(int length){
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		
		for(int i=0 ; i<length; ++i){
			int number = random.nextInt(64);//[0,64]
			sb.append(chars.charAt(number));
		}
		
		return sb.toString();
	}
	
}
