package com.beigebigdata.bdCourt.ca.law.dao.mybatis;

import com.beigebigdata.bdCourt.ca.law.dao.LawCatalogDao;
import com.beigebigdata.bdCourt.ca.law.entity.LawCatalog;
import tk.mybatis.mapper.common.Mapper;

public interface LawCatalogMapper extends LawCatalogDao, Mapper<LawCatalog> {
}