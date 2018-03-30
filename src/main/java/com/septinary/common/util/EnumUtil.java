package com.septinary.common.util;

/**
 * 枚举操作
 * @Filename: com.septinary.common.util.EnumUtil.java of the project [com.septinary.common]
 *     @Type: EnumUtil
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年5月13日下午12:35:05
 *
 */
public abstract class EnumUtil {

	/**
	 * 枚举列表中是否包含指定取值？
	 @method ObjectUtil: ContainsConstant()
	 @memo TODO
	 @param enumValues
	 @param constant
	 @return boolean
	 */
	public static boolean ContainsConstant(Enum<?>[] enumValues, String constant) {
		return ContainsConstant(enumValues, constant, false);
	}

	/**
	 * 枚举列表中是否包含指定取值？
	 @method ObjectUtil: ContainsConstant()
	 @memo TODO
	 @param enumValues
	 @param constant
	 @param caseSensitive
	 @return boolean
	 */
	public static boolean ContainsConstant(Enum<?>[] enumValues, String constant, boolean caseSensitive) {
		for (Enum<?> candidate : enumValues) {
			if (caseSensitive ? candidate.toString().equals(constant) : candidate.toString().equalsIgnoreCase(constant)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 字符串转换枚举类型
	 @method ObjectUtil: ValueOf()
	 @memo TODO
	 @param enumValues
	 @param constant
	 @return E
	 */
	public static <E extends Enum<?>> E ValueOf(E[] enumValues, String constant) {
		for (E candidate : enumValues) {
			if (candidate.toString().equalsIgnoreCase(constant)) {
				return candidate;
			}
		}
		throw new IllegalArgumentException(String.format("constant [%s] does not exist in enum type %s",constant, enumValues.getClass().getComponentType().getName()));
	}
}
