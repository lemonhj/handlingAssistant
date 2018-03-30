package com.septinary.common.type.basic;

import com.septinary.common.util.IProcessable;

/**
 * 关键词接口
 * @Filename: com.septinary.common.type.IKeywordsable.java of the project [com.septinary.common]
 *     @Type: IKeywordsable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年5月19日上午11:59:23
 *
 */
public interface IKeywordsable {

	public String getKeywords();
	public void setKeywords(String keywords);

	public <T> T getKeywords(IProcessable process);
}
