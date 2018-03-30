package com.septinary.common.web.general.service;

import javax.servlet.http.HttpServletRequest;

import com.septinary.common.sys.role.ISimpleUser;
import com.septinary.common.type.VoidSerialization;
import com.septinary.common.util.smart.HashMapSmart;
import com.septinary.common.web.basic.service.IBaseService;

/**
 * 会话session服务
 * @Filename: com.septinary.common.web.general.service.SessionService.java of the project [com.septinary.common.web]
 *     @Type: SessionService
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月8日上午12:15:22
 *
 */
public interface SessionService extends IBaseService<Void, VoidSerialization> {
	
	// 基本接口========================================================
	/**
	 * 保存session数据
	 @method SessionService: save()
	 @memo TODO
	 @param request
	 @param name
	 @param value void
	 */
	public void save(HttpServletRequest request, String name, Object value);
	/**
	 * 移除session数据
	 @method SessionService: rmv()
	 @memo TODO
	 @param request
	 @param name void
	 */
	public void rmv(HttpServletRequest request, String name);
	/**
	 * 读取session数据
	 @method SessionService: read()
	 @memo TODO
	 @param request
	 @param name
	 @return Object
	 */
	public Object read(HttpServletRequest request, String name);
	/**
	 * 清空session数据
	 @method SessionService: clear()
	 @memo TODO
	 @param request
	 @return void
	 */
	public void clear(HttpServletRequest request);
	/**
	 * 注销session
	 @method SessionService: invalid()
	 @memo TODO
	 @param request
	 @return void
	 */
	public void invalid(HttpServletRequest request);


	// 主题变量========================================================
	@SuppressWarnings("serial")
	public class HashMapSession extends HashMapSmart<String,Object> {
		public Object get() {
			return this.get("");
		}
	}
	/**
	 * 获取主题session数据
	 @method SessionService: get()
	 @memo TODO
	 @param request
	 @param subject
	 @return HashMapSession
	 */
	public HashMapSession get(HttpServletRequest request, String subject);
	public HashMapSession set(HttpServletRequest request, String subject, Object defaultValue);


	// 登录信息========================================================
	/**
	 * 读取当前登录用户
	 @method SessionService: whoami()
	 @memo TODO
	 @param request
	 @return ISimpleUser
	 */
	public ISimpleUser whoami(HttpServletRequest request);
	/**
	 * 设置当前登录用户
	 @method SessionService: whoami()
	 @memo TODO
	 @param request
	 @param user
	 @return ISimpleUser
	 */
	public ISimpleUser whoami(HttpServletRequest request, ISimpleUser user);


	// 智能变量========================================================
	/**
	 * 智能session存储对象
	 */
	public class SmartSession {
		private SessionService sessionService;
		private HttpServletRequest request;
		public SmartSession(SessionService service, HttpServletRequest request) {
			this.sessionService = service;
			this.request = request;
		}
		//获取智能session变量
		public Object get(String name) {
			return this.sessionService.getSmartValue(this.request, name);
		}
		//设置智能session变量
		public SmartSession set(String name, Object value, Integer aging) {
			this.sessionService.setSmartValue(this.request, name, value, aging);
			return this;
		}
		//设置智能session变量（默认时效为-1:永久生效，即永远不会过期）
		public SmartSession set(String name, Object value) {
			this.set(name, value, -1);
			return this;
		}
	}
	/**
	 * 获取智能session对象
	 @method SessionService: getSmartSession()
	 @memo TODO
	 @param request
	 @return SmartSession
	 */
	public SmartSession getSmartSession(HttpServletRequest request);
	/**
	 * 读取智能session数据
	 @method SessionService: getSmartValue()
	 @memo TODO
	 @param request
	 @param name
	 @return Object
	 */
	public Object getSmartValue(HttpServletRequest request, String name);
	/**
	 * 设置智能session数据
	 @method SessionService: setSmartValue()
	 @memo TODO
	 @param request
	 @param name
	 @param value
	 @param aging void
	 */
	public void setSmartValue(HttpServletRequest request, String name, Object value, Integer aging);


	// 验证码========================================================
	/**
	 * 读取主题验证码
	 @method SessionService: identifyingCode()
	 @memo TODO
	 @param request
	 @param subject
	 @return String
	 */
	public String identifyingCode(HttpServletRequest request, String subject);
	/**
	 * 设置主题验证码
	 @method SessionService: identifyingCode()
	 @memo TODO
	 @param request
	 @param subject
	 @param code
	 @return String
	 */
	public String identifyingCode(HttpServletRequest request, String subject, String code);
}
