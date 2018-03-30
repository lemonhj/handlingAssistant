package com.septinary.common.type.basic;

import com.septinary.common.util.IProcessable;

/**
 * 展示对象
 * @Filename: com.septinary.common.type.Display.java of the project [com.septinary.common]
 *     @Type: Display
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月23日下午9:55:37
 * 
 * @param <PK>
 * @param <SK>
 * @param <URI>
 * @param <Type>
 */
abstract public class Display<PK, SK, URI, Type> extends Title<PK, SK> implements IDisplayable<URI, Type> {

	private URI thumb;
	
	private URI picture;
	
	private URI img;
	
	private Type type;

	public URI getThumb() {
		return thumb;
	}

	@Override
	public URI getThumb(IProcessable process) {
		// TODO Auto-generated method stub
		// return null;
		
		return process.process(this.getThumb());
	}

	public void setThumb(URI thumb) {
		this.thumb = thumb;
	}

	public URI getImg() {
		return img;
	}

	public void setImg(URI img) {
		this.img = img;
	}

	public URI getPicture() {
		return picture;
	}

	public void setPicture(URI picture) {
		this.picture = picture;
	}
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public <T> T getOnClick(IProcessable process) {
		// TODO Auto-generated method stub
		// return null;
		
		return process.process(this.getType());
	}
	
}
