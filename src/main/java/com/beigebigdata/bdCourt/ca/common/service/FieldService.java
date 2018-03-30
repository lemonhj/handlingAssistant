package com.beigebigdata.bdCourt.ca.common.service;


import com.beigebigdata.bdCourt.ca.common.entity.FieldName;
import com.beigebigdata.bdCourt.ca.common.entity.FieldValue;
import com.septinary.common.web.basic.service.IBaseService;

import java.util.List;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: FieldService
 * @Description:
 * @date 16/10/20
 */
public interface FieldService extends IBaseService<FieldName, Long> {

	/**
	 * 获取字段取值对应的图标
	 * @param name_dot_key_or_value
	 * @return
	 */
	String getFieldValueIcon(String name_dot_key_or_value);


	/**
	 * 通过配置的属性名称获取属性列表
	 * @param fieldName
	 * @return
	 */
	List<FieldValue> fetchFieldValue(String fieldName);

	/**
	 * 获取指定的fieldValue
	 * @param fieldValue
	 * @return
	 */
	FieldValue fetchFieldValue(FieldValue fieldValue);
}
