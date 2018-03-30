package com.septinary.common.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.UndeclaredThrowableException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 反射操作
 * @Filename: com.septinary.common.util.ReflectionUtil.java of the project [com.septinary.common]
 *     @Type: ReflectionUtil
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月13日下午12:31:13
 *
 */
public abstract class ReflectionUtil {

	/**
	 * Naming prefix for CGLIB-renamed methods.
	 * @see #isCglibRenamedMethod
	 */
	private static final String CGLIB_RENAMED_METHOD_PREFIX = "CGLIB$";

	private static final Method[] NO_METHODS = {};

	private static final Field[] NO_FIELDS = {};


	/**
	 * Cache for {@link Class#getDeclaredMethods()} plus equivalent default methods
	 * from Java 8 based interfaces, allowing for fast iteration.
	 */
	private static final Map<Class<?>, Method[]> declaredMethodsCache = new ConcurrentReferenceHashMap<Class<?>, Method[]>(256);

	/**
	 * Cache for {@link Class#getDeclaredFields()}, allowing for fast iteration.
	 */
	private static final Map<Class<?>, Field[]> declaredFieldsCache = new ConcurrentReferenceHashMap<Class<?>, Field[]>(256);


	/**
	 * 查找字段
	 * Attempt to find a {@link Field field} on the supplied {@link Class} with the
	 * supplied {@code name}. Searches all superclasses up to {@link Object}.
	 * @param clazz the class to introspect
	 * @param name the name of the field
	 * @return the corresponding Field object, or {@code null} if not found
	 */
	public static Field FindField(Class<?> clazz, String name) {
		return FindField(clazz, name, null);
	}

	/**
	 * 查找字段
	 * Attempt to find a {@link Field field} on the supplied {@link Class} with the
	 * supplied {@code name} and/or {@link Class type}. Searches all superclasses
	 * up to {@link Object}.
	 * @param clazz the class to introspect
	 * @param name the name of the field (may be {@code null} if type is specified)
	 * @param type the type of the field (may be {@code null} if name is specified)
	 * @return the corresponding Field object, or {@code null} if not found
	 */
	public static Field FindField(Class<?> clazz, String name, Class<?> type) {
		Assert.NotNull(clazz, "Class must not be null");
		Assert.IsTrue(name != null || type != null, "Either name or type of the field must be specified");
		Class<?> searchType = clazz;
		while (Object.class != searchType && searchType != null) {
			Field[] fields = GetDeclaredFields(searchType);
			for (Field field : fields) {
				if ((name == null || name.equals(field.getName())) &&
						(type == null || type.equals(field.getType()))) {
					return field;
				}
			}
			searchType = searchType.getSuperclass();
		}
		return null;
	}

	/**
	 * 设置字段
	 * Set the field represented by the supplied {@link Field field object} on the
	 * specified {@link Object target object} to the specified {@code value}.
	 * In accordance with {@link Field#set(Object, Object)} semantics, the new value
	 * is automatically unwrapped if the underlying field has a primitive type.
	 * <p>Thrown exceptions are handled via a call to {@link #handleReflectionException(Exception)}.
	 * @param field the field to set
	 * @param target the target object on which to set the field
	 * @param value the value to set (may be {@code null})
	 */
	public static void SetField(Field field, Object target, Object value) {
		try {
			field.set(target, value);
		}
		catch (IllegalAccessException ex) {
			HandleReflectionException(ex);
			throw new IllegalStateException(
					"Unexpected reflection exception - " + ex.getClass().getName() + ": " + ex.getMessage());
		}
	}

	/**
	 * 获取字段
	 * Get the field represented by the supplied {@link Field field object} on the
	 * specified {@link Object target object}. In accordance with {@link Field#get(Object)}
	 * semantics, the returned value is automatically wrapped if the underlying field
	 * has a primitive type.
	 * <p>Thrown exceptions are handled via a call to {@link #handleReflectionException(Exception)}.
	 * @param field the field to get
	 * @param target the target object from which to get the field
	 * @return the field's current value
	 */
	public static Object GetField(Field field, Object target) {
		try {
			return field.get(target);
		}
		catch (IllegalAccessException ex) {
			HandleReflectionException(ex);
			throw new IllegalStateException(
					"Unexpected reflection exception - " + ex.getClass().getName() + ": " + ex.getMessage());
		}
	}

	/**
	 * 查找方法
	 * Attempt to find a {@link Method} on the supplied class with the supplied name
	 * and no parameters. Searches all superclasses up to {@code Object}.
	 * <p>Returns {@code null} if no {@link Method} can be found.
	 * @param clazz the class to introspect
	 * @param name the name of the method
	 * @return the Method object, or {@code null} if none found
	 */
	public static Method FindMethod(Class<?> clazz, String name) {
		return FindMethod(clazz, name, new Class<?>[0]);
	}

	/**
	 * 查找方法
	 * Attempt to find a {@link Method} on the supplied class with the supplied name
	 * and parameter types. Searches all superclasses up to {@code Object}.
	 * <p>Returns {@code null} if no {@link Method} can be found.
	 * @param clazz the class to introspect
	 * @param name the name of the method
	 * @param paramTypes the parameter types of the method
	 * (may be {@code null} to indicate any signature)
	 * @return the Method object, or {@code null} if none found
	 */
	public static Method FindMethod(Class<?> clazz, String name, Class<?>... paramTypes) {
		Assert.NotNull(clazz, "Class must not be null");
		Assert.NotNull(name, "Method name must not be null");
		Class<?> searchType = clazz;
		while (searchType != null) {
			Method[] methods = (searchType.isInterface() ? searchType.getMethods() : getDeclaredMethods(searchType));
			for (Method method : methods) {
				if (name.equals(method.getName()) &&
						(paramTypes == null || Arrays.equals(paramTypes, method.getParameterTypes()))) {
					return method;
				}
			}
			searchType = searchType.getSuperclass();
		}
		return null;
	}

	/**
	 * 调用方法
	 * Invoke the specified {@link Method} against the supplied target object with no arguments.
	 * The target object can be {@code null} when invoking a static {@link Method}.
	 * <p>Thrown exceptions are handled via a call to {@link #handleReflectionException}.
	 * @param method the method to invoke
	 * @param target the target object to invoke the method on
	 * @return the invocation result, if any
	 * @see #invokeMethod(Method, Object, Object[])
	 */
	public static Object InvokeMethod(Method method, Object target) {
		return InvokeMethod(method, target, new Object[0]);
	}

	/**
	 * 调用方法
	 * Invoke the specified {@link Method} against the supplied target object with the
	 * supplied arguments. The target object can be {@code null} when invoking a
	 * static {@link Method}.
	 * <p>Thrown exceptions are handled via a call to {@link #handleReflectionException}.
	 * @param method the method to invoke
	 * @param target the target object to invoke the method on
	 * @param args the invocation arguments (may be {@code null})
	 * @return the invocation result, if any
	 */
	public static Object InvokeMethod(Method method, Object target, Object... args) {
		try {
			return method.invoke(target, args);
		}
		catch (Exception ex) {
			HandleReflectionException(ex);
		}
		throw new IllegalStateException("Should never get here");
	}

	/**
	 * 调用方法
	 * Invoke the specified JDBC API {@link Method} against the supplied target
	 * object with no arguments.
	 * @param method the method to invoke
	 * @param target the target object to invoke the method on
	 * @return the invocation result, if any
	 * @throws SQLException the JDBC API SQLException to rethrow (if any)
	 * @see #invokeJdbcMethod(Method, Object, Object[])
	 */
	public static Object InvokeJdbcMethod(Method method, Object target) throws SQLException {
		return InvokeJdbcMethod(method, target, new Object[0]);
	}

	/**
	 * 调用方法
	 * Invoke the specified JDBC API {@link Method} against the supplied target
	 * object with the supplied arguments.
	 * @param method the method to invoke
	 * @param target the target object to invoke the method on
	 * @param args the invocation arguments (may be {@code null})
	 * @return the invocation result, if any
	 * @throws SQLException the JDBC API SQLException to rethrow (if any)
	 * @see #invokeMethod(Method, Object, Object[])
	 */
	public static Object InvokeJdbcMethod(Method method, Object target, Object... args) throws SQLException {
		try {
			return method.invoke(target, args);
		}
		catch (IllegalAccessException ex) {
			HandleReflectionException(ex);
		}
		catch (InvocationTargetException ex) {
			if (ex.getTargetException() instanceof SQLException) {
				throw (SQLException) ex.getTargetException();
			}
			HandleInvocationTargetException(ex);
		}
		throw new IllegalStateException("Should never get here");
	}

	/**
	 * 处理反射异常
	 * Handle the given reflection exception. Should only be called if no
	 * checked exception is expected to be thrown by the target method.
	 * <p>Throws the underlying RuntimeException or Error in case of an
	 * InvocationTargetException with such a root cause. Throws an
	 * IllegalStateException with an appropriate message else.
	 * @param ex the reflection exception to handle
	 */
	public static void HandleReflectionException(Exception ex) {
		if (ex instanceof NoSuchMethodException) {
			throw new IllegalStateException("Method not found: " + ex.getMessage());
		}
		if (ex instanceof IllegalAccessException) {
			throw new IllegalStateException("Could not access method: " + ex.getMessage());
		}
		if (ex instanceof InvocationTargetException) {
			HandleInvocationTargetException((InvocationTargetException) ex);
		}
		if (ex instanceof RuntimeException) {
			throw (RuntimeException) ex;
		}
		throw new UndeclaredThrowableException(ex);
	}

	/**
	 * 处理反射异常
	 * Handle the given invocation target exception. Should only be called if no
	 * checked exception is expected to be thrown by the target method.
	 * <p>Throws the underlying RuntimeException or Error in case of such a root
	 * cause. Throws an IllegalStateException else.
	 * @param ex the invocation target exception to handle
	 */
	public static void HandleInvocationTargetException(InvocationTargetException ex) {
		RethrowRuntimeException(ex.getTargetException());
	}

	/**
	 * 重新抛出异常
	 * Rethrow the given {@link Throwable exception}, which is presumably the
	 * <em>target exception</em> of an {@link InvocationTargetException}.
	 * Should only be called if no checked exception is expected to be thrown
	 * by the target method.
	 * <p>Rethrows the underlying exception cast to an {@link RuntimeException} or
	 * {@link Error} if appropriate; otherwise, throws an {@link IllegalStateException}.
	 * @param ex the exception to rethrow
	 * @throws RuntimeException the rethrown exception
	 */
	public static void RethrowRuntimeException(Throwable ex) {
		if (ex instanceof RuntimeException) {
			throw (RuntimeException) ex;
		}
		if (ex instanceof Error) {
			throw (Error) ex;
		}
		throw new UndeclaredThrowableException(ex);
	}

	/**
	 * 重新抛出异常
	 * Rethrow the given {@link Throwable exception}, which is presumably the
	 * <em>target exception</em> of an {@link InvocationTargetException}.
	 * Should only be called if no checked exception is expected to be thrown
	 * by the target method.
	 * <p>Rethrows the underlying exception cast to an {@link Exception} or
	 * {@link Error} if appropriate; otherwise, throws an {@link IllegalStateException}.
	 * @param ex the exception to rethrow
	 * @throws Exception the rethrown exception (in case of a checked exception)
	 */
	public static void RethrowException(Throwable ex) throws Exception {
		if (ex instanceof Exception) {
			throw (Exception) ex;
		}
		if (ex instanceof Error) {
			throw (Error) ex;
		}
		throw new UndeclaredThrowableException(ex);
	}

	/**
	 * 是否声明异常？
	 * Determine whether the given method explicitly declares the given
	 * exception or one of its superclasses, which means that an exception
	 * of that type can be propagated as-is within a reflective invocation.
	 * @param method the declaring method
	 * @param exceptionType the exception to throw
	 * @return {@code true} if the exception can be thrown as-is;
	 * {@code false} if it needs to be wrapped
	 */
	public static boolean DeclaresException(Method method, Class<?> exceptionType) {
		Assert.NotNull(method, "Method must not be null");
		Class<?>[] declaredExceptions = method.getExceptionTypes();
		for (Class<?> declaredException : declaredExceptions) {
			if (declaredException.isAssignableFrom(exceptionType)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否公开？静态？终态？
	 * Determine whether the given field is a "public static final" constant.
	 * @param field the field to check
	 */
	public static boolean IsPublicStaticFinal(Field field) {
		int modifiers = field.getModifiers();
		return (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers));
	}

	/**
	 * 是否相同方法？
	 * Determine whether the given method is an "equals" method.
	 * @see Object#equals(Object)
	 */
	public static boolean IsEqualsMethod(Method method) {
		if (method == null || !method.getName().equals("equals")) {
			return false;
		}
		Class<?>[] paramTypes = method.getParameterTypes();
		return (paramTypes.length == 1 && paramTypes[0] == Object.class);
	}

	/**
	 * 是否相同方法？
	 * Determine whether the given method is a "hashCode" method.
	 * @see Object#hashCode()
	 */
	public static boolean IsHashCodeMethod(Method method) {
		return (method != null && method.getName().equals("hashCode") && method.getParameterTypes().length == 0);
	}

	/**
	 * 是否toString()方法？
	 * Determine whether the given method is a "toString" method.
	 * @see Object#toString()
	 */
	public static boolean IsToStringMethod(Method method) {
		return (method != null && method.getName().equals("toString") && method.getParameterTypes().length == 0);
	}

	/**
	 * 是否Object的方法？
	 * Determine whether the given method is originally declared by {@link Object}.
	 */
	public static boolean IsObjectMethod(Method method) {
		if (method == null) {
			return false;
		}
		try {
			Object.class.getDeclaredMethod(method.getName(), method.getParameterTypes());
			return true;
		}
		catch (Exception ex) {
			return false;
		}
	}

	/**
	 * 是否CGLIB方法？
	 * Determine whether the given method is a CGLIB 'renamed' method,
	 * following the pattern "CGLIB$methodName$0".
	 * @param renamedMethod the method to check
	 * @see org.springframework.cglib.proxy.Enhancer#rename
	 */
	public static boolean IsCglibRenamedMethod(Method renamedMethod) {
		String name = renamedMethod.getName();
		if (name.startsWith(CGLIB_RENAMED_METHOD_PREFIX)) {
			int i = name.length() - 1;
			while (i >= 0 && Character.isDigit(name.charAt(i))) {
				i--;
			}
			return ((i > CGLIB_RENAMED_METHOD_PREFIX.length()) &&
						(i < name.length() - 1) && name.charAt(i) == '$');
		}
		return false;
	}

	/**
	 * 使其可以访问
	 * Make the given field accessible, explicitly setting it accessible if
	 * necessary. The {@code setAccessible(true)} method is only called
	 * when actually necessary, to avoid unnecessary conflicts with a JVM
	 * SecurityManager (if active).
	 * @param field the field to make accessible
	 * @see Field#setAccessible
	 */
	public static void MakeAccessible(Field field) {
		if ((!Modifier.isPublic(field.getModifiers()) ||
				!Modifier.isPublic(field.getDeclaringClass().getModifiers()) ||
				Modifier.isFinal(field.getModifiers())) && !field.isAccessible()) {
			field.setAccessible(true);
		}
	}

	/**
	 * 使其可以访问
	 * Make the given method accessible, explicitly setting it accessible if
	 * necessary. The {@code setAccessible(true)} method is only called
	 * when actually necessary, to avoid unnecessary conflicts with a JVM
	 * SecurityManager (if active).
	 * @param method the method to make accessible
	 * @see Method#setAccessible
	 */
	public static void MakeAccessible(Method method) {
		if ((!Modifier.isPublic(method.getModifiers()) ||
				!Modifier.isPublic(method.getDeclaringClass().getModifiers())) && !method.isAccessible()) {
			method.setAccessible(true);
		}
	}

	/**
	 * Make the given constructor accessible, explicitly setting it accessible
	 * if necessary. The {@code setAccessible(true)} method is only called
	 * when actually necessary, to avoid unnecessary conflicts with a JVM
	 * SecurityManager (if active).
	 * @param ctor the constructor to make accessible
	 * @see Constructor#setAccessible
	 */
	public static void makeAccessible(Constructor<?> ctor) {
		if ((!Modifier.isPublic(ctor.getModifiers()) ||
				!Modifier.isPublic(ctor.getDeclaringClass().getModifiers())) && !ctor.isAccessible()) {
			ctor.setAccessible(true);
		}
	}

	/**
	 * 本地化方法操作
	 * Perform the given callback operation on all matching methods of the given
	 * class, as locally declared or equivalent thereof (such as default methods
	 * on Java 8 based interfaces that the given class implements).
	 * @param clazz the class to introspect
	 * @param mc the callback to invoke for each method
	 * @see #doWithMethods
	 */
	public static void DoWithLocalMethods(Class<?> clazz, MethodCallback mc) {
		Method[] methods = getDeclaredMethods(clazz);
		for (Method method : methods) {
			try {
				mc.doWith(method);
			}
			catch (IllegalAccessException ex) {
				throw new IllegalStateException("Not allowed to access method '" + method.getName() + "': " + ex);
			}
		}
	}

	/**
	 * 本地化方法操作
	 * Perform the given callback operation on all matching methods of the given
	 * class and superclasses.
	 * <p>The same named method occurring on subclass and superclass will appear
	 * twice, unless excluded by a {@link MethodFilter}.
	 * @param clazz the class to introspect
	 * @param mc the callback to invoke for each method
	 * @see #doWithMethods(Class, MethodCallback, MethodFilter)
	 */
	public static void DoWithMethods(Class<?> clazz, MethodCallback mc) {
		DoWithMethods(clazz, mc, null);
	}

	/**
	 * 本地化方法操作
	 * Perform the given callback operation on all matching methods of the given
	 * class and superclasses (or given interface and super-interfaces).
	 * <p>The same named method occurring on subclass and superclass will appear
	 * twice, unless excluded by the specified {@link MethodFilter}.
	 * @param clazz the class to introspect
	 * @param mc the callback to invoke for each method
	 * @param mf the filter that determines the methods to apply the callback to
	 */
	public static void DoWithMethods(Class<?> clazz, MethodCallback mc, MethodFilter mf) {
		// Keep backing up the inheritance hierarchy.
		Method[] methods = getDeclaredMethods(clazz);
		for (Method method : methods) {
			if (mf != null && !mf.matches(method)) {
				continue;
			}
			try {
				mc.doWith(method);
			}
			catch (IllegalAccessException ex) {
				throw new IllegalStateException("Not allowed to access method '" + method.getName() + "': " + ex);
			}
		}
		if (clazz.getSuperclass() != null) {
			DoWithMethods(clazz.getSuperclass(), mc, mf);
		}
		else if (clazz.isInterface()) {
			for (Class<?> superIfc : clazz.getInterfaces()) {
				DoWithMethods(superIfc, mc, mf);
			}
		}
	}

	/**
	 * 获取所有声明的方法
	 * Get all declared methods on the leaf class and all superclasses.
	 * Leaf class methods are included first.
	 * @param leafClass the class to introspect
	 */
	public static Method[] GetAllDeclaredMethods(Class<?> leafClass) {
		final List<Method> methods = new ArrayList<Method>(32);
		DoWithMethods(leafClass, new MethodCallback() {
			@Override
			public void doWith(Method method) {
				methods.add(method);
			}
		});
		return methods.toArray(new Method[methods.size()]);
	}

	/**
	 * 获取唯一声明方法
	 * Get the unique set of declared methods on the leaf class and all superclasses.
	 * Leaf class methods are included first and while traversing the superclass hierarchy
	 * any methods found with signatures matching a method already included are filtered out.
	 * @param leafClass the class to introspect
	 */
	public static Method[] GetUniqueDeclaredMethods(Class<?> leafClass) {
		final List<Method> methods = new ArrayList<Method>(32);
		DoWithMethods(leafClass, new MethodCallback() {
			@Override
			public void doWith(Method method) {
				boolean knownSignature = false;
				Method methodBeingOverriddenWithCovariantReturnType = null;
				for (Method existingMethod : methods) {
					if (method.getName().equals(existingMethod.getName()) &&
							Arrays.equals(method.getParameterTypes(), existingMethod.getParameterTypes())) {
						// Is this a covariant return type situation?
						if (existingMethod.getReturnType() != method.getReturnType() &&
								existingMethod.getReturnType().isAssignableFrom(method.getReturnType())) {
							methodBeingOverriddenWithCovariantReturnType = existingMethod;
						}
						else {
							knownSignature = true;
						}
						break;
					}
				}
				if (methodBeingOverriddenWithCovariantReturnType != null) {
					methods.remove(methodBeingOverriddenWithCovariantReturnType);
				}
				if (!knownSignature && !IsCglibRenamedMethod(method)) {
					methods.add(method);
				}
			}
		});
		return methods.toArray(new Method[methods.size()]);
	}

	/**
	 * This variant retrieves {@link Class#getDeclaredMethods()} from a local cache
	 * in order to avoid the JVM's SecurityManager check and defensive array copying.
	 * In addition, it also includes Java 8 default methods from locally implemented
	 * interfaces, since those are effectively to be treated just like declared methods.
	 * @param clazz the class to introspect
	 * @return the cached array of methods
	 * @see Class#getDeclaredMethods()
	 */
	private static Method[] getDeclaredMethods(Class<?> clazz) {
		Method[] result = declaredMethodsCache.get(clazz);
		if (result == null) {
			Method[] declaredMethods = clazz.getDeclaredMethods();
			List<Method> defaultMethods = findConcreteMethodsOnInterfaces(clazz);
			if (defaultMethods != null) {
				result = new Method[declaredMethods.length + defaultMethods.size()];
				System.arraycopy(declaredMethods, 0, result, 0, declaredMethods.length);
				int index = declaredMethods.length;
				for (Method defaultMethod : defaultMethods) {
					result[index] = defaultMethod;
					index++;
				}
			}
			else {
				result = declaredMethods;
			}
			declaredMethodsCache.put(clazz, (result.length == 0 ? NO_METHODS : result));
		}
		return result;
	}

	private static List<Method> findConcreteMethodsOnInterfaces(Class<?> clazz) {
		List<Method> result = null;
		for (Class<?> ifc : clazz.getInterfaces()) {
			for (Method ifcMethod : ifc.getMethods()) {
				if (!Modifier.isAbstract(ifcMethod.getModifiers())) {
					if (result == null) {
						result = new LinkedList<Method>();
					}
					result.add(ifcMethod);
				}
			}
		}
		return result;
	}

	/**
	 * 本地化字段
	 * Invoke the given callback on all fields in the target class, going up the
	 * class hierarchy to get all declared fields.
	 * @param clazz the target class to analyze
	 * @param fc the callback to invoke for each field
	 * @since 4.2
	 * @see #doWithFields
	 */
	public static void DoWithLocalFields(Class<?> clazz, FieldCallback fc) {
		for (Field field : GetDeclaredFields(clazz)) {
			try {
				fc.doWith(field);
			}
			catch (IllegalAccessException ex) {
				throw new IllegalStateException("Not allowed to access field '" + field.getName() + "': " + ex);
			}
		}
	}

	/**
	 * 本地化字段
	 * Invoke the given callback on all fields in the target class, going up the
	 * class hierarchy to get all declared fields.
	 * @param clazz the target class to analyze
	 * @param fc the callback to invoke for each field
	 */
	public static void DoWithFields(Class<?> clazz, FieldCallback fc) {
		DoWithFields(clazz, fc, null);
	}

	/**
	 * 本地化字段
	 * Invoke the given callback on all fields in the target class, going up the
	 * class hierarchy to get all declared fields.
	 * @param clazz the target class to analyze
	 * @param fc the callback to invoke for each field
	 * @param ff the filter that determines the fields to apply the callback to
	 */
	public static void DoWithFields(Class<?> clazz, FieldCallback fc, FieldFilter ff) {
		// Keep backing up the inheritance hierarchy.
		Class<?> targetClass = clazz;
		do {
			Field[] fields = GetDeclaredFields(targetClass);
			for (Field field : fields) {
				if (ff != null && !ff.matches(field)) {
					continue;
				}
				try {
					fc.doWith(field);
				}
				catch (IllegalAccessException ex) {
					throw new IllegalStateException("Not allowed to access field '" + field.getName() + "': " + ex);
				}
			}
			targetClass = targetClass.getSuperclass();
		}
		while (targetClass != null && targetClass != Object.class);
	}

	/**
	 * 获取声明的字段
	 * This variant retrieves {@link Class#getDeclaredFields()} from a local cache
	 * in order to avoid the JVM's SecurityManager check and defensive array copying.
	 * @param clazz the class to introspect
	 * @return the cached array of fields
	 * @see Class#getDeclaredFields()
	 */
	private static Field[] GetDeclaredFields(Class<?> clazz) {
		Field[] result = declaredFieldsCache.get(clazz);
		if (result == null) {
			result = clazz.getDeclaredFields();
			declaredFieldsCache.put(clazz, (result.length == 0 ? NO_FIELDS : result));
		}
		return result;
	}

	/**
	 * Given the source object and the destination, which must be the same class
	 * or a subclass, copy all fields, including inherited fields. Designed to
	 * work on objects with public no-arg constructors.
	 */
	public static void ShallowCopyFieldState(final Object src, final Object dest) {
		if (src == null) {
			throw new IllegalArgumentException("Source for field copy cannot be null");
		}
		if (dest == null) {
			throw new IllegalArgumentException("Destination for field copy cannot be null");
		}
		if (!src.getClass().isAssignableFrom(dest.getClass())) {
			throw new IllegalArgumentException("Destination class [" + dest.getClass().getName() +
					"] must be same or subclass as source class [" + src.getClass().getName() + "]");
		}
		DoWithFields(src.getClass(), new FieldCallback() {
			@Override
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
				MakeAccessible(field);
				Object srcValue = field.get(src);
				field.set(dest, srcValue);
			}
		}, COPYABLE_FIELDS);
	}


	/**
	 * Action to take on each method.
	 */
	public interface MethodCallback {

		/**
		 * Perform an operation using the given method.
		 * @param method the method to operate on
		 */
		void doWith(Method method) throws IllegalArgumentException, IllegalAccessException;
	}


	/**
	 * Callback optionally used to filter methods to be operated on by a method callback.
	 */
	public interface MethodFilter {

		/**
		 * Determine whether the given method matches.
		 * @param method the method to check
		 */
		boolean matches(Method method);
	}


	/**
	 * Callback interface invoked on each field in the hierarchy.
	 */
	public interface FieldCallback {

		/**
		 * Perform an operation using the given field.
		 * @param field the field to operate on
		 */
		void doWith(Field field) throws IllegalArgumentException, IllegalAccessException;
	}


	/**
	 * Callback optionally used to filter fields to be operated on by a field callback.
	 */
	public interface FieldFilter {

		/**
		 * Determine whether the given field matches.
		 * @param field the field to check
		 */
		boolean matches(Field field);
	}


	/**
	 * Pre-built FieldFilter that matches all non-static, non-final fields.
	 */
	public static FieldFilter COPYABLE_FIELDS = new FieldFilter() {

		@Override
		public boolean matches(Field field) {
			return !(Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers()));
		}
	};


	/**
	 * Pre-built MethodFilter that matches all non-bridge methods.
	 */
	public static MethodFilter NON_BRIDGED_METHODS = new MethodFilter() {

		@Override
		public boolean matches(Method method) {
			return !method.isBridge();
		}
	};


	/**
	 * Pre-built MethodFilter that matches all non-bridge methods
	 * which are not declared on {@code java.lang.Object}.
	 */
	public static MethodFilter USER_DECLARED_METHODS = new MethodFilter() {

		@Override
		public boolean matches(Method method) {
			return (!method.isBridge() && method.getDeclaringClass() != Object.class);
		}
	};
}
