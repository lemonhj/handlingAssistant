package com.septinary.common.core.some;

import com.septinary.common.util.IProcessable;

/**
 * 某个目标事物
 * @Filename: com.septinary.common.core.some.Something.java of the project [com.septinary.common]
 *     @Type: Something
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月25日下午10:48:12
 *
 */
abstract public class Something<PK> extends Some<PK> implements ISomethingable<PK> {

	@Override
	public String getImg() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setImg(String img) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPicture() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPicture(String picture) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getThumb() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getThumb(IProcessable process) {
		// TODO Auto-generated method stub
		// return null;
		
		return process.process(this.getThumb());
	}

	@Override
	public void setThumb(String thumb) {
		// TODO Auto-generated method stub
		
	}

}
