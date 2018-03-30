package com.septinary.common.core.basic.dto;

import java.io.Serializable;
import java.util.HashMap;

import org.springframework.beans.BeanUtils;

import com.septinary.common.core.basic.bean.Bean;

/**
 * 数据层 Data Transfer Object 数据传输对象 Bean 抽象基类
 * @Filename: com.septinary.common.core.basic.dto.Dto.java of the project [com.septinary.common]
 *     @Type: Dto
 *     @Desc: TODO
 *   @Author: macbook[weide<weide001@gmail.com>]
 *  @Created: 2016年3月12日下午12:16:01
 *
 */
@SuppressWarnings("serial")
abstract public class Dto extends Bean implements IDto, Serializable {

	/**
	 * 将源对象source拷贝给自己
	 @method BaseEntity: clone()
	 @memo TODO
	 @param source 源对象
	 @return BaseEntity 自己
	 */
	public Dto clone(Object source) {

    	BeanUtils.copyProperties(source,this);
    	
		return this;
	}
	
	public Dto clone(Object source, String... ignoreProperties) {

    	BeanUtils.copyProperties(source,this,ignoreProperties);
    	
		return this;
	}
	
	public Dto clone(Object source, HashMap<String,String> fromToPairs) {

		return this;
	}
}
