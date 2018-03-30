package com.septinary.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期时间操作
 * @Filename: com.septinary.common.util.DateTimeUtil.java of the project [com.septinary.common]
 *     @Type: DateTimeUtil
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年2月17日下午11:10:36
 *
 */
abstract public class DateTimeUtil {

	static public String Now(String format) {
		return new SimpleDateFormat(format).format(new Date());
	}
	
	static public long Now() {
		return NowMS() / 1000;
	}
	
	static public long NowMS() {
		return System.currentTimeMillis();
	}
	
	static public Date Date(long timestampMS) {
		return new Date(timestampMS);
	}
	
	static public Date NowDate() {
		return Date(NowMS());
	}
	
	static public String Format(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}
	
	static public String Format(long timestampMS, String format) {
		return new SimpleDateFormat(format).format(Date(timestampMS));
	}
}
