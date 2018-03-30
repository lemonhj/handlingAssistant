package com.septinary.common.util;

import java.util.List;

/**
 * 分页器 分页参数 <FromType>from + size + page + total + count + row[]
 * @Filename: com.septinary.common.util.Pager.java of the project [com.septinary.common]
 *     @Type: Pager
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年1月26日下午4:48:20
 * 
 * @param <T> 列表数据类型
 */
@SuppressWarnings("serial")
public class Pager<T> extends Pageee {

	//数据列表
	private List<T> rows = null;

	public Pager(){}
	public Pager(int page, int size){
		super(page, size);
	}
	public Pager(int page, int size, List<T> rows){
		super(page, size);
		this.setRows(rows);
	}
	public Pager(int page, int size, int total){
		super(page, size, total);
	}
	public Pager(int page, int size, int total, List<T> rows){
		super(page, size, total);
		this.setRows(rows);
	}
	public Pager(int page, int size, Object from){
		super(page, size, from);
	}
	public Pager(int page, int size, Object from, List<T> rows){
		super(page, size, from);
		this.setRows(rows);
	}
	public Pager(Pagee<String> pageee){
		super(pageee);
	}
	public Pager(Page pagee){
		super(pagee);
	}
	public Pager(Pageee page){
		super(page);
	}
	public Pager(Pager<T> pager){
		super(pager);
		this.rows = pager.rows;
	}
	
	public List<T> getRows() {
		return rows;
	}

	public Pager<T> setRows(List<T> rows) {
		this.rows = rows;
		return this;
	}

	@Override
	public String toString() {
		return this.getSize() + "," +this.getPage() + "," + this.getFrom();
	}
}
