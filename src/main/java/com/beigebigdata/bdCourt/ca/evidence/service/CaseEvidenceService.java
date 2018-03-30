package com.beigebigdata.bdCourt.ca.evidence.service;

import com.beigebigdata.bdCourt.ca.evidence.entity.CaseEvidence;
import com.septinary.common.web.basic.service.IBaseService;

import java.util.List;


/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CaseEvidenceService
 * @Description:
 * @date 16/10/21
 */
public interface CaseEvidenceService extends IBaseService<CaseEvidence,Long> {

    /**
     * 获取已经存在的证据
     * @param causeCode 证据类型编号
     * @return 证据
     */
    List<CaseEvidence> fetchExistEvidence(Integer causeCode, String caseCode);


    /**
     * 撤销案件证据
     * @param caseEvidCode 证据编号
     * @return
     */
    int revokeCaseEvidence(String caseEvidCode);
}
