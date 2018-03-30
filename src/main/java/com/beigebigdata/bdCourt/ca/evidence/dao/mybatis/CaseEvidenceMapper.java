package com.beigebigdata.bdCourt.ca.evidence.dao.mybatis;


import com.beigebigdata.bdCourt.ca.evidence.dao.CaseEvidenceDao;
import com.beigebigdata.bdCourt.ca.evidence.entity.CaseEvidence;
import tk.mybatis.mapper.common.Mapper;

public interface CaseEvidenceMapper extends CaseEvidenceDao, Mapper<CaseEvidence> {
}