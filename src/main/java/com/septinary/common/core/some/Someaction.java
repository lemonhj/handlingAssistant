package com.septinary.common.core.some;

import com.septinary.common.util.IProcessable;

/**
 * 某个行为动作
 * @Filename: com.septinary.common.core.some.Someaction.java of the project [com.septinary.common]
 *     @Type: Someaction
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月25日下午10:47:47
 *
 */
abstract public class Someaction<PK> extends Some<PK> implements ISomeactionable<PK> {

	@Override
	public String getExtra() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setExtra(String extra) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T getExtra(IProcessable process) {
		// TODO Auto-generated method stub
		// return null;
		
		return process.process(this.getExtra());
	}

	@Override
	public String getTag() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTag(String tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIcon(String icon) {
		// TODO Auto-generated method stub
		
	}

}
