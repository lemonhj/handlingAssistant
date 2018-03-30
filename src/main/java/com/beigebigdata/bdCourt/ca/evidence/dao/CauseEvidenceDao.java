package com.beigebigdata.bdCourt.ca.evidence.dao;

import com.beigebigdata.bdCourt.ca.cases.entity.CaseBase;
import com.beigebigdata.bdCourt.ca.evidence.entity.CauseEvidence;
import com.septinary.common.web.basic.dao.IBaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CauseEvidenceDao
 * @Description: 案由要素与证据关系数据操作
 * @date 16/10/25
 */
public interface CauseEvidenceDao extends IBaseDao<CauseEvidence,Long> {

    List<CaseBase> fetchCaseEvidenceCategory(@Param("caseType") Integer caseType);

    List<CauseEvidence> fetchChildEvidence(@Param("causeCode") Integer causeCode, @Param("caseType") Integer caseType);

    List<Integer> essential(@Param("evidNo") Integer evidNo, @Param("causeNo") Integer causeNo);
}
