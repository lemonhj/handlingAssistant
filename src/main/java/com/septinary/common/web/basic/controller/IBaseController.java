package com.septinary.common.web.basic.controller;

import com.septinary.common.web.basic.business.IBusiness;

/**
 * Web 请求处理控制器基类接口
 * @Filename: com.septinary.common.web.basic.controller.IBaseController.java of the project [com.septinary.common.web]
 *     @Type: IBaseController
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月12日下午12:29:18
 *
 */
public interface IBaseController extends IBusiness {
	//成功视图
	static public final String SUCCESS = "SUCCESS";
	//错误视图
	static public final String ERROR = "ERROR";
	//异常视图
	static public final String EXCEPTION = "EXCEPTION";
	//跳转视图
	static public final String REDIRECT = "REDIRECT";
	//返回视图
	static public final String GOBACK = "GOBACK";

	//成功代码
	static public final String OK = "200";
	//无效请求
	static public final String INVALID = "400";
	//未授权认证
	static public final String UNAUTH = "401";
	//无权访问
	static public final String FORBIDDEN = "403";
	//未找到目标
	static public final String NOTFOUND = "404";
	//目标丢失
	static public final String MISSING = "410";
	//目标过期
	static public final String OUTDATE = "1102";
	//数据为空
	static public final String EMPTY = "1201";
	//内部错误
	static public final String INTERNALERROR = "500";
}
