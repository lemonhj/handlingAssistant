package com.septinary.common.sys.op;

import java.io.Serializable;

import com.septinary.common.type.basic.ID;
import com.septinary.common.util.IProcessable;
import com.septinary.common.util.StringUtil;

/**
 * 动作实体
 * @Filename: com.septinary.common.sys.op.Action.java of the project [com.septinary.common]
 *     @Type: Action
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年5月30日 下午8:45:07
 *  @param <PK>
 */
public class Action<PK extends Serializable> extends ID<PK> implements IActionable<PK> {
	
	private String tag;
	
	private String subject;
	
	private String extra;

	@Override
	public String getTag() {
		// TODO Auto-generated method stub
		// return null;
		
		return this.tag;
	}

	@Override
	public void setTag(String tag) {
		// TODO Auto-generated method stub
		
		this.tag = tag;
	}

	@Override
	public String getSubject() {
		// TODO Auto-generated method stub
		// return null;
		
		return this.subject;
	}

	@Override
	public void setSubject(String subject) {
		// TODO Auto-generated method stub
		
		this.subject = subject;
	}

	@Override
	public String getExtra() {
		// TODO Auto-generated method stub
		// return null;
		
		return this.extra;
	}

	@Override
	public void setExtra(String extra) {
		// TODO Auto-generated method stub
		
		this.extra = extra;
	}

	@Override
	public <T> T getExtra(IProcessable process) {
		// TODO Auto-generated method stub
		// return null;
		
		return process.process(this.getExtra());
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		// return super.equals(obj);
		
		if( super.equals(obj) ) {
			return true;
		}
		
		if( obj instanceof Action ) {
			Action<PK> action = (Action<PK>)obj;
			if( StringUtil.Equal(this.getTag(), action.getTag()) ) {
				return true;
			}
		}
		
		return false;
	}
}
