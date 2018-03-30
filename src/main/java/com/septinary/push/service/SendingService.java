package com.septinary.push.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gexin.rp.sdk.base.uitls.AppConditions;
import com.septinary.push.entity.AppAttr;
import com.septinary.push.entity.Message;
import com.septinary.push.entity.PushType;

public interface SendingService  {

	public void push(Message message);

	/**
	 * 给单个用户进行推送
	 * @param request 请求参数
	 * @param cid 用户id
	 * @param message 推送的消息
	 * @param pushType 推送类型
	 * @throws Exception
	 */
	public void push2Single(HttpServletRequest request, String cid, Message message, PushType pushType) throws Exception;

	/**
	 * 获取推送服务器参数
	 * @param request 请求参数
	 * @return
	 * @throws IOException
	 */
	public AppAttr loadAppAttr(HttpServletRequest request) throws IOException;

	/**
	 * 给别名用户进行推送
	 * @param request 请求参数
	 * @param alias 用户别名
	 * @param message 推送的消息
	 * @param pushType 推送类型
	 * @throws Exception
	 */
	public void push2Alias(HttpServletRequest request, String alias, Message message, PushType pushType) throws Exception;

	/**
	 * 给APP推送
	 * @param request 请求参数
	 * @param message 推送的消息
	 * @param pushType 推送类型
	 * @throws Exception
	 */
	public void push2App(HttpServletRequest request, Message message, PushType pushType) throws Exception;


	/**
	 * 推送整个列表
	 * @param request 请求参数
	 * @param cids 客户端ids
	 * @param message 推送的消息
	 * @param pushType 推送类型
	 * @throws Exception
	 */
	public void push2List(HttpServletRequest request, List<String> cids, Message message, PushType pushType)throws Exception;


	void pushWithConditions(HttpServletRequest request, AppConditions cdt, Message message, PushType pushType)throws Exception;
}
