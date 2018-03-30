package com.beigebigdata.bdCourt.ca.admin.common.tag;

import com.beigebigdata.bdCourt.ca.admin.common.utils.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Enumeration;


/**
 * 分页标签
 */
public class PageSplitTag extends TagSupport {

	private String url = ""; // 页面指向地址

	private String pageNo = ""; // 当前页面，字符串型，由外面传入

	private String paramsStr = ""; // 组装后的参数字符串

	private int totalPages = 1; // 总页面数

	private int count = 0; // 总记录数

	private int intPageNo = 1; // 当前页面
	
	private String type ;

	private int pageSize = 20; // 每一页面显示的最大记录数

	public PageSplitTag() {
	}

	public int doStartTag() throws JspException {
		if (url == null) {
			url = "";
		}
		url = url.trim();

		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		Enumeration en = request.getParameterNames();
		StringBuffer param = new StringBuffer();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			if ("pageNo".equals(key) || key.toLowerCase().startsWith("submit")||"pageSize".equals(key))
				continue;
			String value = Utils.trim(request.getParameter(key));
			if (value.equals(""))
				continue;
			param.append("&" + key + "=" + Utils.encodeStr(value));
		}
		paramsStr = param.toString();

		try {
			intPageNo = Utils.parseInt(pageNo, 1);
			if(intPageNo<1){
				intPageNo = 1;
			}
		} catch (Exception e) {
		}
		if (count % pageSize > 0) {
			totalPages = count / pageSize + 1;
		} else {
			totalPages = count / pageSize;
		}
		if (intPageNo > totalPages) {
			intPageNo = totalPages;
		}
		return (SKIP_BODY);
	}

	public int doEndTag() throws JspException {
		StringBuffer reStr = new StringBuffer();
		reStr.append("<div class='btn-group' role='group' aria-label='...'>");
		
		if (totalPages < 2) {
			reStr.append("<button type='button' class='btn btn-default'>");
			reStr.append(""+intPageNo+"/"+totalPages+"</button></div>");
			reStr.append("<div class='btn-group' role='group' aria-label='...'>");
			reStr.append("<button type='button' class='btn btn-default' onclick='nextPage(1,"+pageSize+")'>首页</button>");
			reStr.append("<button type='button' class='btn btn-default' onclick='nextPage("+(intPageNo-1)+","+pageSize+")'>上一页</button>");
			reStr.append("<button type='button' class='btn btn-default' onclick='nextPage("+(intPageNo+1)+","+pageSize+")'>下一页</button></div>");
			reStr.append("<button type='button' class='btn btn-default' onclick='nextPage("+totalPages+","+pageSize+")'>尾页</button>");
		} else {
			if (intPageNo < 2) {
				reStr.append("<button type='button' class='btn btn-default'>");
				reStr.append(""+intPageNo+"/"+totalPages+"</button></div>");
				reStr.append("<div class='btn-group' role='group' aria-label='...'>");
				reStr.append("<button type='button' class='btn btn-default' onclick='nextPage("+(intPageNo+1)+","+pageSize+")'>下一页</button></div>");
				reStr.append("<button type='button' class='btn btn-default' onclick='nextPage("+totalPages+","+pageSize+")'>尾页</button>");
			} else if (intPageNo == totalPages) {
				reStr.append("<button type='button' class='btn btn-default'>");
				reStr.append(""+intPageNo+"/"+totalPages+"</button></div>");
				reStr.append("<div class='btn-group' role='group' aria-label='...'>");
				reStr.append("<button type='button' class='btn btn-default' onclick='nextPage(1,"+pageSize+")'>首页</button>");
				reStr.append("<button type='button' class='btn btn-default' onclick='nextPage("+(intPageNo-1)+","+pageSize+")'>上一页</button>");
			} else {
				reStr.append("<button type='button' class='btn btn-default'>");
				reStr.append(""+intPageNo+"/"+totalPages+"</button></div>");
				reStr.append("<div class='btn-group' role='group' aria-label='...'>");
				reStr.append("<button type='button' class='btn btn-default' onclick='nextPage(1,"+pageSize+")'>首页</button>");
				reStr.append("<button type='button' class='btn btn-default' onclick='nextPage("+(intPageNo-1)+","+pageSize+")'>上一页</button>");
				reStr.append("<button type='button' class='btn btn-default' onclick='nextPage("+(intPageNo+1)+","+pageSize+")'>下一页</button></div>");
				reStr.append("<button type='button' class='btn btn-default' onclick='nextPage("+totalPages+","+pageSize+")'>尾页</button>");
			}
		}
		
		JspWriter writer = pageContext.getOut();
		try {
			writer.println(new String(reStr.toString().getBytes()));
		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}
		return (EVAL_PAGE);
	}

	private String getUrl(int pageNo, String name) {
		return "<li class='"+name+"'><a href='" + dealUrl(url, pageNo)
				+ "'></a></li>";
	}

	private String dealUrl(String url, int pageNo) {
		return url + "?pageNo=" + pageNo +"&pageSize="+pageSize+ paramsStr;
	}

	private String addParams(String params) {
		if (params == null || params.equals("")) {
			return "";
		}
		return "?" + params.substring(1);
	}

	public void release() {
		super.release();
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
