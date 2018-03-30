package com.beigebigdata.bdCourt.ca.evidence.service;

import com.beigebigdata.bdCourt.ca.cases.entity.CaseBase;
import com.beigebigdata.bdCourt.ca.evidence.entity.CauseEvidence;
import com.septinary.common.web.basic.service.IBaseService;

import java.util.List;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CauseEvidenceService
 * @Description:
 * @date 16/10/21
 */
public interface CauseEvidenceService extends IBaseService<CauseEvidence,Long> {

    /**
     * 获取案件证据证据类别
     * @param caseType 案件code
     * @return 证据类别
     */
    List<CaseBase> fetchCaseEvidenceCategory(Integer caseType);

    /**
     * 通过父证据类别来获取子证据类别
     * @param causeCode 证据类别编号
     * @return 子证据类型
     */
    List<CauseEvidence> fetchChildEvidence(Integer causeCode, Integer caseType);

    /**
     * 是否必须提供必要证据
     * @param evidNo 证据编号
     * @return
     */
    boolean needEssential(Integer evidNo, Integer caseType);

}
