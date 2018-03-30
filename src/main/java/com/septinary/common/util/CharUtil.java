package com.septinary.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符工具
 * @Filename: com.septinary.common.util.CharUtil.java of the project [com.septinary.common]
 *     @Type: CharUtil
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年9月19日 上午10:17:04
 */
public abstract class CharUtil {

	static private final String[] ascii_00_31 = {
		"NUL~null",
		"SOH~start of headline",
		"STX~start of text",
		"ETX~end of text",
		"EOT~end of transmission",
		"ENQ~enquiry",
		"ACK~acknowledge",
		"BEL~bell",
		" BS~backspace",
		" HT~horizontal tab",
		" LF~NL line feed, new line",
		" VT~vertical tab",
		" FF~NP form feed, new page",
		" CR~carriage return",
		" SO~shift out",
		" SI~shift in",
		"DLE~data link escape",
		"DC1~device control 1",
		"DC2~device control 2",
		"DC3~device control 3",
		"DC4~device control 4",
		"NAK~negative acknowledge",
		"SYN~synchronous idle",
		"ETB~end of trans. block",
		"CAN~cancel",
		" EM~end of medium",
		"SUB~substitute",
		"ESC~escape",
		" FS~file separator",
		" GS~group separator",
		" RS~record separator",
		" US~unit separator"
	};
	static private final String ascii_127 = "DEL~delete";
	
	/**
	 * 转换成ASCII码字符串
	 * @param c
	 * @return
	 */
	public static String ASCII(char c) {
		return (0<=c&&c<=31) ? ("{"+ascii_00_31[c].split("~")[0]+"}") : (127==c?("{"+ascii_127.split("~")[0]+"}"):(c+""));
	}
	
	/**
	 * 转换成ASCII码字符串
	 * @param b
	 * @return
	 */
	public static String ASCII(byte b) {
		return 0>b ? ("{"+b+"}") : ASCII((char)b);
	}
	
	/**
	 * 转换成ASCII码字符串
	 * @param s
	 * @return
	 */
	public static String ASCII(short s) {
		return (Byte.MIN_VALUE<=s&&s<=Byte.MAX_VALUE) ? ASCII((byte)s) : ("[BAD]");
	}
	
	/**
	 * ASCII取值转换成字符
	 * @param ascii_value
	 * @return
	 */
	public static char Char(byte ascii_value) {
		return (char)ascii_value;
	}
	
	/**
	 * ASCII取值转换成字符
	 * @param ascii_value
	 * @return
	 */
	public static char Char(short ascii_value) {
		return Char((byte)ascii_value);
	}
	
	public static char Char(String ascii_string) {
		if(StringUtil.Invalid(ascii_string)) return '\0';
		if(1==ascii_string.length()) return ascii_string.charAt(0);
		if(ascii_string.matches("^\\{(.+)\\}$") || ascii_string.matches("^(.+)?$")) {
			String tag = null;
			Pattern p = Pattern.compile("^\\{?(.+)\\}?$");
            Matcher m = p.matcher(ascii_string);
            if(m.find()) tag = m.group(1);
            short i = 0;
            for(String string: ascii_00_31) {
				if(string.split("~")[0].equalsIgnoreCase(tag)) return Char((short)i);
				if(string.split("~")[0].equalsIgnoreCase(" "+tag)) return Char((short)i);
				if(("{"+string.split("~")[0]+"}").equalsIgnoreCase(tag)) return Char((short)i);
				if(("{"+string.split("~")[0]+"}").equalsIgnoreCase(" "+tag)) return Char((short)i);
				i ++;
			}
			if(ascii_127.equals(ascii_string)) return Char((short)127);
		}
		return '\0';
	}
	
	/**
	 * 字符转字符串
	 * @param c
	 * @return
	 */
	public static String String(char c) {
		return (""+c);
	}
}
