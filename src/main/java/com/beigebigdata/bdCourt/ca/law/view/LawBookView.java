package com.beigebigdata.bdCourt.ca.law.view;

import java.sql.Date;

public class LawBookView {
	
	private Integer id;		//法律典籍ID
	
	private Integer no;		//法律典籍编号
	
	private Integer type;	//法律典籍类型：1-国家法律，2-司法解释，3-行政法规（地方法规），4-部门规章（指引文件）

	private String area;	//法律典籍适用地区
	
	private String name;	//法律典籍名称
	
	private Date date;		//法律典籍颁布日期

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
