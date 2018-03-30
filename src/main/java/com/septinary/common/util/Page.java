package com.septinary.common.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.septinary.common.type.Mark;

/**
 * 分页参数 page + size (无设置逻辑)
 * @Filename: com.septinary.common.util.Pagee.java of the project [com.septinary.common]
 *     @Type: Pagee
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年8月6日 下午3:57:31
 */
@SuppressWarnings("serial")
public class Page implements Serializable {

	//每页行数 (*不可缺少)
	private Integer size = 0;
	
	//当前页码 (*不可缺少)
	private Integer page = 0;
	
	public Page() {}
	public Page(int page, int size) {
		this.page = page;
		this.size = size;
	};
	public Page(Page page) {
		this.page = page.getPage();
		this.size = page.getSize();
	};

	public Integer getSize() {
		return size;
	}

	public Page setSize(Integer size) {
		this.size = size;
		return this;
	}

	public Integer getPage() {
		return page;
	}

	public Page setPage(Integer page) {
		this.page = page;
		return this;
	}
	
	/**
	 * 解析列表字符串中的分页信息
	 * @param listString			列表字符串如：C-002, C-003(1:10), C-004, C-005
	 * @param defaultPager
	 * @param delimiterPattern		缺省“,”
	 * @return
	 */
	public static List<Mark<String,Page>> Parse(String listString, Page defaultPager, String delimiterPattern) {
		if(null==listString) return null;
		Assert.NotNull(defaultPager, "Default Pager must not be NULL!");
		
		List<String> itemPatternList = Arrays.asList(listString.split(delimiterPattern));
		List<Mark<String,Page>> itemPages = new ArrayList<Mark<String,Page>>();
        Pattern p = Pattern.compile("^([\\w\\-]+)(\\((\\d+):(\\d+)\\))?$");
		for(String itemPattern: itemPatternList) {
			itemPattern = itemPattern.replaceAll("\\s+", "");
            Matcher m = p.matcher(itemPattern);
            if(m.find()) {
            	String tag = m.group(1); String pageStr = m.group(3); String sizeStr = m.group(4);
            	int page = NumericUtil.ToInt(pageStr);
            	int size = NumericUtil.ToInt(sizeStr);
            	Mark<String,Page> mark = new Mark<String,Page>();
            	mark.setMark(tag);
            	mark.setValue(new Page(0>=page?defaultPager.getPage():page, 0>=size?defaultPager.getSize():size));
            	itemPages.add(mark);
            }
		}
		
		return itemPages;
	}
	public static List<Mark<String,Page>> Parse(String listString, Page defaultPager) {
		return Parse(listString, defaultPager, ",");
	}
}
