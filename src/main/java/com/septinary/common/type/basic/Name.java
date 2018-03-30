package com.septinary.common.type.basic;

/**
 * 名称对象
 * @Filename: com.septinary.common.type.Name.java of the project [com.septinary.common]
 *     @Type: NAME
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月22日下午4:21:06
 * 
 * @param <ID>
 * @param <CODE>
 */
abstract public class Name<PK,SK> extends CODE<PK,SK> implements INameable {

	private String tag;
	
	private String alias;
	
	private String name;
	
	private String fullname;
	
	public Name(PK id, SK code, String tag, String alias, String name, String fullname) {
		super(id, code);
		this.tag = tag;
		this.alias = alias;
		this.name = name;
		this.fullname = fullname;
	}
	
	public Name(PK id, SK code, String tag, String alias, String name) {
		this(id, code, tag, alias, name, null);
	}
	
	public Name(PK id, SK code, String tag, String alias) {
		this(id, code, tag, alias, null, null);
	}
	
	public Name(PK id, SK code, String tag) {
		this(id, code, tag, null, null, null);
	}
	
	public Name(PK id, SK code) {
		super(id, code);
	}
	
	public Name(PK id) {
		this(id, null);
	}
	
	public Name() {
		this(null, null);
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
}
