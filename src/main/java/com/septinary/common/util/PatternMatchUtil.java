package com.septinary.common.util;

/**
 * 模式匹配操作
 * @Filename: com.septinary.common.util.PatternMatchUtil.java of the project [com.septinary.common]
 *     @Type: PatternMatchUtil
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月13日上午1:04:22
 *
 */
public abstract class PatternMatchUtil {

	/**
	 * 简单匹配
	 * Match a String against the given pattern, supporting the following simple
	 * pattern styles: "xxx*", "*xxx", "*xxx*" and "xxx*yyy" matches (with an
	 * arbitrary number of pattern parts), as well as direct equality.
	 * @param pattern the pattern to match against
	 * @param str the String to match
	 * @return whether the String matches the given pattern
	 */
	public static boolean SimpleMatch(String pattern, String str) {
		if (pattern == null || str == null) {
			return false;
		}
		int firstIndex = pattern.indexOf('*');
		if (firstIndex == -1) {
			return pattern.equals(str);
		}
		if (firstIndex == 0) {
			if (pattern.length() == 1) {
				return true;
			}
			int nextIndex = pattern.indexOf('*', firstIndex + 1);
			if (nextIndex == -1) {
				return str.endsWith(pattern.substring(1));
			}
			String part = pattern.substring(1, nextIndex);
			if ("".equals(part)) {
				return SimpleMatch(pattern.substring(nextIndex), str);
			}
			int partIndex = str.indexOf(part);
			while (partIndex != -1) {
				if (SimpleMatch(pattern.substring(nextIndex), str.substring(partIndex + part.length()))) {
					return true;
				}
				partIndex = str.indexOf(part, partIndex + 1);
			}
			return false;
		}
		return (str.length() >= firstIndex &&
				pattern.substring(0, firstIndex).equals(str.substring(0, firstIndex)) &&
				SimpleMatch(pattern.substring(firstIndex), str.substring(firstIndex)));
	}

	/**
	 * 简单匹配
	 * Match a String against the given patterns, supporting the following simple
	 * pattern styles: "xxx*", "*xxx", "*xxx*" and "xxx*yyy" matches (with an
	 * arbitrary number of pattern parts), as well as direct equality.
	 * @param patterns the patterns to match against
	 * @param str the String to match
	 * @return whether the String matches any of the given patterns
	 */
	public static boolean SimpleMatch(String[] patterns, String str) {
		if (patterns != null) {
			for (String pattern : patterns) {
				if (SimpleMatch(pattern, str)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 转义正则特殊字符 $()*+.[]?\^{},|
	 * 
	 * @param pattern
	 * @return
	 */
	public static String Escape(String pattern) {
		if(StringUtil.Invalid(pattern)) return pattern;
		
		String[] arr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };
		for (String key : arr) {
			if (pattern.contains(key)) pattern = pattern.replace(key, "\\" + key);
		}
		
		return pattern;
	}
	
	/**
	 * 解决java.lang.IllegalArgumentException: Illegal group reference异常
	 * @param replacement
	 * @return
	 * 
	 * String replaceAll(regex, replacement); 和 String replaceFirst(regex, replacement); 函数,
	 * 由于第一个参数支持正则表达式，replacement中出现“$”,会按照$1$2的分组模式进行匹配，当编译器发现“$”
	 * 后跟的不是整数的时候，就会抛出“非法的组引用”的异常。所以我们在使用这两个函数的时候要特别小心。如果
	 * 真的要把String中的字符替换成"$AAA"的话，可以对replacement 进行“$”的转义处理：
	 * String content = "3213123,17,0,19900101,\"aaa$aaaaa258456\",1,\"如果上天给你一次机会,你是否还会继续那么\",\\\\N";
	 * content = content.replace("$", "\\$");
	 */
	public static String EscapeReplacement(String replacement) {
		if(null==replacement) return null;
		replacement = replacement.replace("$", "\\$");
		return replacement;
	}
}
