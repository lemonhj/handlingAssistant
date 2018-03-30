package com.septinary.common.type.basic;

/**
 * 标题对象
 * @Filename: com.septinary.common.type.Title.java of the project [com.septinary.common]
 *     @Type: Title
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月22日下午4:06:31
 * 
 * @param <ID>
 * @param <SN>
 */
abstract public class Title<PK, SK> extends Name<PK, SK> implements ITitleable {
	
	private String label;

	private String title;
	
	private String intro;
	
	public Title(PK id, SK code, String tag, String alias, String name, String fullname, String label, String title, String intro) {
		super(id, code, tag, alias, name, fullname);
		this.label = label;
		this.title = title;
		this.intro = intro;
	}
	
	public Title(PK id, SK code, String tag, String alias, String name, String fullname, String label, String title) {
		this(id, code, tag, alias, name, fullname, label, title, null);
	}
	
	public Title(PK id, SK code, String tag, String alias, String name, String fullname, String label) {
		this(id, code, tag, alias, name, fullname, label, null);
	}
	
	public Title(PK id, SK code, String tag, String alias, String name, String fullname) {
		this(id, code, tag, alias, name, fullname, null);
	}
	
	public Title(PK id, SK code, String tag, String alias, String name) {
		this(id, code, tag, alias, name, null);
	}
	
	public Title(PK id, SK code, String tag, String alias) {
		this(id, code, tag, alias, null);
	}
	
	public Title(PK id, SK code, String tag) {
		this(id, code, tag, null);
	}
	
	public Title(PK id, SK code) {
		super(id, code);
	}
	
	public Title(PK id) {
		this(id, null);
	}
	
	public Title() {
		this(null, null);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
}
