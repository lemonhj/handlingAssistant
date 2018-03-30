package com.beigebigdata.bdCourt.ca.cause.dao;

import com.beigebigdata.bdCourt.ca.cause.entity.CauseFactor;
import com.septinary.common.web.basic.dao.IBaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CauseFactorDao
 * @Description: 罪种要素信息操作
 * @date 16/11/1
 */
public interface CauseFactorDao extends IBaseDao<CauseFactor,Long> {

    List<CauseFactor> causeFactorsByCt(@Param("causeType") Integer causeType);

    List<CauseFactor> causeFactorsByCaseCode(@Param("caseCode") String caseCode, @Param("causeType") String causeType);

    List<String> fetchJudgementBasis(@Param("caseNo") Long caseNo);

    List<CauseFactor> fetchConditionsCause(@Param("caseTyp") Integer caseTyp, @Param("pFactorNo") Integer pFactorNo);
}
