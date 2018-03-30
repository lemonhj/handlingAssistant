package com.septinary.common.core.some;

/**
 * 某个人员
 * @Filename: com.septinary.common.core.some.ISomebodyable.java of the project [com.septinary.common]
 *     @Type: ISomeoneable
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月23日下午4:09:38
 *
 */
public interface ISomebodyable<PK> extends ISomeable<PK> {

	//照片
	public String getPhoto();
	public void setPhoto(String photo);
}
