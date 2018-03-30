package com.septinary.common.type;

import java.util.List;

import com.septinary.common.util.IProcessable;

/**
 * 树节点
 * @Filename: com.septinary.common.type.TreeNode.java of the project [com.septinary.common]
 *     @Type: TreeNode
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年5月19日下午12:41:37
 * 
 * @param <PK>
 * @param <O>
 */
public abstract class TreeNode<PK, O extends Comparable<?>> implements ITreeable<PK, TreeNode<PK,O>, O> {
	
	private PK id;

	private TreeNode<PK, O> parent;
	
	private String xpath;
	
	private String navigation;
	
	private O order;
	
	private List<TreeNode<PK, O>> children;

	@Override
	public PK getId() {
		return this.id;
	}

	@Override
	public void setId(PK id) {
		this.id = id;
	}

	@Override
	public O getOrder() {
		return this.order;
	}

	@Override
	public void setOrder(O order) {
		this.order = order;
	}

	@Override
	public TreeNode<PK, O> getParent() {
		return this.parent;
	}

	@Override
	public void setParent(TreeNode<PK, O> parent) {
		this.parent = parent;
	}

	@Override
	public String getXpath() {
		return this.xpath;
	}

	@Override
	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

	@Override
	public String getNavigation() {
		return this.navigation;
	}

	@Override
	public void setNavigation(String navigation) {
		this.navigation = navigation;
	}

	@Override
	public <T> T getNavigation(IProcessable process) {
		// TODO Auto-generated method stub
		// return null;
		
		return process.process(this.getNavigation());
	}

	@Override
	public List<TreeNode<PK, O>> getChildren() {
		return this.children;
	}

	@Override
	public void setChildren(List<TreeNode<PK, O>> children) {
		this.children = children;
	}
}
