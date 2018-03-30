package com.septinary.common.type;

/**
 * 配置
 * @Filename: com.septinary.common.type.Configuration.java of the project [com.septinary.common]
 *     @Type: Configuration
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月22日下午3:27:20
 *
 */
public class Configuration extends Pair<String,Object> {
	private String path;
	
	private Boolean inherited;
	
	private Boolean hidden;
	
	private FieldValue datatype;
	
	private String seperater;
	
	private String eqtag;
	
	private String memo;
	
	public Configuration(String key, Object value) {
		super(key, value);
	}
	
	public Configuration(String key) {
		this(key, null);
	}
	
	public Configuration() {
		this(null);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Boolean getInherited() {
		return inherited;
	}

	public void setInherited(Boolean inherited) {
		this.inherited = inherited;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public FieldValue getDatatype() {
		return datatype;
	}

	public void setDatatype(FieldValue datatype) {
		this.datatype = datatype;
	}

	public String getSeperater() {
		return seperater;
	}

	public void setSeperater(String seperater) {
		this.seperater = seperater;
	}

	public String getEqtag() {
		return eqtag;
	}

	public void setEqtag(String eqtag) {
		this.eqtag = eqtag;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
