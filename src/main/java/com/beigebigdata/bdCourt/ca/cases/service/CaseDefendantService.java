package com.beigebigdata.bdCourt.ca.cases.service;

import com.beigebigdata.bdCourt.ca.cases.entity.CaseDefendant;
import com.septinary.common.web.basic.service.IBaseService;

import java.util.List;


/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CaseService
 * @Description: 历史案件服务
 * @date 16/10/21
 */
public interface CaseDefendantService extends IBaseService<CaseDefendant,Long> {

    /**
     * 获取案件的被告信息
     * @param caseNo 案件no
     * @return
     */
    List<CaseDefendant> fetchDefendantInfo(Long caseNo);

}
