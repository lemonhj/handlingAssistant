package com.septinary.common.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.septinary.common.type.ISerializable;

/**
 * 字符串操作
 * @Filename: com.septinary.common.util.StringUtil.java of the project [com.septinary.common]
 * @Type: StringUtil
 * @Desc: TODO
 * @Author: macbook[weide<weide001@gmail.com>]
 * @Created: 2016-03-01 19:15:00
 */
abstract public class StringUtil {
	/**
	 * 字符串转换方式
	 */
	public enum Style {
	    normal,                     	//原值
	    uppercase,                  	//转换为大写
	    lowercase,                  	//转换为小写
	    
	    camelhumpToUnderline,           //驼峰转下划线
	    camelhumpToPackage,           	//驼峰转包点符
	    camelhumpToPath,           		//驼峰转路径符
	    camelhumpToUnderlineUppercase,  //驼峰转下划线大写形式
	    camelhumpToPackageUppercase,	//驼峰转包点符大写形式
	    camelhumpToPathUppercase,		//驼峰转路径符大写形式
	    camelhumpToUnderlineLowercase,  //驼峰转下划线小写形式
	    camelhumpToPackageLowercase,	//驼峰转包点符小写形式
	    camelhumpToPathLowercase,		//驼峰转路径符小写形式
	    
	    camelhumpFromUnderline,         //下划线转驼峰
	    camelhumpFromPackage,          	//包点符转驼峰
	    camelhumpFromPath,           	//路径符转驼峰
	}

	//---------------------------------------------------------------------
	// 格式化字符串转换
	//---------------------------------------------------------------------
    
    /**
     * 根据指定的样式进行转换
     *
     * @param str
     * @param style
     * @return
     */
    public static String ConvertByStyle(String string, Style style) {
    	if(null==string) return null;
        switch (style) {
            case uppercase:
                return string.toUpperCase();
            case lowercase:
                return string.toLowerCase();
            case camelhumpToUnderline:           	//驼峰转下划线
                return CamelhumpToUnderline(string);
            case camelhumpToPackage:           		//驼峰转包点符
            	return CamelhumpToPackage(string);
            case camelhumpToPath:           		//驼峰转路径符
            	return CamelhumpToPath(string);
            case camelhumpToUnderlineUppercase:     //驼峰转下划线大写形式
            	string = CamelhumpToUnderline(string).toUpperCase();
            	return '_'==string.charAt(0) ? string.substring(1) : string;
            case camelhumpToPackageUppercase:		//驼峰转包点符大写形式
            	string = CamelhumpToPackage(string).toUpperCase();
            	return '.'==string.charAt(0) ? string.substring(1) : string;
            case camelhumpToPathUppercase:			//驼峰转路径符大写形式
            	string = CamelhumpToPath(string).toUpperCase();
            	return File.separatorChar==string.charAt(0) ? string.substring(1) : string;
            case camelhumpToUnderlineLowercase:     //驼峰转下划线小写形式
                string = CamelhumpToUnderline(string).toLowerCase();
            	return '_'==string.charAt(0) ? string.substring(1) : string;
            case camelhumpToPackageLowercase:		//驼峰转包点符小写形式
            	string = CamelhumpToPackage(string).toLowerCase();
            	return '.'==string.charAt(0) ? string.substring(1) : string;
            case camelhumpToPathLowercase:			//驼峰转路径符小写形式
            	string = CamelhumpToPath(string).toLowerCase();
            	return File.separatorChar==string.charAt(0) ? string.substring(1) : string;
            case camelhumpFromUnderline:         	//下划线转驼峰
            	return UnderlineToCamelhump(string);
            case camelhumpFromPackage:          	//包点符转驼峰
            	return PackageToCamelhump(string);
            case camelhumpFromPath:           		//路径符转驼峰
            	return PathToCamelhump(string);
            case normal:
            default:
                break;
        }
        return string;
    }

	private static String camelhump_to_xxx(String string,char separator,boolean orignal,boolean ignorfirst) {
        final int size;
        final char[] chars;
        //size*3/2+1的原因：下划线的数目 < 源串/2 + 1，因为每个单词至少2个字母...
        final StringBuilder sb = new StringBuilder( (size = (chars = string.toCharArray()).length) * 3 / 2 + 1 );
        char c = '\0';
        for (int i = 0; i < size; i++) {
            c = chars[i];
            if (IsUppercaseAlpha(c)) {
                sb.append(separator).append(orignal?c:ToLowerAscii(c));
            } else {
                sb.append(c);
            }
        }
        return ignorfirst&&sb.charAt(0)==separator ? sb.substring(1) : sb.toString();
    }
	
	/**
     * 将驼峰风格替换为下划线风格
     * 
     * 例如：_comSeptinaryCommon  -->  _com_Septinary_Common
     */
    public static String CamelhumpToUnderline(String string) {
    	return camelhump_to_xxx(string,'_',true,false);
    }

	/**
     * 将驼峰风格替换为包点符风格
     * 
     * 例如：.comSeptinaryCommon  -->  .com.Septinary.Sommon
     */
    public static String CamelhumpToPackage(String string) {
    	return camelhump_to_xxx(string,'.',true,false);
	}

	/**
     * 将驼峰风格替换为路径符风格
     * 
     * 例如：/comSeptinaryCommon  -->  /com/Septinary/Common
     */
    public static String CamelhumpToPath(String string) {
    	return camelhump_to_xxx(string,File.separatorChar,true,false);
	}

	private static String camelhump_from_xxx(String string,char separator) {
        Matcher matcher = Pattern.compile(separator+"[a-zA-Z]").matcher(string);
        StringBuilder builder = new StringBuilder(string);
        for (int i = 0; matcher.find(); i++) {
            builder.replace(matcher.start() - i, matcher.end() - i, matcher.group().substring(1).toUpperCase());
        }
        if (Character.isUpperCase(builder.charAt(0))) {
            builder.replace(0, 1, String.valueOf(Character.toLowerCase(builder.charAt(0))));
        }
        return builder.toString();
	}
	
    /**
     * 将下划线风格替换为驼峰风格
     * 
     * 例如：_com_septinary_Common  -->  comSeptinaryCommon
     */
    public static String UnderlineToCamelhump(String string) {
    	return camelhump_from_xxx(string,'_');
    }

    /**
     * 将包点符风格替换为驼峰风格
     * 
     * 例如：.com.septinary.Common  -->  comSeptinaryCommon
     */
    public static String PackageToCamelhump(String string) {
    	return camelhump_from_xxx(string,'.');
    }

    /**
     * 将路径符风格替换为驼峰风格
     * 
     * 例如：/com/septinary/Common  -->  comSeptinaryCommon
     */
    public static String PathToCamelhump(String string) {
    	return camelhump_from_xxx(string,File.separatorChar);
    }

	//---------------------------------------------------------------------
	// 基本操作
	//---------------------------------------------------------------------

    /**
     * 是否无效字符串，即是否为 NULL 或者 空白串
     @method StringUtil: Invalid()
     @memo TODO
     @param str
     @return boolean
     */
    static public boolean Invalid(String string) {
        return (null == string || "".equals(string.trim()));
    }
    
    /**
     * 是否有效字符串。即是否 非空
     @method StringUtil: Valid()
     @memo TODO
     @param string
     @return boolean
     */
    static public boolean Valid(String string) {
    	return !Invalid(string);
    }
    
    /**
     * 是否空白字符串
     * @param string
     * @return
     */
    static public boolean White(String string) {
    	return null==string ? true : Invalid(string.trim());
    }
    
    /**
     * 是否可见字符串
     * @param string
     * @return
     */
    static public boolean Visiable(String string) {
    	return !White(string);
    }
    
    /**
     * 空 - 即 NULL 或者 ""
     *
     * @param string
     * @return
     */
    public static boolean IsEmpty(String string) {
        return string == null || string.length() == 0;
    }
    public static boolean IsEmpty(Object object) {
        return object == null || (null==object?"":object.toString()).length() == 0;
    }

    /**
     * 非空
     *
     * @param str
     * @return
     */
    public static boolean IsNotEmpty(String string) {
        return !IsEmpty(string);
    }
    public static boolean IsNotEmpty(Object object) {
        return !IsEmpty(object);
    }
    
    /**
     * 转字符串
     @method StringUtil: String()
     @memo TODO
     @param object
     @return String
     */
    public static String String(Object object) {
    	return (null==object?null:object.toString());
    }
    public static String StringNull(Object object) {
    	return (null==object?"NULL":object.toString());
    }
    public static String StringNotNull(Object object) {
    	return (null==object?"":object.toString());
    }
    
    /**
     * 判断字符串是否为NULL	备注：通常用于解析字符串，因为"null", "Null", "nUlL", ..., "NULL"均被解析为null!
     * @param string
     * @return
     */
    public static boolean Null(String string) {
    	return NullString(string);
    }
    public static boolean NullString(String string) {
    	return (null==string || "NULL".equalsIgnoreCase(string));
    }
    
    /**
     * 保证是一个有效的字符串		null将会转为""
     * @param string
     * @return
     */
    public static String String(String string) {
    	return StringNotNull(string);
    }

    /**
     * 是否具有长度？
     @method StringUtil: HasLength()
     @memo TODO
     @param str
     @return boolean
     */
	public static boolean HasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}
	public static boolean HasLength(String str) {
		return HasLength((CharSequence) str);
	}
	
	/**
	 * 是否含有文本？
	 @method StringUtil: HasText()
	 @memo TODO
	 @param str
	 @return boolean
	 */
	public static boolean HasText(CharSequence str) {
		if (!HasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}
	public static boolean HasText(String str) {
		return HasText((CharSequence) str);
	}

	/**
	 * 是否包含空白符？
	 @method StringUtil: ContainsWhitespace()
	 @memo TODO
	 @param str
	 @return boolean
	 */
	public static boolean ContainsWhitespace(CharSequence str) {
		if (!HasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}
	public static boolean ContainsWhitespace(String str) {
		return ContainsWhitespace((CharSequence) str);
	}
	
	/**
	 * 去除前后的空白符 备注：不同于String.trim()，后者只能去除空格符
	 @method StringUtil: TrimWhitespace()
	 @memo TODO
	 @param str
	 @return String
	 */
	public static String TrimWhitespace(String str) {
		if (!HasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
			sb.deleteCharAt(0);
		}
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * 去除所有空白符
	 @method StringUtil: TrimAllWhitespace()
	 @memo TODO
	 @param str
	 @return String
	 */
	public static String TrimAllWhitespace(String str) {
		if (!HasLength(str)) {
			return str;
		}
		int len = str.length();
		StringBuilder sb = new StringBuilder(str.length());
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			if (!Character.isWhitespace(c)) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 去除开头的（连续）空白符
	 @method StringUtil: TrimLeadingWhitespace()
	 @memo TODO
	 @param str
	 @return String
	 */
	public static String TrimLeadingWhitespace(String str) {
		if (!HasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}

	/**
	 * 去除结尾的（连续）空白符
	 @method StringUtil: TrimTrailingWhitespace()
	 @memo TODO
	 @param str
	 @return String
	 */
	public static String TrimTrailingWhitespace(String str) {
		if (!HasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * 去除开头的（连续）字符
	 @method StringUtil: TrimLeadingCharacter()
	 @memo TODO
	 @param str
	 @param leadingCharacter
	 @return String
	 */
	public static String TrimLeadingCharacter(String str, char leadingCharacter) {
		if (!HasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && sb.charAt(0) == leadingCharacter) {
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}

	/**
	 * 去除结尾的（连续）字符
	 @method StringUtil: TrimTrailingCharacter()
	 @memo TODO
	 @param str
	 @param trailingCharacter
	 @return String
	 */
	public static String TrimTrailingCharacter(String str, char trailingCharacter) {
		if (!HasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && sb.charAt(sb.length() - 1) == trailingCharacter) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	private static String change_first_character_case(String str, boolean capitalize) {
		if (str == null || str.length() == 0) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str.length());
		if (capitalize) {
			sb.append(Character.toUpperCase(str.charAt(0)));
		}
		else {
			sb.append(Character.toLowerCase(str.charAt(0)));
		}
		sb.append(str.substring(1));
		return sb.toString();
	}
	
	/**
	 * 首字母大写
	 @method StringUtil: Capitalize()
	 @memo TODO
	 @param str
	 @return String
	 */
	public static String Capitalize(String str) {
		return change_first_character_case(str, true);
	}

	/**
	 * 首字母小写
	 @method StringUtil: Uncapitalize()
	 @memo TODO
	 @param str
	 @return String
	 */
	public static String Uncapitalize(String str) {
		return change_first_character_case(str, false);
	}

	/**
	 * 是否以前缀开头？
	 @method StringUtil: StartsWithIgnoreCase()
	 @memo TODO
	 @param str
	 @param prefix
	 @return boolean
	 */
	public static boolean StartsWithIgnoreCase(String str, String prefix) {
		if (str == null || prefix == null) {
			return false;
		}
		if (str.startsWith(prefix)) {
			return true;
		}
		if (str.length() < prefix.length()) {
			return false;
		}
		String lcStr = str.substring(0, prefix.length()).toLowerCase();
		String lcPrefix = prefix.toLowerCase();
		return lcStr.equals(lcPrefix);
	}

	/**
	 * 是否以前缀开头？	等同于String.startsWith()
	 @method StringUtil: StartsWith()
	 @memo TODO
	 @param str
	 @param prefix
	 @return boolean
	 */
	public static boolean StartsWith(String str, String prefix) {
		if (str == null || prefix == null) {
			return false;
		}
		if (str.startsWith(prefix)) {
			return true;
		}
		if (str.length() < prefix.length()) {
			return false;
		}
		String lcStr = str.substring(0, prefix.length());//.toLowerCase();
		String lcPrefix = prefix;//.toLowerCase();
		return lcStr.equals(lcPrefix);
	}

	/**
	 * 是否已后缀结尾？
	 @method StringUtil: EndsWithIgnoreCase()
	 @memo TODO
	 @param str
	 @param suffix
	 @return boolean
	 */
	public static boolean EndsWithIgnoreCase(String str, String suffix) {
		if (str == null || suffix == null) {
			return false;
		}
		if (str.endsWith(suffix)) {
			return true;
		}
		if (str.length() < suffix.length()) {
			return false;
		}

		String lcStr = str.substring(str.length() - suffix.length()).toLowerCase();
		String lcSuffix = suffix.toLowerCase();
		return lcStr.equals(lcSuffix);
	}

	/**
	 * 是否已后缀结尾？	等同于String.endsWith()
	 @method StringUtil: EndsWith()
	 @memo TODO
	 @param str
	 @param suffix
	 @return boolean
	 */
	public static boolean EndsWith(String str, String suffix) {
		if (str == null || suffix == null) {
			return false;
		}
		if (str.endsWith(suffix)) {
			return true;
		}
		if (str.length() < suffix.length()) {
			return false;
		}

		String lcStr = str.substring(str.length() - suffix.length());//.toLowerCase();
		String lcSuffix = suffix;//.toLowerCase();
		return lcStr.equals(lcSuffix);
	}

    /**
     * 是否大写字母
     @method StringUtil: IsUppercaseAlpha()
     @memo TODO
     @param c
     @return boolean
     */
    public static boolean IsUppercaseAlpha(char c) {
        return (c >= 'A') && (c <= 'Z');
    }

    /**
     * 是否小写字母
     @method StringUtil: IsLowercaseAlpha()
     @memo TODO
     @param c
     @return boolean
     */
    public static boolean IsLowercaseAlpha(char c) {
        return (c >= 'a') && (c <= 'z');
    }

    /**
     * 转大写
     @method StringUtil: ToUpperAscii()
     @memo TODO
     @param c
     @return char
     */
    public static char ToUpperAscii(char c) {
        if (IsUppercaseAlpha(c)) {
            c -= (char) 0x20;
        }
        return c;
    }
    
    /**
     * 转大写		等同于String.toUpper()
     @method StringUtil: ToUpper()
     @memo TODO
     @param str
     @return String
     */
    public static String ToUpper(String str) {
        if(IsEmpty(str)) return str;
        return str.toUpperCase();
    }

    /**
     * 转小写
     @method StringUtil: ToLowerAscii()
     @memo TODO
     @param c
     @return char
     */
    public static char ToLowerAscii(char c) {
        if (IsUppercaseAlpha(c)) {
            c += (char) 0x20;
        }
        return c;
    }
    
    /**
     * 转小写		等同于String.toLower()
     @method StringUtil: ToLower()
     @memo TODO
     @param str
     @return String
     */
    public static String ToLower(String str) {
        if(IsEmpty(str)) return str;
        return str.toLowerCase();
    }
    
    /**
     * 从指定位置获取指定长度的字符串
     @method StringUtil: Substring()
     @memo TODO
     @param source
     @param begin
     @param len
     @return String
     */
    static public String Substring(String source, int begin, int len) {
    	if(null==source || begin>source.length() || begin+len>=source.length() || 0>=len || 0>=len-Math.abs(begin) ) return source;

    	String result = source.substring(begin, begin+len);
    	return result;
    }
    
    /**
     * 从指定位置开始，到指定位置结束进行替换
     @method StringUtil: ReplaceFromTo()
     @memo TODO
     @param source
     @param from
     @param to
     @param replacement
     @return String
     */
    static public String ReplaceFromTo(String source, int from, int to, String replacement) {
    	from = Math.min(from,to);
    	to = Math.max(from, to);
    	replacement = String(replacement);
    	if(null==source || source.length()<from || 0>=to || 0>=to-from) return source;
    	
    	String result = source.substring(0, 0<=from?from:0) + replacement + source.substring(to+1);
    	
    	return result;
    }
    
    /**
     * 从指定位置替换指定长度的字符串
     @method StringUtil: Replace()
     @memo TODO
     @param source
     @param begin
     @param len
     @param replacement
     @return String
     */
    static public String Replace(String source, int begin, int len, String replacement) {
    	if( 0>=len ) return source;
    	
    	return ReplaceFromTo(source, begin, begin+len, replacement);
    }

	/**
	 * 字符串替换	备注：等同于String.replace()，因此比String.replaceAll()弱
	 @method StringUtil: Replace()
	 @memo TODO
	 @param inString
	 @param oldPattern
	 @param newPattern
	 @return String
	 */
	public static String Replace(String inString, String oldPattern, String newPattern) {
		if (!HasLength(inString) || !HasLength(oldPattern) || newPattern == null) {
			return inString;
		}
		StringBuilder sb = new StringBuilder();
		int pos = 0; // our position in the old string
		int index = inString.indexOf(oldPattern);
		// the index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sb.append(inString.substring(pos, index));
			sb.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sb.append(inString.substring(pos));
		// remember to append any characters to the right of a match
		return sb.toString();
	}
	
	/**
	 * 替换重复出现指定数目的字符串（数目过多、过少均不会被替换）
	 @method StringUtil: Replace()
	 @memo TODO
	 @param source
	 @param find
	 @param numFind
	 @param replacement
	 @return String
	 */
	public static String Replace(String source, String find, int numFind, String replacement) {
		if(Invalid(source) || IsEmpty(find) || 0>=numFind ) return source;

		replacement = String(replacement);
		
		int rlen = replacement.length();
		int len = find.length();
		int start = 0;
		int end = 0;
		do {
			start = source.indexOf(find,start);
			if(0>start) break; //未找到任何匹配字串
			end = start + len-1; //找到第一个匹配字符串，end下移一个单位
			int num = numFind;
			int off = start;
			do {
				num --;
				int myoff = off+len;
				myoff = source.indexOf(find, myoff);
				if(off+len<myoff) { // off+len < myoff 表示不是连续出现的
					off = myoff;
					break;
				}
				if(0>myoff) { // 0 > myoff 表示已经到结尾
					off += len;
					break;
				}
				//此情况表示连续出现的
				off += len;
				end += len;
			} while(true);
			if(0>num) { // 0 > num 表示重复数目太多
				start = off;
			} else if(0<num) {  // 0 < num 表示重复数目不够
				start = off;
			} else {
				//表示重复数目刚好相等
				source = ReplaceFromTo(source,start,end,replacement);
				start = off - ((len*numFind)-rlen);
			}
		} while(true);
		
		return source;
	}
	
	/**
	 * 替换单个出现的字符串	其实就是String.replace()
	 * @param source
	 * @param find
	 * @param replacement
	 * @return
	 */
	public static String ReplaceSingle(String source, String find, String replacement) {
		return Replace(source,find,1,replacement);
	}
	
	/**
	 * 正则匹配替换		后来发现：String.repalceAll()与String.repalceFirst()也可以实现子匹配替换：$1, $2, ..., $N	N为正整数
	 @method StringUtil: ReplaceAll()
	 @memo TODO
	 @param source
	 @param exp
	 @param smartReplacement	里面可以使用子匹配，如${0},${1},${2},...${N} N为正整数
	 @return String
	 */
	public static String ReplaceAll(String source, String exp, String smartReplacement) {
		if(Invalid(source) || IsEmpty(exp) ) return source;
		
		smartReplacement = String(smartReplacement);
		
        Pattern pattern = Pattern.compile(exp);
        Matcher matcher = pattern.matcher(source);
        while(matcher.find()) {
        	String replacement = smartReplacement;
        	
            Pattern p = Pattern.compile("\\$\\{(\\d+)\\}");
            Matcher m = p.matcher(replacement);
            while(m.find()) {
            	int index = Integer.parseInt(m.group(1));
            	if(matcher.groupCount()>=index) replacement = replacement.replace(m.group(0), matcher.group(index));
            }
        	
            source = source.replace(matcher.group(0), replacement);
        }
        
		return source;
	}

	/**
	 * 替换字符串中的标识串		如：${username}, ${password}, ...
	 @method StringUtil: Replace()
	 @memo TODO
	 @param source
	 @param replacements
	 @param starttag	缺省	${
	 @param endtag		缺省	 }
	 @return String
	 */
	public static String Replace(String source, HashMap<String,Object> replacements, String starttag, String endtag) {
		if(Invalid(source) || null==replacements || 0>=replacements.size()) return source;
		if(Invalid(starttag)) starttag = "${";
		if(Invalid(endtag)) endtag = "}";
		
        Iterator<Entry<String, Object>> iterator = replacements.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
            String key = starttag + entry.getKey() + endtag;
            String value = String(entry.getValue());
            source = source.replace(key, value);
        }
		
		return source;
	}

	/**
	 * 替换字符串中的标识串		如：${username}, ${password}, ...等
	 @method StringUtil: Replace()
	 @memo TODO
	 @param source
	 @param replacements
	 @return String
	 */
	public static String Replace(String source, HashMap<String,Object> replacements) {
		return Replace(source, replacements, "${", "}");
	}
	
	/**
	 * 替换字符串中的标识串(即数组下标标识)
	 @method StringUtil: Replace()
	 @memo TODO
	 @param source
	 @param replacements
	 @param starttag	缺省	${
	 @param endtag		缺省	 }
	 @return String
	 */
	public static String Replace(String source, Object[] replacements, String starttag, String endtag) {
		return Replace(source, ArrayUtil.ArrayToMap(replacements), starttag, endtag);
	}
	
	/**
	 * 替换字符串中的标识串(即数组下标标识)
	 @method StringUtil: Replace()
	 @memo TODO
	 @param source
	 @param replacements
	 @return String
	 */
	public static String ReplaceWithArray(String source, Object[] replacements) {
		return Replace(source, replacements, "${", "}");
	}

	/**
	 * 替换字符串中的标识串(即数组下标标识)
	 @method StringUtil: Replace()
	 @memo TODO
	 @param source			占位符如: {N}
	 @param replacements
	 @return String
	 */
	public static String Replace(String source, Object... replacements) {
		return Replace(source, replacements, "${", "}");
	}
    
    /**
     * 是否包含子串？
     @method StringUtil: Contains()
     @memo TODO
     @param str
     @param off
     @param substring
     @return boolean
     */
	public static boolean Contains(CharSequence str, int off, CharSequence substring) {
		for (int j = 0; j < substring.length(); j++) {
			int i = off + j;
			if (i >= str.length() || str.charAt(i) != substring.charAt(j)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 统计子串出现数目
	 @method StringUtil: Count()
	 @memo TODO
	 @param str
	 @param sub
	 @return int
	 */
	public static int Count(String str, String sub) {
		if (str == null || sub == null || str.length() == 0 || sub.length() == 0) {
			return 0;
		}
		int count = 0;
		int pos = 0;
		int idx;
		while ((idx = str.indexOf(sub, pos)) != -1) {
			++count;
			pos = idx + sub.length();
		}
		return count;
	}

	/**
	 * 删除子串
	 @method StringUtil: Delete()
	 @memo TODO
	 @param inString
	 @param pattern
	 @return String
	 */
	public static String Delete(String inString, String pattern) {
		return Replace(inString, pattern, "");
	}

	/**
	 * 删除字符集中的字符
	 @method StringUtil: DeleteAny()
	 @memo TODO
	 @param inString
	 @param charsToDelete
	 @return String
	 */
	public static String DeleteAny(String inString, String charsToDelete) {
		if (!HasLength(inString) || !HasLength(charsToDelete)) {
			return inString;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < inString.length(); i++) {
			char c = inString.charAt(i);
			if (charsToDelete.indexOf(c) == -1) {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 重复复制某个字符串
	 * @param string
	 * @param count
	 * @return
	 */
	public static String Repeat(String string, int count) {
		if(null==string) return null;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<count; i++) sb.append(string);
		return sb.toString();
	}
	
	/**
	 * 从左边取固定长度字符串
	 * @param string
	 * @param length
	 * @param fillment
	 * @return
	 */
	public static String Left(String string, int length, String fillment) {
		if(null==string || 0>length) return null;
		if(0==length) return "";
		if(length==string.length()) return string;
		if(length>string.length()) {
			if(IsEmpty(fillment)) return string;
			return string + (Repeat(fillment,(length-string.length())/fillment.length()) + fillment.substring(0,(length-string.length())%fillment.length()));
		} else {
			if(IsEmpty(fillment)) return string.substring(0, length);
			int num = 2<=fillment.length() ? 1 : 3;
			String replacement = Repeat(fillment, num);
			return length<=replacement.length() ? replacement : (string.substring(0, length-replacement.length()) + replacement);
		}
	}
	public static String Left(String string, int length) {
		return Left(string,length," ");
	}
	
	/**
	 * 从右边取固定长度字符串
	 * @param string
	 * @param length
	 * @param fillment
	 * @return
	 */
	public static String Right(String string, int length, String fillment) {
		if(null==string || 0>length || length==string.length()) return string;
		if(0==length) return "";
		if(length>string.length()) {
			if(IsEmpty(fillment)) return string;
			return (fillment.substring(fillment.length()-(length-string.length())%fillment.length()) + Repeat(fillment,(length-string.length())/fillment.length())) + string;
		} else {
			if(IsEmpty(fillment)) return string.substring(string.length()-length);
			int num = 2<=fillment.length() ? 1 : 3;
			String replacement = Repeat(fillment, num);
			return length<=replacement.length() ? replacement : (replacement + string.substring(string.length()-(length-replacement.length())));
		}
	}
	public static String Right(String string, int length) {
		return Right(string,length," ");
	}
	
	/**
	 * 从中间取固定长度字符串
	 * @param string
	 * @param length
	 * @param fillment
	 * @return
	 */
	public static String Middle(String string, int length, String fillment) {
		if(null==string || 0>length) return null;
		if(0==length) return "";
		if(length==string.length()) return string;
		int head = Math.abs(length-string.length())/2;
		int tail = head + Math.abs(length-string.length())%2;
		if(length>string.length()) {
			if(IsEmpty(fillment)) return string;
			return (Repeat(fillment,head/fillment.length()) + fillment.substring(0,head%fillment.length())) + string + (fillment.substring(fillment.length()-tail%fillment.length()) + Repeat(fillment,tail/fillment.length()));
		} else {
			int start = string.length()/2 - head;
			int end = start + length;
			if(IsEmpty(fillment)) return string.substring(start,end);
			int num = 2<=fillment.length() ? 1 : 3;
			String replacement = Repeat(fillment, num);
			if(length<=replacement.length()) return replacement;
			if(length<=2*replacement.length()) {
				int diff = 2*replacement.length()-length;
				start += diff/2;
				end -= (diff/2 + diff%2);
			}
			return replacement + string.substring(start,end) + replacement;
		}
	}
	public static String Middle(String string, int length) {
		return Middle(string,length," ");
	}
    
    /**
     * 对象比较，Object.toString的比较
     @method StringUtil: Equal()
     @memo TODO
     @param left
     @param right
     @return boolean
     */
    static public boolean Equal(Object left, Object right) {
    	if(left==right) return true;
    	
    	if(null!=left && null!=right) return left.equals(right) ? true : left.toString().equals(right.toString());
    	
    	return false;
    }
    
    /**
     * 是否全部是字母？
     @method StringUtil: IsAllLetter()
     @memo TODO
     @param string
     @return boolean
     */
    static public boolean IsAllLetter(String string) {
    	if(null==string) return false;
    	return string.matches("^[A-Za-z]+$");
    }
    
    /**
     * 是否全部是大写字母
     @method StringUtil: IsAllUpper()
     @memo TODO
     @param string
     @return boolean
     */
    static public boolean IsAllUpper(String string) {
    	if(null==string) return false;
    	return string.matches("^[A-Z]+$");
    }
    
    /**
     * 是否全部是小写字母
     @method StringUtil: IsAllLower()
     @memo TODO
     @param string
     @return boolean
     */
    static public boolean IsAllLower(String string) {
    	if(null==string) return false;
    	return string.matches("^[a-z]+$");
    }
    
    /**
     * 是否全部是数字
     @method StringUtil: IsAllNumber()
     @memo TODO
     @param string
     @return boolean
     */
    static public boolean IsAllNumber(String string) {
    	if(null==string) return false;
    	return string.matches("^[0-9]+$");
    }
    
    /**
     * 是否全部是单词构成字符
     @method StringUtil: IsAllWord()
     @memo TODO
     @param string
     @return boolean
     */
    static public boolean IsAllWord(String string) {
    	if(null==string) return false;
    	return string.matches("^[\\w\\s-]*[a-zA-Z]+[\\w\\s-]*$");
    }

	//---------------------------------------------------------------------
	// 集合操作
	//---------------------------------------------------------------------
    
    /**
     * 集合整合为格式化字符串
     @method StringUtil: Implode()
     @memo TODO
     @param arr
     @param separator
     @return String
     */
    static public String Implode(Collection<?> collection, String separator) {
        return Implode(collection,separator,null);
    }
    static public String Implode(Collection<?> collection, String separator, ISerializable serialization) {
        if (null == collection) return null;

        String string = "";
        int i = 0;
        for (Object item: collection) {
            string += ((i++ == 0) ? "" : separator) + (null==serialization?item.toString():serialization.serialize(item));
        }
        return string;
    }
    
    /**
     * 对象数组整合为格式化字符串
     @method StringUtil: Implode()
     @memo TODO
     @param arr
     @param separator
     @return String
     */
    static public String Implode(Object[] arr, String separator) {
    	return Implode(arr,separator,null);
    }
    static public String Implode(Object[] arr, String separator, ISerializable serialization) {
        if (null == arr) return null;

        String string = "";
        for (int i = 0; i < arr.length; i++) {
            string += ((i == 0) ? "" : separator) + (null==serialization?arr[i].toString():serialization.serialize(arr[i]));
        }
        return string;
    }

    /**
     * 字符串数组整合为格式化的字符串
     @method StringUtil: Implode()
     @memo TODO
     @param arr
     @param separator
     @return String
     */
    static public String Implode(String[] arr, String separator) {
        if (null == arr) return null;

        String string = "";
        for (int i = 0; i < arr.length; i++) {
            string += ((i == 0) ? "" : separator) + arr[i];
        }
        return string;
    }

    /**
     * HashMap整合为格式化的字符串
     @method StringUtil: Implode()
     @memo TODO
     @param map
     @param separator
     @param eq
     @param quote
     @param codec
     @return
     @throws UnsupportedEncodingException String
     */
    static public String Implode(HashMap<Object, Object> map, String separator, String eq, String quote, ICodecable codec) throws UnsupportedEncodingException {
        if (null == map) return null;

        String string = "";
        int i = 0;
        Iterator<Entry<Object, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Object, Object> entry = (Entry<Object, Object>) iterator.next();
            String key = quote + (null == codec ? entry.getKey().toString() : codec.encode(entry.getKey().toString(), null)) + quote;
            String value = quote + (null == codec ? entry.getValue().toString() : codec.encode(entry.getValue().toString(), null)) + quote;
            String section = key + eq + value;
            string += (0==i ? "" : separator) + section;
        }
        return string;
    }

    /**
     * 格式化字符串分化为字符串数组
     @method StringUtil: Explode()
     @memo TODO
     @param string
     @param separator
     @return String[]
     */
    static public String[] Explode(String string, String separator) {
        if (null == string) return null;

        String arr[] = new String[] {};
        if (null != string) arr = string.split(separator);
        return arr;
    }

    /**
     * 格式化字符串分化为HashMap
     @method StringUtil: Explode()
     @memo TODO
     @param string
     @param separator
     @param eq
     @param quote
     @param codec
     @return
     @throws UnsupportedEncodingException HashMap<Object,Object>
     */
    static public HashMap<Object, Object> Explode(String string, String separator, String eq, String quote, ICodecable codec) throws UnsupportedEncodingException {
        if (null == string) return null;

        HashMap<Object, Object> map = new HashMap<Object, Object>();
        String arr[] = string.split(separator);
        for (int i = 0; i < arr.length; i++) {
            String key = arr[i].substring(0, arr[i].indexOf(eq));
            String value = arr[i].substring(arr[i].indexOf(eq) + eq.length());
            key = key.replaceAll("^\\s*" + quote + "\\s*", "").replaceAll("\\s*" + quote + "\\s*$", "");
            value = value.replaceAll("^\\s*" + quote, "").replaceAll(quote + "\\s*$", "");
            if (null != codec) {
                key = codec.decode(key, null);
                value = codec.decode(value, null);
            }
            map.put(key, value);
        }
        return map;
    }
    
    /**
     * 显示byte流中的取值
     @method StringUtil: ShowBytes()
     @memo TODO
     @param data
     @return String
     */
    static public String ShowBytes(byte[] data) {
        if(null == data) return null;
        
        StringBuilder sb = new StringBuilder("{");
        int i = 0;
        for(byte b: data) sb.append(0==i++?"":",").append(b);
        sb.append("}");
        
        return sb.toString();
    }
    
	/**
	 * 添加进数组
	 @method StringUtil: AppendToArray()
	 @memo TODO
	 @param array
	 @param str
	 @return String[]
	 */
	public static String[] AppendToArray(String[] array, String str) {
		if (ObjectUtil.IsEmpty(array)) {
			return new String[] {str};
		}
		String[] newArr = new String[array.length + 1];
		System.arraycopy(array, 0, newArr, 0, array.length);
		newArr[array.length] = str;
		return newArr;
	}

	/**
	 * 合并数组	不去除重复
	 @method StringUtil: Concatenate()
	 @memo TODO
	 @param array1
	 @param array2
	 @return String[]
	 */
	public static String[] Concatenate(String[] array1, String[] array2) {
		if (ObjectUtil.IsEmpty(array1)) {
			return array2;
		}
		if (ObjectUtil.IsEmpty(array2)) {
			return array1;
		}
		String[] newArr = new String[array1.length + array2.length];
		System.arraycopy(array1, 0, newArr, 0, array1.length);
		System.arraycopy(array2, 0, newArr, array1.length, array2.length);
		return newArr;
	}

	/**
	 * 合并数组	去除重复
	 @method StringUtil: Merge()
	 @memo TODO
	 @param array1
	 @param array2
	 @return String[]
	 */
	public static String[] Merge(String[] array1, String[] array2) {
		if (ObjectUtil.IsEmpty(array1)) {
			return array2;
		}
		if (ObjectUtil.IsEmpty(array2)) {
			return array1;
		}
		List<String> result = new ArrayList<String>();
		result.addAll(Arrays.asList(array1));
		for (String str : array2) {
			if (!result.contains(str)) {
				result.add(str);
			}
		}
		return ToStringArray(result);
	}

	/**
	 * 数组排序
	 @method StringUtil: Sort()
	 @memo TODO
	 @param array
	 @return String[]
	 */
	public static String[] Sort(String[] array) {
		if (ObjectUtil.IsEmpty(array)) {
			return new String[0];
		}
		Arrays.sort(array);
		return array;
	}

	/**
	 * 集合转字符串数组
	 @method StringUtil: ToStringArray()
	 @memo TODO
	 @param collection
	 @return String[]
	 */
	public static String[] ToStringArray(Collection<String> collection) {
		if (collection == null) {
			return null;
		}
		return collection.toArray(new String[collection.size()]);
	}

	/**
	 * 枚举转字符串数组
	 @method StringUtil: ToStringArray()
	 @memo TODO
	 @param enumeration
	 @return String[]
	 */
	public static String[] ToStringArray(Enumeration<String> enumeration) {
		if (enumeration == null) {
			return null;
		}
		List<String> list = Collections.list(enumeration);
		return list.toArray(new String[list.size()]);
	}

	/**
	 * 对数组元素依次执行trim操作
	 @method StringUtil: TrimArrayElements()
	 @memo TODO
	 @param array
	 @return String[]
	 */
	public static String[] TrimArrayElements(String[] array) {
		if (ObjectUtil.IsEmpty(array)) {
			return new String[0];
		}
		String[] result = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			String element = array[i];
			result[i] = (element != null ? element.trim() : null);
		}
		return result;
	}

	/**
	 * 删除数组元素中重复的元素
	 @method StringUtil: RemoveDuplicate()
	 @memo TODO
	 @param array
	 @return String[]
	 */
	public static String[] RemoveDuplicate(String[] array) {
		if (ObjectUtil.IsEmpty(array)) {
			return array;
		}
		Set<String> set = new LinkedHashSet<String>();
		for (String element : array) {
			set.add(element);
		}
		return ToStringArray(set);
	}

	/**
	 * 分解字符串为数组
	 @method StringUtil: Split()
	 @memo TODO
	 @param toSplit
	 @param delimiter
	 @return String[]
	 */
	public static String[] Split(String toSplit, String delimiter) {
		if (!HasLength(toSplit) || !HasLength(delimiter)) {
			return null;
		}
		int offset = toSplit.indexOf(delimiter);
		if (offset < 0) {
			return null;
		}
		String beforeDelimiter = toSplit.substring(0, offset);
		String afterDelimiter = toSplit.substring(offset + delimiter.length());
		return new String[] {beforeDelimiter, afterDelimiter};
	}

	/**
	 * 将数组元素分解进入Properties中
	 @method StringUtil: SplitArrayElementsIntoProperties()
	 @memo TODO
	 @param array
	 @param delimiter
	 @return Properties
	 */
	public static Properties SplitArrayElementsIntoProperties(String[] array, String delimiter) {
		return SplitArrayElementsIntoProperties(array, delimiter, null);
	}

	/**
	 * 将数组元素分解进入Properties中	数组元素中排除charsToDelete中的字符
	 @method StringUtil: SplitArrayElementsIntoProperties()
	 @memo TODO
	 @param array
	 @param delimiter
	 @param charsToDelete
	 @return Properties
	 */
	public static Properties SplitArrayElementsIntoProperties(String[] array, String delimiter, String charsToDelete) {

		if (ObjectUtil.IsEmpty(array)) {
			return null;
		}
		Properties result = new Properties();
		for (String element : array) {
			if (charsToDelete != null) {
				element = DeleteAny(element, charsToDelete);
			}
			String[] splittedElement = Split(element, delimiter);
			if (splittedElement == null) {
				continue;
			}
			result.setProperty(splittedElement[0].trim(), splittedElement[1].trim());
		}
		return result;
	}

	/**
	 * Tokenize分解成字符串数组
	 @method StringUtil: TokenizeToStringArray()
	 @memo TODO
	 @param str
	 @param delimiters
	 @return String[]
	 */
	public static String[] TokenizeToStringArray(String str, String delimiters) {
		return TokenizeToStringArray(str, delimiters, true, true);
	}

	/**
	 * Tokenize分解成字符串数组
	 @method StringUtil: TokenizeToStringArray()
	 @memo TODO
	 @param str
	 @param delimiters
	 @param trimTokens
	 @param ignoreEmptyTokens
	 @return String[]
	 */
	public static String[] TokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {

		if (str == null) {
			return null;
		}
		StringTokenizer st = new StringTokenizer(str, delimiters);
		List<String> tokens = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (trimTokens) {
				token = token.trim();
			}
			if (!ignoreEmptyTokens || token.length() > 0) {
				tokens.add(token);
			}
		}
		return ToStringArray(tokens);
	}

	/**
	 * 固定分隔符的字符串分解为字符串数组
	 @method StringUtil: DelimitedListToStringArray()
	 @memo TODO
	 @param str
	 @param delimiter
	 @return String[]
	 */
	public static String[] DelimitedListToStringArray(String str, String delimiter) {
		return DelimitedListToStringArray(str, delimiter, null);
	}

	/**
	 * 固定分隔符的字符串分解为字符串数组	排除charsToDelete中的字符
	 @method StringUtil: DelimitedListToStringArray()
	 @memo TODO
	 @param str
	 @param delimiter
	 @param charsToDelete
	 @return String[]
	 */
	public static String[] DelimitedListToStringArray(String str, String delimiter, String charsToDelete) {
		if (str == null) {
			return new String[0];
		}
		if (delimiter == null) {
			return new String[] {str};
		}
		List<String> result = new ArrayList<String>();
		if ("".equals(delimiter)) {
			for (int i = 0; i < str.length(); i++) {
				result.add(DeleteAny(str.substring(i, i + 1), charsToDelete));
			}
		}
		else {
			int pos = 0;
			int delPos;
			while ((delPos = str.indexOf(delimiter, pos)) != -1) {
				result.add(DeleteAny(str.substring(pos, delPos), charsToDelete));
				pos = delPos + delimiter.length();
			}
			if (str.length() > 0 && pos <= str.length()) {
				// Add rest of String, but not in case of empty input.
				result.add(DeleteAny(str.substring(pos), charsToDelete));
			}
		}
		return ToStringArray(result);
	}

	/**
	 * 逗号分隔的字符串分解成字符串数组
	 @method StringUtil: CommaDelimitedListToStringArray()
	 @memo TODO
	 @param str
	 @return String[]
	 */
	public static String[] CommaDelimitedListToStringArray(String str) {
		return DelimitedListToStringArray(str, ",");
	}

	/**
	 * 逗号分隔的字符串分解成字符串集合
	 @method StringUtil: CommaDelimitedListToSet()
	 @memo TODO
	 @param str
	 @return Set<String>
	 */
	public static Set<String> CommaDelimitedListToSet(String str) {
		Set<String> set = new LinkedHashSet<String>();
		String[] tokens = CommaDelimitedListToStringArray(str);
		for (String token : tokens) {
			set.add(token);
		}
		return set;
	}

	/**
	 * 集合按照分隔符组装成字符串
	 @method StringUtil: CollectionToDelimitedString()
	 @memo TODO
	 @param coll
	 @param delim
	 @param prefix
	 @param suffix
	 @return String
	 */
	public static String CollectionToDelimitedString(Collection<?> coll, String delim, String prefix, String suffix) {
		if (CollectionUtil.IsEmpty(coll)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Iterator<?> it = coll.iterator();
		while (it.hasNext()) {
			sb.append(prefix).append(it.next()).append(suffix);
			if (it.hasNext()) {
				sb.append(delim);
			}
		}
		return sb.toString();
	}

	/**
	 * 集合按照分隔符组装成字符串
	 @method StringUtil: CollectionToDelimitedString()
	 @memo TODO
	 @param coll
	 @param delim
	 @return String
	 */
	public static String CollectionToDelimitedString(Collection<?> coll, String delim) {
		return CollectionToDelimitedString(coll, delim, "", "");
	}

	/**
	 * 集合按照逗号分隔符组装成字符串
	 @method StringUtil: CollectionToCommaDelimitedString()
	 @memo TODO
	 @param coll
	 @return String
	 */
	public static String CollectionToCommaDelimitedString(Collection<?> coll) {
		return CollectionToDelimitedString(coll, ",");
	}

	/**
	 * 数组按照分隔符组装成字符串
	 @method StringUtil: ArrayToDelimitedString()
	 @memo TODO
	 @param arr
	 @param delim
	 @return String
	 */
	public static String ArrayToDelimitedString(Object[] arr, String delim) {
		if (ObjectUtil.IsEmpty(arr)) {
			return "";
		}
		if (arr.length == 1) {
			return ObjectUtil.ToStringNullSafe(arr[0]);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(delim);
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}

	/**
	 * 数组按照逗号分隔符组装成字符串
	 @method StringUtil: ArrayToCommaDelimitedString()
	 @memo TODO
	 @param arr
	 @return String
	 */
	public static String ArrayToCommaDelimitedString(Object[] arr) {
		return ArrayToDelimitedString(arr, ",");
	}
	
	/**
	 * 查找第一个模式出现的位置
	 * @param string
	 * @param pattern
	 * @return
	 */
	public static int IndexOf(String string, String pattern) {
		return IndexOf(string, pattern, 0);
	}
	public static int IndexOf(String string, String pattern, int off) {
		if( null==string || null==pattern || 0>off ) return -1;

        Pattern p = Pattern.compile("("+pattern+")");
        Matcher m = p.matcher(string);
        
        //局部类
        class Index {
        	public int index = 0;
        	public String delimiter = "";
        	public Index(int index, String delimiter){
        		this.index = index;
        		this.delimiter = delimiter;
        	}
        }
        
        List<Index> patterns = new ArrayList<Index>();
        int from = 0;
    	String delimiter = "";
        while( m.find() ) {
        	delimiter = m.group(1);
        	patterns.add( new Index(from,delimiter) );
        	from = string.indexOf(delimiter, from)+delimiter.length();
        }
        int len = patterns.size();
        if( off>=len ) return -1;
        
		return string.indexOf(patterns.get(off).delimiter, patterns.get(off).index);
	}
	
	/**
	 * 查找最后一个模式出现的位置
	 * @param string
	 * @param pattern
	 * @return
	 */
	public static int LastOf(String string, String pattern) {
		return LastOf(string, pattern, 0);
	}
	public static int LastOf(String string, String pattern, int off) {
		if( null==string || null==pattern || 0>off ) return -1;

        Pattern p = Pattern.compile("("+pattern+")");
        Matcher m = p.matcher(string);
        
        //局部类
        class Index {
        	public int index = 0;
        	public String delimiter = "";
        	public Index(int index, String delimiter){
        		this.index = index;
        		this.delimiter = delimiter;
        	}
        }
        
        List<Index> patterns = new ArrayList<Index>();
        int from = 0;
    	String delimiter = "";
        while( m.find() ) {
        	delimiter = m.group(1);
        	from = string.indexOf(delimiter, from)+delimiter.length();
        	patterns.add( new Index(from,delimiter) );
        }
        int len = patterns.size();
        if( 0>=len || off>=len ) return -1;
        
		return string.lastIndexOf(patterns.get(len-off-1).delimiter, patterns.get(len-off-1).index);
	}
	
	/**
	 * 查找第一个出现的模式匹配字串		注：不同于String.split(String regex, int limit);
	 * @param string
	 * @param pattern
	 * @return
	 */
	public static String Find(String string, String pattern) {
		return Find(string, pattern, 0);
	}
	public static String Find(String string, String pattern, int off) {
		if( null==string || null==pattern || 0>off ) return null;
		
		String[] patterns = Finds(string, pattern);
        if( off>=patterns.length ) return null;
        
		return patterns[off];
	}
	
	/**
	 * 查找所有出现的模式匹配字串		注：不同于String.split(String regex, int limit);
	 * @param string
	 * @param pattern
	 * @return
	 */
	public static String[] Finds(String string, String pattern) {
		if( null==string || null==pattern ) return null;

        Pattern p = Pattern.compile("("+pattern+")");
        Matcher m = p.matcher(string);
        
        List<String> patterns = new ArrayList<String>();
        while( m.find() ) {
        	patterns.add( m.group(1) );
        }
        
		return patterns.toArray(new String[patterns.size()]);
	}
	
}
