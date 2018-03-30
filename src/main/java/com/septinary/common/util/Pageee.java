package com.septinary.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.septinary.common.type.Mark;

/**
 * 分页参数 <FromType>from + size + page + total + count + row (必须遵循设置逻辑)
 * 逻辑规则：
 * 1. setTotal()时，如果已经设置了每页size，那么可以计算出总页数count；(反之亦然)
 * 2.  setPage()时，如果已经设置了每页size，那么可以计算出当前所处的行数row；(反之亦然)
 * 3.  setFromObject()时，如果要设置起始分界线主键from值，那么，所设置的页码page将失效，即自动从第1页开始计算；(反之设置page时，from不会失效)
 * @Filename: com.septinary.common.util.Pageee.java of the project [com.septinary.common]
 *     @Type: Page
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016-5-27 18:11:06
 * 
 */
public class Pageee extends Pagee<String> {

	//数据总数
	private Integer total = null;
	
	//计算而得：页码总数
	private Integer count = null;
	
	//计算而得：起始记录 (*不可缺少) ---->相当于“起始行号”
	private Integer row = 0;

	public Pageee(){}
	public Pageee(int page, int size){
		this.setSize(size);
		this.setPage(page);
	}
	public Pageee(int page, int size, int total){
		this.setSize(size);
		this.setPage(page);
		this.setTotal(total);
	}
	public Pageee(int page, int size, Object from){
		this.setSize(size);
		this.setPage(page);
		this.setFromObject(from);
	}
	public Pageee(Pageee pageee){
		if(null==pageee) return;
		//设置顺序不能乱：
		//直接赋值
		this.setCount(pageee.getCount());
		this.setRow(pageee.getRow());
		//除了赋值外，还有其他逻辑
		this.setSize(pageee.getSize());
		this.setTotal(pageee.getTotal());
		this.setPage(pageee.getPage());
		this.setFrom(pageee.getFrom());
	}
	public Pageee(Page page){
		super(page);
	}
	public <T> Pageee(Pagee<T> pagee){
		super(StringUtil.String(pagee.getFrom()),pagee.getPage(),pagee.getSize());
	}
	
	public Integer getTotal() {
		return total;
	}

	/**
	 * 如果已经设置了每页size，那么可以计算出总页数count
	 * @param total
	 * @return
	 */
	public Pageee setTotal(Integer total) {
		this.total = total;
		
		//如果已经设置了每页size，那么可以计算出总页数count
		if(null!=this.total && null!=this.getSize() && 0<this.getSize()) {
			count = this.total/this.getSize();
		}
		
		return this;
	}

	public Integer getCount() {
		return count;
	}

	protected Pageee setCount(Integer count) {
		this.count = count;
		return this;
	}

	public Integer getSize() {
		return super.getSize();
	}

	/**
	 * 1.如果已经设置了总数total，那么可以计算出总页数count
	 * 2.如果已经设置了当前page，那么可以计算出当前所处的行数row
	 * @param size
	 * @return
	 */
	public Pageee setSize(Integer size) {
		super.setSize(size);
		
		//如果已经设置了总数total，那么可以计算出总页数count
		if(null!=this.getTotal() && null!=this.getSize() && 0<this.getSize()) {
			count = this.getTotal()/this.getSize();
		}
		
		//如果已经设置了当前page，那么可以计算出当前所处的行数row
		if(null!=this.getPage() && null!=this.getSize() && 0<this.getSize()) {
			row = (this.getPage()-1)*this.getSize();
		}
		
		return this;
	}

	public Integer getPage() {
		return super.getPage();
	}

	/**
	 * 1.如果已经设置了每页size，那么可以计算出当前所处的行数row
	 * 2.如果已经设置起始分界线主键from值，那么，from被重置为null
	 * @param page
	 * @return
	 */
	public Pageee setPage(Integer page) {
		super.setPage(page);
		
		//如果已经设置了每页size，那么可以计算出当前所处的行数row
		if(null!=this.getPage() && null!=this.getSize() && 0<this.getSize()) {
			row = (this.getPage()-1)*this.getSize();
		}
		
		//如果已经设置起始分界线主键from值，那么： 
		if(null!=this.getPage() && 1!=this.getPage()) {
			super.setFrom(null); //不能设置，否则陷入一个设置短路！！！
		}
		
		return this;
	}

	public Integer getRow() {
		return row;
	}

	protected Pageee setRow(Integer row) {
		this.row = row;
		return this;
	}

	public String getFrom() {
		return super.getFrom();
	}

	/**
	 * 如果要设置起始分界线主键值，那么，所设置的页码page将失效，即自动从第1页开始计算
	 * @param from
	 * @return
	 */
	public Pageee setFromString(String from) {
		super.setFrom(from);

		//如果要设置起始分界线主键值，那么，所设置的页码page将失效，即自动从第1页开始计算
		if(null!=this.getFrom()) {
			this.setPage(1);
		}

		return this;
	}
	
	public Pageee setFromObject(Object from) {
		if(null==from) {
			super.setFrom(null);
		} else {
			this.setFromString(from.toString());
		}

		return this;
	}
	
	/**
	 * 
	 * 解析列表字符串中的分页信息
	 * @param listString			列表字符串如：C-002, C-003(1:10), C-004, C-005
	 * @param defaultPageee
	 * @param delimiterPattern		缺省“,”
	 * @return
	 */
	public static List<Mark<String,Pageee>> Parse(String listString, Pageee defaultPageee, String delimiterPattern) {
		if(null==listString) return null;
		Assert.NotNull(defaultPageee, "Default Pager must not be NULL!");
		
		List<String> itemPatternList = Arrays.asList(listString.split(delimiterPattern));
		List<Mark<String,Pageee>> itemPages = new ArrayList<Mark<String,Pageee>>();
        Pattern p = Pattern.compile("^([\\w\\-]+)(\\(([\\w\\-]+):(\\d+)\\))?$");
		for(String itemPattern: itemPatternList) {
			itemPattern = itemPattern.replaceAll("\\s+", "");
            Matcher m = p.matcher(itemPattern);
            if(m.find()) {
            	String tag = m.group(1); String fromStr = m.group(3); String sizeStr = m.group(4);
            	int page = 1;
            	int size = NumericUtil.ToInt(sizeStr);
            	String from = fromStr;
            	Mark<String,Pageee> mark = new Mark<String,Pageee>();
            	mark.setMark(tag);
            	mark.setValue(new Pageee(page, 0>=size?defaultPageee.getSize():size, StringUtil.Invalid(from)?defaultPageee.getFrom():from));
            	itemPages.add(mark);
            }
		}
		
		return itemPages;
	}
	public static <T> List<Mark<String,Pageee>> Parse(String listString, Pagee<T> defaultPageee, String delimiterPattern) {
		Pageee pageee = new Pageee();
		pageee.setPage(defaultPageee.getPage());
		pageee.setSize(defaultPageee.getSize());
		pageee.setFromObject(defaultPageee.getFrom());
		return Parse(listString, pageee, delimiterPattern);
	}
	public static List<Mark<String,Pageee>> Parse(String listString, Pageee defaultPageee) {
		return Parse(listString, defaultPageee, ",");
	}
	public static <T> List<Mark<String,Pageee>> Parse(String listString, Pagee<T> defaultPageee) {
		return Parse(listString, defaultPageee, ",");
	}

	@Override
	public String toString() {
		return this.total +","+this.count +","+this.getSize() +","+this.getPage() +","+this.getFrom() +","+this.row;
	}
}
