package com.septinary.common.core.some;

import com.septinary.common.core.basic.bean.Bean;

/**
 * 某个（人员、行为、事物）
 * @Filename: com.septinary.common.core.some.Some.java of the project [com.septinary.common]
 *     @Type: Some
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月23日下午9:11:00
 *
 */
abstract public class Some<PK> extends Bean implements ISomeable<PK> {

	@Override
	public String getClassname() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setClassname(String classname) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PK getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(PK id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

}
