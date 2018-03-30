package com.beigebigdata.bdCourt.ca.cases.service;

import com.beigebigdata.bdCourt.ca.cases.entity.Case;
import com.septinary.common.web.basic.service.IBaseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CaseService
 * @Description: 历史案件服务
 * @date 16/10/21
 */
public interface CaseService extends IBaseService<Case,Long> {

    /**
     * 获取历史案件的案件要素
     * @param caseNo 案件no
     * @return
     */
    List<Map<String,Object>> fetchCaseCause(Long caseNo);

    /**
     * 获取案件数量
     * @param caseNo 案件号
     * @param causeNo 案由号
     * @return
     */
    Integer fetchCaseNum(Long caseNo, Integer causeNo);

    /**
     * 获取历史案件的统计信息
     * @param caseCode 案件编号
     * @return
     */
    HashMap<String,HashMap<String,Integer>> fetchStatisticsInfo(String caseCode);

    /**
     * 通过案由号获取统计信息
     * @param causeNo
     * @return
     */
    HashMap<String,HashMap<String,Integer>> fetchStatisticsInfoByCauseNo(String causeNo);


}
