package com.septinary.common.type.basic;

import com.septinary.common.util.IProcessable;

/**
 * 图集图片
 * @Filename: com.septinary.common.type.ImageOfGalleryable.java of the project [com.septinary.common]
 *     @Type: ImageOfGalleryable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年4月26日下午5:25:18
 * 
 * @param <URI>
 * @param <O>
 */
abstract public class ImageOfGallery<PK, URI, O extends Comparable<?>> extends ID<PK> implements IImageOfGalleryable<PK, URI, O> {

	private URI thumb;
	
	private URI img;
	
	private URI picture;

	private String text;
	
	private O order;
	
	public ImageOfGallery(PK id, URI thumb, URI img, URI picture, String text, O order){
		super(id);
		this.thumb = thumb;
		this.img = img;
		this.picture = picture;
		this.text = text;
		this.order = order;
	}
	
	public ImageOfGallery(PK id, URI thumb, URI img, URI picture, String text){
		this(id, thumb, img, picture, text, null);
	}
	
	public ImageOfGallery(PK id, URI thumb, URI img, URI picture){
		this(id, thumb, img, picture, null);
	}
	
	public ImageOfGallery(PK id, URI thumb, URI img){
		this(id, thumb, img, null);
	}
	
	public ImageOfGallery(PK id, URI thumb){
		this(id, thumb, null);
	}
	
	public ImageOfGallery(PK id){
		this(id, null);
	}
	
	public ImageOfGallery(){
		this(null);
	}

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

	public O getOrder() {
		return order;
	}

	public void setOrder(O order) {
		this.order = order;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
