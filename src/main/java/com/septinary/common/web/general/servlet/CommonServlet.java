package com.septinary.common.web.general.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.septinary.common.web.basic.servlet.IServlet;

/**
 * 
 * @Filename: com.septinary.common.web.general.servlet.CommonServlet.java of the project [com.septinary.common.web]
 *     @Type: CommonServlet
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月29日上午12:12:32
 *
 *	Servlet 的主要功能在于交互式地浏览和修改数据，生成动态 Web 内容。这个过程为：
 *		1) 客户端发送请求至服务器端；
 *		2) 服务器将请求信息发送至 Servlet；
 *		3) Servlet 生成响应内容并将其传给服务器。响应内容动态生成，通常取决于客户端的请求；
 *		4) 服务器将响应返回给客户端。
 *	在 Web 应用程序中，一个 Servlet 在一个时刻可能被多个用户同时访问。这时 Web 容器将为每
 *	个用户创建一个线程来执行 Servlet。如果 Servlet 不涉及共享资源的问题，不必关心多线程问题。
 *	但如果 Servlet 需要共享资源，需要保证 Servlet 是线程安全的。
 *		最新版本3.1，为了简化开发流程，Servlet 3.0 引入了注解（annotation），这使得 web
 *	部署描述符 web.xml 不再是必须的选择。
 */
@SuppressWarnings("serial")
public class CommonServlet extends HttpServlet implements IServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected long getLastModified(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return super.getLastModified(req);
	}

	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doHead(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doOptions(req, resp);
	}

	@Override
	protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doTrace(req, resp);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, res);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public String getInitParameter(String name) {
		// TODO Auto-generated method stub
		return super.getInitParameter(name);
	}

	@Override
	public Enumeration<String> getInitParameterNames() {
		// TODO Auto-generated method stub
		return super.getInitParameterNames();
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return super.getServletConfig();
	}

	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return super.getServletContext();
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return super.getServletInfo();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	@Override
	public void log(String msg) {
		// TODO Auto-generated method stub
		super.log(msg);
	}

	@Override
	public void log(String message, Throwable t) {
		// TODO Auto-generated method stub
		super.log(message, t);
	}

	@Override
	public String getServletName() {
		// TODO Auto-generated method stub
		return super.getServletName();
	}

}
