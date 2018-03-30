package com.septinary.common.web.general.filter;

import java.io.IOException;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 实现用户自动登陆
 * @Filename: com.septinary.common.web.general.filter.AutoLoginFilter.java of the project [com.septinary.common.web]
 *     @Type: AutoLoginFilter
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月30日下午5:01:54
 *	思路是这样的：
 *		1、在用户登陆成功后，发送一个名称为user的cookie给客户端，cookie的值为用户名和md5加
 *	密后的密码。
 *		2、编写一个AutoLoginFilter，这个filter检查用户是否带有名称为user的cookie来，如
 *	果有，则调用dao查询cookie的用户名和密码是否和数据库匹配，匹配则向session中存入user对象
 *	（即用户登陆标记），以实现程序完成自动登陆。
 */
//@WebFilter("/autoLoginFilter")
public class AutoLoginFilter extends CommonFilter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		super.init(filterConfig);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public boolean preDo(ServletRequest request, ServletResponse response, Object chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// return super.preDo(request, response);

		//只能处理HTTP请求
		if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
			throw new ServletException(this.getClass().getName()+" just supports HTTP requests");
		}
	    HttpServletRequest req = (HttpServletRequest) request; // 把ServletRequest强转成HttpServletRequest
	    HttpServletResponse res = (HttpServletResponse) response; // 把ServletResponse强转成HttpServletResponse
	     
	    //如果已经登录了，就直接放行
	    if( null!=req.getSession().getAttribute("user") ){
	      return true;
	    }
	     
	    //1.得到用户带过来的authlogin的cookie
	    String value = null;
	    Cookie cookies[] = req.getCookies();
	    for( int i=0; cookies!=null && i<cookies.length; i++ ){
	    	if(cookies[i].getName().equals("autologin")){
	    		value = cookies[i].getValue();
	    	}
	    }
	     
	    //2.得到 cookie中的用户名和密码 
	    if( null!=value ){
	    	/*
	    	String username = value.split("\\.")[0];
	    	String password = value.split("\\.")[1];
	        
	    	//3.调用dao获取用户对应的密码
	    	UserDao dao = new UserDao();
	    	User user = dao.find(username);
	    	String dbpassword = user.getPassword();
	       
	    	//4.检查用户带过来的md5的密码和数据库中的密码是否匹配,如匹配则自动登陆
	    	if(password.equals(WebUtils.md5(dbpassword))){
	    		req.getSession().setAttribute("user", user);
	    	}
	    	*/
	    }
	    
		return super.preDo(req, res, chain);
	}

	@Override
	public void postDo(ServletRequest request, ServletResponse response, Object chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.postDo(request, response, chain);
	}

}
