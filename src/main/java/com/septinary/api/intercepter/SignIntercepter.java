package com.septinary.api.intercepter;

import java.util.Arrays;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.septinary.api.constants.CTConstants;
import com.septinary.api.util.ResponseUtils;
import com.septinary.common.core.annotation.Debug;
import com.septinary.common.util.AESUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * 
* @ClassName: SignIntercepter 
* @Description: 签名验证拦截器 
* @author lin.tb lin.tb@septinary.com
* @date 2015年4月26日 上午11:15:20 
*
 */
public class SignIntercepter extends HandlerInterceptorAdapter{
	private boolean debug;  

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object handler) throws Exception {

		//首先判断是否是Debug模式(全局)，如果否则使拦截器失效
		if(this.debug) return true;

		boolean flag = false;
		if( !(handler instanceof HandlerMethod) ) return false;

		HandlerMethod method = (HandlerMethod)handler;
		Debug isDebug = method.getMethodAnnotation(Debug.class);

		//判断是否需要拦截
		if(isDebug !=null && isDebug.value() == true) return true;

		//获取请求中是否带有sign
		String sign = req.getParameter("sign");
		if(!StringUtils.isEmpty(sign)){
//			String deviceId = req.getHeader("APP-IMEI");
		//	if(!StringUtils.isEmpty(deviceId)){
				String token = req.getHeader("APP-TOKEN");
				String params = param2Str(req);
				String url = req.getRequestURL().toString();
				StringBuffer signStr = new StringBuffer();
				if(!StringUtils.isEmpty(token)){
					token = AESUtil.aesEncrypt(token, CTConstants.AESKEY);
					flag = AESUtil.valiSign((signStr.append(url).append("&").append(params).append("&").append(token)).toString(),sign);
				}
		//	}
		}
		if(!flag){
			//返回提示非法的请求sign
			ResponseUtils.setResData(400, "非法数据请求!", null, res);
			return false;
		}else{
			return true;
		}
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
	/**
	 * 
	* @Title: param2Str 
	* @Description: 参数转换成字符串
	* @param @param request
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	private String param2Str(HttpServletRequest req){
		StringBuffer paramBuffer = new StringBuffer();
		Map<String,String[]> paramMap = req.getParameterMap();
		if(!paramMap.isEmpty()){
			Object[] paramArr = paramMap.keySet().toArray();
			Arrays.sort(paramArr);
			for(Object p : paramArr){
				if(!p.toString().equals("sign")){
					paramBuffer.append(p).append("=").append((paramMap.get(p))[0]);
				}
			}
		}
		return paramBuffer.toString();
	}
	

}
