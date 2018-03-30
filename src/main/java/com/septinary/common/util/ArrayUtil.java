package com.septinary.common.util;

import java.lang.reflect.Array;
import java.util.HashMap;

/**
 * 数组操作
 * @Filename: com.septinary.common.util.ArrayUtil.java of the project [com.septinary.common]
 *     @Type: ArrayUtil
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年5月13日下午12:31:38
 *
 */
public abstract class ArrayUtil {

	/**
	 * 是否array对象？
	 @method ObjectUtil: IsArray()
	 @memo TODO
	 @param obj
	 @return boolean
	 */
	public static boolean IsArray(Object obj) {
		return (obj != null && obj.getClass().isArray());
	}
	
	/**
	 * 是否空对象？
	 @method ObjectUtil: IsEmpty()
	 @memo TODO
	 @param array
	 @return boolean
	 */
	public static boolean IsEmpty(Object[] array) {
		return (array == null || array.length == 0);
	}

	/**
	 * 是否数组中包含有元素？
	 @method ObjectUtil: ContainsElement()
	 @memo TODO
	 @param array
	 @param element
	 @return boolean
	 */
	public static boolean ContainsElement(Object[] array, Object element) {
		if (array == null) {
			return false;
		}
		for (Object arrayEle : array) {
			if (ObjectUtil.EqualNullSafe(arrayEle, element)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 对对象数组中每一个元素依次执行指定回调方法
	 @method ObjectUtil: EachDo()
	 @memo TODO
	 @param arr
	 @param callback
	 @param abortReturnFilter 终止返回值条件
	 @return int 成功执行的次数
	 */
	static public int EachDo(Object[] arr, ICallbackable callback, Object abortReturnFilter ) {
		if(null==arr || null==callback) return 0;
		
		int count = 0;
		for(Object item: arr) {
			Object ret = callback.callback(item);
			count ++;
			if(null!=abortReturnFilter && abortReturnFilter.equals(ret)) return count;
		}
		
		return count;
	}
	static public int EachDo(Object[] arr, ICallbackable callback) {
		return EachDo(arr, callback, null);
	}
	
	/**
	 * 数组转Map
	 @method StringUtil: ArrayToMap()
	 @memo TODO
	 @param arr
	 @return HashMap<String,Object>
	 */
	public static HashMap<String,Object> ArrayToMap(Object[] arr) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		if(null==arr || 0>=arr.length ) return result;
		
		for(int i=arr.length-1; i>=0; i--) result.put(String.valueOf(i), arr[i]);
		return result;
	}

	/**
	 * 追加进入数组
	 @method ObjectUtil: AppendToArray()
	 @memo TODO
	 @param array
	 @param obj
	 @return A[]
	 */
	public static <A, O extends A> A[] AppendToArray(A[] array, O obj) {
		Class<?> compType = Object.class;
		if (array != null) {
			compType = array.getClass().getComponentType();
		}
		else if (obj != null) {
			compType = obj.getClass();
		}
		int newArrLength = (array != null ? array.length + 1 : 1);
		@SuppressWarnings("unchecked")
		A[] newArr = (A[]) Array.newInstance(compType, newArrLength);
		if (array != null) {
			System.arraycopy(array, 0, newArr, 0, array.length);
		}
		newArr[newArr.length - 1] = obj;
		return newArr;
	}
	
	/**
	 * 获取第index个元素
	 * @param tArray
	 * @param index
	 * @param defaultvalue
	 * @return
	 */
	public static <A> A get(A[] tArray, int index, A defaultvalue) {
		if(null==tArray || 0>index || index>=tArray.length) return defaultvalue;
		return tArray[index];
	}
	public static <A> A get(A[] tArray, int index) {
		return get(tArray, index, null);
	}
	public static boolean get(boolean[] tArray, int index, boolean defaultvalue) {
		if(null==tArray || 0>index || index>=tArray.length) return defaultvalue;
		return tArray[index];
	}
	public static boolean get(boolean[] tArray, int index) {
		return get(tArray,index,false);
	}
	public static byte get(byte[] tArray, int index, byte defaultvalue) {
		if(null==tArray || 0>index || index>=tArray.length) return defaultvalue;
		return tArray[index];
	}
	public static byte get(byte[] tArray, int index) {
		return get(tArray,index,(byte)0);
	}
	public static char get(char[] tArray, int index, char defaultvalue) {
		if(null==tArray || 0>index || index>=tArray.length) return defaultvalue;
		return tArray[index];
	}
	public static char get(char[] tArray, int index) {
		return get(tArray,index,(char)0);
	}
	public static short get(short[] tArray, int index, short defaultvalue) {
		if(null==tArray || 0>index || index>=tArray.length) return defaultvalue;
		return tArray[index];
	}
	public static short get(short[] tArray, int index) {
		return get(tArray,index,(short)0);
	}
	public static int get(int[] tArray, int index, int defaultvalue) {
		if(null==tArray || 0>index || index>=tArray.length) return defaultvalue;
		return tArray[index];
	}
	public static int get(int[] tArray, int index) {
		return get(tArray,index,0);
	}
	public static long get(long[] tArray, int index, long defaultvalue) {
		if(null==tArray || 0>index || index>=tArray.length) return defaultvalue;
		return tArray[index];
	}
	public static long get(long[] tArray, int index) {
		return get(tArray,index,(long)0);
	}
	public static float get(float[] tArray, int index, float defaultvalue) {
		if(null==tArray || 0>index || index>=tArray.length) return defaultvalue;
		return tArray[index];
	}
	public static float get(float[] tArray, int index) {
		return get(tArray,index,(float)0);
	}
	public static double get(double[] tArray, int index, double defaultvalue) {
		if(null==tArray || 0>index || index>=tArray.length) return defaultvalue;
		return tArray[index];
	}
	public static double get(double[] tArray, int index) {
		return get(tArray,index,(double)0);
	}
}
