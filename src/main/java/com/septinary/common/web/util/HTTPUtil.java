package com.septinary.common.web.util;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.septinary.common.general.util.TimestampGsonAdapter;
import com.septinary.common.util.ICallbackable;
import com.septinary.common.util.StringUtil;

/**
 * HTTP解析
 * @Filename: com.septinary.common.web.util.HTTPUtil.java of the project [com.septinary.common.web]
 *     @Type: HTTPUtil
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年2月17日下午11:10:23
 *
 */
public class HTTPUtil {

	public static String ParseIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        
        return ip;
    }
	
	public static void DumpParameters(HttpServletRequest request, ICallbackable echo) {
		Map<String,String> map = new HashMap<String,String>();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }
        Set<Map.Entry<String, String>> set = map.entrySet();
        for (Map.Entry<String,String> entry : set) echo.callback(entry.getKey() + ":" + entry.getValue());
	}
	
	public static void DumpAttributes(HttpServletResponse response, ICallbackable echo) {
		
	}
	
	public static void Dump(HttpServletRequest request, ICallbackable echo) {
		
	}
	
	public static void Dump(HttpServletResponse response, ICallbackable echo) {
		
	}
	
	@SuppressWarnings("deprecation")
	public static String ParseRequest(HttpServletRequest request) {
		try {
			//头域部分:
			StringBuilder header = new StringBuilder();
			Enumeration<String> headers = request.getHeaderNames();
		    while(headers.hasMoreElements()) {
		    	String name = headers.nextElement().toString();
		    	Enumeration<String> values = request.getHeaders(name);
		    	while(values.hasMoreElements()) {
		    		header.append("\r\n\t ");
		    		header.append(name + "=" + values.nextElement());
		    	}
		    }
		    //Cookie部分:
		    StringBuilder cookie = new StringBuilder();
		    Cookie[] cookies = request.getCookies();
		    for(int i=0; null!=cookies && i<cookies.length; i++) {
		    	cookie.append("\r\n\t");
		    	cookie.append(" Name=" + cookies[i].getName());
		    	cookie.append(" Value=\"" + cookies[i].getValue() + "\"");
		    	cookie.append(" Domain=" + cookies[i].getDomain());
		    	cookie.append(" Path=\"" + cookies[i].getPath() + "\"");
		    	cookie.append(" MaxAge=" + cookies[i].getMaxAge());
		    	cookie.append(" Version=" + cookies[i].getVersion());
		    	cookie.append(" Secure=" + cookies[i].getSecure());
		    	cookie.append(" Comment=\"" + cookies[i].getComment() + "\"");
		    	cookie.append(" HttpOnly=" + cookies[i].isHttpOnly());
		    }
		    //定制部分:
		    StringBuilder locale = new StringBuilder();
		    Enumeration<Locale> locales = request.getLocales();
		    while(locales.hasMoreElements()) {
		    	Locale item = locales.nextElement();
		    	locale.append("\r\n\t");
		    	locale.append(" Country=" + item.getCountry());
		    	locale.append(" DisplayCountry=" + item.getDisplayCountry());
		    	locale.append(" ISO3Country=" + item.getISO3Country());
		    	locale.append(" Language=" + item.getLanguage());
		    	locale.append(" DisplayLanguage=" + item.getDisplayLanguage());
		    	locale.append(" ISO3Language=" + item.getISO3Language());
		    	locale.append(" DisplayName=" + item.getDisplayName());
		    	locale.append(" Variant=" + item.getVariant());
		    	locale.append(" DisplayVariant=" + item.getDisplayVariant());
		    	locale.append(" Script=" + item.getScript());
		    	locale.append(" DisplayScript=" + item.getDisplayScript());
		    }
		    //参数部分:
		    StringBuilder param = new StringBuilder();
		    Enumeration<String> params = request.getParameterNames();
		    while(params.hasMoreElements()) {
		    	String name = params.nextElement().toString();
		    	String value = request.getParameter(name);
		    	param.append("\r\n\t ");
		    	param.append(name + "=\"" + value + "\"");
		    }
		    //属性部分:
		    StringBuilder attr = new StringBuilder();
		    Enumeration<String> attrs = request.getAttributeNames();
		    while(attrs.hasMoreElements()) {
		    	String name = attrs.nextElement().toString();
		    	String value = request.getAttribute(name).toString();
		    	attr.append("\r\n\t ");
		    	attr.append(name + "=" + value);
		    }

		    //组装结果:
			StringBuilder result = new StringBuilder();
			result.append("\r\n Method: " + request.getMethod());
			result.append("\r\n UserAgent: " + request.getHeader("user-agent"));
			result.append("\r\n ServletPath: " + request.getServletPath());
			result.append("\r\n ServerName: " + request.getServerName() + " ServerPort: " + request.getServerPort());
			result.append("\r\n RemoteHost: " + request.getRemoteHost() + " RemoteAddr: " + request.getRemoteAddr() + " RemotePort: " + request.getRemotePort() + " RemoteUser: " + request.getRemoteUser());
			result.append("\r\n ClientIP: " + ParseIP(request));
			result.append("\r\n Protocol: " + request.getProtocol());
			result.append("\r\n AuthType: " + request.getAuthType());
			result.append("\r\n CharacterEncoding: " + request.getCharacterEncoding());
			result.append("\r\n ContentType: " + request.getContentType() + " ContentLength: " + request.getContentLength() + " ContentLengthLong: " + /*request.getContentLengthLong()*/"java.lang.NoSuchMethodError: javax.servlet.http.HttpServletRequest.getContentLengthLong()");
			result.append("\r\n ContextPath: " + request.getContextPath());
			result.append("\r\n LocalName: " + request.getLocalName() + " LocalAddr: " + request.getLocalAddr() + " LocalPort: " + request.getLocalPort());
			result.append("\r\n Locale:");
			result.append(	"\r\n\t Country=" + request.getLocale().getCountry());
			result.append(	"\r\n\t DisplayCountry=" + request.getLocale().getDisplayCountry());
			result.append(	"\r\n\t ISO3Country=" + request.getLocale().getISO3Country());
			result.append(	"\r\n\t Language=" + request.getLocale().getLanguage());
			result.append(	"\r\n\t DisplayLanguage=" + request.getLocale().getDisplayLanguage());
			result.append(	"\r\n\t ISO3Language=" + request.getLocale().getISO3Language());
			result.append(	"\r\n\t DisplayName=" + request.getLocale().getDisplayName());
			result.append(	"\r\n\t Variant=" + request.getLocale().getVariant());
			result.append(	"\r\n\t DisplayVariant=" + request.getLocale().getDisplayVariant());
			result.append(	"\r\n\t Script=" + request.getLocale().getScript());
			result.append(	"\r\n\t DisplayScript=" + request.getLocale().getDisplayScript());
			result.append("\r\n PathInfo: " + request.getPathInfo() + " PathTranslated: " + request.getPathTranslated());
			result.append("\r\n QueryString: " + request.getQueryString());
			result.append("\r\n RequestedSessionId: " + request.getRequestedSessionId());
			result.append("\r\n RequestedSessionIdFrom: " + (!request.isRequestedSessionIdValid()?"Invalid!!!":(request.isRequestedSessionIdFromCookie()?"FromCookie":request.isRequestedSessionIdFromURL()?"FromURL":request.isRequestedSessionIdFromUrl()?"FromUrl":"Unknown!!!")));
			result.append("\r\n Session: ");
			result.append(	"\r\n\t Id=" + request.getSession().getId());
			result.append(	"\r\n\t IsNew=" + request.getSession().isNew());
			result.append(	"\r\n\t CreationTime=" + request.getSession().getCreationTime());
			result.append(	"\r\n\t LastAccessedTime=" + request.getSession().getLastAccessedTime());
			result.append(	"\r\n\t MaxInactiveInterval=" + request.getSession().getMaxInactiveInterval());
			result.append("\r\n RequestURI: " + request.getRequestURI());
			result.append("\r\n RequestURL: " + request.getRequestURL());
			result.append("\r\n Scheme: " + request.getScheme());
			result.append("\r\n DispatcherType: " + (null==request.getDispatcherType()?"null":request.getDispatcherType().getClass().getName()+"."+request.getDispatcherType().name()));
			result.append("\r\n IsAsyncStarted: " + request.isAsyncStarted());
			result.append("\r\n IsAsyncSupported: " + request.isAsyncSupported());
			result.append("\r\n IsSecure: " + request.isSecure());
			result.append("\r\n UserPrincipal: " + (null==request.getUserPrincipal() ?"null":request.getUserPrincipal().getName()));
			result.append("\r\n Headers: ").append(header);
			result.append("\r\n Cookies: ").append(cookie);
			result.append("\r\n Locales: ").append(locale);
			result.append("\r\n Parameters: ").append(param);
			result.append("\r\n Attributes: ").append(attr);
				
			return result.toString();
		} catch (Exception e) {
			System.out.println("解析HTTP请求头时发生异常...");
			e.printStackTrace();
			return null;
		}
	}
	
	public static String ParseResponse(HttpServletResponse response) {
		
		return null;
	}
	
	static public void Response(HttpServletResponse response, Object data, String callback, String timeformat) {
		boolean cors = !StringUtil.Invalid(callback);	//跨域请求
		if(cors) {
			response.setHeader("Access-Control-Allow-Origin", "*");
	        response.addHeader("Access-Control-Allow-Methods","*");
	        response.addHeader("Access-Control-Max-Age","100");
	        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
	        response.addHeader("Access-Control-Allow-Credentials","false");
		}
		response.setContentType("application/json; charset=utf-8");
		
		Gson gson = new GsonBuilder().registerTypeAdapter(Timestamp.class, new TimestampGsonAdapter(timeformat)).setDateFormat(timeformat).create();
		
		try {
			response.getWriter().write((cors?(callback+"("):"") + gson.toJson(data) + (cors?")":""));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static public void Response(HttpServletResponse response, Object data, String callback) {
		Response(response, data, callback, "yyyy-MM-dd HH:mm:ss");
	}
	
	static public void Response(HttpServletResponse response, Object data) {
		Response(response, data, null);
	}
}
