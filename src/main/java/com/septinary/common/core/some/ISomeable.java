package com.septinary.common.core.some;

/**
 * 某个（人员、行为、事物）
 * @Filename: com.septinary.common.core.some.ISomeable.java of the project [com.septinary.common]
 *     @Type: ISomeable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月23日下午9:06:45
 *
 */
public interface ISomeable<PK> {

	//类别
	public String getClassname();
	public void setClassname(String classname);
	
	//唯一标识：内码(id)，外码-编号(code)或者外码-序列(sn)
	public PK getId();
	public void setId(PK id);
	
	//名称
	public String getName();
	public void setName(String name);
	
}
