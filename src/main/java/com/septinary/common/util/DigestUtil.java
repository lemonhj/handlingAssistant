package com.septinary.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 摘要操作	主要为MD5
 * @Filename: com.septinary.common.util.DigestUtil.java of the project [com.septinary.common]
 *     @Type: DigestUtil
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月13日下午12:24:57
 *
 */
public abstract class DigestUtil {

	private static final String MD5_ALGORITHM_NAME = "MD5";

	private static final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


	/**
	 * MD5加密
	 * Calculate the MD5 digest of the given bytes.
	 * @param bytes the bytes to calculate the digest over
	 * @return the digest
	 */
	public static byte[] Md5Digest(byte[] bytes) {
		return digest(MD5_ALGORITHM_NAME, bytes);
	}

	/**
	 * MD5加密
	 * Calculate the MD5 digest of the given InputStream.
	 * @param inputStream the inputStream to calculate the digest over
	 * @return the digest
	 */
	public static byte[] Md5Digest(InputStream inputStream) throws IOException {
		return digest(MD5_ALGORITHM_NAME, inputStream);
	}

	/**
	 * MD5加密
	 * Return a hexadecimal string representation of the MD5 digest of the given
	 * bytes.
	 * @param bytes the bytes to calculate the digest over
	 * @return a hexadecimal digest string
	 */
	public static String Md5DigestAsHex(byte[] bytes) {
		return digestAsHexString(MD5_ALGORITHM_NAME, bytes);
	}

	/**
	 * MD5加密
	 * Return a hexadecimal string representation of the MD5 digest of the given
	 * inputStream.
	 * @param inputStream the inputStream to calculate the digest over
	 * @return a hexadecimal digest string
	 */
	public static String Md5DigestAsHex(InputStream inputStream) throws IOException {
		return digestAsHexString(MD5_ALGORITHM_NAME, inputStream);
	}

	/**
	 * MD5加密
	 * Append a hexadecimal string representation of the MD5 digest of the given
	 * bytes to the given {@link StringBuilder}.
	 * @param bytes the bytes to calculate the digest over
	 * @param builder the string builder to append the digest to
	 * @return the given string builder
	 */
	public static StringBuilder AppendMd5DigestAsHex(byte[] bytes, StringBuilder builder) {
		return appendDigestAsHex(MD5_ALGORITHM_NAME, bytes, builder);
	}

	/**
	 * MD5加密
	 * Append a hexadecimal string representation of the MD5 digest of the given
	 * inputStream to the given {@link StringBuilder}.
	 * @param inputStream the inputStream to calculate the digest over
	 * @param builder the string builder to append the digest to
	 * @return the given string builder
	 */
	public static StringBuilder AppendMd5DigestAsHex(InputStream inputStream, StringBuilder builder) throws IOException {
		return appendDigestAsHex(MD5_ALGORITHM_NAME, inputStream, builder);
	}

	private static MessageDigest getDigest(String algorithm) {
		try {
			return MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException ex) {
			throw new IllegalStateException("Could not find MessageDigest with algorithm \"" + algorithm + "\"", ex);
		}
	}

	private static byte[] digest(String algorithm, byte[] bytes) {
		return getDigest(algorithm).digest(bytes);
	}

	private static byte[] digest(String algorithm, InputStream inputStream) throws IOException {
		MessageDigest messageDigest = getDigest(algorithm);
		if (inputStream instanceof UpdateMessageDigestInputStream){
			((UpdateMessageDigestInputStream) inputStream).updateMessageDigest(messageDigest);
			return messageDigest.digest();
		} else{
			return messageDigest.digest(StreamUtil.CopyToByteArray(inputStream));
		}
	}

	private static String digestAsHexString(String algorithm, byte[] bytes) {
		char[] hexDigest = digestAsHexChars(algorithm, bytes);
		return new String(hexDigest);
	}

	private static String digestAsHexString(String algorithm, InputStream inputStream) throws IOException {
		char[] hexDigest = digestAsHexChars(algorithm, inputStream);
		return new String(hexDigest);
	}

	private static StringBuilder appendDigestAsHex(String algorithm, byte[] bytes, StringBuilder builder) {
		char[] hexDigest = digestAsHexChars(algorithm, bytes);
		return builder.append(hexDigest);
	}

	private static StringBuilder appendDigestAsHex(String algorithm, InputStream inputStream, StringBuilder builder) throws IOException {

		char[] hexDigest = digestAsHexChars(algorithm, inputStream);
		return builder.append(hexDigest);
	}

	private static char[] digestAsHexChars(String algorithm, byte[] bytes) {
		byte[] digest = digest(algorithm, bytes);
		return encodeHex(digest);
	}

	private static char[] digestAsHexChars(String algorithm, InputStream inputStream) throws IOException {
		byte[] digest = digest(algorithm, inputStream);
		return encodeHex(digest);
	}

	private static char[] encodeHex(byte[] bytes) {
		char chars[] = new char[32];
		for (int i = 0; i < chars.length; i = i + 2) {
			byte b = bytes[i / 2];
			chars[i] = HEX_CHARS[(b >>> 0x4) & 0xf];
			chars[i + 1] = HEX_CHARS[b & 0xf];
		}
		return chars;
	}
}
