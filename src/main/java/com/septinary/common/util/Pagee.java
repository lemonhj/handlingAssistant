package com.septinary.common.util;

/**
 * 分页参数 <FromType>from + size + page (无设置逻辑)
 * @Filename: com.septinary.common.util.Pagee.java of the project [com.septinary.common]
 *     @Type: Pagee
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年8月9日 下午5:59:04
 *  @param <FromType>
 */
@SuppressWarnings("serial")
public class Pagee<FromType> extends Page {

	//起始标识
	private FromType from = null;
	
	public Pagee(){}
	public Pagee(FromType from, int size){
		super();
		super.setSize(size);
		this.from = from;
	}
	public Pagee(FromType from, int page, int size){
		super(page, size);
		this.from = from;
	}
	public Pagee(Page page) {
		super(page);
	}
	public Pagee(Pagee<FromType> pagee) {
		super(pagee);
		this.from = pagee.getFrom();
	}

	public FromType getFrom() {
		return from;
	}

	public Pagee<FromType> setFrom(FromType from) {
		this.from = from;
		return this;
	}
}
