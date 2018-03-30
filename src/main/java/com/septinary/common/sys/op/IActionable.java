package com.septinary.common.sys.op;

import java.io.Serializable;

import com.septinary.common.type.basic.IExtrable;
import com.septinary.common.type.basic.IIdable;

/**
 * 动作接口
 * @Filename: com.septinary.common.sys.op.IActionable.java of the project [com.septinary.common]
 *     @Type: IActionable
 *     @Desc: TODO
 *   @Author: weide's Windows 7 pc[Administrator<weide001@gmail.com>]
 *  @Created: 2016年5月30日 下午8:42:51
 *  @param <PK>
 */
public interface IActionable<PK extends Serializable> extends IIdable<PK>, IExtrable {

	public String getTag();
	public void setTag(String tag);
	
	public String getSubject();
	public void setSubject(String subject);
}
