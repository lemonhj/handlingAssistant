package com.septinary.common.sys.op;

import java.math.BigInteger;

import com.septinary.common.sys.role.ISimpleUser;
import com.septinary.common.sys.target.ISimpleTarget;

/**
 * 操作行为接口
 * @Filename: com.septinary.common.sys.op.IOperation.java of the project [com.septinary.common]
 *     @Type: IOperation
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年8月8日 下午7:07:31
 */
public interface IOperation {
	public ISimpleUser getUser();

	public void setUser(ISimpleUser user);

	public ISimpleTarget getTarget();

	public void setTarget(ISimpleTarget target);
	
	public ITraceTrail<BigInteger> getTrail();

	public void setTrail(ITraceTrail<BigInteger> trail);
}
