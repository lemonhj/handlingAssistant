package com.beigebigdata.bdCourt.ca.common.service.impl;


import com.beigebigdata.bdCourt.ca.common.dao.FieldNameDao;
import com.beigebigdata.bdCourt.ca.common.dao.FieldValueDao;
import com.beigebigdata.bdCourt.ca.common.entity.FieldName;
import com.beigebigdata.bdCourt.ca.common.entity.FieldValue;
import com.beigebigdata.bdCourt.ca.common.service.FieldService;
import com.septinary.common.core.util.IFieldGetter;
import com.septinary.common.type.IField;
import com.septinary.common.type.IFieldValue;
import com.septinary.common.web.basic.dao.IBaseDao;
import com.septinary.common.web.basic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: FieldServiceImpl
 * @Description:
 * @date 16/10/20
 */
@Service("fieldService")
public class FieldServiceImpl extends BaseService<FieldName, Long> implements FieldService, IFieldGetter {
	
	@Autowired
	private FieldNameDao fieldNameDao = null;

	@Autowired
	private FieldValueDao fieldValueDao;

	@Override
	public IBaseDao<FieldName, Long> getBaseDao() {
		return this.fieldNameDao;
	}

	
	
	@Override
	public IField get(String name) {
		return this.fieldNameDao.findFieldByName(name);
	}

	@Override
	public List<IField> get(Class<? extends Enum<?>> clazz) {
		return this.fieldNameDao.loadFieldsByClassname(clazz.getName());
	}



	@Override
	public String getFieldValueIcon(String name_dot_key_or_value) {
		IFieldValue fv = fielder.value(name_dot_key_or_value);
		if(null==fv) return null;
		
		return this.fieldNameDao.getFieldValueIcon(fv, configger.get("FILE_URL_PREFIX"));
	}

	@Override
	public List<FieldValue> fetchFieldValue(String name) {
		FieldName fieldName = new FieldName();
		fieldName.setName(name);
		fieldName = fieldNameDao.selectOne(fieldName);
		List<FieldValue> fieldValues = new ArrayList<>();
		if (fieldName == null)return fieldValues;
		FieldValue fieldValue = new FieldValue();
		fieldValue.setField(fieldName.getId());
		fieldValues = fieldValueDao.select(fieldValue);
		return fieldValues == null ? new ArrayList<FieldValue>() : fieldValues;
	}

	@Override
	public FieldValue fetchFieldValue(FieldValue fieldValue) {
		return fieldValueDao.selectOne(fieldValue);
	}


}
