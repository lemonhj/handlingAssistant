package com.beigebigdata.bdCourt.ca.cases.dao.mybatis;

import com.beigebigdata.bdCourt.ca.cases.dao.CaseDefendantDao;
import com.beigebigdata.bdCourt.ca.cases.entity.CaseDefendant;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository("caseDefendantDao")
public interface CaseDefendantMapper extends CaseDefendantDao, Mapper<CaseDefendant> {
}