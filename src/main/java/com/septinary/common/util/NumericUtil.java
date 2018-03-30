package com.septinary.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 数值操作
 * @Filename: com.septinary.common.util.NumericUtil.java of the project [com.septinary.common]
 *     @Type: NumericUtil
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月13日上午12:53:18
 *
 */
abstract public class NumericUtil {

	private static final BigInteger LONG_MIN = BigInteger.valueOf(Long.MIN_VALUE);

	private static final BigInteger LONG_MAX = BigInteger.valueOf(Long.MAX_VALUE);
	
	/**
	 * 标准数值类型
	 * Standard number types (all immutable):
	 * Byte, Short, Integer, Long, BigInteger, Float, Double, BigDecimal.
	 */
	public static final Set<Class<?>> STANDARD_NUMBER_TYPES;
	static {
		Set<Class<?>> numberTypes = new HashSet<Class<?>>(8);
		numberTypes.add(Byte.class);
		numberTypes.add(Short.class);
		numberTypes.add(Integer.class);
		numberTypes.add(Long.class);
		numberTypes.add(BigInteger.class);
		numberTypes.add(Float.class);
		numberTypes.add(Double.class);
		numberTypes.add(BigDecimal.class);
		STANDARD_NUMBER_TYPES = Collections.unmodifiableSet(numberTypes);
	}
	
	/**
	 * 是否字符？
	 * @param string
	 * @return
	 */
	static public boolean IsChar(String string) {
		if(null==string) return false;
		return 1>=string.length();
	}

	/**
	 * 是否数字？
	 @method NumericUtil: IsNumber()
	 @memo TODO
	 @param string
	 @return boolean
	 */
	static public boolean IsNumber(String string) {
		if(null==string) return false;
		
		return string.matches("^\\s*[\\+\\-]?\\s*\\d+[0-9 ]*(\\.\\d+[0-9 ]*)?\\s*$");
	}
	
	/**
	 * 是否整型数？
	 @method NumericUtil: IsInt()
	 @memo TODO
	 @param string
	 @return boolean
	 */
	static public boolean IsInt(String string) {
		if(null==string) return false;

		return string.matches("^\\s*[\\+\\-]?\\s*[0-9 ]+\\s*$");
	}
	
	/**
	 * 是否实数？
	 @method NumericUtil: IsReal()
	 @memo TODO
	 @param string
	 @return boolean
	 */
	static public boolean IsReal(String string) {
		if(null==string) return false;
		
		return IsInt(string) ? true : string.matches("^\\s*[\\+\\-]?\\s*[0-9 ]+\\.[0-9 ]+\\s*$");
	}
	
	/**
	 * 是否布尔？
	 @method NumericUtil: IsBool()
	 @memo TODO
	 @param object
	 @return boolean
	 */
	static public boolean IsBool(String string) {
		if(null==string) return false;
		
		return string.matches("^\\s*(true|false)\\s*$");
	}
	
	/**
	 * 字符串转换Byte
	 * @param byter
	 * @return
	 */
	public static Byte ToByteObject(String byter) {
		if( StringUtil.Invalid(byter) ) return null;
		byter = byter.trim();
		try {
			return ParseNumber(byter,Byte.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static byte ToByte(String byter, byte defaultvalue) {
		Byte b = ToByteObject(byter);
		return null==b ? defaultvalue : b;
	}
	public static byte ToByte(String byter) {
		return ToByte(byter, (byte)0);
	}
	
	/**
	 * 字符串转换Character
	 * @param character
	 * @return
	 */
	public static Character ToCharacter(String character) {
		if( StringUtil.Invalid(character) || !IsChar(character) ) return null;
		if( 0==character.length() ) return '\0';
		return character.charAt(0);
	}
	public static char ToChar(String character, char defaultvalue) {
		Character c = ToCharacter(character);
		return null==c ? defaultvalue : c;
	}
	public static char ToChar(String character) {
		return ToChar(character, '\0');
	}
	
	/**
	 * 字符串转换Boolean
	 * @param bool
	 * @return
	 */
	public static Boolean ToBoolean(String bool) {
		if( StringUtil.Invalid(bool) ) return null;
		bool = bool.trim();
		if( "true".equalsIgnoreCase(bool) ) return true;
		if( "false".equalsIgnoreCase(bool) ) return false;
		return null;
	}
	public static boolean ToBool(String bool, boolean defaultvalue) {
		Boolean b = ToBoolean(bool);
		return null==b ? defaultvalue : b;
	}
	public static boolean ToBool(String bool) {
		return ToBool(bool, false);
	}
	public static boolean ToBool(Boolean bool, boolean defaultvalue) {
		return null==bool ? defaultvalue : bool;
	}
	public static boolean ToBool(Boolean bool) {
		return ToBool(bool, false);
	}
	
	/**
	 * 字符串转换Short
	 * @param shorter
	 * @return
	 */
	public static Short ToShortInteger(String shorter) {
		if( StringUtil.Invalid(shorter) || !IsInt(shorter) ) return null;
		shorter = shorter.trim();
		try {
			return ParseNumber(shorter,Short.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static short ToShort(String shorter, short defaultvalue) {
		Short s = ToShortInteger(shorter);
		return null==s ? defaultvalue : s;
	}
	public static short ToShort(String shorter) {
		return ToShort(shorter, (short)0);
	}
	public static short ToShort(Short shorter, short defaultvalue) {
		return null==shorter ? defaultvalue : shorter;
	}
	public static short ToShort(Short shorter) {
		return ToShort(shorter, (short)0);
	}
	
	/**
	 * 字符串转换Integer
	 * @param integer
	 * @return
	 */
	public static Integer ToInteger(String integer) {
		if( StringUtil.Invalid(integer) || !IsInt(integer) ) return null;
		integer = integer.trim();
		try {
			return ParseNumber(integer,Integer.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static int ToInt(String integer, int defaultvalue) {
		Integer i = ToInteger(integer);
		return null==i ? defaultvalue : i;
	}
	public static int ToInt(String integer) {
		return ToInt(integer, 0);
	}
	public static int ToInt(Integer integer, int defaultvalue) {
		return null==integer ? defaultvalue : integer;
	}
	public static int ToInt(Integer integer) {
		return ToInt(integer, 0);
	}
	
	/**
	 * 字符串转换Long
	 * @param longer
	 * @return
	 */
	public static Long ToLongInteger(String longer) {
		if( StringUtil.Invalid(longer) || !IsInt(longer) ) return null;
		longer = longer.trim();
		try {
			return ParseNumber(longer,Long.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static long ToLong(String longer, long defaultvalue) {
		Long l = ToLongInteger(longer);
		return null==l ? defaultvalue : l;
	}
	public static long ToLong(String longer) {
		return ToLong(longer, 0L);
	}
	public static long ToLong(Long longer, long defaultvalue) {
		return null==longer ? defaultvalue : longer;
	}
	public static long ToLong(Long longer) {
		return ToLong(longer, 0L);
	}
	
	/**
	 * 字符串转换BigInteger
	 * @param big
	 * @return
	 */
	public static BigInteger ToBigInteger(String big) {
		if( StringUtil.Invalid(big) || !IsInt(big) ) return null;
		big = big.trim();
		try {
			return ParseNumber(big,BigInteger.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static BigInteger ToBigInteger(String big, BigInteger defaultvalue) {
		BigInteger bi = ToBigInteger(big);
		return null==bi ? defaultvalue : bi;
	}
	public static BigInteger ToBigInteger(BigInteger big, BigInteger defaultvalue) {
		return null==big ? defaultvalue : big;
	}
	public static BigInteger ToBigInteger(BigInteger big) {
		return ToBigInteger(big, BigInteger.ZERO);
	}
	
	/**
	 * 字符串转换Float
	 * @param floater
	 * @return
	 */
	public static Float ToFloatNumber(String floater) {
		if( StringUtil.Invalid(floater) || !IsReal(floater) ) return null;
		floater = floater.trim();
		try {
			return ParseNumber(floater,Float.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static float ToFloat(String floater, float defaultvalue) {
		Float f = ToFloatNumber(floater);
		return null==f ? defaultvalue : f;
	}
	public static float ToFloat(String floater) {
		return ToFloat(floater, 0);
	}
	public static float ToFloat(Float floater, float defaultvalue) {
		return null==floater ? defaultvalue : floater;
	}
	public static float ToFloat(Float floater) {
		return ToFloat(floater, 0);
	}
	
	/**
	 * 字符串转换Double
	 * @param doubler
	 * @return
	 */
	public static Double ToDoubleNumber(String doubler) {
		if( StringUtil.Invalid(doubler) || !IsReal(doubler) ) return null;
		doubler = doubler.trim();
		try {
			return ParseNumber(doubler,Double.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static double ToDouble(String doubler, double defaultvalue) {
		Double d = ToDoubleNumber(doubler);
		return null==d ? defaultvalue : d;
	}
	public static double ToDouble(String doubler) {
		return ToDouble(doubler, 0);
	}
	public static double ToDouble(Double doubler, double defaultvalue) {
		return null==doubler ? defaultvalue : doubler;
	}
	public static double ToDouble(Double doubler) {
		return ToDouble(doubler, 0);
	}
	
	/**
	 * 字符串转换BigDecimal
	 * @param big
	 * @return
	 */
	public static BigDecimal ToBigDecimal(String big) {
		if( StringUtil.Invalid(big) || !IsReal(big) ) return null;
		big = big.trim();
		try {
			return ParseNumber(big,BigDecimal.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static BigDecimal ToBigDecimal(String big, BigDecimal defaultvalue) {
		BigDecimal bi = ToBigDecimal(big);
		return null==bi ? defaultvalue : bi;
	}
	public static BigDecimal ToBigDecimal(BigDecimal big, BigDecimal defaultvalue) {
		return null==big ? defaultvalue : big;
	}
	public static BigDecimal ToBigDecimal(BigDecimal big) {
		return ToBigDecimal(big, BigDecimal.ZERO);
	}

	/**
	 * 转换数值为相应目标类型
	 * Convert the given number into an instance of the given target class.
	 * @param number the number to convert
	 * @param targetClass the target class to convert to
	 * @return the converted number
	 * @throws IllegalArgumentException if the target class is not supported
	 * (i.e. not a standard Number subclass as included in the JDK)
	 * @see Byte
	 * @see Short
	 * @see Integer
	 * @see Long
	 * @see BigInteger
	 * @see Float
	 * @see Double
	 * @see BigDecimal
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Number> T ConvertNumberToTargetClass(Number number, Class<T> targetClass) throws IllegalArgumentException {

		Assert.NotNull(number, "Number must not be null");
		Assert.NotNull(targetClass, "Target class must not be null");

		if (targetClass.isInstance(number)) {
			return (T) number;
		} else if (Byte.class == targetClass) {
			long value = number.longValue();
			if (value < Byte.MIN_VALUE || value > Byte.MAX_VALUE) {
				raiseOverflowException(number, targetClass);
			}
			return (T) Byte.valueOf(number.byteValue());
		} else if (Short.class == targetClass) {
			long value = number.longValue();
			if (value < Short.MIN_VALUE || value > Short.MAX_VALUE) {
				raiseOverflowException(number, targetClass);
			}
			return (T) Short.valueOf(number.shortValue());
		} else if (Integer.class == targetClass) {
			long value = number.longValue();
			if (value < Integer.MIN_VALUE || value > Integer.MAX_VALUE) {
				raiseOverflowException(number, targetClass);
			}
			return (T) Integer.valueOf(number.intValue());
		} else if (Long.class == targetClass) {
			BigInteger bigInt = null;
			if (number instanceof BigInteger) {
				bigInt = (BigInteger) number;
			} else if (number instanceof BigDecimal) {
				bigInt = ((BigDecimal) number).toBigInteger();
			}
			// Effectively analogous to JDK 8's BigInteger.longValueExact()
			if (bigInt != null && (bigInt.compareTo(LONG_MIN) < 0 || bigInt.compareTo(LONG_MAX) > 0)) {
				raiseOverflowException(number, targetClass);
			}
			return (T) Long.valueOf(number.longValue());
		} else if (BigInteger.class == targetClass) {
			if (number instanceof BigDecimal) {
				// do not lose precision - use BigDecimal's own conversion
				return (T) ((BigDecimal) number).toBigInteger();
			} else {
				// original value is not a Big* number - use standard long conversion
				return (T) BigInteger.valueOf(number.longValue());
			}
		} else if (Float.class == targetClass) {
			return (T) Float.valueOf(number.floatValue());
		} else if (Double.class == targetClass) {
			return (T) Double.valueOf(number.doubleValue());
		} else if (BigDecimal.class == targetClass) {
			// always use BigDecimal(String) here to avoid unpredictability of BigDecimal(double)
			// (see BigDecimal javadoc for details)
			return (T) new BigDecimal(number.toString());
		} else {
			throw new IllegalArgumentException("Could not convert number [" + number + "] of type [" +
					number.getClass().getName() + "] to unsupported target class [" + targetClass.getName() + "]");
		}
	}

	/**
	 * 抛出溢出异常
	 * Raise an <em>overflow</em> exception for the given number and target class.
	 * @param number the number we tried to convert
	 * @param targetClass the target class we tried to convert to
	 * @throws IllegalArgumentException
	 */
	private static void raiseOverflowException(Number number, Class<?> targetClass) {
		throw new IllegalArgumentException("Could not convert number [" + number + "] of type [" + number.getClass().getName() + "] to target class [" + targetClass.getName() + "]: overflow");
	}

	/**
	 * 解析数值型数据
	 * Parse the given {@code text} into a {@link Number} instance of the given
	 * target class, using the corresponding {@code decode} / {@code valueOf} method.
	 * <p>Trims the input {@code String} before attempting to parse the number.
	 * <p>Supports numbers in hex format (with leading "0x", "0X", or "#") as well.
	 * @param text the text to convert
	 * @param targetClass the target class to parse into
	 * @return the parsed number
	 * @throws IllegalArgumentException if the target class is not supported
	 * (i.e. not a standard Number subclass as included in the JDK)
	 * @see Byte#decode
	 * @see Short#decode
	 * @see Integer#decode
	 * @see Long#decode
	 * @see #decodeBigInteger(String)
	 * @see Float#valueOf
	 * @see Double#valueOf
	 * @see BigDecimal#BigDecimal(String)
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Number> T ParseNumber(String text, Class<T> targetClass) {
		Assert.NotNull(text, "Text must not be null");
		Assert.NotNull(targetClass, "Target class must not be null");
		String trimmed = StringUtil.TrimAllWhitespace(text);

		if (Byte.class == targetClass) {
			return (T) (IsHexNumber(trimmed) ? Byte.decode(trimmed) : Byte.valueOf(trimmed));
		} else if (Short.class == targetClass) {
			return (T) (IsHexNumber(trimmed) ? Short.decode(trimmed) : Short.valueOf(trimmed));
		} else if (Integer.class == targetClass) {
			return (T) (IsHexNumber(trimmed) ? Integer.decode(trimmed) : Integer.valueOf(trimmed));
		} else if (Long.class == targetClass) {
			return (T) (IsHexNumber(trimmed) ? Long.decode(trimmed) : Long.valueOf(trimmed));
		} else if (BigInteger.class == targetClass) {
			return (T) (IsHexNumber(trimmed) ? decodeBigInteger(trimmed) : new BigInteger(trimmed));
		} else if (Float.class == targetClass) {
			return (T) Float.valueOf(trimmed);
		} else if (Double.class == targetClass) {
			return (T) Double.valueOf(trimmed);
		} else if (BigDecimal.class == targetClass || Number.class == targetClass) {
			return (T) new BigDecimal(trimmed);
		}  else if (BigInteger.class == targetClass || Number.class == targetClass) {
			return (T) new BigInteger(trimmed);
		} else {
			throw new IllegalArgumentException("Cannot convert String [" + text + "] to target class [" + targetClass.getName() + "]");
		}
	}

	/**
	 * 解析数值型数据
	 * Parse the given {@code text} into a {@link Number} instance of the
	 * given target class, using the supplied {@link NumberFormat}.
	 * <p>Trims the input {@code String} before attempting to parse the number.
	 * @param text the text to convert
	 * @param targetClass the target class to parse into
	 * @param numberFormat the {@code NumberFormat} to use for parsing (if
	 * {@code null}, this method falls back to {@link #parseNumber(String, Class)})
	 * @return the parsed number
	 * @throws IllegalArgumentException if the target class is not supported
	 * (i.e. not a standard Number subclass as included in the JDK)
	 * @see NumberFormat#parse
	 * @see #convertNumberToTargetClass
	 * @see #parseNumber(String, Class)
	 */
	public static <T extends Number> T ParseNumber(String text, Class<T> targetClass, NumberFormat numberFormat) {
		if (numberFormat != null) {
			Assert.NotNull(text, "Text must not be null");
			Assert.NotNull(targetClass, "Target class must not be null");
			DecimalFormat decimalFormat = null;
			boolean resetBigDecimal = false;
			if (numberFormat instanceof DecimalFormat) {
				decimalFormat = (DecimalFormat) numberFormat;
				if (BigDecimal.class == targetClass && !decimalFormat.isParseBigDecimal()) {
					decimalFormat.setParseBigDecimal(true);
					resetBigDecimal = true;
				}
			}
			try {
				Number number = numberFormat.parse(StringUtil.TrimAllWhitespace(text));
				return ConvertNumberToTargetClass(number, targetClass);
			} catch (ParseException ex) {
				throw new IllegalArgumentException("Could not parse number: " + ex.getMessage());
			}
			finally {
				if (resetBigDecimal) {
					decimalFormat.setParseBigDecimal(false);
				}
			}
		}
		else {
			return ParseNumber(text, targetClass);
		}
	}

	/**
	 * 是否16进制数？
	 * Determine whether the given {@code value} String indicates a hex number,
	 * i.e. needs to be passed into {@code Integer.decode} instead of
	 * {@code Integer.valueOf}, etc.
	 */
	public static boolean IsHexNumber(String value) {
		int index = (value.startsWith("-") ? 1 : 0);
		return (value.startsWith("0x", index) || value.startsWith("0X", index) || value.startsWith("#", index));
	}

	/**
	 * Decode a {@link BigInteger} from the supplied {@link String} value.
	 * <p>Supports decimal, hex, and octal notation.
	 * @see BigInteger#BigInteger(String, int)
	 */
	private static BigInteger decodeBigInteger(String value) {
		int radix = 10;
		int index = 0;
		boolean negative = false;

		// Handle minus sign, if present.
		if (value.startsWith("-")) {
			negative = true;
			index++;
		}

		// Handle radix specifier, if present.
		if (value.startsWith("0x", index) || value.startsWith("0X", index)) {
			index += 2;
			radix = 16;
		} else if (value.startsWith("#", index)) {
			index++;
			radix = 16;
		} else if (value.startsWith("0", index) && value.length() > 1 + index) {
			index++;
			radix = 8;
		}

		BigInteger result = new BigInteger(value.substring(index), radix);
		return (negative ? result.negate() : result);
	}
}
