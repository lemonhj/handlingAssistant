package com.septinary.common.util;

import java.beans.Introspector;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 类型操作
 * @Filename: com.septinary.common.util.ClassUtil.java of the project [com.septinary.common]
 *     @Type: ClassUtil
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月9日下午5:36:00
 *
 */
abstract public class ClassUtil {

	/** Suffix for array class names: "[]" */
	public static final String ARRAY_SUFFIX = "[]";

	/** Prefix for internal array class names: "[" */
	private static final String INTERNAL_ARRAY_PREFIX = "[";

	/** Prefix for internal non-primitive array class names: "[L" */
	private static final String NON_PRIMITIVE_ARRAY_PREFIX = "[L";

	/** The package separator character '.' */
	private static final char PACKAGE_SEPARATOR = '.';

	/** The path separator character '/' */
	private static final char PATH_SEPARATOR = '/';

	/** The inner class separator character '$' */
	private static final char INNER_CLASS_SEPARATOR = '$';

	/** The CGLIB class separator character "$$" */
	public static final String CGLIB_CLASS_SEPARATOR = "$$";

	/** The ".class" file suffix */
	public static final String CLASS_FILE_SUFFIX = ".class";


	/**
	 * Map with primitive wrapper type as key and corresponding primitive
	 * type as value, for example: Integer.class -> int.class.
	 */
	private static final Map<Class<?>, Class<?>> primitiveWrapperTypeMap = new IdentityHashMap<Class<?>, Class<?>>(8);

	/**
	 * Map with primitive type as key and corresponding wrapper
	 * type as value, for example: int.class -> Integer.class.
	 */
	private static final Map<Class<?>, Class<?>> primitiveTypeToWrapperMap = new IdentityHashMap<Class<?>, Class<?>>(8);

	/**
	 * Map with primitive type name as key and corresponding primitive
	 * type as value, for example: "int" -> "int.class".
	 */
	private static final Map<String, Class<?>> primitiveTypeNameMap = new HashMap<String, Class<?>>(32);

	/**
	 * Map with common "java.lang" class name as key and corresponding Class as value.
	 * Primarily for efficient deserialization of remote invocations.
	 */
	private static final Map<String, Class<?>> commonClassCache = new HashMap<String, Class<?>>(32);


	static {
		primitiveWrapperTypeMap.put(Boolean.class, boolean.class);
		primitiveWrapperTypeMap.put(Byte.class, byte.class);
		primitiveWrapperTypeMap.put(Character.class, char.class);
		primitiveWrapperTypeMap.put(Double.class, double.class);
		primitiveWrapperTypeMap.put(Float.class, float.class);
		primitiveWrapperTypeMap.put(Integer.class, int.class);
		primitiveWrapperTypeMap.put(Long.class, long.class);
		primitiveWrapperTypeMap.put(Short.class, short.class);

		for (Map.Entry<Class<?>, Class<?>> entry : primitiveWrapperTypeMap.entrySet()) {
			primitiveTypeToWrapperMap.put(entry.getValue(), entry.getKey());
			registerCommonClasses(entry.getKey());
		}

		Set<Class<?>> primitiveTypes = new HashSet<Class<?>>(32);
		primitiveTypes.addAll(primitiveWrapperTypeMap.values());
		primitiveTypes.addAll(Arrays.asList(new Class<?>[] {
			boolean[].class, byte[].class, char[].class, double[].class, float[].class, int[].class, long[].class, short[].class
		}));
		primitiveTypes.add(void.class);
		for (Class<?> primitiveType : primitiveTypes) {
			primitiveTypeNameMap.put(primitiveType.getName(), primitiveType);
		}

		registerCommonClasses(Boolean[].class, Byte[].class, Character[].class, Double[].class, Float[].class, Integer[].class, Long[].class, Short[].class);
		registerCommonClasses(Number.class, Number[].class, String.class, String[].class, Object.class, Object[].class, Class.class, Class[].class);
		registerCommonClasses(Throwable.class, Exception.class, RuntimeException.class, Error.class, StackTraceElement.class, StackTraceElement[].class);
	}


	/**
	 * Register the given common classes with the ClassUtils cache.
	 */
	private static void registerCommonClasses(Class<?>... commonClasses) {
		for (Class<?> clazz : commonClasses) {
			commonClassCache.put(clazz.getName(), clazz);
		}
	}

	/**
	 * 强制转换
	 @method ClassUtil: cast()
	 @memo TODO
	 @param object
	 @return T
	 */
	@SuppressWarnings("unchecked")
	public <T> T Cast(Object object) {
		try {
			return (T)object;
		} catch (ClassCastException cce) {
			return null;
		}
	}
	
	/**
	 * 获取默认的ClassLoader
	 * Return the default ClassLoader to use: typically the thread context
	 * ClassLoader, if available; the ClassLoader that loaded the ClassUtils
	 * class will be used as fallback.
	 * <p>Call this method if you intend to use the thread context ClassLoader
	 * in a scenario where you clearly prefer a non-null ClassLoader reference:
	 * for example, for class path resource loading (but not necessarily for
	 * {@code Class.forName}, which accepts a {@code null} ClassLoader
	 * reference as well).
	 * @return the default ClassLoader (only {@code null} if even the system
	 * ClassLoader isn't accessible)
	 * @see Thread#getContextClassLoader()
	 * @see ClassLoader#getSystemClassLoader()
	 */
	public static ClassLoader GetDefaultClassLoader() {
		ClassLoader cl = null;
		try {
			cl = Thread.currentThread().getContextClassLoader();
		}
		catch (Throwable ex) {
			// Cannot access thread context ClassLoader - falling back...
		}
		if (cl == null) {
			// No thread context class loader -> use class loader of this class.
			cl = ClassUtil.class.getClassLoader();
			if (cl == null) {
				// getClassLoader() returning null indicates the bootstrap ClassLoader
				try {
					cl = ClassLoader.getSystemClassLoader();
				}
				catch (Throwable ex) {
					// Cannot access system ClassLoader - oh well, maybe the caller can live with null...
				}
			}
		}
		return cl;
	}

	/**
	 * 重载线程上下文ClassLoader
	 * Override the thread context ClassLoader with the environment's bean ClassLoader
	 * if necessary, i.e. if the bean ClassLoader is not equivalent to the thread
	 * context ClassLoader already.
	 * @param classLoaderToUse the actual ClassLoader to use for the thread context
	 * @return the original thread context ClassLoader, or {@code null} if not overridden
	 */
	public static ClassLoader OverrideThreadContextClassLoader(ClassLoader classLoaderToUse) {
		Thread currentThread = Thread.currentThread();
		ClassLoader threadContextClassLoader = currentThread.getContextClassLoader();
		if (classLoaderToUse != null && !classLoaderToUse.equals(threadContextClassLoader)) {
			currentThread.setContextClassLoader(classLoaderToUse);
			return threadContextClassLoader;
		}
		else {
			return null;
		}
	}

	/**
	 * 加载类
	 * Replacement for {@code Class.forName()} that also returns Class instances
	 * for primitives (e.g. "int") and array class names (e.g. "String[]").
	 * Furthermore, it is also capable of resolving inner class names in Java source
	 * style (e.g. "java.lang.Thread.State" instead of "java.lang.Thread$State").
	 * @param name the name of the Class
	 * @param classLoader the class loader to use
	 * (may be {@code null}, which indicates the default class loader)
	 * @return Class instance for the supplied name
	 * @throws ClassNotFoundException if the class was not found
	 * @throws LinkageError if the class file could not be loaded
	 * @see Class#forName(String, boolean, ClassLoader)
	 */
	public static Class<?> ForName(String name, ClassLoader classLoader) throws ClassNotFoundException, LinkageError {
		Assert.NotNull(name, "Name must not be null");

		Class<?> clazz = ResolvePrimitiveClassName(name);
		if (clazz == null) {
			clazz = commonClassCache.get(name);
		}
		if (clazz != null) {
			return clazz;
		}

		// "java.lang.String[]" style arrays
		if (name.endsWith(ARRAY_SUFFIX)) {
			String elementClassName = name.substring(0, name.length() - ARRAY_SUFFIX.length());
			Class<?> elementClass = ForName(elementClassName, classLoader);
			return Array.newInstance(elementClass, 0).getClass();
		}

		// "[Ljava.lang.String;" style arrays
		if (name.startsWith(NON_PRIMITIVE_ARRAY_PREFIX) && name.endsWith(";")) {
			String elementName = name.substring(NON_PRIMITIVE_ARRAY_PREFIX.length(), name.length() - 1);
			Class<?> elementClass = ForName(elementName, classLoader);
			return Array.newInstance(elementClass, 0).getClass();
		}

		// "[[I" or "[[Ljava.lang.String;" style arrays
		if (name.startsWith(INTERNAL_ARRAY_PREFIX)) {
			String elementName = name.substring(INTERNAL_ARRAY_PREFIX.length());
			Class<?> elementClass = ForName(elementName, classLoader);
			return Array.newInstance(elementClass, 0).getClass();
		}

		ClassLoader clToUse = classLoader;
		if (clToUse == null) {
			clToUse = GetDefaultClassLoader();
		}
		try {
			return (clToUse != null ? clToUse.loadClass(name) : Class.forName(name));
		}
		catch (ClassNotFoundException ex) {
			int lastDotIndex = name.lastIndexOf(PACKAGE_SEPARATOR);
			if (lastDotIndex != -1) {
				String innerClassName =
						name.substring(0, lastDotIndex) + INNER_CLASS_SEPARATOR + name.substring(lastDotIndex + 1);
				try {
					return (clToUse != null ? clToUse.loadClass(innerClassName) : Class.forName(innerClassName));
				}
				catch (ClassNotFoundException ex2) {
					// Swallow - let original exception get through
				}
			}
			throw ex;
		}
	}

	/**
	 * 解析类名
	 * Resolve the given class name into a Class instance. Supports
	 * primitives (like "int") and array class names (like "String[]").
	 * <p>This is effectively equivalent to the {@code forName}
	 * method with the same arguments, with the only difference being
	 * the exceptions thrown in case of class loading failure.
	 * @param className the name of the Class
	 * @param classLoader the class loader to use
	 * (may be {@code null}, which indicates the default class loader)
	 * @return Class instance for the supplied name
	 * @throws IllegalArgumentException if the class name was not resolvable
	 * (that is, the class could not be found or the class file could not be loaded)
	 * @see #forName(String, ClassLoader)
	 */
	public static Class<?> ResolveClassName(String className, ClassLoader classLoader) throws IllegalArgumentException {
		try {
			return ForName(className, classLoader);
		}
		catch (ClassNotFoundException ex) {
			throw new IllegalArgumentException("Cannot find class [" + className + "]", ex);
		}
		catch (LinkageError ex) {
			throw new IllegalArgumentException(
					"Error loading class [" + className + "]: problem with class file or dependent class.", ex);
		}
	}

	/**
	 * 解析原始类名
	 * Resolve the given class name as primitive class, if appropriate,
	 * according to the JVM's naming rules for primitive classes.
	 * <p>Also supports the JVM's internal class names for primitive arrays.
	 * Does <i>not</i> support the "[]" suffix notation for primitive arrays;
	 * this is only supported by {@link #forName(String, ClassLoader)}.
	 * @param name the name of the potentially primitive class
	 * @return the primitive class, or {@code null} if the name does not denote
	 * a primitive class or primitive array class
	 */
	public static Class<?> ResolvePrimitiveClassName(String name) {
		Class<?> result = null;
		// Most class names will be quite long, considering that they
		// SHOULD sit in a package, so a length check is worthwhile.
		if (name != null && name.length() <= 8) {
			// Could be a primitive - likely.
			result = primitiveTypeNameMap.get(name);
		}
		return result;
	}

	/**
	 * 判断父类？
	 * Determine whether the {@link Class} identified by the supplied name is present
	 * and can be loaded. Will return {@code false} if either the class or
	 * one of its dependencies is not present or cannot be loaded.
	 * @param className the name of the class to check
	 * @param classLoader the class loader to use
	 * (may be {@code null}, which indicates the default class loader)
	 * @return whether the specified class is present
	 */
	public static boolean IsPresent(String className, ClassLoader classLoader) {
		try {
			ForName(className, classLoader);
			return true;
		}
		catch (Throwable ex) {
			// Class or one of its dependencies is not present...
			return false;
		}
	}

	/**
	 * 获取自定义类
	 * Return the user-defined class for the given instance: usually simply
	 * the class of the given instance, but the original class in case of a
	 * CGLIB-generated subclass.
	 * @param instance the instance to check
	 * @return the user-defined class
	 */
	public static Class<?> GetUserClass(Object instance) {
		Assert.NotNull(instance, "Instance must not be null");
		return GetUserClass(instance.getClass());
	}

	/**
	 * 获取自定义类
	 * Return the user-defined class for the given class: usually simply the given
	 * class, but the original class in case of a CGLIB-generated subclass.
	 * @param clazz the class to check
	 * @return the user-defined class
	 */
	public static Class<?> GetUserClass(Class<?> clazz) {
		if (clazz != null && clazz.getName().contains(CGLIB_CLASS_SEPARATOR)) {
			Class<?> superclass = clazz.getSuperclass();
			if (superclass != null && Object.class != superclass) {
				return superclass;
			}
		}
		return clazz;
	}

	/**
	 * 是否缓存安全？
	 * Check whether the given class is cache-safe in the given context,
	 * i.e. whether it is loaded by the given ClassLoader or a parent of it.
	 * @param clazz the class to analyze
	 * @param classLoader the ClassLoader to potentially cache metadata in
	 */
	public static boolean IsCacheSafe(Class<?> clazz, ClassLoader classLoader) {
		Assert.NotNull(clazz, "Class must not be null");
		try {
			ClassLoader target = clazz.getClassLoader();
			if (target == null) {
				return true;
			}
			ClassLoader cur = classLoader;
			if (cur == target) {
				return true;
			}
			while (cur != null) {
				cur = cur.getParent();
				if (cur == target) {
					return true;
				}
			}
			return false;
		}
		catch (SecurityException ex) {
			// Probably from the system ClassLoader - let's consider it safe.
			return true;
		}
	}


	/**
	 * 获取简短名称
	 * Get the class name without the qualified package name.
	 * @param className the className to get the short name for
	 * @return the class name of the class without the package name
	 * @throws IllegalArgumentException if the className is empty
	 */
	public static String GetShortName(String className) {
		Assert.HasLength(className, "Class name must not be empty");
		int lastDotIndex = className.lastIndexOf(PACKAGE_SEPARATOR);
		int nameEndIndex = className.indexOf(CGLIB_CLASS_SEPARATOR);
		if (nameEndIndex == -1) {
			nameEndIndex = className.length();
		}
		String shortName = className.substring(lastDotIndex + 1, nameEndIndex);
		shortName = shortName.replace(INNER_CLASS_SEPARATOR, PACKAGE_SEPARATOR);
		return shortName;
	}

	/**
	 * 获取简短名称
	 * Get the class name without the qualified package name.
	 * @param clazz the class to get the short name for
	 * @return the class name of the class without the package name
	 */
	public static String GetShortName(Class<?> clazz) {
		return GetShortName(GetQualifiedName(clazz));
	}

	/**
	 * 获取简短名称
	 * Return the short string name of a Java class in uncapitalized JavaBeans
	 * property format. Strips the outer class name in case of an inner class.
	 * @param clazz the class
	 * @return the short name rendered in a standard JavaBeans property format
	 * @see Introspector#decapitalize(String)
	 */
	public static String GetShortNameAsProperty(Class<?> clazz) {
		String shortName = ClassUtil.GetShortName(clazz);
		int dotIndex = shortName.lastIndexOf(PACKAGE_SEPARATOR);
		shortName = (dotIndex != -1 ? shortName.substring(dotIndex + 1) : shortName);
		return Introspector.decapitalize(shortName);
	}

	/**
	 * 获取类文件名
	 * Determine the name of the class file, relative to the containing
	 * package: e.g. "String.class"
	 * @param clazz the class
	 * @return the file name of the ".class" file
	 */
	public static String GetClassFileName(Class<?> clazz) {
		Assert.NotNull(clazz, "Class must not be null");
		String className = clazz.getName();
		int lastDotIndex = className.lastIndexOf(PACKAGE_SEPARATOR);
		return className.substring(lastDotIndex + 1) + CLASS_FILE_SUFFIX;
	}

	/**
	 * 获取类包名
	 * Determine the name of the package of the given class,
	 * e.g. "java.lang" for the {@code java.lang.String} class.
	 * @param clazz the class
	 * @return the package name, or the empty String if the class
	 * is defined in the default package
	 */
	public static String GetPackageName(Class<?> clazz) {
		Assert.NotNull(clazz, "Class must not be null");
		return GetPackageName(clazz.getName());
	}

	/**
	 * 获取类包名
	 * Determine the name of the package of the given fully-qualified class name,
	 * e.g. "java.lang" for the {@code java.lang.String} class name.
	 * @param fqClassName the fully-qualified class name
	 * @return the package name, or the empty String if the class
	 * is defined in the default package
	 */
	public static String GetPackageName(String fqClassName) {
		Assert.NotNull(fqClassName, "Class name must not be null");
		int lastDotIndex = fqClassName.lastIndexOf(PACKAGE_SEPARATOR);
		return (lastDotIndex != -1 ? fqClassName.substring(0, lastDotIndex) : "");
	}

	/**
	 * 获取合格的类名
	 * Return the qualified name of the given class: usually simply
	 * the class name, but component type class name + "[]" for arrays.
	 * @param clazz the class
	 * @return the qualified name of the class
	 */
	public static String GetQualifiedName(Class<?> clazz) {
		Assert.NotNull(clazz, "Class must not be null");
		if (clazz.isArray()) {
			return GetQualifiedNameForArray(clazz);
		}
		else {
			return clazz.getName();
		}
	}

	/**
	 * 获取合格的类名
	 * Build a nice qualified name for an array:
	 * component type class name + "[]".
	 * @param clazz the array class
	 * @return a qualified name for the array class
	 */
	private static String GetQualifiedNameForArray(Class<?> clazz) {
		StringBuilder result = new StringBuilder();
		while (clazz.isArray()) {
			clazz = clazz.getComponentType();
			result.append(ClassUtil.ARRAY_SUFFIX);
		}
		result.insert(0, clazz.getName());
		return result.toString();
	}

	/**
	 * 获取合格的类方法名
	 * Return the qualified name of the given method, consisting of
	 * fully qualified interface/class name + "." + method name.
	 * @param method the method
	 * @return the qualified name of the method
	 */
	public static String GetQualifiedMethodName(Method method) {
		Assert.NotNull(method, "Method must not be null");
		return method.getDeclaringClass().getName() + "." + method.getName();
	}

	/**
	 * 获取描述性类型
	 * Return a descriptive name for the given object's type: usually simply
	 * the class name, but component type class name + "[]" for arrays,
	 * and an appended list of implemented interfaces for JDK proxies.
	 * @param value the value to introspect
	 * @return the qualified name of the class
	 */
	public static String GetDescriptiveType(Object value) {
		if (value == null) {
			return null;
		}
		Class<?> clazz = value.getClass();
		if (Proxy.isProxyClass(clazz)) {
			StringBuilder result = new StringBuilder(clazz.getName());
			result.append(" implementing ");
			Class<?>[] ifcs = clazz.getInterfaces();
			for (int i = 0; i < ifcs.length; i++) {
				result.append(ifcs[i].getName());
				if (i < ifcs.length - 1) {
					result.append(',');
				}
			}
			return result.toString();
		}
		else if (clazz.isArray()) {
			return GetQualifiedNameForArray(clazz);
		}
		else {
			return clazz.getName();
		}
	}

	/**
	 * 匹配类型名称
	 * Check whether the given class matches the user-specified type name.
	 * @param clazz the class to check
	 * @param typeName the type name to match
	 */
	public static boolean MatchesTypeName(Class<?> clazz, String typeName) {
		return (typeName != null &&
				(typeName.equals(clazz.getName()) || typeName.equals(clazz.getSimpleName()) ||
				(clazz.isArray() && typeName.equals(GetQualifiedNameForArray(clazz)))));
	}


	/**
	 * 判断有无构造函数？
	 * Determine whether the given class has a public constructor with the given signature.
	 * <p>Essentially translates {@code NoSuchMethodException} to "false".
	 * @param clazz the clazz to analyze
	 * @param paramTypes the parameter types of the method
	 * @return whether the class has a corresponding constructor
	 * @see Class#getMethod
	 */
	public static boolean HasConstructor(Class<?> clazz, Class<?>... paramTypes) {
		return (GetConstructorIfAvailable(clazz, paramTypes) != null);
	}

	/**
	 * 获取构造
	 * Determine whether the given class has a public constructor with the given signature,
	 * and return it if available (else return {@code null}).
	 * <p>Essentially translates {@code NoSuchMethodException} to {@code null}.
	 * @param clazz the clazz to analyze
	 * @param paramTypes the parameter types of the method
	 * @return the constructor, or {@code null} if not found
	 * @see Class#getConstructor
	 */
	public static <T> Constructor<T> GetConstructorIfAvailable(Class<T> clazz, Class<?>... paramTypes) {
		Assert.NotNull(clazz, "Class must not be null");
		try {
			return clazz.getConstructor(paramTypes);
		}
		catch (NoSuchMethodException ex) {
			return null;
		}
	}

	/**
	 * 是否存在方法？
	 * Determine whether the given class has a public method with the given signature.
	 * <p>Essentially translates {@code NoSuchMethodException} to "false".
	 * @param clazz the clazz to analyze
	 * @param methodName the name of the method
	 * @param paramTypes the parameter types of the method
	 * @return whether the class has a corresponding method
	 * @see Class#getMethod
	 */
	public static boolean HasMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) {
		return (GetMethodIfAvailable(clazz, methodName, paramTypes) != null);
	}

	/**
	 * 获取方法
	 * Determine whether the given class has a public method with the given signature,
	 * and return it if available (else throws an {@code IllegalStateException}).
	 * <p>In case of any signature specified, only returns the method if there is a
	 * unique candidate, i.e. a single public method with the specified name.
	 * <p>Essentially translates {@code NoSuchMethodException} to {@code IllegalStateException}.
	 * @param clazz the clazz to analyze
	 * @param methodName the name of the method
	 * @param paramTypes the parameter types of the method
	 * (may be {@code null} to indicate any signature)
	 * @return the method (never {@code null})
	 * @throws IllegalStateException if the method has not been found
	 * @see Class#getMethod
	 */
	public static Method GetMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) {
		Assert.NotNull(clazz, "Class must not be null");
		Assert.NotNull(methodName, "Method name must not be null");
		if (paramTypes != null) {
			try {
				return clazz.getMethod(methodName, paramTypes);
			}
			catch (NoSuchMethodException ex) {
				throw new IllegalStateException("Expected method not found: " + ex);
			}
		}
		else {
			Set<Method> candidates = new HashSet<Method>(1);
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				if (methodName.equals(method.getName())) {
					candidates.add(method);
				}
			}
			if (candidates.size() == 1) {
				return candidates.iterator().next();
			}
			else if (candidates.isEmpty()) {
				throw new IllegalStateException("Expected method not found: " + clazz + "." + methodName);
			}
			else {
				throw new IllegalStateException("No unique method found: " + clazz + "." + methodName);
			}
		}
	}

	/**
	 * 获取方法
	 * Determine whether the given class has a public method with the given signature,
	 * and return it if available (else return {@code null}).
	 * <p>In case of any signature specified, only returns the method if there is a
	 * unique candidate, i.e. a single public method with the specified name.
	 * <p>Essentially translates {@code NoSuchMethodException} to {@code null}.
	 * @param clazz the clazz to analyze
	 * @param methodName the name of the method
	 * @param paramTypes the parameter types of the method
	 * (may be {@code null} to indicate any signature)
	 * @return the method, or {@code null} if not found
	 * @see Class#getMethod
	 */
	public static Method GetMethodIfAvailable(Class<?> clazz, String methodName, Class<?>... paramTypes) {
		Assert.NotNull(clazz, "Class must not be null");
		Assert.NotNull(methodName, "Method name must not be null");
		if (paramTypes != null) {
			try {
				return clazz.getMethod(methodName, paramTypes);
			}
			catch (NoSuchMethodException ex) {
				return null;
			}
		}
		else {
			Set<Method> candidates = new HashSet<Method>(1);
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				if (methodName.equals(method.getName())) {
					candidates.add(method);
				}
			}
			if (candidates.size() == 1) {
				return candidates.iterator().next();
			}
			return null;
		}
	}

	/**
	 * 获取方法数目
	 * Return the number of methods with a given name (with any argument types),
	 * for the given class and/or its superclasses. Includes non-public methods.
	 * @param clazz	the clazz to check
	 * @param methodName the name of the method
	 * @return the number of methods with the given name
	 */
	public static int GetMethodCountForName(Class<?> clazz, String methodName) {
		Assert.NotNull(clazz, "Class must not be null");
		Assert.NotNull(methodName, "Method name must not be null");
		int count = 0;
		Method[] declaredMethods = clazz.getDeclaredMethods();
		for (Method method : declaredMethods) {
			if (methodName.equals(method.getName())) {
				count++;
			}
		}
		Class<?>[] ifcs = clazz.getInterfaces();
		for (Class<?> ifc : ifcs) {
			count += GetMethodCountForName(ifc, methodName);
		}
		if (clazz.getSuperclass() != null) {
			count += GetMethodCountForName(clazz.getSuperclass(), methodName);
		}
		return count;
	}

	/**
	 * 判断是否至少存在一个此名称的方法？
	 * Does the given class or one of its superclasses at least have one or more
	 * methods with the supplied name (with any argument types)?
	 * Includes non-public methods.
	 * @param clazz	the clazz to check
	 * @param methodName the name of the method
	 * @return whether there is at least one method with the given name
	 */
	public static boolean HasAtLeastOneMethodWithName(Class<?> clazz, String methodName) {
		Assert.NotNull(clazz, "Class must not be null");
		Assert.NotNull(methodName, "Method name must not be null");
		Method[] declaredMethods = clazz.getDeclaredMethods();
		for (Method method : declaredMethods) {
			if (method.getName().equals(methodName)) {
				return true;
			}
		}
		Class<?>[] ifcs = clazz.getInterfaces();
		for (Class<?> ifc : ifcs) {
			if (HasAtLeastOneMethodWithName(ifc, methodName)) {
				return true;
			}
		}
		return (clazz.getSuperclass() != null && HasAtLeastOneMethodWithName(clazz.getSuperclass(), methodName));
	}

	/**
	 * 获取最明确的方法
	 * Given a method, which may come from an interface, and a target class used
	 * in the current reflective invocation, find the corresponding target method
	 * if there is one. E.g. the method may be {@code IFoo.bar()} and the
	 * target class may be {@code DefaultFoo}. In this case, the method may be
	 * {@code DefaultFoo.bar()}. This enables attributes on that method to be found.
	 * <p><b>NOTE:</b> In contrast to {@link org.springframework.aop.support.AopUtils#getMostSpecificMethod},
	 * this method does <i>not</i> resolve Java 5 bridge methods automatically.
	 * Call {@link org.springframework.core.BridgeMethodResolver#findBridgedMethod}
	 * if bridge method resolution is desirable (e.g. for obtaining metadata from
	 * the original method definition).
	 * <p><b>NOTE:</b> Since Spring 3.1.1, if Java security settings disallow reflective
	 * access (e.g. calls to {@code Class#getDeclaredMethods} etc, this implementation
	 * will fall back to returning the originally provided method.
	 * @param method the method to be invoked, which may come from an interface
	 * @param targetClass the target class for the current invocation.
	 * May be {@code null} or may not even implement the method.
	 * @return the specific target method, or the original method if the
	 * {@code targetClass} doesn't implement it or is {@code null}
	 */
	public static Method GetMostSpecificMethod(Method method, Class<?> targetClass) {
		if (method != null && IsOverridable(method, targetClass) &&
				targetClass != null && targetClass != method.getDeclaringClass()) {
			try {
				if (Modifier.isPublic(method.getModifiers())) {
					try {
						return targetClass.getMethod(method.getName(), method.getParameterTypes());
					}
					catch (NoSuchMethodException ex) {
						return method;
					}
				}
				else {
					Method specificMethod =
							ReflectionUtil.FindMethod(targetClass, method.getName(), method.getParameterTypes());
					return (specificMethod != null ? specificMethod : method);
				}
			}
			catch (SecurityException ex) {
				// Security settings are disallowing reflective access; fall back to 'method' below.
			}
		}
		return method;
	}

	/**
	 * 是否自定义方法？
	 * Determine whether the given method is declared by the user or at least pointing to
	 * a user-declared method.
	 * <p>Checks {@link Method#isSynthetic()} (for implementation methods) as well as the
	 * {@code GroovyObject} interface (for interface methods; on an implementation class,
	 * implementations of the {@code GroovyObject} methods will be marked as synthetic anyway).
	 * Note that, despite being synthetic, bridge methods ({@link Method#isBridge()}) are considered
	 * as user-level methods since they are eventually pointing to a user-declared generic method.
	 * @param method the method to check
	 * @return {@code true} if the method can be considered as user-declared; [@code false} otherwise
	 */
	public static boolean IsUserLevelMethod(Method method) {
		Assert.NotNull(method, "Method must not be null");
		return (method.isBridge() || (!method.isSynthetic() && !IsGroovyObjectMethod(method)));
	}

	private static boolean IsGroovyObjectMethod(Method method) {
		return method.getDeclaringClass().getName().equals("groovy.lang.GroovyObject");
	}

	/**
	 * Determine whether the given method is overridable in the given target class.
	 * @param method the method to check
	 * @param targetClass the target class to check against
	 */
	private static boolean IsOverridable(Method method, Class<?> targetClass) {
		if (Modifier.isPrivate(method.getModifiers())) {
			return false;
		}
		if (Modifier.isPublic(method.getModifiers()) || Modifier.isProtected(method.getModifiers())) {
			return true;
		}
		return GetPackageName(method.getDeclaringClass()).equals(GetPackageName(targetClass));
	}

	/**
	 * 获取静态方法
	 * Return a public static method of a class.
	 * @param clazz the class which defines the method
	 * @param methodName the static method name
	 * @param args the parameter types to the method
	 * @return the static method, or {@code null} if no static method was found
	 * @throws IllegalArgumentException if the method name is blank or the clazz is null
	 */
	public static Method GetStaticMethod(Class<?> clazz, String methodName, Class<?>... args) {
		Assert.NotNull(clazz, "Class must not be null");
		Assert.NotNull(methodName, "Method name must not be null");
		try {
			Method method = clazz.getMethod(methodName, args);
			return Modifier.isStatic(method.getModifiers()) ? method : null;
		}
		catch (NoSuchMethodException ex) {
			return null;
		}
	}


	/**
	 * 是否原生包装？
	 * Check if the given class represents a primitive wrapper,
	 * i.e. Boolean, Byte, Character, Short, Integer, Long, Float, or Double.
	 * @param clazz the class to check
	 * @return whether the given class is a primitive wrapper class
	 */
	public static boolean IsPrimitiveWrapper(Class<?> clazz) {
		Assert.NotNull(clazz, "Class must not be null");
		return primitiveWrapperTypeMap.containsKey(clazz);
	}

	/**
	 * 是否原生的或者包装的？
	 * Check if the given class represents a primitive (i.e. boolean, byte,
	 * char, short, int, long, float, or double) or a primitive wrapper
	 * (i.e. Boolean, Byte, Character, Short, Integer, Long, Float, or Double).
	 * @param clazz the class to check
	 * @return whether the given class is a primitive or primitive wrapper class
	 */
	public static boolean IsPrimitiveOrWrapper(Class<?> clazz) {
		Assert.NotNull(clazz, "Class must not be null");
		return (clazz.isPrimitive() || IsPrimitiveWrapper(clazz));
	}

	/**
	 * 是否原生数组？
	 * Check if the given class represents an array of primitives,
	 * i.e. boolean, byte, char, short, int, long, float, or double.
	 * @param clazz the class to check
	 * @return whether the given class is a primitive array class
	 */
	public static boolean IsPrimitiveArray(Class<?> clazz) {
		Assert.NotNull(clazz, "Class must not be null");
		return (clazz.isArray() && clazz.getComponentType().isPrimitive());
	}

	/**
	 * 是否原生包装数组？
	 * Check if the given class represents an array of primitive wrappers,
	 * i.e. Boolean, Byte, Character, Short, Integer, Long, Float, or Double.
	 * @param clazz the class to check
	 * @return whether the given class is a primitive wrapper array class
	 */
	public static boolean IsPrimitiveWrapperArray(Class<?> clazz) {
		Assert.NotNull(clazz, "Class must not be null");
		return (clazz.isArray() && IsPrimitiveWrapper(clazz.getComponentType()));
	}

	/**
	 * 解析原生
	 * Resolve the given class if it is a primitive class,
	 * returning the corresponding primitive wrapper type instead.
	 * @param clazz the class to check
	 * @return the original class, or a primitive wrapper for the original primitive type
	 */
	public static Class<?> ResolvePrimitiveIfNecessary(Class<?> clazz) {
		Assert.NotNull(clazz, "Class must not be null");
		return (clazz.isPrimitive() && clazz != void.class ? primitiveTypeToWrapperMap.get(clazz) : clazz);
	}

	/**
	 * 可否赋值？
	 * Check if the right-hand side type may be assigned to the left-hand side
	 * type, assuming setting by reflection. Considers primitive wrapper
	 * classes as assignable to the corresponding primitive types.
	 * @param lhsType the target type
	 * @param rhsType the value type that should be assigned to the target type
	 * @return if the target type is assignable from the value type
	 * @see TypeUtils#isAssignable
	 */
	public static boolean IsAssignable(Class<?> lhsType, Class<?> rhsType) {
		Assert.NotNull(lhsType, "Left-hand side type must not be null");
		Assert.NotNull(rhsType, "Right-hand side type must not be null");
		if (lhsType.isAssignableFrom(rhsType)) {
			return true;
		}
		if (lhsType.isPrimitive()) {
			Class<?> resolvedPrimitive = primitiveWrapperTypeMap.get(rhsType);
			if (lhsType == resolvedPrimitive) {
				return true;
			}
		}
		else {
			Class<?> resolvedWrapper = primitiveTypeToWrapperMap.get(rhsType);
			if (resolvedWrapper != null && lhsType.isAssignableFrom(resolvedWrapper)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 可否赋值？
	 * Determine if the given type is assignable from the given value,
	 * assuming setting by reflection. Considers primitive wrapper classes
	 * as assignable to the corresponding primitive types.
	 * @param type the target type
	 * @param value the value that should be assigned to the type
	 * @return if the type is assignable from the value
	 */
	public static boolean IsAssignableValue(Class<?> type, Object value) {
		Assert.NotNull(type, "Type must not be null");
		return (value != null ? IsAssignable(type, value.getClass()) : !type.isPrimitive());
	}


	/**
	 * 转换资源路径为类名称
	 * Convert a "/"-based resource path to a "."-based fully qualified class name.
	 * @param resourcePath the resource path pointing to a class
	 * @return the corresponding fully qualified class name
	 */
	public static String ConvertResourcePathToClassName(String resourcePath) {
		Assert.NotNull(resourcePath, "Resource path must not be null");
		return resourcePath.replace(PATH_SEPARATOR, PACKAGE_SEPARATOR);
	}

	/**
	 * 转换类名称为资源路径
	 * Convert a "."-based fully qualified class name to a "/"-based resource path.
	 * @param className the fully qualified class name
	 * @return the corresponding resource path, pointing to the class
	 */
	public static String ConvertClassNameToResourcePath(String className) {
		Assert.NotNull(className, "Class name must not be null");
		return className.replace(PACKAGE_SEPARATOR, PATH_SEPARATOR);
	}

	/**
	 * 增加资源路径到包路径
	 * Return a path suitable for use with {@code ClassLoader.getResource}
	 * (also suitable for use with {@code Class.getResource} by prepending a
	 * slash ('/') to the return value). Built by taking the package of the specified
	 * class file, converting all dots ('.') to slashes ('/'), adding a trailing slash
	 * if necessary, and concatenating the specified resource name to this.
	 * <br/>As such, this function may be used to build a path suitable for
	 * loading a resource file that is in the same package as a class file,
	 * although {@link org.springframework.core.io.ClassPathResource} is usually
	 * even more convenient.
	 * @param clazz the Class whose package will be used as the base
	 * @param resourceName the resource name to append. A leading slash is optional.
	 * @return the built-up resource path
	 * @see ClassLoader#getResource
	 * @see Class#getResource
	 */
	public static String AddResourcePathToPackagePath(Class<?> clazz, String resourceName) {
		Assert.NotNull(resourceName, "Resource name must not be null");
		if (!resourceName.startsWith("/")) {
			return ClassPackageAsResourcePath(clazz) + "/" + resourceName;
		}
		return ClassPackageAsResourcePath(clazz) + resourceName;
	}

	/**
	 * 转换包类为资源路径
	 * Given an input class object, return a string which consists of the
	 * class's package name as a pathname, i.e., all dots ('.') are replaced by
	 * slashes ('/'). Neither a leading nor trailing slash is added. The result
	 * could be concatenated with a slash and the name of a resource and fed
	 * directly to {@code ClassLoader.getResource()}. For it to be fed to
	 * {@code Class.getResource} instead, a leading slash would also have
	 * to be prepended to the returned value.
	 * @param clazz the input class. A {@code null} value or the default
	 * (empty) package will result in an empty string ("") being returned.
	 * @return a path which represents the package name
	 * @see ClassLoader#getResource
	 * @see Class#getResource
	 */
	public static String ClassPackageAsResourcePath(Class<?> clazz) {
		if (clazz == null) {
			return "";
		}
		String className = clazz.getName();
		int packageEndIndex = className.lastIndexOf(PACKAGE_SEPARATOR);
		if (packageEndIndex == -1) {
			return "";
		}
		String packageName = className.substring(0, packageEndIndex);
		return packageName.replace(PACKAGE_SEPARATOR, PATH_SEPARATOR);
	}

	/**
	 * 类转名称字符串
	 * Build a String that consists of the names of the classes/interfaces
	 * in the given array.
	 * <p>Basically like {@code AbstractCollection.toString()}, but stripping
	 * the "class "/"interface " prefix before every class name.
	 * @param classes a Collection of Class objects (may be {@code null})
	 * @return a String of form "[com.foo.Bar, com.foo.Baz]"
	 * @see java.util.AbstractCollection#toString()
	 */
	public static String ClassNamesToString(Class<?>... classes) {
		return ClassNamesToString(Arrays.asList(classes));
	}

	/**
	 * 类转名称字符串
	 * Build a String that consists of the names of the classes/interfaces
	 * in the given collection.
	 * <p>Basically like {@code AbstractCollection.toString()}, but stripping
	 * the "class "/"interface " prefix before every class name.
	 * @param classes a Collection of Class objects (may be {@code null})
	 * @return a String of form "[com.foo.Bar, com.foo.Baz]"
	 * @see java.util.AbstractCollection#toString()
	 */
	public static String ClassNamesToString(Collection<Class<?>> classes) {
		if (CollectionUtil.IsEmpty(classes)) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder("[");
		for (Iterator<Class<?>> it = classes.iterator(); it.hasNext(); ) {
			Class<?> clazz = it.next();
			sb.append(clazz.getName());
			if (it.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * 转换类数组
	 * Copy the given Collection into a Class array.
	 * The Collection must contain Class elements only.
	 * @param collection the Collection to copy
	 * @return the Class array ({@code null} if the passed-in
	 * Collection was {@code null})
	 */
	public static Class<?>[] ToClassArray(Collection<Class<?>> collection) {
		if (collection == null) {
			return null;
		}
		return collection.toArray(new Class<?>[collection.size()]);
	}

	/**
	 * 获取所有接口
	 * Return all interfaces that the given instance implements as array,
	 * including ones implemented by superclasses.
	 * @param instance the instance to analyze for interfaces
	 * @return all interfaces that the given instance implements as array
	 */
	public static Class<?>[] GetAllInterfaces(Object instance) {
		Assert.NotNull(instance, "Instance must not be null");
		return GetAllInterfacesForClass(instance.getClass());
	}

	/**
	 * 获取所有接口
	 * Return all interfaces that the given class implements as array,
	 * including ones implemented by superclasses.
	 * <p>If the class itself is an interface, it gets returned as sole interface.
	 * @param clazz the class to analyze for interfaces
	 * @return all interfaces that the given object implements as array
	 */
	public static Class<?>[] GetAllInterfacesForClass(Class<?> clazz) {
		return GetAllInterfacesForClass(clazz, null);
	}

	/**
	 * 获取所有接口
	 * Return all interfaces that the given class implements as array,
	 * including ones implemented by superclasses.
	 * <p>If the class itself is an interface, it gets returned as sole interface.
	 * @param clazz the class to analyze for interfaces
	 * @param classLoader the ClassLoader that the interfaces need to be visible in
	 * (may be {@code null} when accepting all declared interfaces)
	 * @return all interfaces that the given object implements as array
	 */
	public static Class<?>[] GetAllInterfacesForClass(Class<?> clazz, ClassLoader classLoader) {
		Set<Class<?>> ifcs = GetAllInterfacesForClassAsSet(clazz, classLoader);
		return ifcs.toArray(new Class<?>[ifcs.size()]);
	}

	/**
	 * 获取所有接口
	 * Return all interfaces that the given instance implements as Set,
	 * including ones implemented by superclasses.
	 * @param instance the instance to analyze for interfaces
	 * @return all interfaces that the given instance implements as Set
	 */
	public static Set<Class<?>> GetAllInterfacesAsSet(Object instance) {
		Assert.NotNull(instance, "Instance must not be null");
		return GetAllInterfacesForClassAsSet(instance.getClass());
	}

	/**
	 * 获取所有接口
	 * Return all interfaces that the given class implements as Set,
	 * including ones implemented by superclasses.
	 * <p>If the class itself is an interface, it gets returned as sole interface.
	 * @param clazz the class to analyze for interfaces
	 * @return all interfaces that the given object implements as Set
	 */
	public static Set<Class<?>> GetAllInterfacesForClassAsSet(Class<?> clazz) {
		return GetAllInterfacesForClassAsSet(clazz, null);
	}

	/**
	 * 获取所有接口
	 * Return all interfaces that the given class implements as Set,
	 * including ones implemented by superclasses.
	 * <p>If the class itself is an interface, it gets returned as sole interface.
	 * @param clazz the class to analyze for interfaces
	 * @param classLoader the ClassLoader that the interfaces need to be visible in
	 * (may be {@code null} when accepting all declared interfaces)
	 * @return all interfaces that the given object implements as Set
	 */
	public static Set<Class<?>> GetAllInterfacesForClassAsSet(Class<?> clazz, ClassLoader classLoader) {
		Assert.NotNull(clazz, "Class must not be null");
		if (clazz.isInterface() && IsVisible(clazz, classLoader)) {
			return Collections.<Class<?>>singleton(clazz);
		}
		Set<Class<?>> interfaces = new LinkedHashSet<Class<?>>();
		while (clazz != null) {
			Class<?>[] ifcs = clazz.getInterfaces();
			for (Class<?> ifc : ifcs) {
				interfaces.addAll(GetAllInterfacesForClassAsSet(ifc, classLoader));
			}
			clazz = clazz.getSuperclass();
		}
		return interfaces;
	}

	/**
	 * 创建接口
	 * Create a composite interface Class for the given interfaces,
	 * implementing the given interfaces in one single Class.
	 * <p>This implementation builds a JDK proxy class for the given interfaces.
	 * @param interfaces the interfaces to merge
	 * @param classLoader the ClassLoader to create the composite Class in
	 * @return the merged interface as Class
	 * @see Proxy#getProxyClass
	 */
	public static Class<?> CreateCompositeInterface(Class<?>[] interfaces, ClassLoader classLoader) {
		Assert.NotEmpty(interfaces, "Interfaces must not be empty");
		Assert.NotNull(classLoader, "ClassLoader must not be null");
		return Proxy.getProxyClass(classLoader, interfaces);
	}

	/**
	 * 获取共同基类
	 * Determine the common ancestor of the given classes, if any.
	 * @param clazz1 the class to introspect
	 * @param clazz2 the other class to introspect
	 * @return the common ancestor (i.e. common superclass, one interface
	 * extending the other), or {@code null} if none found. If any of the
	 * given classes is {@code null}, the other class will be returned.
	 * @since 3.2.6
	 */
	public static Class<?> DetermineCommonAncestor(Class<?> clazz1, Class<?> clazz2) {
		if (clazz1 == null) {
			return clazz2;
		}
		if (clazz2 == null) {
			return clazz1;
		}
		if (clazz1.isAssignableFrom(clazz2)) {
			return clazz1;
		}
		if (clazz2.isAssignableFrom(clazz1)) {
			return clazz2;
		}
		Class<?> ancestor = clazz1;
		do {
			ancestor = ancestor.getSuperclass();
			if (ancestor == null || Object.class == ancestor) {
				return null;
			}
		}
		while (!ancestor.isAssignableFrom(clazz2));
		return ancestor;
	}

	/**
	 * 是否可见？
	 * Check whether the given class is visible in the given ClassLoader.
	 * @param clazz the class to check (typically an interface)
	 * @param classLoader the ClassLoader to check against (may be {@code null},
	 * in which case this method will always return {@code true})
	 */
	public static boolean IsVisible(Class<?> clazz, ClassLoader classLoader) {
		if (classLoader == null) {
			return true;
		}
		try {
			Class<?> actualClass = classLoader.loadClass(clazz.getName());
			return (clazz == actualClass);
			// Else: different interface class found...
		}
		catch (ClassNotFoundException ex) {
			// No interface class found...
			return false;
		}
	}

	/**
	 * 是否CGLIB proxy？
	 * Check whether the given object is a CGLIB proxy.
	 * @param object the object to check
	 * @see org.springframework.aop.support.AopUtils#isCglibProxy(Object)
	 */
	public static boolean IsCglibProxy(Object object) {
		return ClassUtil.IsCglibProxyClass(object.getClass());
	}

	/**
	 * 是否CGLIB proxy类？
	 * Check whether the specified class is a CGLIB-generated class.
	 * @param clazz the class to check
	 */
	public static boolean IsCglibProxyClass(Class<?> clazz) {
		return (clazz != null && IsCglibProxyClassName(clazz.getName()));
	}

	/**
	 * 是否CGLIB proxy类名称？
	 * Check whether the specified class name is a CGLIB-generated class.
	 * @param className the class name to check
	 */
	public static boolean IsCglibProxyClassName(String className) {
		return (className != null && className.contains(CGLIB_CLASS_SEPARATOR));
	}
	
	/**
	 * 获取对象类名称
	 * @param object
	 * @return
	 */
	public static String ClassName(Object object) {
		return null==object ? "NULL" : object.getClass().getName();
	}
}
