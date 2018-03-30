package com.septinary.common.type.basic;

/**
 * ID
 * @Filename: com.septinary.common.type.ID.java of the project [com.septinary.common]
 *     @Type: ID
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月22日下午4:08:16
 * 
 * @param <PK>
 */
abstract public class ID<PK> implements IIdable<PK> {

	private PK id;
	
	public ID(PK id) {
		super();
		this.id = id;
	}
	
	public ID() {
		this(null);
	}

	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}
}
