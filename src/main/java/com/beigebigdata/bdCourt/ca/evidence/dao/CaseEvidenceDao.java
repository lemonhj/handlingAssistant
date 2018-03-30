package com.beigebigdata.bdCourt.ca.evidence.dao;


import com.beigebigdata.bdCourt.ca.evidence.entity.CaseEvidence;
import com.septinary.common.web.basic.dao.IBaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CaseEvidenceDao
 * @Description:
 * @date 16/10/27
 */
public interface CaseEvidenceDao extends IBaseDao<CaseEvidence,Long> {

    List<CaseEvidence> fetchExistEvidence(@Param("causeCode") String parentCauseCode, @Param("caseCode") String caseCode);

    int revokeCaseEvidence(@Param("caseEvidCode") String caseEvidCode);
}
