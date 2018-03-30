package com.septinary.common.web.general.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.septinary.common.sys.role.ISimpleUser;
import com.septinary.common.type.VoidSerialization;
import com.septinary.common.util.Assert;
import com.septinary.common.web.basic.dao.IBaseDao;
import com.septinary.common.web.basic.service.BaseService;
import com.septinary.common.web.general.service.SessionService;

/**
 * 
 * @Filename: com.septinary.common.web.general.service.impl.SessionServiceImpl.java of the project [com.septinary.common.web]
 *     @Type: SessionServiceImpl
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月8日上午12:15:38
 *
 */
@Service("sessionService")
public class SessionServiceImpl extends BaseService<Void,VoidSerialization> implements SessionService {

	static final String CURRENT_MEMBER = SessionServiceImpl.class.getName() + "-member";
	static final String MY_SMARTMEMORY = SessionServiceImpl.class.getName() + "-my";
	static final String MYIDENTIFYCODE = SessionServiceImpl.class.getName() + "-code";
	static final String MY_SUBJECT_KEY = SessionServiceImpl.class.getName() + "-subject";
	
	@Override
	public IBaseDao<Void,VoidSerialization> getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void save(HttpServletRequest request, String name, Object value) {
		
		Assert.NotNull(request, "HttpServletRequest request must not be NULL!");
		
		request.getSession().setAttribute(name, value);
	}

	@Override
	public void rmv(HttpServletRequest request, String name) {
		
		Assert.NotNull(request, "HttpServletRequest request must not be NULL!");
		
		request.getSession().removeAttribute(name);
	}

	@Override
	public Object read(HttpServletRequest request, String name) {
		
		Assert.NotNull(request, "HttpServletRequest request must not be NULL!");
		
		return request.getSession().getAttribute(name);
	}

	@Override
	public void clear(HttpServletRequest request) {
		
		Assert.NotNull(request, "HttpServletRequest request must not be NULL!");
		
		@SuppressWarnings("rawtypes")
		Enumeration e = request.getSession().getAttributeNames();
		while( e.hasMoreElements())   { 
		    String name = (String)e.nextElement();
		    this.rmv(request, name);
		}

	}

	@Override
	public void invalid(HttpServletRequest request) {
		
		Assert.NotNull(request, "HttpServletRequest request must not be NULL!");
		
		request.getSession().invalidate();
	}

	
	@Override
	public HashMapSession get(HttpServletRequest request, String subject) {
		
		Assert.NotNull(request, "HttpServletRequest request must not be NULL!");
		
		String key = MY_SUBJECT_KEY+"."+subject;
		HashMapSession value = (HashMapSession)this.read(request, key);
		
		if(null==value) {
			value = new HashMapSession();
			this.save(request, key, value);
		}
		
		return value;
	}
	
	@Override
	public HashMapSession set(HttpServletRequest request, String subject, Object defaultValue) {
		
		Assert.NotNull(request, "HttpServletRequest request must not be NULL!");
		
		HashMapSession hms = this.get(request, subject);
		hms.add("", defaultValue);
		
		return hms;
	}


	@Override
	public ISimpleUser whoami(HttpServletRequest request) {
		
		Assert.NotNull(request, "HttpServletRequest request must not be NULL!");
		
		ISimpleUser user = (ISimpleUser)request.getAttribute(configger.getString("HTTP_CURRENT_USER"));
		
		return null!=user ? user : (ISimpleUser)this.read(request, CURRENT_MEMBER);
	}

	@Override
	public ISimpleUser whoami(HttpServletRequest request, ISimpleUser user) {
		
		Assert.NotNull(request, "HttpServletRequest request must not be NULL!");
		
		this.save(request, CURRENT_MEMBER, user);
		
		return user;
	}

	
	@SuppressWarnings("serial")
	public class SmartSessionValue implements Serializable {
		private Timestamp active;	//最近活跃时间
		private Integer aging;		//变量时效，单位: 秒
		private Object value;		//变量取值
		public SmartSessionValue() {
			this(null, null);
		}
		public SmartSessionValue(Object value, Integer aging) {
			this.setValue(value);
			this.setAging(aging);
			this.setActive(null);
		}
		public Timestamp getActive() {
			return active;
		}
		public void setActive(Timestamp active) {
			this.active = null==active ? new Timestamp(System.currentTimeMillis()) : active;
		}
		public Integer getAging() {
			return aging;
		}
		public void setAging(Integer aging) {
			this.aging = null==aging||0>aging ? -1 : aging;
		}
		public Object getValue() {
			if( this.getAging()*1000<System.currentTimeMillis()-this.getActive().getTime() ) {
				return null;
			}
			this.setActive(null);
			return value;
		}
		public void setValue(Object value) {
			this.value = value;
			this.setActive(null);
		}
	}
	
	@Override
	public SmartSession getSmartSession(HttpServletRequest request) {
		
		Assert.NotNull(request, "HttpServletRequest request must not be NULL!");
		
		//只要session是同一个，获取到不同的SmartSession实例对象也不要紧，存取的仍然是同一个值
		return new SmartSession(this, request);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getSmartValue(HttpServletRequest request, String name) {
		
		Assert.NotNull(request, "HttpServletRequest request must not be NULL!");
		
		HashMap<String, Object> memory = (HashMap<String, Object>)this.read(request, MY_SMARTMEMORY);
		if(null==memory) memory = new HashMap<String, Object>();
		
		SmartSessionValue ssv = (SmartSessionValue)memory.get(name);
		if(null==ssv) ssv = new SmartSessionValue();
		Object value = ssv.getValue();
		
		//读取到null值，说明此值过期或者无效，不管哪种情况，那就可以清理掉了
		if(null==value) {
			memory.remove(name);
			this.save(request, MY_SMARTMEMORY, memory);
		}
		
		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setSmartValue(HttpServletRequest request, String name, Object value, Integer aging) {
		
		Assert.NotNull(request, "HttpServletRequest request must not be NULL!");

		HashMap<String, Object> memory = (HashMap<String, Object>)this.read(request, MY_SMARTMEMORY);
		if(null==memory) memory = new HashMap<String, Object>();

		SmartSessionValue ssv = (SmartSessionValue)memory.get(name);
		if(null==ssv) {
			ssv = new SmartSessionValue(value, aging);
		} else {
			ssv.setValue(value);
			ssv.setAging(aging);
		}
		memory.put(name, ssv);
		
		this.save(request, MY_SMARTMEMORY, memory);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public String identifyingCode(HttpServletRequest request, String subject) {
		
		Assert.NotNull(request, "HttpServletRequest request must not be NULL!");
		
		HashMap<String, String> memory = (HashMap<String, String>)this.read(request, MYIDENTIFYCODE);
		if(null==memory) memory = new HashMap<String, String>();
		
		//验证码只读取一次，之后便作废了
		String code = memory.get(subject);
		memory.remove(subject);
		this.save(request, MYIDENTIFYCODE, memory);
		
		return code;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String identifyingCode(HttpServletRequest request, String subject, String code) {
		
		Assert.NotNull(request, "HttpServletRequest request must not be NULL!");
		
		HashMap<String, String> memory = (HashMap<String, String>)this.read(request, MYIDENTIFYCODE);
		if(null==memory) memory = new HashMap<String, String>();
		memory.put(subject, code);
		this.save(request, MYIDENTIFYCODE, memory);
		
		return code;
	}

}
