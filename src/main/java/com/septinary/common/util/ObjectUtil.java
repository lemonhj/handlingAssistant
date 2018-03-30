package com.septinary.common.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * 基类（根类）操作
 * @Filename: com.septinary.common.util.ObjectUtil.java of the project [com.septinary.common]
 *     @Type: ObjectUtil
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月25日下午2:53:12
 *
 */
abstract public class ObjectUtil {

	private static final int INITIAL_HASH = 7;
	private static final int MULTIPLIER = 31;

	private static final String EMPTY_STRING = "";
	private static final String NULL_STRING = "null";
	private static final String ARRAY_START = "{";
	private static final String ARRAY_END = "}";
	private static final String EMPTY_ARRAY = ARRAY_START + ARRAY_END;
	private static final String ARRAY_ELEMENT_SEPARATOR = ", ";
	
	/**
	 * 是否checked异常类对象？
	 @method ObjectUtil: IsCheckedException()
	 @memo TODO
	 @param ex
	 @return boolean
	 */
	public static boolean IsCheckedException(Throwable ex) {
		return !(ex instanceof RuntimeException || ex instanceof Error);
	}

	/**
	 * 是否compatiable异常类对象？
	 @method ObjectUtil: IsCompatibleWithThrowsClause()
	 @memo TODO
	 @param ex
	 @param declaredExceptions
	 @return boolean
	 */
	public static boolean IsCompatibleWithThrowsClause(Throwable ex, Class<?>... declaredExceptions) {
		if (!IsCheckedException(ex)) {
			return true;
		}
		if (declaredExceptions != null) {
			for (Class<?> declaredException : declaredExceptions) {
				if (declaredException.isInstance(ex)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 是否空对象？
	 @method ObjectUtil: IsEmpty()
	 @memo TODO
	 @param obj
	 @return boolean
	 */
	@SuppressWarnings("rawtypes")
	public static boolean IsEmpty(Object obj) {
		if (obj == null) {
			return true;
		}

		if (obj.getClass().isArray()) {
			return Array.getLength(obj) == 0;
		}
		if (obj instanceof CharSequence) {
			return ((CharSequence) obj).length() == 0;
		}
		if (obj instanceof Collection) {
			return ((Collection) obj).isEmpty();
		}
		if (obj instanceof Map) {
			return ((Map) obj).isEmpty();
		}

		// else
		return false;
	}

	/**
	 * 转换为数组
	 @method ObjectUtil: ToArray()
	 @memo TODO
	 @param source
	 @return Object[]
	 */
	public static Object[] ToArray(Object source) {
		if (source instanceof Object[]) {
			return (Object[]) source;
		}
		if (source == null) {
			return new Object[0];
		}
		if (!source.getClass().isArray()) {
			Object[] arr = new Object[1];
			arr[0] = source;
			return arr;
		}
		int length = Array.getLength(source);
		if (length == 0) {
			return new Object[0];
		}
		Class<?> wrapperType = Array.get(source, 0).getClass();
		Object[] newArray = (Object[]) Array.newInstance(wrapperType, length);
		for (int i = 0; i < length; i++) {
			newArray[i] = Array.get(source, i);
		}
		return newArray;
	}


	//---------------------------------------------------------------------
	// Convenience methods for content-based equality/hash-code handling
	//---------------------------------------------------------------------

	/**
	 * 对象是否相等？	NULL安全模式：即null!=null
	 @method ObjectUtil: EqualNullSafe()
	 @memo TODO
	 @param o1
	 @param o2
	 @return boolean
	 */
	public static boolean EqualNullSafe(Object o1, Object o2) {
		if (o1 == o2) {
			return true;
		}
		if (o1 == null || o2 == null) {
			return false;
		}
		if (o1.equals(o2)) {
			return true;
		}
		if (o1.getClass().isArray() && o2.getClass().isArray()) {
			if (o1 instanceof Object[] && o2 instanceof Object[]) {
				return Arrays.equals((Object[]) o1, (Object[]) o2);
			}
			if (o1 instanceof boolean[] && o2 instanceof boolean[]) {
				return Arrays.equals((boolean[]) o1, (boolean[]) o2);
			}
			if (o1 instanceof byte[] && o2 instanceof byte[]) {
				return Arrays.equals((byte[]) o1, (byte[]) o2);
			}
			if (o1 instanceof char[] && o2 instanceof char[]) {
				return Arrays.equals((char[]) o1, (char[]) o2);
			}
			if (o1 instanceof double[] && o2 instanceof double[]) {
				return Arrays.equals((double[]) o1, (double[]) o2);
			}
			if (o1 instanceof float[] && o2 instanceof float[]) {
				return Arrays.equals((float[]) o1, (float[]) o2);
			}
			if (o1 instanceof int[] && o2 instanceof int[]) {
				return Arrays.equals((int[]) o1, (int[]) o2);
			}
			if (o1 instanceof long[] && o2 instanceof long[]) {
				return Arrays.equals((long[]) o1, (long[]) o2);
			}
			if (o1 instanceof short[] && o2 instanceof short[]) {
				return Arrays.equals((short[]) o1, (short[]) o2);
			}
		}
		return false;
	}

	/**
	 * 获取对象的hashcode	NULL安全模式
	 @method ObjectUtil: HashCodeNullSafe()
	 @memo TODO
	 @param obj
	 @return int
	 */
	public static int HashCodeNullSafe(Object obj) {
		if (obj == null) {
			return 0;
		}
		if (obj.getClass().isArray()) {
			if (obj instanceof Object[]) {
				return HashCodeNullSafe((Object[]) obj);
			}
			if (obj instanceof boolean[]) {
				return HashCodeNullSafe((boolean[]) obj);
			}
			if (obj instanceof byte[]) {
				return HashCodeNullSafe((byte[]) obj);
			}
			if (obj instanceof char[]) {
				return HashCodeNullSafe((char[]) obj);
			}
			if (obj instanceof double[]) {
				return HashCodeNullSafe((double[]) obj);
			}
			if (obj instanceof float[]) {
				return HashCodeNullSafe((float[]) obj);
			}
			if (obj instanceof int[]) {
				return HashCodeNullSafe((int[]) obj);
			}
			if (obj instanceof long[]) {
				return HashCodeNullSafe((long[]) obj);
			}
			if (obj instanceof short[]) {
				return HashCodeNullSafe((short[]) obj);
			}
		}
		return obj.hashCode();
	}

	public static int HashCodeNullSafe(Object[] array) {
		if (array == null) {
			return 0;
		}
		int hash = INITIAL_HASH;
		for (Object element : array) {
			hash = MULTIPLIER * hash + HashCodeNullSafe(element);
		}
		return hash;
	}
	
	public static int HashCodeNullSafe(boolean[] array) {
		if (array == null) {
			return 0;
		}
		int hash = INITIAL_HASH;
		for (boolean element : array) {
			hash = MULTIPLIER * hash + HashCode(element);
		}
		return hash;
	}
	
	public static int HashCodeNullSafe(byte[] array) {
		if (array == null) {
			return 0;
		}
		int hash = INITIAL_HASH;
		for (byte element : array) {
			hash = MULTIPLIER * hash + element;
		}
		return hash;
	}
	
	public static int HashCodeNullSafe(char[] array) {
		if (array == null) {
			return 0;
		}
		int hash = INITIAL_HASH;
		for (char element : array) {
			hash = MULTIPLIER * hash + element;
		}
		return hash;
	}
	
	public static int HashCodeNullSafe(double[] array) {
		if (array == null) {
			return 0;
		}
		int hash = INITIAL_HASH;
		for (double element : array) {
			hash = MULTIPLIER * hash + HashCode(element);
		}
		return hash;
	}
	
	public static int HashCodeNullSafe(float[] array) {
		if (array == null) {
			return 0;
		}
		int hash = INITIAL_HASH;
		for (float element : array) {
			hash = MULTIPLIER * hash + HashCode(element);
		}
		return hash;
	}
	
	public static int HashCodeNullSafe(int[] array) {
		if (array == null) {
			return 0;
		}
		int hash = INITIAL_HASH;
		for (int element : array) {
			hash = MULTIPLIER * hash + element;
		}
		return hash;
	}
	
	public static int HashCodeNullSafe(long[] array) {
		if (array == null) {
			return 0;
		}
		int hash = INITIAL_HASH;
		for (long element : array) {
			hash = MULTIPLIER * hash + HashCode(element);
		}
		return hash;
	}
	
	public static int HashCodeNullSafe(short[] array) {
		if (array == null) {
			return 0;
		}
		int hash = INITIAL_HASH;
		for (short element : array) {
			hash = MULTIPLIER * hash + element;
		}
		return hash;
	}

	public static int HashCode(boolean bool) {
		return (bool ? 1231 : 1237);
	}
	
	public static int HashCode(double dbl) {
		return HashCode(Double.doubleToLongBits(dbl));
	}
	
	public static int HashCode(float flt) {
		return Float.floatToIntBits(flt);
	}
	
	public static int HashCode(long lng) {
		return (int) (lng ^ (lng >>> 32));
	}

	//---------------------------------------------------------------------
	// Convenience methods for toString output
	//---------------------------------------------------------------------

	/**
	 * 获取对象的身份表述字符串
	 @method ObjectUtil: IdentityToString()
	 @memo TODO
	 @param obj
	 @return String
	 */
	public static String IdentityToString(Object obj) {
		if (obj == null) {
			return EMPTY_STRING;
		}
		return obj.getClass().getName() + "@" + GetIdentityHexString(obj);
	}

	/**
	 * 获取对象的16进制身份表述字符串
	 @method ObjectUtil: GetIdentityHexString()
	 @memo TODO
	 @param obj
	 @return String
	 */
	public static String GetIdentityHexString(Object obj) {
		return Integer.toHexString(System.identityHashCode(obj));
	}

	/**
	 * 获取对象的展示字符串
	 @method ObjectUtil: GetDisplayString()
	 @memo TODO
	 @param obj
	 @return String
	 */
	public static String GetDisplayString(Object obj) {
		if (obj == null) {
			return EMPTY_STRING;
		}
		return ToStringNullSafe(obj);
	}

	/**
	 * 获取对象类名称	NULL安全模式
	 @method ObjectUtil: ClassNameNullSafe()
	 @memo TODO
	 @param obj
	 @return String
	 */
	public static String ClassNameNullSafe(Object obj) {
		return (obj != null ? obj.getClass().getName() : NULL_STRING);
	}

	/**
	 * 对象转成字符串取值	NULL安全模式
	 @method ObjectUtil: ToStringNullSafe()
	 @memo TODO
	 @param obj
	 @return String
	 */
	public static String ToStringNullSafe(Object obj) {
		if (obj == null) {
			return NULL_STRING;
		}
		if (obj instanceof String) {
			return (String) obj;
		}
		if (obj instanceof Object[]) {
			return ToStringNullSafe((Object[]) obj);
		}
		if (obj instanceof boolean[]) {
			return ToStringNullSafe((boolean[]) obj);
		}
		if (obj instanceof byte[]) {
			return ToStringNullSafe((byte[]) obj);
		}
		if (obj instanceof char[]) {
			return ToStringNullSafe((char[]) obj);
		}
		if (obj instanceof double[]) {
			return ToStringNullSafe((double[]) obj);
		}
		if (obj instanceof float[]) {
			return ToStringNullSafe((float[]) obj);
		}
		if (obj instanceof int[]) {
			return ToStringNullSafe((int[]) obj);
		}
		if (obj instanceof long[]) {
			return ToStringNullSafe((long[]) obj);
		}
		if (obj instanceof short[]) {
			return ToStringNullSafe((short[]) obj);
		}
		String str = obj.toString();
		return (str != null ? str : EMPTY_STRING);
	}

	public static String ToStringNullSafe(Object[] array) {
		if (array == null) {
			return NULL_STRING;
		}
		int length = array.length;
		if (length == 0) {
			return EMPTY_ARRAY;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			if (i == 0) {
				sb.append(ARRAY_START);
			}
			else {
				sb.append(ARRAY_ELEMENT_SEPARATOR);
			}
			sb.append(String.valueOf(array[i]));
		}
		sb.append(ARRAY_END);
		return sb.toString();
	}
	
	public static String ToStringNullSafe(boolean[] array) {
		if (array == null) {
			return NULL_STRING;
		}
		int length = array.length;
		if (length == 0) {
			return EMPTY_ARRAY;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			if (i == 0) {
				sb.append(ARRAY_START);
			}
			else {
				sb.append(ARRAY_ELEMENT_SEPARATOR);
			}

			sb.append(array[i]);
		}
		sb.append(ARRAY_END);
		return sb.toString();
	}
	
	public static String ToStringNullSafe(byte[] array) {
		if (array == null) {
			return NULL_STRING;
		}
		int length = array.length;
		if (length == 0) {
			return EMPTY_ARRAY;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			if (i == 0) {
				sb.append(ARRAY_START);
			}
			else {
				sb.append(ARRAY_ELEMENT_SEPARATOR);
			}
			sb.append(array[i]);
		}
		sb.append(ARRAY_END);
		return sb.toString();
	}
	
	public static String ToStringNullSafe(char[] array) {
		if (array == null) {
			return NULL_STRING;
		}
		int length = array.length;
		if (length == 0) {
			return EMPTY_ARRAY;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			if (i == 0) {
				sb.append(ARRAY_START);
			}
			else {
				sb.append(ARRAY_ELEMENT_SEPARATOR);
			}
			sb.append("'").append(array[i]).append("'");
		}
		sb.append(ARRAY_END);
		return sb.toString();
	}
	
	public static String ToStringNullSafe(double[] array) {
		if (array == null) {
			return NULL_STRING;
		}
		int length = array.length;
		if (length == 0) {
			return EMPTY_ARRAY;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			if (i == 0) {
				sb.append(ARRAY_START);
			}
			else {
				sb.append(ARRAY_ELEMENT_SEPARATOR);
			}

			sb.append(array[i]);
		}
		sb.append(ARRAY_END);
		return sb.toString();
	}
	
	public static String ToStringNullSafe(float[] array) {
		if (array == null) {
			return NULL_STRING;
		}
		int length = array.length;
		if (length == 0) {
			return EMPTY_ARRAY;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			if (i == 0) {
				sb.append(ARRAY_START);
			}
			else {
				sb.append(ARRAY_ELEMENT_SEPARATOR);
			}

			sb.append(array[i]);
		}
		sb.append(ARRAY_END);
		return sb.toString();
	}
	
	public static String ToStringNullSafe(int[] array) {
		if (array == null) {
			return NULL_STRING;
		}
		int length = array.length;
		if (length == 0) {
			return EMPTY_ARRAY;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			if (i == 0) {
				sb.append(ARRAY_START);
			}
			else {
				sb.append(ARRAY_ELEMENT_SEPARATOR);
			}
			sb.append(array[i]);
		}
		sb.append(ARRAY_END);
		return sb.toString();
	}
	
	public static String ToStringNullSafe(long[] array) {
		if (array == null) {
			return NULL_STRING;
		}
		int length = array.length;
		if (length == 0) {
			return EMPTY_ARRAY;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			if (i == 0) {
				sb.append(ARRAY_START);
			}
			else {
				sb.append(ARRAY_ELEMENT_SEPARATOR);
			}
			sb.append(array[i]);
		}
		sb.append(ARRAY_END);
		return sb.toString();
	}
	
	public static String ToStringNullSafe(short[] array) {
		if (array == null) {
			return NULL_STRING;
		}
		int length = array.length;
		if (length == 0) {
			return EMPTY_ARRAY;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			if (i == 0) {
				sb.append(ARRAY_START);
			}
			else {
				sb.append(ARRAY_ELEMENT_SEPARATOR);
			}
			sb.append(array[i]);
		}
		sb.append(ARRAY_END);
		return sb.toString();
	}
}
