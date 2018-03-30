package com.septinary.common.type.basic;

import java.sql.Timestamp;
import java.util.Date;

import com.septinary.common.util.IProcessable;

/**
 * 可描述对象
 * @Filename: com.septinary.common.type.Description.java of the project [com.septinary.common]
 *     @Type: Description
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月26日下午4:37:30
 *
 */
abstract public class Description<PK, SK, URI, Type> extends Display<PK, SK, URI, Type> implements IDescriptionable {

	private String desc;
	
	private String extra;
	
	private String keywords;
	
	private String memo;
	
	private Date create;
	
	private Timestamp update;
	
	private Boolean deleted;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getExtra() {
		return extra;
	}

	@Override
	public <T> T getExtra(IProcessable process) {
		// TODO Auto-generated method stub
		// return null;
		
		return process.process(this.getExtra());
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getKeywords() {
		return keywords;
	}
	
	@Override
	public <T> T getKeywords(IProcessable process) {
		// TODO Auto-generated method stub
		// return null;

		return process.process(this.getKeywords());
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	public Timestamp getUpdate() {
		return update;
	}

	public void setUpdate(Timestamp update) {
		this.update = update;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	@Override
	public Boolean isDeleted() {
		// TODO Auto-generated method stub
		// return null;
		
		return this.getDeleted();
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
