package com.beigebigdata.bdCourt.ca.common.dao.mybatis;

import com.beigebigdata.bdCourt.ca.common.dao.FieldNameDao;
import com.beigebigdata.bdCourt.ca.common.entity.FieldName;
import com.septinary.common.web.general.dao.mybatis.CommonMapperMySQL;
import org.springframework.stereotype.Repository;

@Repository("fieldNameDao")
public interface FieldNameMapper extends FieldNameDao, CommonMapperMySQL<FieldName> {

}
