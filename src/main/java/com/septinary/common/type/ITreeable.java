package com.septinary.common.type;

import java.util.List;

import com.septinary.common.type.basic.IIdable;
import com.septinary.common.type.basic.IOrderable;
import com.septinary.common.util.IProcessable;

/**
 * 树对象接口
 * @Filename: com.septinary.common.type.ITreeable.java of the project [com.septinary.common]
 *     @Type: ITreeable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日下午10:01:50
 * 
 * @param <Node>
 */
public interface ITreeable<PK, Node, O extends Comparable<?>> extends IIdable<PK>, IOrderable<O> {

	public Node getParent();
	public void setParent(Node parent);
	
	public String getXpath();
	public void setXpath(String xpath);
	
	public String getNavigation();
	public void setNavigation(String navigation);
	
	public <T> T getNavigation(IProcessable process);
	
	public List<Node> getChildren();
	public void setChildren(List<Node> children);
}
