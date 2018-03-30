package com.septinary.push.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.uitls.AppConditions;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.septinary.push.entity.AppAttr;
import com.septinary.push.entity.Message;
import com.septinary.push.entity.PushType;
import com.septinary.push.service.SendingService;

@Service("pushSendingService")
public class SendingServiceImpl  implements SendingService {

	private static AppAttr appAttr = null;

	private static final Logger logger = Logger.getLogger(SendingServiceImpl.class);


	@Override
	public void push(Message message) {

	}

	/**
	 * 给单个用户进行推送
	 * @param request 请求参数
	 * @param cid 用户id
	 * @param message 推送的消息
	 * @param pushType 推送类型
	 * @throws Exception
	 */
	public void push2Single(HttpServletRequest request,String cid,Message message,PushType pushType) throws Exception{
		appAttr = loadAppAttr(request);
		IGtPush push = new IGtPush(appAttr.getHost(), appAttr.getAppKey(), appAttr.getMaster());


		TransmissionTemplate template = transmissionTemplateDemo(message);
		SingleMessage msg = new SingleMessage();
		msg.setOffline(true);
		//离线有效时间，单位为毫秒，可选
		msg.setOfflineExpireTime(24 * 3600 * 1000);
		msg.setData(template);
		msg.setPushNetWorkType(0);

		Target target = new Target();

		target.setAppId(appAttr.getAppId());
		target.setClientId(cid);
		//用户别名推送，cid和用户别名只能2者选其一
		//String alias = "个";
		//target.setAlias(alias);
		IPushResult ret = null;
		try{
			ret = push.pushMessageToSingle(msg, target);
		}catch(RequestException e){
			e.printStackTrace();
			ret = push.pushMessageToSingle(msg, target, e.getRequestId());
		}
		if(ret != null){
			logger.info(ret.getResponse().toString());
		}else{
			logger.info("服务器响应异常");
		}

	}

	/**
	 * 给别名用户进行推送
	 * @param request 请求参数
	 * @param alias 用户别名
	 * @param message 推送的消息
	 * @param pushType 推送类型
	 * @throws Exception
	 */
	public void push2Alias(HttpServletRequest request,String alias,Message message,PushType pushType) throws Exception{
		appAttr = loadAppAttr(request);
		IGtPush push = new IGtPush(appAttr.getHost(), appAttr.getAppKey(), appAttr.getMaster());


		TransmissionTemplate template = transmissionTemplateDemo(message);
		SingleMessage msg = new SingleMessage();
		msg.setOffline(true);
		//离线有效时间，单位为毫秒，可选
		msg.setOfflineExpireTime(24 * 3600 * 1000);
		msg.setData(template);
		msg.setPushNetWorkType(0);

		Target target = new Target();

		target.setAppId(appAttr.getAppId());

		//用户别名推送，cid和用户别名只能2者选其一
		target.setAlias(alias);
		IPushResult ret = null;
		try{
			ret = push.pushMessageToSingle(msg, target);
		}catch(RequestException e){
			e.printStackTrace();
			ret = push.pushMessageToSingle(msg, target, e.getRequestId());
		}
		if(ret != null){
			logger.info(ret.getResponse().toString());
		}else {
			logger.info("服务器响应异常");
		}

	}

	/**
	 * 给APP推送
	 * @param request 请求参数
	 * @param message 推送的消息
	 * @param pushType 推送类型
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void push2App (HttpServletRequest request,Message message,PushType pushType) throws Exception{
		appAttr = loadAppAttr(request);
		IGtPush push = new IGtPush(appAttr.getHost(), appAttr.getAppKey(), appAttr.getMaster());
		//透传模板
		TransmissionTemplate template = transmissionTemplateDemo(message);
		AppMessage msg = new AppMessage();
		msg.setData(template);
		//设置消息离线，并设置离线时间
		msg.setOffline(true);
		//离线有效时间，单位为毫秒，可选
		msg.setOfflineExpireTime(24*1000*3600);
		//设置推送目标条件过滤

		List appIdList = new ArrayList();
		List phoneTypeList = new ArrayList();
		List provinceList = new ArrayList();
		List tagList = new ArrayList();
		appIdList.add(appAttr.getAppId());

		//设置机型
		phoneTypeList.add("ANDROID");
		//设置省份
		provinceList.add("浙江");
		//设置标签内容
		tagList.add("开心");
		msg.setAppIdList(appIdList);
		//message.setPhoneTypeList(phoneTypeList);
		//message.setProvinceList(provinceList);
		// message.setTagList(tagList);

		//设置省市平台tag的新方式
		AppConditions cdt = new AppConditions();
		cdt.addCondition(AppConditions.PHONE_TYPE, phoneTypeList);
		cdt.addCondition(AppConditions.REGION, provinceList);
		cdt.addCondition(AppConditions.TAG,tagList);
		msg.setConditions(cdt);
		//message.setPushNetWorkType(1);
		//message.setSpeed(1000);
		IPushResult ret = push.pushMessageToApp(msg);
		logger.info(ret.getResponse().toString());
	}


	/**
	 * 推送整个列表
	 * @param request 请求参数
	 * @param cids 客户端ids
	 * @param message 推送的消息
	 * @param pushType 推送类型
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void push2List(HttpServletRequest request,List<String> cids,Message message,PushType pushType)throws Exception{
		appAttr = loadAppAttr(request);
		IGtPush push = new IGtPush(appAttr.getHost(), appAttr.getAppKey(), appAttr.getMaster());

		//通知透传模板
		TransmissionTemplate template = transmissionTemplateDemo(message);
		ListMessage msg = new ListMessage();
		msg.setData(template);

		//设置消息离线，并设置离线时间
		msg.setOffline(true);
		//离线有效时间，单位为毫秒，可选
		msg.setOfflineExpireTime(24*1000*3600);

		List targets = constructTargets(cids);

		//获取taskID
		String taskId = push.getContentId(msg);
		//使用taskID对目标进行推送
		IPushResult ret = push.pushMessageToList(taskId, targets);
		//打印服务器返回信息
		logger.info(ret.getResponse().toString());
	}


	/**
	 * 根据条件推送
	 * @param request
	 * @param cdt  可以设置区域，终端类型，标签
	 * @param message 消息
	 * @param pushType 推送类型
	 * @throws Exception
	 */
	public void pushWithConditions(HttpServletRequest request,AppConditions cdt,Message message,PushType pushType)throws Exception{
		appAttr = loadAppAttr(request);
		IGtPush push = new IGtPush(appAttr.getHost(), appAttr.getAppKey(), appAttr.getMaster());

		TransmissionTemplate template = transmissionTemplateDemo(message);
		AppMessage msg = new AppMessage();
		msg.setData(template);

		msg.setOffline(true);
		//离线有效时间，单位为毫秒，可选
		msg.setOfflineExpireTime(24 * 1000 * 3600);
		List<String> appIdList = new ArrayList<>();
		appIdList.add(appAttr.getAppId());
		msg.setAppIdList(appIdList);
		msg.setConditions(cdt);

		IPushResult ret = push.pushMessageToApp(msg,"任务别名_withConditions");
		logger.info(ret.getResponse().toString());
	}

//	public int QueryUserCount(){
//
//	}



	/**
	 * 构建推送目标客户端
	 * @param cids
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List constructTargets(List<String> cids){
		List targets = new ArrayList();
		for (String cid : cids){
			Target target = new Target();
			target.setClientId(cid);
			target.setAppId(appAttr.getAppId());
			targets.add(target);
		}
		return targets;
	}


	public AppAttr loadAppAttr(HttpServletRequest request) throws IOException {
		if (appAttr != null){
			return appAttr;
		}else{
			InputStream is = request.getServletContext().getResourceAsStream("/WEB-INF/classes/push.properties");
			Properties p = new Properties();
			p.load(is);
			AppAttr appAttr = new AppAttr();
			appAttr.setAppId(p.getProperty("appId"));
			appAttr.setAppKey(p.getProperty("appKey"));
			appAttr.setMaster(p.getProperty("master"));
			appAttr.setHost(p.getProperty("host"));
			return appAttr;
		}

	}



	/**
	 * 透传模板
	 * @param msg 消息
	 * @return
	 */
	public static TransmissionTemplate transmissionTemplateDemo(Message msg) {
		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(appAttr.getAppId());
		template.setAppkey(appAttr.getAppKey());
		// 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
		template.setTransmissionType(2);
		template.setTransmissionContent(msg.getTransmissionContent());
		// 设置定时展示时间
		// template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
		return template;
	}

	/**
	 * 通知模板
	 * @param msg 消息
	 * @return
	 */
	public static NotificationTemplate notificationTemplateDemo(Message msg) {
		NotificationTemplate template = new NotificationTemplate();
		// 设置APPID与APPKEY
		template.setAppId(appAttr.getAppId());
		template.setAppkey(appAttr.getAppKey());
		// 设置通知栏标题与内容
		template.setTitle(msg.getTitle());
		template.setText(msg.getContent());
		// 配置通知栏图标
		template.setLogo(msg.getLogo());
		// 配置通知栏网络图标
		template.setLogoUrl("");
		// 设置通知是否响铃，震动，或者可清除
		template.setIsRing(true);
		template.setIsVibrate(true);
		template.setIsClearable(true);
		// 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
		template.setTransmissionType(2);
		template.setTransmissionContent(msg.getTransmissionContent());
		// 设置定时展示时间
		// template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
		return template;
	}

	/**
	 * 点击通知打开网页模板
	 * @param msg 消息
	 * @return
	 */
	public static LinkTemplate linkTemplateDemo(Message msg) {
		LinkTemplate template = new LinkTemplate();
		// 设置APPID与APPKEY
		template.setAppId(appAttr.getAppId());
		template.setAppkey(appAttr.getAppKey());
		// 设置通知栏标题与内容
		template.setTitle(msg.getTitle());
		template.setText(msg.getTitle());
		// 配置通知栏图标
		template.setLogo(msg.getLogo());
		// 配置通知栏网络图标
		template.setLogoUrl("");
		// 设置通知是否响铃，震动，或者可清除
		template.setIsRing(true);
		template.setIsVibrate(true);
		template.setIsClearable(true);
		// 设置打开的网址地址
		template.setUrl(msg.getUrl());
				// 设置定时展示时间
				// template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
		return template;
	}


}
