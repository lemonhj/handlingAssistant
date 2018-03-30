package com.septinary.common.web.basic.controller;

import java.util.Collection;

import com.septinary.common.util.ArrayUtil;
import com.septinary.common.util.ClassUtil;
import com.septinary.common.util.CollectionUtil;
import com.septinary.common.util.IMessageText;
import com.septinary.common.util.MessageText;
import com.septinary.common.util.Page;
import com.septinary.common.util.Pagee;
import com.septinary.common.util.Pageee;
import com.septinary.common.util.StringUtil;
import com.septinary.common.util.ViewJSON;
import com.septinary.common.web.basic.business.Business;

/**
 * Web 请求处理控制器抽象基类
 * @Filename: com.septinary.common.web.basic.controller.BaseController.java of the project [com.septinary.common.web]
 *     @Type: BaseController
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月12日下午12:29:47
 *
 */
public abstract class BaseController extends Business implements IBaseController {

	//protected Trailerable trailer = new Trailerable(this){};
	
	/**
	 * 生成消息文本实例
	 * @param codeSuffix
	 * @param args
	 * @return
	 */
	protected IMessageText text(String codeSuffix, Object[] args) {
		codeSuffix = StringUtil.Invalid(codeSuffix) ? null : codeSuffix.trim();
		String code = null==codeSuffix ? null : (codeSuffix.startsWith(".")?ClassUtil.ClassName(this)+codeSuffix:codeSuffix);
		return new MessageText(code, ArrayUtil.IsEmpty(args)?null:args);
	}
	protected IMessageText text(String codeSuffix, Collection<?> args) {
		codeSuffix = StringUtil.Invalid(codeSuffix) ? null : codeSuffix.trim();
		String code = null==codeSuffix ? null : (codeSuffix.startsWith(".")?ClassUtil.ClassName(this)+codeSuffix:codeSuffix);
		return new MessageText(code, CollectionUtil.IsEmpty(args)?null:args);
	}
	static protected final String HINT = ViewJSON.DEFAULT_HINT_KEY;
	
	/**
	 * 校验分页参数
	 * @param page
	 * @return
	 */
	static protected Page Page(Page page) {
		if(null==page) page = new Page();
    	if(0>=page.getSize() || configger.getInt("DEFAULT_PAGER_MAX_SIZE")<page.getSize()) page.setSize(configger.getInt("DEFAULT_PAGER_SIZE"));
    	if(0>=page.getPage()) page.setPage(configger.getInt("DEFAULT_PAGER_PAGE"));
    	return page;
	}
	static protected Page Page() {
		return Page(new Page());
	}
	/**
	 * 校验分页参数
	 * @param pagee
	 * @return
	 */
	static protected <T extends Comparable<?>> Pagee<T> Page(Pagee<T> pagee) {
		Pagee<T> rtn = new Pagee<T>(pagee);
    	if(0>=pagee.getSize() || configger.getInt("DEFAULT_PAGER_MAX_SIZE")<pagee.getSize()) rtn.setSize(configger.getInt("DEFAULT_PAGER_SIZE"));
    	if(0>=pagee.getPage()) rtn.setPage(configger.getInt("DEFAULT_PAGER_PAGE"));
		if(null!=pagee.getFrom()) rtn.setFrom(pagee.getFrom());
    	return rtn;
	}
	/**
	 * 校验分页参数
	 * @param pageee
	 * @return
	 */
	static protected Pageee Page(Pageee pageee) {
		Pageee rtn = new Pageee(pageee);
    	if(0>=pageee.getSize() || configger.getInt("DEFAULT_PAGER_MAX_SIZE")<pageee.getSize()) rtn.setSize(configger.getInt("DEFAULT_PAGER_SIZE"));
		if(0>=pageee.getPage()) rtn.setPage(configger.getInt("DEFAULT_PAGER_PAGE"));
    	if(null!=pageee.getFrom()) rtn.setFrom(pageee.getFrom());
    	return rtn;
	}
}
