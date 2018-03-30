package com.septinary.common.util;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

/**
 * 反射类型操作
 * @Filename: com.septinary.common.util.TypeUtil.java of the project [com.septinary.common]
 *     @Type: TypeUtil
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月13日下午12:33:36
 *
 */
public abstract class TypeUtil {

	/**
	 * 可否赋值？
	 * Check if the right-hand side type may be assigned to the left-hand side
	 * type following the Java generics rules.
	 * @param lhsType the target type
	 * @param rhsType the value type that should be assigned to the target type
	 * @return true if rhs is assignable to lhs
	 */
	public static boolean IsAssignable(Type lhsType, Type rhsType) {
		Assert.NotNull(lhsType, "Left-hand side type must not be null");
		Assert.NotNull(rhsType, "Right-hand side type must not be null");

		// all types are assignable to themselves and to class Object
		if (lhsType.equals(rhsType) || Object.class == lhsType) {
			return true;
		}

		if (lhsType instanceof Class<?>) {
			Class<?> lhsClass = (Class<?>) lhsType;

			// just comparing two classes
			if (rhsType instanceof Class<?>) {
				return ClassUtil.IsAssignable(lhsClass, (Class<?>) rhsType);
			}

			if (rhsType instanceof ParameterizedType) {
				Type rhsRaw = ((ParameterizedType) rhsType).getRawType();

				// a parameterized type is always assignable to its raw class type
				if (rhsRaw instanceof Class<?>) {
					return ClassUtil.IsAssignable(lhsClass, (Class<?>) rhsRaw);
				}
			} else if (lhsClass.isArray() && rhsType instanceof GenericArrayType) {
				Type rhsComponent = ((GenericArrayType) rhsType).getGenericComponentType();

				return IsAssignable(lhsClass.getComponentType(), rhsComponent);
			}
		}

		// parameterized types are only assignable to other parameterized types and class types
		if (lhsType instanceof ParameterizedType) {
			if (rhsType instanceof Class<?>) {
				Type lhsRaw = ((ParameterizedType) lhsType).getRawType();

				if (lhsRaw instanceof Class<?>) {
					return ClassUtil.IsAssignable((Class<?>) lhsRaw, (Class<?>) rhsType);
				}
			} else if (rhsType instanceof ParameterizedType) {
				return isAssignable((ParameterizedType) lhsType, (ParameterizedType) rhsType);
			}
		}

		if (lhsType instanceof GenericArrayType) {
			Type lhsComponent = ((GenericArrayType) lhsType).getGenericComponentType();

			if (rhsType instanceof Class<?>) {
				Class<?> rhsClass = (Class<?>) rhsType;

				if (rhsClass.isArray()) {
					return IsAssignable(lhsComponent, rhsClass.getComponentType());
				}
			}
			else if (rhsType instanceof GenericArrayType) {
				Type rhsComponent = ((GenericArrayType) rhsType).getGenericComponentType();

				return IsAssignable(lhsComponent, rhsComponent);
			}
		}

		if (lhsType instanceof WildcardType) {
			return isAssignable((WildcardType) lhsType, rhsType);
		}

		return false;
	}

	private static boolean isAssignable(ParameterizedType lhsType, ParameterizedType rhsType) {
		if (lhsType.equals(rhsType)) {
			return true;
		}

		Type[] lhsTypeArguments = lhsType.getActualTypeArguments();
		Type[] rhsTypeArguments = rhsType.getActualTypeArguments();

		if (lhsTypeArguments.length != rhsTypeArguments.length) {
			return false;
		}

		for (int size = lhsTypeArguments.length, i = 0; i < size; ++i) {
			Type lhsArg = lhsTypeArguments[i];
			Type rhsArg = rhsTypeArguments[i];

			if (!lhsArg.equals(rhsArg) &&
					!(lhsArg instanceof WildcardType && isAssignable((WildcardType) lhsArg, rhsArg))) {
				return false;
			}
		}

		return true;
	}

	private static boolean isAssignable(WildcardType lhsType, Type rhsType) {
		Type[] lUpperBounds = lhsType.getUpperBounds();

		// supply the implicit upper bound if none are specified
		if (lUpperBounds.length == 0) {
			lUpperBounds = new Type[] { Object.class };
		}

		Type[] lLowerBounds = lhsType.getLowerBounds();

		// supply the implicit lower bound if none are specified
		if (lLowerBounds.length == 0) {
			lLowerBounds = new Type[] { null };
		}

		if (rhsType instanceof WildcardType) {
			// both the upper and lower bounds of the right-hand side must be
			// completely enclosed in the upper and lower bounds of the left-
			// hand side.
			WildcardType rhsWcType = (WildcardType) rhsType;
			Type[] rUpperBounds = rhsWcType.getUpperBounds();

			if (rUpperBounds.length == 0) {
				rUpperBounds = new Type[] { Object.class };
			}

			Type[] rLowerBounds = rhsWcType.getLowerBounds();

			if (rLowerBounds.length == 0) {
				rLowerBounds = new Type[] { null };
			}

			for (Type lBound : lUpperBounds) {
				for (Type rBound : rUpperBounds) {
					if (!IsAssignableBound(lBound, rBound)) {
						return false;
					}
				}

				for (Type rBound : rLowerBounds) {
					if (!IsAssignableBound(lBound, rBound)) {
						return false;
					}
				}
			}

			for (Type lBound : lLowerBounds) {
				for (Type rBound : rUpperBounds) {
					if (!IsAssignableBound(rBound, lBound)) {
						return false;
					}
				}

				for (Type rBound : rLowerBounds) {
					if (!IsAssignableBound(rBound, lBound)) {
						return false;
					}
				}
			}
		}
		else {
			for (Type lBound : lUpperBounds) {
				if (!IsAssignableBound(lBound, rhsType)) {
					return false;
				}
			}

			for (Type lBound : lLowerBounds) {
				if (!IsAssignableBound(rhsType, lBound)) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * 可否赋值？
	 @method TypeUtil: IsAssignableBound()
	 @memo TODO
	 @param lhsType
	 @param rhsType
	 @return boolean
	 */
	public static boolean IsAssignableBound(Type lhsType, Type rhsType) {
		if (rhsType == null) {
			return true;
		}

		if (lhsType == null) {
			return false;
		}
		return IsAssignable(lhsType, rhsType);
	}
}
