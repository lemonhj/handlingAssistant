package com.septinary.common.core.util;

import java.util.List;

import com.septinary.common.type.IField;
import com.septinary.common.util.IGetter;

/**
 * 字段读取接口
 * @Filename: com.septinary.common.core.util.IFieldGetter.java of the project [com.septinary.common]
 * @Type: IFieldGetter
 * @Desc: TODO
 * @Author: macbook[weide<weide001@gmail.com>]
 * @Created: 2016-7-13 17:48:44
 */
public interface IFieldGetter extends IGetter<String,IField> {
	
	public List<IField> get(Class<? extends Enum<?>> clazz);
}
