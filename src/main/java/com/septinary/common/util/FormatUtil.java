package com.septinary.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 格式化操作
 * @Filename: com.septinary.common.util.FormatUtil.java of the project [com.septinary.common]
 *     @Type: FormatUtil
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年8月3日 下午3:02:20
 */
abstract public class FormatUtil {

	/**
	 * 格式化字符串
	 * @param tagPattern占位标识
	 * @param format	格式字串
	 * @param params	替换参数
	 * @return String
	 */
	static public String Format(String tagPattern, String format, Object... params) {
		if(null==format || null==params || ArrayUtil.IsEmpty(params)) {
			return format;
		}
		for(Object param: params) format = format.replaceFirst(tagPattern, PatternMatchUtil.EscapeReplacement(StringUtil.StringNull(param)));
		return format;
	}
	
	/**
	 * 格式化字符串
	 * @param tagPattern占位标识
	 * @param format	格式字串
	 * @param params	替换参数
	 * @return
	 */
	static public String Format(String tagPattern, String format, Collection<?> params) {
		if(null==format || null==params || CollectionUtil.IsEmpty(params)) {
			return format;
		}
		for(Object param: params) format = format.replaceFirst(tagPattern,  PatternMatchUtil.EscapeReplacement(StringUtil.StringNull(param)));
		return format;
	}
	
	/**
	 * 格式化字符串(默认占位符{})
	 * @param format	占位符为：{}
	 * @param params	替换参数
	 * @return
	 */
	static public String Format(String format, Object... params) {
		return Format("{}", format, params);
	}
	
	/**
	 * 格式化字符串（带下标的占位符）
	 * @param placeholderBegin	占位符开始
	 * @param placeholderEnd	占位符结束
	 * @param format			格式字串
	 * @param params			替换参数
	 * @return	String
	 */
	static public String Format(String placeholderBegin, String placeholderEnd, String format, Object... params) {
		if(null==format || null==params || ArrayUtil.IsEmpty(params)) {
			return format;
		}
		for(int i=0; i<params.length; i++) {
			String tagPattern = PatternMatchUtil.Escape(placeholderBegin) + "\\s*"+i+"\\s*" + PatternMatchUtil.Escape(placeholderEnd);
			format = format.replaceAll(tagPattern, PatternMatchUtil.EscapeReplacement(StringUtil.String(params[i])));
		}
		return format;
	}

	/**
	 * 格式化字符串（带下标的占位符）
	 * @param placeholderBegin	占位符开始
	 * @param placeholderEnd	占位符结束
	 * @param format			格式字串
	 * @param params			替换参数
	 * @return	String
	 */
	static public String Format(String placeholderBegin, String placeholderEnd, String format, Collection<?> params) {
		return Format(placeholderBegin, placeholderEnd, format, params.toArray());
	}
	
	/**
	 * 格式化字符串（带key的占位符）
	 * @param placeholderBegin
	 * @param placeholderEnd
	 * @param format
	 * @param params
	 * @return
	 */
	static public String Format(String placeholderBegin, String placeholderEnd, String format, HashMap<String,Object> params) {
		return StringUtil.Replace(format, params, placeholderBegin, placeholderEnd);
	}
	
	/**
	 * 格式化字符串(默认占位符{})（带key的占位符）	如：{username}, {password}, ...等
	 * @param format
	 * @param params
	 * @return
	 */
	static public String Format(String format, HashMap<String,Object> params) {
		return StringUtil.Replace(format, params, "{", "}");
	}
	
	/**
	 * 标准格式化字符串
	 * @param format	格式字串
	 * @param params	替换参数
	 * @return String
	 * 说明：
	 * format中的占位符为标准格式串：
	 * 		%%						表示		%
	 * 		%b						表示		布尔类型
	 * 		%{\d}c|C				表示		字符类型		{\d}为输出宽度
	 * 		%{\d}[.]s|S				表示		字串类型		[.]为截取符号“...”
	 * 		%[0]{\d}d|i				表示		整数类型		[0]为填充0
	 * 		%[0]{\d}[.][0]{\d}f|F	表示		浮点类型
	 * 		%B						表示		2进制整型
	 * 		%o|O					表示		8进制整型
	 * 		%x|X					表示		16进制整型(带0x或者0X前缀)
	 * 		%u|U					表示		16进制整型
	 * 		%t|T(format)			表示		时间
	 * 		%m|M(locale)			表示		货币
	 * 		%a|A					表示		ASCII码
	 */
	static public String Formating(String format, Object... params) {
		StringBuilder sb = new StringBuilder();
		for(int i=0, index=0; i<format.length(); i++) {
			char c = format.charAt(i);
			if('%'!=c) {
				sb.append(c);
				continue;
			}
			String fromhere = format.substring(i);
            //转义%%
            if(fromhere.matches("^%%.*$")) {
            	sb.append(c);
            	i++;
            	continue;
            }
            //当前一个参数
			Object item = params[index];
        	String flag = null;
            //布尔%b
            if(fromhere.matches("^%b.*$")) {
            	Assert.IsTrue(ClassUtil.IsAssignableValue(Boolean.class, item), "Argument No."+(index+1)+": "+item+" is not a Boolean.");
            	index ++;
            	sb.append(null==item?"NULL":item.toString());
            	i += 1;
            	continue;
            }
            //匹配
            Pattern p = null;
            Matcher m = null;
            int length = 0, length2 = 0;
            //字符%{\d}c|C
            if(fromhere.matches("^%([1-9]\\d*)?[cC].*$")) {
            	Assert.IsTrue(ClassUtil.IsAssignableValue(Character.class, item), "Argument No."+(index+1)+": "+item+" is not a Character.");
            	index ++;
                p = Pattern.compile("^%([1-9]\\d*)?[cC].*$");
                m = p.matcher(fromhere);
                String len = "";
                if(m.find()) len = m.group(1);
                String append = null==item?"NULL":item.toString();
                if(StringUtil.Valid(len)) length = Integer.parseInt(len);
            	sb.append(StringUtil.Right(append, 0==length?append.length():length, " "));
            	i += StringUtil.String(len).length()+1;
            	continue;
            }
            //字串%{\d}[.]s|S
            if(fromhere.matches("^%([1-9]\\d*)?(\\.)?[sS].*$")) {
            	Assert.IsTrue(ClassUtil.IsAssignableValue(String.class, item), "Argument No."+(index+1)+": "+item+" is not a String.");
            	index ++;
                p = Pattern.compile("^%([1-9]\\d*)?(\\.)?[sS].*$");
                m = p.matcher(fromhere);
                String len = "";
                String dot = "";
                if(m.find()) {
                	len = m.group(1);
                	dot = m.group(2);
                }
                String append = null==item?"NULL":item.toString();
                if(StringUtil.Valid(len)) length = Integer.parseInt(len);
            	sb.append(StringUtil.Left(append, 0==length?append.length():length, StringUtil.Invalid(dot)?" ":dot));
            	i += StringUtil.String(len).length()+StringUtil.String(dot).length()+1;
            	continue;
            }
            //整数%[0]{\d}d|i
            if(fromhere.matches("^%(0)?([1-9]\\d*)?[di].*$")) {
            	Assert.IsTrue(
            		ClassUtil.IsAssignableValue(Short.class, item) 		||
            		ClassUtil.IsAssignableValue(Integer.class, item) 	||
            		ClassUtil.IsAssignableValue(Long.class, item)		||
            		ClassUtil.IsAssignableValue(BigInteger.class, item), "Argument No."+(index+1)+": "+item+" is not a Int Number."
            	);
            	index ++;
                p = Pattern.compile("^%(0)?([1-9]\\d*)?[di].*$");
                m = p.matcher(fromhere);
                String len = "";
                String fil = "";
                if(m.find()) {
                	fil = m.group(1);
                	len = m.group(2);
                }
                String append = null==item?"NULL":item.toString();
                if(StringUtil.Valid(len)) length = Integer.parseInt(len);
            	sb.append(StringUtil.Right(append, 0==length?append.length():length, StringUtil.Invalid(fil)?" ":fil));
            	i += StringUtil.String(len).length()+StringUtil.String(fil).length()+1;
            	continue;
            }
            //小数%[0]{\d}[.][0]{\d}f|F
            if(fromhere.matches("^%(0)?([1-9]\\d*)?((\\.)(0)?([1-9]\\d*)?)?[fF].*$")) {
            	Assert.IsTrue(ClassUtil.IsAssignableValue(Number.class, item), "Argument No."+(index+1)+": "+item+" is not a Number.");
            	index ++;
                p = Pattern.compile("^%(0)?([1-9]\\d*)?((\\.)(0)?([1-9]\\d*)?)?[fF].*$");
                m = p.matcher(fromhere);
                String len_1 = "";
                String len_2 = "";
                String fil_1 = "";
                String fil_2 = "";
                String dot = "";
                if(m.find()) {
                	fil_1 = m.group(1);
                	len_1 = m.group(2);
                	dot = m.group(4);
                	fil_2 = m.group(5);
                	len_2 = m.group(6);
                }
                String append = null;
                if(null==item) {
                	append = "NULL";
                } else {
                	String[] vals =  item.toString().split("\\.");
                	if(StringUtil.Valid(len_1)) length = Integer.parseInt(len_1);
                	if(StringUtil.Valid(len_2)) length2= Integer.parseInt(len_2);
                	String str1 = vals[0];
                	String str2 = 2<=vals.length ? vals[1] : "";
                	String integer = StringUtil.Right(str1, 0==length?str1.length():length, StringUtil.Invalid(fil_1)?" ":fil_1);
                	String decimal = StringUtil.Left(str2, 0==length2?str2.length():length2, StringUtil.Invalid(fil_2)?" ":fil_2);
                	append = integer + "." + decimal;
                }
            	sb.append(append);
            	i += StringUtil.String(fil_1).length()+StringUtil.String(len_1).length()+StringUtil.String(dot).length()+StringUtil.String(fil_2).length()+StringUtil.String(len_2).length()+1;
            	continue;
            }
            //二进制%B
            if(fromhere.matches("^%B.*$")) {
            	String value = null;
            	if(null==item) {
            		value = "NULL";
            	} else if(item instanceof Boolean) {
            		value = StringUtil.Right(Integer.toBinaryString(((boolean)item)?1:0), 1, "0");
            	} else if(item instanceof Byte) {
            		value = StringUtil.Right(Integer.toBinaryString((int)(byte)item), 8, "0");
            	} else if(item instanceof Character) {
            		value = StringUtil.Right(Integer.toBinaryString((int)(char)item), 8*2, "0");
            	} else if(item instanceof Short) {
            		value = StringUtil.Right(Integer.toBinaryString((int)(short)item), 8*2, "0");
            	} else if(item instanceof Integer) {
            		value = StringUtil.Right(Integer.toBinaryString((int)item), 8*4, "0");
            	} else if(item instanceof Float) {
            		value = StringUtil.Right(Integer.toBinaryString(Float.floatToIntBits((float)item)), 8*4, 0>(float)item?"1":"0");
            	} else if(item instanceof Long) {
            		value = StringUtil.Right(Long.toBinaryString((long)item), 8*8, "0");
            	} else if(item instanceof Double) {
            		value = StringUtil.Right(Long.toBinaryString(Double.doubleToLongBits((double)item)), 8*8, 0>(double)item?"1":"0");
            	} else {
            		throw new IllegalArgumentException("Argument No."+(index+1)+": "+item+" is invalid cast to binary.");
            	}
            	sb.append(value);
            	index ++;
            	i += 1;
            	continue;
            }
            //八进制%o|O
            if(fromhere.matches("^%[oO].*$")) {
            	flag = "O";
            	p = Pattern.compile("^%([oO]).*$");
            	m = p.matcher(fromhere);
            	if(m.find()) flag = m.group(1);
            	String value = null;
            	if(null==item) {
            		value = "NULL";
            	} else if(item instanceof Boolean) {
            		value = flag + StringUtil.Right(Integer.toOctalString(((boolean)item)?1:0), 1, "0");
            	} else if(item instanceof Byte) {
            		value = flag + StringUtil.Right(Integer.toOctalString((int)(byte)item), 4, "0");
            	} else if(item instanceof Character) {
            		value = flag + StringUtil.Right(Integer.toOctalString((int)(char)item), 4*2, "0");
            	} else if(item instanceof Short) {
            		value = flag + StringUtil.Right(Integer.toOctalString((int)(short)item), 4*2, "0");
            	} else if(item instanceof Integer) {
            		value = flag + StringUtil.Right(Integer.toOctalString((int)item), 4*4, "0");
            	} else if(item instanceof Long) {
            		value = flag + StringUtil.Right(Long.toOctalString((long)item), 4*8, "0");
            	} else {
            		throw new IllegalArgumentException("Argument No."+(index+1)+": "+item+" is invalid cast to octal.");
            	}
            	sb.append(value);
            	index ++;
            	i += 1;
            	continue;
            }
       	 	//十六进制(带0x或者0X前缀)%x|X
       		//十六进制%u|U
            if(fromhere.matches("^%[xXuU].*$")) {
            	flag = "0X";
            	p = Pattern.compile("^%([xXuU]).*$");
            	m = p.matcher(fromhere);
            	if(m.find()) flag = ("x".equalsIgnoreCase(m.group(1))?"0":"") + m.group(1);
            	String value = null;
            	if(null==item) {
            		value = "NULL";
            	} else if(item instanceof Boolean) {
            		value = flag + StringUtil.Right(Integer.toHexString(((boolean)item)?1:0), 1, "0");
            	} else if(item instanceof Byte) {
            		value = flag + StringUtil.Right(Integer.toHexString((int)(byte)item), 2, "0");
            	} else if(item instanceof Character) {
            		value = flag + StringUtil.Right(Integer.toHexString((int)(char)item), 2*2, "0");
            	} else if(item instanceof Short) {
            		value = flag + StringUtil.Right(Integer.toHexString((int)(short)item), 2*2, "0");
            	} else if(item instanceof Integer) {
            		value = flag + StringUtil.Right(Integer.toHexString((int)item), 2*4, "0");
            	} else if(item instanceof Float) {
            		String[] tmp = Float.toHexString((float)item).split("\\.");
            		flag = tmp[0] + ".";
            		value = flag + StringUtil.Right(tmp[1], 2*8, "0");
            	} else if(item instanceof Long) {
            		value = flag + StringUtil.Right(Long.toHexString((long)item), 2*16, "0");
            	} else if(item instanceof Double) {
            		String[] tmp = Double.toHexString((double)item).split("\\.");
            		flag = tmp[0] + ".";
            		value = flag + StringUtil.Right(tmp[1], 2*16, "0");
            	} else {
            		throw new IllegalArgumentException("Argument No."+(index+1)+": "+item+" is invalid cast to hex.");
            	}
            	sb.append(value);
            	index ++;
            	i += 1;
            	continue;
            }
       	 	//时间%t|T(format)
            if(fromhere.matches("^%[tT](\\((.*)\\))?.*$")) {
            	Assert.IsTrue(
            			//ClassUtil.IsAssignableValue(Timestamp.class, item) ||
            			//ClassUtil.IsAssignableValue(Time.class, item) ||
            			ClassUtil.IsAssignableValue(Long.class, item) ||
            			ClassUtil.IsAssignableValue(Date.class, item)
            			, "Argument No."+(index+1)+": "+item+" is not a Long|Date|Time|Datetime|Timestamp.");
            	String timeformat = null;
            	int taglen = 0;
            	p = Pattern.compile("^%[tT](\\((.*)\\))?.*$");
            	m = p.matcher(fromhere);
            	if(m.find()) {
            		taglen = StringUtil.String(m.group(1)).length();
            		timeformat = m.group(2);
            	}
            	String value = null;
            	if(null==item) {
            		value = "NULL";
            	} else if (item instanceof Long) {
            		value = StringUtil.Invalid(timeformat)?item.toString():DateTimeUtil.Format((long)item, timeformat);
            	} else if(item instanceof Date) {
            		value = StringUtil.Invalid(timeformat)?item.toString():DateTimeUtil.Format((Date)item, timeformat);
            	} else {
            		throw new IllegalArgumentException("Argument No."+(index+1)+": "+item+" is invalid cast to datetime.");
            	}
            	sb.append(value);
            	index ++;
            	i += taglen + 1;
            	continue;
            }
            //货币%m|M(locale)
            if(fromhere.matches("^%[mM](\\((.*)\\))?.*$")) {
            	Assert.IsTrue(ClassUtil.IsAssignableValue(Number.class, item), "Argument No."+(index+1)+": "+item+" is not a Money number.");
            	String locale = Locale.getDefault().toString();
            	int taglen = 0;
            	p = Pattern.compile("^%[mM](\\((.*)\\))?.*$");
            	m = p.matcher(fromhere);
            	if(m.find()) {
            		taglen = StringUtil.String(m.group(1)).length();
            		locale = m.group(2);
            	}
            	NumberFormat nf = NumberFormat.getPercentInstance(new Locale(locale));
            	String value = nf.format(item);
            	sb.append(value);
            	index ++;
            	i += taglen + 1;
            	continue;
            }
            //ASCII码%a|A
            if(fromhere.matches("^%[aA].*$")) {
            	if(ClassUtil.IsAssignableValue(String.class, item)) {
            		for(int j=0; j<((String)item).length(); j++) sb.append(CharUtil.ASCII(((String)item).charAt(j)));
            	} else {
            		byte[] bytes = {};
            		ByteArrayOutputStream bos = new ByteArrayOutputStream();
            		try {
	            		ObjectOutputStream oos = new ObjectOutputStream(bos);
	            		oos.writeObject(item);
	            		oos.flush();
	            		bytes = bos.toByteArray ();
	            		oos.close();
	            		bos.close();
            		} catch (IOException ex) {
            			ex.printStackTrace();
            		}
            		for(byte b: bytes) sb.append(CharUtil.ASCII(b));
            		index ++;
                	i += 1;
                	continue;
            	}
            }
            //不是任何格式：
            sb.append(c);
		}
		return sb.toString();
	}
	
	/**
	 * 获取成对出现的TAG之间的字符串数组
	 * @param string
	 * @param tagBegin
	 * @param tagEnd
	 * @return
	 */
	public static List<String> Pair(String string, char tagBegin, char tagEnd) {
		if(null==string) return null;
		
		Pattern p = null;
        Matcher m = null;
        p = Pattern.compile(tagBegin + "([^"+tagBegin+""+tagEnd+"]+)" + tagEnd);
        m = p.matcher(string);
        
        List<String> list = new ArrayList<String>();
        while(m.find()) list.add(m.group(1));
        
        return list;
	}
	public static List<String> Pair(String string, char tag) {
		return Pair(string, tag, tag);
	}
	
	/**
	 * 按照匹配模式解析出对象实体
	 * @param input
	 * @param pattern
	 * @return
	 * 
	 * input如：		10001001~weide001~weide001@gmail.com
	 * pattern如：	#{no, type=java.lang.String, pattern=(\\d{8})}~#{member.username, type=java.lang.String, pattern=(\\w{6,32})}~#{member.mailbox, type=java.lang.String, pattern=(\\w+@\\w+\\.\\w+)}
	 * 输出：
	 * class view {
	 * 		private String no = "10001001";
	 * 		private class Member member = {
	 * 			private String username = "weide001";
	 * 			private String mailbox = "weide001@gmail.com";
	 * 		};
	 * }
	 */
	public static <T> T Parse(String input, String pattern) {
		
		return null;
	}
}
