package com.beigebigdata.bdCourt.ca.common.dao;

import com.beigebigdata.bdCourt.ca.common.entity.FieldName;
import com.septinary.common.type.IField;
import com.septinary.common.type.IFieldValue;
import com.septinary.common.web.basic.dao.IBaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: FieldNameDao
 * @Description:
 * @date 16/10/20
 */
public interface FieldNameDao extends IBaseDao<FieldName,Long> {

	/**
	 * 获取字段定义实体
	 * @param name
	 * @return
	 */
	public IField findFieldByName(String name);
	
	/**
	 * 获取字段定义列表
	 * @param clazz
	 * @return
	 */
	public List<IField> loadFieldsByClassname(String clazz);
	
	/**
	 * 获取字段取值的图标
	 * @param fv
	 * @param prefix
	 * @return
	 */
	public String getFieldValueIcon(@Param("fv") IFieldValue fv, @Param("uri") String prefix);
}
