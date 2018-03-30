package com.septinary.common.sys.op;

import java.math.BigInteger;

import com.septinary.common.sys.role.ISimpleUser;
import com.septinary.common.sys.target.ISimpleTarget;

/**
 * 操作行为实体
 * @Filename: com.septinary.common.sys.op.Operation.java of the project [com.septinary.common]
 *     @Type: Operation
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年5月30日 下午8:44:48
 *  @param <PK>
 */
public class Operation extends Action<String> implements IOperation {

	private ISimpleUser user;
	
	private ISimpleTarget target;
	
	private ITraceTrail<BigInteger> trail;

	public ISimpleUser getUser() {
		return user;
	}

	public void setUser(ISimpleUser user) {
		this.user = user;
	}

	public ISimpleTarget getTarget() {
		return target;
	}

	public void setTarget(ISimpleTarget target) {
		this.target = target;
	}

	public ITraceTrail<BigInteger> getTrail() {
		return trail;
	}

	public void setTrail(ITraceTrail<BigInteger> trail) {
		this.trail = trail;
	}
}
