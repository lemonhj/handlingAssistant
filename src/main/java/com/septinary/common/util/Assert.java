package com.septinary.common.util;

import java.util.Collection;
import java.util.Map;

/**
 * 断言
 * @Filename: com.septinary.common.util.Assert.java of the project [com.septinary.common]
 *     @Type: Assert
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月12日下午4:53:23
 *
 */
public abstract class Assert {
	
	/**
	 * 断言相等
	 @method Assert: IsEqual()
	 @memo TODO
	 @param o1
	 @param o2
	 @param message void
	 */
	public static void IsEqual(Object o1, Object o2, String message) {
		if(ObjectUtil.IsEmpty(o1) || ObjectUtil.IsEmpty(o2) || !ObjectUtil.EqualNullSafe(o1, o2)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言相等
	 @method Assert: IsEqual()
	 @memo TODO
	 @param o1
	 @param o2
	 @param message void
	 */
	public static void IsEqual(Object o1, Object o2) {
		IsEqual(o1,o2,"[Assertion failed] - object of class["+(null==o1?"null":o1.getClass().getName())+"] must equal object of class ["+(null==o2?"null":o2.getClass().getName())+"]");
	}
	
	/**
	 * 断言不等
	 @method Assert: NotEqual()
	 @memo TODO
	 @param o1
	 @param o2
	 @param message void
	 */
	public static void NotEqual(Object o1, Object o2, String message) {
		if(ObjectUtil.IsEmpty(o1) || ObjectUtil.IsEmpty(o2) || ObjectUtil.EqualNullSafe(o1, o2)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言不等
	 @method Assert: NotEqual()
	 @memo TODO
	 @param o1
	 @param o2
	 @param message void
	 */
	public static void NotEqual(Object o1, Object o2) {
		IsEqual(o1,o2,"[Assertion failed] - object of class["+(null==o1?"null":o1.getClass().getName())+"] must not equal object of class ["+(null==o2?"null":o2.getClass().getName())+"]");
	}

	/**
	 * 断言为真
	 @method Assert: IsTrue()
	 @memo TODO
	 @param expression
	 @param message void
	 */
	public static void IsTrue(boolean expression, String message) {
		if (!expression) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言为真
	 @method Assert: IsTrue()
	 @memo TODO
	 @param expression void
	 */
	public static void IsTrue(boolean expression) {
		IsTrue(expression, "[Assertion failed] - this expression must be true");
	}
	
	/**
	 * 断言非真
	 @method Assert: NotTrue()
	 @memo TODO
	 @param expression
	 @param message void
	 */
	public static void NotTrue(boolean expression, String message) {
		if (expression) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言为假
	 @method Assert: NotTrue()
	 @memo TODO
	 @param expression void
	 */
	public static void NotTrue(boolean expression) {
		NotTrue(expression, "[Assertion failed] - this expression must not be true");
	}

	/**
	 * 断言为NULL
	 @method Assert: IsNull()
	 @memo TODO
	 @param object
	 @param message void
	 */
	public static void IsNull(Object object, String message) {
		if (object != null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言为NULL
	 @method Assert: IsNull()
	 @memo TODO
	 @param object void
	 */
	public static void IsNull(Object object) {
		IsNull(object, "[Assertion failed] - the object argument must be null");
	}

	/**
	 * 断言非NULL
	 @method Assert: NotNull()
	 @memo TODO
	 @param object
	 @param message void
	 */
	public static void NotNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言非NULL
	 @method Assert: NotNull()
	 @memo TODO
	 @param object void
	 */
	public static void NotNull(Object object) {
		NotNull(object, "[Assertion failed] - this argument is required; it must not be null");
	}

	/**
	 * 断言具有长度
	 @method Assert: HasLength()
	 @memo TODO
	 @param text
	 @param message void
	 */
	public static void HasLength(String text, String message) {
		if (!StringUtil.HasLength(text)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言具有长度
	 @method Assert: HasLength()
	 @memo TODO
	 @param text void
	 */
	public static void HasLength(String text) {
		HasLength(text,"[Assertion failed] - this String argument must have length; it must not be null or empty");
	}

	/**
	 * 断言具有文本
	 @method Assert: HasText()
	 @memo TODO
	 @param text
	 @param message void
	 */
	public static void HasText(String text, String message) {
		if (!StringUtil.HasText(text)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言具有文本
	 @method Assert: HasText()
	 @memo TODO
	 @param text void
	 */
	public static void HasText(String text) {
		HasText(text,"[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
	}

	/**
	 * 断言不包含
	 @method Assert: NotContain()
	 @memo TODO
	 @param textToSearch
	 @param substring
	 @param message void
	 */
	public static void NotContain(String textToSearch, String substring, String message) {
		if (StringUtil.HasLength(textToSearch) && StringUtil.HasLength(substring) && textToSearch.contains(substring)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言不包含
	 @method Assert: NotContain()
	 @memo TODO
	 @param textToSearch
	 @param substring void
	 */
	public static void NotContain(String textToSearch, String substring) {
		NotContain(textToSearch, substring,"[Assertion failed] - this String argument must not contain the substring [" + substring + "]");
	}

	/**
	 * 断言包含
	 @method Assert: Contains()
	 @memo TODO
	 @param textToSearch
	 @param substring
	 @param message void
	 */
	public static void Contains(String textToSearch, String substring, String message) {
		if (StringUtil.HasLength(textToSearch) && StringUtil.HasLength(substring) && !textToSearch.contains(substring)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言包含
	 @method Assert: Contains()
	 @memo TODO
	 @param textToSearch
	 @param substring void
	 */
	public static void Contains(String textToSearch, String substring) {
		Contains(textToSearch, substring,"[Assertion failed] - this String argument must contain the substring [" + substring + "]");
	}

	/**
	 * 断言非空
	 @method Assert: NotEmpty()
	 @memo TODO
	 @param array
	 @param message void
	 */
	public static void NotEmpty(Object[] array, String message) {
		if (ObjectUtil.IsEmpty(array)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言非空
	 @method Assert: NotEmpty()
	 @memo TODO
	 @param array void
	 */
	public static void NotEmpty(Object[] array) {
		NotEmpty(array, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
	}
	
	/**
	 * 断言为空
	 @method Assert: IsEmpty()
	 @memo TODO
	 @param array
	 @param message void
	 */
	public static void IsEmpty(Object[] array, String message) {
		if (!ObjectUtil.IsEmpty(array)) {
			throw new IllegalArgumentException(message);
		}
	}
	
	/**
	 * 断言为空
	 @method Assert: IsEmpty()
	 @memo TODO
	 @param array void
	 */
	public static void IsEmpty(Object[] array) {
		IsEmpty(array, "[Assertion failed] - this array must be empty");
	}

	/**
	 * 断言不含NULL元素
	 @method Assert: NoNullElements()
	 @memo TODO
	 @param array
	 @param message void
	 */
	public static void NoNullElements(Object[] array, String message) {
		if (array != null) {
			for (Object element : array) {
				if (element == null) {
					throw new IllegalArgumentException(message);
				}
			}
		} else {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言不含NULL元素
	 @method Assert: NoNullElements()
	 @memo TODO
	 @param array void
	 */
	public static void NoNullElements(Object[] array) {
		NoNullElements(array, "[Assertion failed] - this array must not contain any null elements");
	}

	/**
	 * 断言不含NULL参数
	 * @param message
	 * @param array
	 */
	public static void NoneNull(String message, Object... array) {
		NoNullElements(array, message);
	}

	/**
	 * 断言不含NULL参数
	 * @param array
	 */
	public static void NoneNull(Object... array) {
		NoNullElements(array, "[Assertion failed] - those arguments must not contain any null elements");
	}
	
	/**
	 * 断言不含true参数
	 * @param message
	 * @param array
	 */
	public static void NoneTrue(String message, boolean... array) {
		for (boolean element : array) {
			if (element == true) {
				throw new IllegalArgumentException(message);
			}
		}
	}
	
	/**
	 * 断言不含true参数
	 * @param array
	 */
	public static void NoneTrue(boolean... array) {
		NoneTrue("[Assertion failed] - those arguments must not contain any true express", array);
	}
	
	/**
	 * 断言不含false参数
	 * @param message
	 * @param array
	 */
	public static void NoneFalse(String message, boolean... array) {
		for (boolean element : array) {
			if (element == false) {
				throw new IllegalArgumentException(message);
			}
		}
	}
	
	/**
	 * 断言不含false参数
	 * @param array
	 */
	public static void NoneFalse(boolean... array) {
		NoneFalse("[Assertion failed] - those arguments must not contain any true express", array);
	}
	
	/**
	 * 断言参数全为false
	 * @param message
	 * @param array
	 */
	public static void AllFalse(String message, boolean... array) {
		for (boolean element : array) {
			if (element != false) {
				throw new IllegalArgumentException(message);
			}
		}
	}
	
	/**
	 * 断言参数全为false
	 * @param array
	 */
	public static void AllFalse(boolean... array) {
		AllFalse("[Assertion failed] - those arguments must not contain any false express", array);
	}
	
	/**
	 * 断言参数全为true
	 * @param message
	 * @param array
	 */
	public static void AllTrue(String message, boolean... array) {
		for (boolean element : array) {
			if (element != true) {
				throw new IllegalArgumentException(message);
			}
		}
	}
	
	/**
	 * 断言参数全为true
	 * @param array
	 */
	public static void AllTrue(boolean... array) {
		AllTrue("[Assertion failed] - those arguments must not contain any false express", array);
	}
	
	/**
	 * 断言元素全部为NULL
	 * @param array
	 * @param message
	 */
	public static void AllNullElements(Object[] array, String message) {
		if (array != null) {
			for (Object element : array) {
				if (element != null) {
					throw new IllegalArgumentException(message);
				}
			}
		}
	}
	
	/**
	 * 断言元素全部为NULL
	 * @param array
	 */
	public static void AllNullElements(Object[] array) {
		AllNullElements(array, "[Assertion failed] - this array must not contain any not null elements");
	}
	
	/**
	 * 断言参数全是NULL
	 * @param message
	 * @param array
	 */
	public static void AllNull(String message, Object... array) {
		AllNullElements(array, message);
	}
	
	/**
	 * 断言参数全是NULL
	 * @param array
	 */
	public static void AllNull(Object... array) {
		AllNullElements(array, "[Assertion failed] - those arguments must not contain any not null elements");
	}

	/**
	 * 断言非空
	 @method Assert: NotEmpty()
	 @memo TODO
	 @param collection
	 @param message void
	 */
	public static void NotEmpty(Collection<?> collection, String message) {
		if (CollectionUtil.IsEmpty(collection)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言非空
	 @method Assert: NotEmpty()
	 @memo TODO
	 @param collection void
	 */
	public static void NotEmpty(Collection<?> collection) {
		NotEmpty(collection,"[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
	}
	
	/**
	 * 断言为空
	 @method Assert: IsEmpty()
	 @memo TODO
	 @param collection
	 @param message void
	 */
	public static void IsEmpty(Collection<?> collection, String message) {
		if (!CollectionUtil.IsEmpty(collection)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言为空
	 @method Assert: IsEmpty()
	 @memo TODO
	 @param collection void
	 */
	public static void IsEmpty(Collection<?> collection) {
		IsEmpty(collection,"[Assertion failed] - this collection must be empty");
	}

	/**
	 * 断言非空
	 @method Assert: NotEmpty()
	 @memo TODO
	 @param map
	 @param message void
	 */
	public static void NotEmpty(Map<?, ?> map, String message) {
		if (CollectionUtil.IsEmpty(map)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言非空
	 @method Assert: NotEmpty()
	 @memo TODO
	 @param map void
	 */
	public static void NotEmpty(Map<?, ?> map) {
		NotEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least 1 entry");
	}

	/**
	 * 断言为空
	 @method Assert: IsEmpty()
	 @memo TODO
	 @param map
	 @param message void
	 */
	public static void IsEmpty(Map<?, ?> map, String message) {
		if (!CollectionUtil.IsEmpty(map)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言为空
	 @method Assert: IsEmpty()
	 @memo TODO
	 @param map void
	 */
	public static void IsEmpty(Map<?, ?> map) {
		IsEmpty(map, "[Assertion failed] - this map must be empty");
	}

	/**
	 * 断言对象类型
	 @method Assert: InstanceOf()
	 @memo TODO
	 @param clazz
	 @param obj void
	 */
	public static void InstanceOf(Class<?> clazz, Object obj) {
		InstanceOf(clazz, obj, null);
	}

	/**
	 * 断言对象类型
	 @method Assert: InstanceOf()
	 @memo TODO
	 @param type
	 @param obj
	 @param message void
	 */
	public static void InstanceOf(Class<?> type, Object obj, String message) {
		NotNull(type, "Type to check against must not be null");
		if (!type.isInstance(obj)) {
			throw new IllegalArgumentException( (StringUtil.HasLength(message) ? message + " " : "") + "Object of class [" + (obj != null ? obj.getClass().getName() : "null") + "] must be an instance of " + type);
		}
	}

	/**
	 * 断言非对象类型
	 @method Assert: NotInstanceOf()
	 @memo TODO
	 @param clazz
	 @param obj void
	 */
	public static void NotInstanceOf(Class<?> clazz, Object obj) {
		NotInstanceOf(clazz, obj, null);
	}

	/**
	 * 断言非对象类型
	 @method Assert: NotInstanceOf()
	 @memo TODO
	 @param type
	 @param obj
	 @param message void
	 */
	public static void NotInstanceOf(Class<?> type, Object obj, String message) {
		if (null==type || type.isInstance(obj)) {
			throw new IllegalArgumentException( (StringUtil.HasLength(message) ? message + " " : "") + "Object of class [" + (obj != null ? obj.getClass().getName() : "null") + "] must not be an instance of " + type);
		}
	}

	/**
	 * 断言可赋值
	 @method Assert: IsAssignable()
	 @memo TODO
	 @param superType
	 @param subType void
	 */
	public static void IsAssignable(Class<?> superType, Class<?> subType) {
		IsAssignable(superType, subType, "");
	}

	/**
	 * 断言可赋值
	 @method Assert: IsAssignable()
	 @memo TODO
	 @param superType
	 @param subType
	 @param message void
	 */
	public static void IsAssignable(Class<?> superType, Class<?> subType, String message) {
		NotNull(superType, "Type to check against must not be null");
		if (subType == null || !superType.isAssignableFrom(subType)) {
			throw new IllegalArgumentException((StringUtil.HasLength(message) ? message + " " : "") + subType + " is not assignable to " + superType);
		}
	}

	/**
	 * 断言状态
	 @method Assert: State()
	 @memo TODO
	 @param expression
	 @param message void
	 */
	public static void State(boolean expression, String message) {
		if (!expression) {
			throw new IllegalStateException(message);
		}
	}

	/**
	 * 断言状态
	 @method Assert: State()
	 @memo TODO
	 @param expression void
	 */
	public static void State(boolean expression) {
		State(expression, "[Assertion failed] - this state invariant must be true");
	}
	
	/**
	 * 断言等于枚举值
	 @method Assert: Enum()
	 @memo TODO
	 @param e
	 @param constanit
	 @param message void
	 */
	public static <E extends Enum<?>> void IsEnum(E e, String constanit, String message) {
		if(null==e || !e.name().equals(constanit)) {
			throw new IllegalArgumentException(message);
		}
	}
	
	/**
	 * 断言等于枚举值
	 @method Assert: Enum()
	 @memo TODO
	 @param e
	 @param constanit
	 @param message void
	 */
	public static <E extends Enum<?>> void IsEnum(E e, String constanit) {
		IsEnum(e,constanit,"[Assertion failed] - this String["+constanit+"] must be "+e.name());
	}
	
	/**
	 * 断言非指定的枚举值
	 @method Assert: NotEnum()
	 @memo TODO
	 @param e
	 @param constanit
	 @param message void
	 */
	public static <E extends Enum<?>> void NotEnum(E e, String constanit, String message) {
		if(null==e || e.name().equals(constanit)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 断言非指定的枚举值
	 @method Assert: NotEnum()
	 @memo TODO
	 @param e
	 @param constanit
	 @param message void
	 */
	public static <E extends Enum<?>> void NotEnum(E e, String constanit) {
		NotEnum(e,constanit,"[Assertion failed] - this String["+constanit+"] must not be "+e.name());
	}
	
	/**
	 * 断言是某一枚举取值
	 @method Assert: InEnum()
	 @memo TODO
	 @param E
	 @param constanit
	 @param message void
	 */
	public static void InEnum(Enum<?> E, String constanit, String message) {
		Enum<?> values[] = E.getClass().getEnumConstants();
		boolean in = false;
		for(Enum<?> item: values) {
			if( constanit.equals(item.name()) ) {
				in = true;
				break;
			}
		}
		if(!in) {
			throw new IllegalArgumentException(message);
		}
	}
	
	/**
	 * 断言是某一枚举取值
	 @method Assert: InEnum()
	 @memo TODO
	 @param E
	 @param constanit
	 @param message void
	 */
	public static void InEnum(Enum<?> E, String constanit) {
		Enum<?> values[] = E.getClass().getEnumConstants();
		String v = null;
		for(Enum<?> item: values) v += (null==v ? "" : ",") + item.name();
		InEnum(E,constanit,"[Assertion failed] - this String["+constanit+"] must be between ["+v+"]");
	}
	
	/**
	 * 断言不是任一枚举取值
	 @method Assert: NotInEnum()
	 @memo TODO
	 @param E
	 @param constanit
	 @param message void
	 */
	public static void NotInEnum(Enum<?> E, String constanit, String message) {
		Enum<?> values[] = E.getClass().getEnumConstants();
		for(Enum<?> item: values) {
			if( constanit.equals(item.name()) ) {
				throw new IllegalArgumentException(message);
			}
		}
	}
	
	/**
	 * 断言不是任一枚举取值
	 @method Assert: NotInEnum()
	 @memo TODO
	 @param E
	 @param constanit
	 @param message void
	 */
	public static void NotInEnum(Enum<?> E, String constanit) {
		Enum<?> values[] = E.getClass().getEnumConstants();
		String v = null;
		for(Enum<?> item: values) v += (null==v ? "" : ",") + item.name();
		NotInEnum(E,constanit,"[Assertion failed] - this String["+constanit+"] must not be between ["+v+"]");
	}
}