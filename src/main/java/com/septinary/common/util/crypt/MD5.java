package com.septinary.common.util.crypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * 非对称加密
 * MD5的全称是Message-Digest Algorithm 5，在90年代初由MIT的计算机科学实验室和RSA Data Security Inc发明，经MD2、MD3和MD4发展而来。
 * @Filename: com.septinary.common.util.MD5.java of the project [com.septinary.common]
 *     @Type: MD5
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月22日下午5:47:23
 *
 *		MD5将任意长度的“字节串”变换成一个128bit的大整数，并且它是一个不可逆的字符串变换算法，换句话说就是，即使你看到源程序和算法描述，也无法将一个
 *	MD5的值变换回原始的字符串，从数学原理上说，是因为原始的字符串有无穷多个，这有点象不存在反函数的数学函数。
 *		MD5的典型应用是对一段Message(字节串)产生fingerprint(指纹)，以防止被“篡改”。举个例子，你将一段话写在一个叫 readme.txt文件中，并对这个
 *	readme.txt产生一个MD5的值并记录在案，然后你可以传播这个文件给别人，别人如果修改了文件中的任何内容，你对这个文件重新计算MD5时就会发现。如果再有
 *	一个第三方的认证机构，用MD5还可以防止文件作者的“抵赖”，这就是所谓的数字签名应用。
 *		MD5还广泛用于加密和解密技术上，在很多操作系统中，用户的密码是以MD5值（或类似的其它算法）的方式保存的， 用户Login的时候，系统是把用户输入的密
 *	码计算成MD5值，然后再去和系统中保存的MD5值进行比较，而系统并不“知道”用户的密码是什么。
 *
 *	MD5相对MD4所作的改进： 
 *		1.增加了第四轮. 
 *		2.每一步均有唯一的加法常数. 
 *		3.为减弱第二轮中函数G的对称性从(X&Y)|(X&Z)|(Y&Z)变为(X&Z)|(Y&(~Z)) 
 *		4.第一步加上了上一步的结果,这将引起更快的雪崩效应. 
 *		5.改变了第二轮和第三轮中访问消息子分组的次序,使其更不相似. 
 *		6.近似优化了每一轮中的循环左移位移量以实现更快的雪崩效应.各轮的位移量互不相同.
 */
public class MD5 implements IEncryptable<String> {
	
	//十六进制表示字符集
	static private final char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	
	/**
	 * Java自带的MD5工具
	 @method MD5: java_security_MessageDigest_md5()
	 @memo TODO
	 @param source
	 @return byte[]
	 	只适合用于加密比较短的字节流或者字符串，倘若目标是比较大（如超过1G）的文件时，此方法效率较低，不能用于实际应用
	 */
	static private byte[] java_security_MessageDigest_md5(byte[] source) {

        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInstance = MessageDigest.getInstance("MD5");
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
	 * 字符串MD5加密
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
		// return target;
		
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
		byte[] md5 = MD5.java_security_MessageDigest_md5(source.getBytes());
		if(null==md5) return null;

        // 把密文字节流转换成十六进制的字符流形式
        int len = md5.length;
        char dest[] = new char[len * 2];	//每个字节用 16 进制表示的话，使用两个字符
        for (int i=0,j=0; i < len; i++) {	//j表示转换结果中对应的字符位置，i表示从第一个字节开始，对每一个字节,转换成 16 进制字符的转换
            byte byte0 = md5[i];	//取第 i 个字节
            dest[j++] = MD5.hexDigits[byte0 >>> 4 & 0xf];	//取字节中高 4 位的数字转换, >>>为逻辑右移，将符号位一起右移
            dest[j++] = MD5.hexDigits[byte0 & 0xf];			//取字节中低 4 位的数字转换
        }
        
        String tmp = new String(dest);
        //密文字符串
        return (null==casive ? tmp : (casive.equalsIgnoreCase("upper")?tmp.toUpperCase() : (casive.equalsIgnoreCase("lower")?tmp.toLowerCase():tmp)));
	
	}

}
