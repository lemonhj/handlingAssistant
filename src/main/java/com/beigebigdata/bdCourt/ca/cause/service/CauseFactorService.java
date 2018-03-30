package com.beigebigdata.bdCourt.ca.cause.service;

import com.beigebigdata.bdCourt.ca.cause.entity.CauseFactor;
import com.septinary.common.web.basic.service.IBaseService;

import java.util.List;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CaseBaseService
 * @Description:罪种要素信息操作
 * @date 16/10/21
 */
public interface CauseFactorService extends IBaseService<CauseFactor,Long> {

    /**
     * 通过案件类型来查询此案件类型下所涉及到的要素
     * @param causeType 案件类型
     * @return 要素列表
     */
    List<CauseFactor> causeFactorsByCt(Integer causeType);

    /**
     * 获取指定案件所具备的要素
     * @param caseCode 案件code
     * @return 要素结果集
     */
    List<CauseFactor> causeFactorsByCaseCode(String caseCode);

    /**
     * 查询案件的审判依据
     * @param caseNo 案件no
     * @return
     */
    List<String> fetchJudgementBasis(Long caseNo);

    /**
     * 筛选条件
     * @param caseTyp 案件类型
     * @param pCasueNo 父要素编号
     * @return
     */
    List<CauseFactor> fetchConditionsCause(Integer caseTyp, Integer pFactorNo);
}
