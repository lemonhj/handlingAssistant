package com.septinary.common.web.general.interceptor.springmvc;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;

import com.septinary.common.core.annotation.AccessPermissionRestriction;
import com.septinary.common.core.type.AccessRestrictionLevel;
import com.septinary.common.core.type.AccessSecurityRule;
import com.septinary.common.core.type.AccessTraceType;
import com.septinary.common.core.type.RunningMode;
import com.septinary.common.core.util.Fielderable;
import com.septinary.common.general.util.GsonUtil;
import com.septinary.common.sys.op.ITraceTrail;
import com.septinary.common.sys.op.Operation;
import com.septinary.common.sys.op.SimpleTrail;
import com.septinary.common.sys.role.Member;
import com.septinary.common.util.ArrayUtil;
import com.septinary.common.util.DateTimeUtil;
import com.septinary.common.util.NumericUtil;
import com.septinary.common.util.StringUtil;
import com.septinary.common.util.ViewJSON;
import com.septinary.common.web.general.runtime.trace.Current;
import com.septinary.common.web.general.service.SessionService;
import com.septinary.common.web.util.HTTPUtil;

public class AccessPermissionRestrictionInterceptor extends CommonSpringMVCInterceptor {

	@Autowired
	private SessionService sysSessionService;
	
	private RunningMode mode = RunningMode.UNDEFINED;

	public RunningMode getMode() {
		return mode;
	}

	public void setMode(RunningMode mode) {
		this.mode = mode;
	}
	public void setMode(String mode) {
		this.setMode( RunningMode.valueOf(mode) );
	}

	@Override
	public boolean preDo(ServletRequest req, ServletResponse res, Object handler) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// return super.preDo(request, response, object);

		//只能处理HTTP请求
		if (!(req instanceof HttpServletRequest) || !(res instanceof HttpServletResponse)) {
			ServletException se = new ServletException(this.getClass().getName()+" just supports HTTP requests");
			logger.exception("Interceptor could not support non-HTTP requests.", se);
			throw se;
		}
		
		HttpServletRequest request = (HttpServletRequest) req;
	    HttpServletResponse response = (HttpServletResponse) res;
	    
	    //当前运行信息
	    Current current = new Current();
		//current.setApplication(application);
		current.setServlet(request.getServletContext());
		current.setSession(request.getSession());
		current.setRequest(request);
		current.setResponse(response);
		current.setOperation(new Operation());
		//current.setPage(page);
		ITraceTrail<BigInteger> trail = new SimpleTrail();
		current.getOperation().setTrail(trail);
		request.setAttribute(configger.getString("TRACE_CURRENT_INFO"), current);
		
		//传入错误的处理器
		if( !(handler instanceof HandlerMethod) ) {
			logger.verbose("The inputting handler is not a instance of HandlerMethod, so abort processing!");
			return false;
		}
		
		//当前认证信息
		String key = configger.get("HTTP_HEADER_PREFIX") + configger.get("HTTP_CURRENT_USER");
		String token = request.getHeader(key);
		if(StringUtil.Invalid(token)) token = request.getParameter(key);
		// TODO ... 解密数据
		Member user = (Member)GsonUtil.JsonTo(token, Member.class);
		if(null!=user) {
			request.setAttribute(configger.get("HTTP_CURRENT_USER"), user);
			current.getOperation().setUser(user);
		}
	    
	    //运行模式：DEBUG, TEST, RELEASE
		if( RunningMode.DEBUG == this.mode ) {
			logger.verbose("RunningMode.DEBUG just going ...");
			return true;
		}
		
		HandlerMethod method = (HandlerMethod)handler;
		AccessRestrictionLevel level = null;	//访问限制级别
		AccessSecurityRule rules[] = null;		//访问安全规则
		String module = null;					//访问模块
		String operate = null;					//访问操作
		AccessTraceType traces[] = null;		//访问跟踪类型
		AccessPermissionRestriction access = method.getMethodAnnotation(AccessPermissionRestriction.class);
		if( null==access ) {
			level = AccessRestrictionLevel.UNDEFINED;
			rules = new AccessSecurityRule[]{AccessSecurityRule.NONE};
			module = method.getBean().getClass().getSimpleName();
			operate = method.getClass().getSimpleName();
			traces = new AccessTraceType[]{AccessTraceType.NONE};
			logger.verbose("This action method has no definition of annotation for AccessPermissionRestriction, so made it to be default.");
		} else {
			level = access.value();
			rules = access.rules();
			module = access.module();
			operate = access.operate();
			traces = access.trace();
		}
		
		logger.verbose("This access for AccessPermissionRestriction:\r\n\t"
			+" level="+level
			+" rules="+StringUtil.Implode(rules, ",")
			+" module="+module
			+" operate="+operate
			+" trace="+StringUtil.Implode(traces, ",")
		);
		
		//公开接口，直接放行
		if( AccessRestrictionLevel.PUBLIC==level || AccessRestrictionLevel.UNDEFINED==level ) {
			logger.verbose("Request：\r\n"+request.getRequestURL()+" \r\nmapping \r\n"+method+" of AccessRestrictionLevel.PUBLIC ...");
			return true;
		}
		
		//安全要求，检查安全性：签名，次数，频率，...
		if( AccessRestrictionLevel.SECURITY.ordinal() <= level.ordinal() ) {
			// TODO ... 安全检查 ...
			/*
			String key = configger.get("HTTP_HEADER_PREFIX")+configger.get("HTTP_SIGNATURE_TAG");
			String signature = request.getHeader(key);
			if(StringUtil.Invalid(signature)) {
				logger.verbose("Request：\r\n"+request.getRequestURL()+" \r\nmapping \r\n"+method.toString()+" of AccessRestrictionLevel.SECURITY failed: had not found the access signature!");
				HTTPUtil.Response(response, new ViewJSON<Object>("403", ViewJSON.DEFAULT_HINT_KEY, "SECURITY check failed: had not found the access signature!"));
				return false;
			}
			*/
			// TODO ... 验证签名
		}
		
		//会话要求，检查是否第一次请求？
		if( AccessRestrictionLevel.SESSION.ordinal()<=level.ordinal() ) {
			/*
			if(request.getSession().isNew()) {
				logger.verbose("Request：\r\n"+request.getRequestURL()+" \r\nmapping \r\n"+method.toString()+" of AccessRestrictionLevel.SESSION failed!");
				HTTPUtil.Response(response, new ViewJSON<Object>("403", ViewJSON.DEFAULT_HINT_KEY, "SESSION check failed"));
				return false;
			}
			*/
		}
		
		//登录要求，检查是否已经登录？
		if( AccessRestrictionLevel.LOGIN.ordinal()<=level.ordinal() ) {
			// TODO ... 检验请求来源: APP 还是 H5 ?
			if(StringUtil.Invalid(token)) {
				logger.verbose("Request：\r\n"+request.getRequestURL()+" \r\nmapping \r\n"+method.toString()+" of AccessRestrictionLevel.LOGIN failed: had not found the logon token!");
				HTTPUtil.Response(response, new ViewJSON<Object>("401", ViewJSON.DEFAULT_HINT_KEY, "LOGIN check failed: had not found the logon token!"));
				return false;
			}
			// TODO ... 解密数据
			if(null==user) {
				logger.verbose("Request：\r\n"+request.getRequestURL()+" \r\nmapping \r\n"+method.toString()+" of AccessRestrictionLevel.LOGIN failed: logon token parse error!");
				HTTPUtil.Response(response, new ViewJSON<Object>("401", ViewJSON.DEFAULT_HINT_KEY, "LOGIN check failed: logon token parse error!"));
				return false;
			}
		}
		
		//权限要求，检查是否具有权限？
		if( AccessRestrictionLevel.PERMISSION.ordinal()<=level.ordinal() ) {
			// TODO ... 权限检查 ...
			/*
			logger.verbose("Request：\r\n"+req.getRequestURL()+" \r\nmapping \r\n"+method.toString()+" of AccessRestrictionLevel.PERMISSION failed!");
			HTTPUtil.Response(res, new ViewJSON<Object>("406", "Not Acceptable", "PERMISSION chekc failed"));
			return false;
			*/
		}
		
		//是否要跟踪轨迹
		if(ArrayUtil.ContainsElement(traces, AccessTraceType.TRAIL)) {
			Fielderable fielder = Fielderable.GetFielder();
			String prefix = configger.get("HTTP_HEADER_PREFIX");
			//绑定访问跟踪信息
			trail.setUserClassname(user.getClass().getSimpleName());
			trail.setUserId(StringUtil.String(user.getId()));
			trail.setUserName(user.getUsername());
			trail.setActtime(DateTimeUtil.NowDate());
			trail.setObjectClassname(null);
			trail.setObjectId(null);
			trail.setObjectName(null);
			trail.setActid(null);
			trail.setActtag(operate);
			trail.setActsubject(null);
			trail.setActextra(null);
			trail.setSessionid(request.getSession(false).getId());
			trail.setToken(request.getHeader(prefix+"token"));
			if(null!=request.getHeader(prefix+"terminal")) trail.setTerminal(fielder.value("VisitorTerminalType."+request.getHeader(prefix+"terminal")).getValue(Integer.class));
			if(null!=request.getHeader(prefix+"comefrom")) trail.setComefrom(fielder.value("VisitorComeFrom."+request.getHeader(prefix+"comefrom")).getValue(Integer.class));
			if(null!=request.getHeader(prefix+"network")) trail.setNetwork(fielder.value("TerminalNetworkType."+request.getHeader(prefix+"network")).getValue(Integer.class));
			if(null!=request.getHeader(prefix+"operators")) trail.setOperators(fielder.value("BroadbandOperatorsType."+request.getHeader(prefix+"operators")).getValue(Integer.class));
			trail.setHost(request.getHeader(prefix+"host"));
			trail.setIp(HTTPUtil.ParseIP(request));
			trail.setDevice(request.getHeader(prefix+"device"));
			trail.setImei(request.getHeader(prefix+"imei"));
			trail.setOs(request.getHeader(prefix+"OS"));
			trail.setClient(request.getHeader(prefix+"client"));
			trail.setClientid(request.getHeader(prefix+"clientid"));
			trail.setUseragent(request.getHeader("User-Agent"));
			trail.setAppname(request.getHeader(prefix+"appname"));
			trail.setVersion(request.getHeader(prefix+"version"));
			trail.setX(NumericUtil.ToFloat(request.getHeader(prefix+"X")));
			trail.setY(NumericUtil.ToFloat(request.getHeader(prefix+"Y")));
			trail.setZ(NumericUtil.ToFloat(request.getHeader(prefix+"Z")));
			trail.setArea(request.getHeader(prefix+"area"));
			trail.setAddress(request.getHeader(prefix+"address"));
			current.getOperation().setTrail(trail);
		}
		
		return super.preDo(request, response, handler);
	}

	@Override
	public void postDo(ServletRequest request, ServletResponse response, Object handler) throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.postDo(request, response, handler);
	}

}
