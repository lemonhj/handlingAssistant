package com.septinary.common.web.general.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 跨域请求处理过滤器
 * @Filename: com.septinary.common.web.general.filter.CORSRequestFilter.java of the project [com.septinary.common.web.general]
 *     @Type: CORSRequestFilter
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年9月10日 下午3:20:10
 *  
 *  跨域请求解决方案：
 *  （1）JSONP
 *  使用jsonp来进行跨域是一种比较常见的方式，但是在接口已经写好的情况下，无论是服务端还是调用端都需要进行改造且要兼容原来的接口，工作量偏大，于是我们考虑其他方法。
 *  （2）CORS协议
 *  按照参考资料的说法：每一个页面需要返回一个名为‘Access-Control-Allow-Origin’的HTTP头来允许外域的站点访问。你可以仅仅暴露有限的资源和有限的外域站点访问。
 *  在COR模式中，访问控制的职责可以放到页面开发者的手中，而不是服务器管理员。当然页面开发者需要写专门的处理代码来允许被外域访问。 我们可以理解为：
 *  	如果一个请求需要允许跨域访问，则需要在http头中设置Access-Control-Allow-Origin来决定需要允许哪些站点来访问。如假设需要允许www.foo.com这个站点的请
 *  求跨域，则可以设置：Access-Control-Allow-Origin:http://www.foo.com。或者Access-Control-Allow-Origin: * 。 
 *  CORS作为HTML5的一部分，在大部分现代浏览器中有所支持。
 *  
 *  CORS具有以下常见的header：
 *  Access-Control-Allow-Origin: http://foo.org
 *  Access-Control-Max-Age: 3628800
 *  Access-Control-Allow-Methods: GET，PUT, DELETE
 *  Access-Control-Allow-Headers: content-type
 *  说明：
 *  "Access-Control-Allow-Origin"表明它允许"http://foo.org"发起跨域请求
 *  "Access-Control-Max-Age"表明在3628800秒内，不需要再发送预检验请求，可以缓存该结果
 *  "Access-Control-Allow-Methods"表明它允许GET、PUT、DELETE的外域请求
 *  "Access-Control-Allow-Headers"表明它允许跨域请求包含content-type头
 *  
 *  CORS基本流程：
 *  首先发出预检验（Preflight）请求，它先向资源服务器发出一个OPTIONS方法、包含“Origin”头的请求。该回复可以控制COR请求的方法，HTTP头以及验证等信息。只有该请求
 *  获得允许以后，才会发起真实的外域请求。
 *  Spring MVC支持CORS
 *  Response to preflight request doesn't pass access control check: No 'Access-Control-Allow-Origin' header is present on the requested resource.
 *  Origin 'null' is therefore not allowed access. The response had HTTP status code 403.
 *  跟踪SpringMVC代码发现，在org.springframework.web.servlet.DispatcherServlet.doDispatch中会根据根据request来获取HandlerExecutionChain，SpringMVC
 *  在获取常规的处理器后会检查是否为跨域请求，如果是则替换原有的实例。
@Override public final HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
	Object handler = getHandlerInternal(request);
	if (handler == null) {
		handler = getDefaultHandler();
	}
	if (handler == null) {
		return null;
	}
	// Bean name or resolved handler?
	if (handler instanceof String) {
		String handlerName = (String) handler;
		handler = getApplicationContext().getBean(handlerName);
	}

	HandlerExecutionChain executionChain = getHandlerExecutionChain(handler, request);
	if (CorsUtils.isCorsRequest(request)) {
		CorsConfiguration globalConfig = this.corsConfigSource.getCorsConfiguration(request);
		CorsConfiguration handlerConfig = getCorsConfiguration(handler, request);
		CorsConfiguration config = (globalConfig != null ? globalConfig.combine(handlerConfig) : handlerConfig);
		executionChain = getCorsHandlerExecutionChain(request, executionChain, config);
	}
	return executionChain;
}
 *  检查的方法也很简单，即检查请求头中是否有origin字段
public static boolean isCorsRequest(HttpServletRequest request) {
	return (request.getHeader(HttpHeaders.ORIGIN) != null);
}
 *  请求接着会交由 HttpRequestHandlerAdapter.handle来处理，根据handle不同，处理不同的逻辑。前面根据请求头判断是一个跨域请求，获取到的Handler为PreFlightHandler,
 *  其实现为：
@Override public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
	corsProcessor.processRequest(this.config, request, response);
}
 *  继续跟进
@Override public boolean processRequest(CorsConfiguration config, HttpServletRequest request, HttpServletResponse response) throws IOException {
	if (!CorsUtils.isCorsRequest(request)) {
		return true;
	}

	ServletServerHttpResponse serverResponse = new ServletServerHttpResponse(response);
	ServletServerHttpRequest serverRequest = new ServletServerHttpRequest(request);

	if (WebUtils.isSameOrigin(serverRequest)) {
		logger.debug("Skip CORS processing, request is a same-origin one");
		return true;
	}
	if (responseHasCors(serverResponse)) {
		logger.debug("Skip CORS processing, response already contains \"Access-Control-Allow-Origin\" header");
		return true;
	}

	boolean preFlightRequest = CorsUtils.isPreFlightRequest(request);
	if (config == null) {
		if (preFlightRequest) {
			rejectRequest(serverResponse);
			return false;
		}
		else {
			return true;
		}
	}

	return handleInternal(serverRequest, serverResponse, config, preFlightRequest);
}
 *  此方法首先会检查是否为跨域请求，如果不是则直接返回，接着检查是否同一个域下，或者response头里是否具有Access-Control-Allow-Origin字段或者request里是否具
 *  有Access-Control-Request-Method。如果满足判断条件，则拒绝这个请求。 由此我们知道，可以通过在检查之前设置response的Access-Control-Allow-Origin头来通
 *  过检查。我们在拦截器的preHandle的处理。加入如下代码:
response.setHeader("Access-Control-Allow-Origin", "*");
 *  此时浏览器中OPTIONS请求返回200。但是依然报错：
 *  Request header field Content-Type is not allowed by Access-Control-Allow-Headers in preflight response.
 *  我们注意到：在request的请求头里有Access-Control-Request-Headers:accept, content-type，但是这个请求头的中没有，此时浏览器没有据需发送请求。尝试在response中加入:
response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
 *  执行成功：Object {userId: 123, userName: “adminUpdate-wangdachui”}。
 *  至此：我们通过分析原理使SpringMVC实现跨域，原有实现以及客户端代码不需要任何改动。
 */
public class CORSRequestFilter implements javax.servlet.Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		FilterChain chainNextFilter = arg2;
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.addHeader("Access-Control-Allow-Methods", "*");
		response.addHeader("Access-Control-Max-Age", "3628800");
		
		chainNextFilter.doFilter(request, response);
	}
}
