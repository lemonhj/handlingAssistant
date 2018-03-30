package com.septinary.common.general.util;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/**
 * 通用gson util操作类
 * @Filename: com.septinary.common.general.util.GsonUtil.java of the project [com.septinary.common.general]
 *     @Type: GsonUtil
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月23日下午1:53:56
 *
 *	依赖gson包。
 */
public class GsonUtil {

	/**
	 * 对象转JSON字符串
	 * @param object
	 * @param type
	 * @return
	 */
	public static String ToJson(Object object, Type type) {
		return new Gson().toJson(object, type);		
	}
	
	/**
	 * 对象转JSON字符串
	 * @param object
	 * @return
	 */
	public static String ToJson(Object object) {
		return new GsonBuilder().create().toJson(object);
	}
	
	/**
	 * 对象转JSON字符串
	 * @param object
	 * @param timeformat
	 * @return
	 */
	public static String ToJson(Object object, String timeformat) {
		return new GsonBuilder().registerTypeAdapter(Timestamp.class, new TimestampGsonAdapter(timeformat)).setDateFormat(timeformat).create().toJson(object);
	}
	
	/**
	 * JSON字符串转实体
	 * @param jsonString
	 * @return
	 */
	public static <T> T JsonTo(String jsonString) {
	    Type type = new TypeToken<T>(){}.getType();
	    try{
		    return new Gson().fromJson(jsonString, type);
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		}
	    return null;
	}
	
	/**
	 * JSON字符串转实体
	 * @param jsonString
	 * @param type
	 * @return
	 */
	public static <T> T JsonTo(String jsonString, Type type) {
		try {
			return new Gson().fromJson(jsonString, type);
		} catch (JsonSyntaxException jse) {
			jse.printStackTrace();
		}
		return null;
	}
	
	/**
	 * JSON字符串转MAP
	 * @param jsonString
	 * @return
	 */
	static public Map<String,String> JsonToMap(String jsonString) {
	    Type type = new TypeToken<Map<String,String>>(){}.getType();
	    Gson gson = new Gson();
	    return gson.fromJson(jsonString, type);
	}
}
