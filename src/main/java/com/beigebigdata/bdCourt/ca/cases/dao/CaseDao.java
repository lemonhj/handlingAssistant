package com.beigebigdata.bdCourt.ca.cases.dao;

import com.beigebigdata.bdCourt.ca.cases.entity.Case;
import com.septinary.common.web.basic.dao.IBaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CaseDao
 * @Description:案件操作类
 * @date 16/11/2
 */
public interface CaseDao extends IBaseDao<Case,Long> {

    List<Map<String,Object>> fetchCaseCause(@Param("caseNo") Long caseNo, @Param("causeNo") Integer causeNo);

    Integer fetchCaseNum(@Param("caseNo") Long caseNo, @Param("causeNo") Integer causeNo);

    Integer fetchCaseNumByCondition(@Param("caseCode") String caseCode, @Param("condition") String condition);

    Integer fetchCaseNumByCauseNo(@Param("causeNo") String causeNo, @Param("condition") String condition);

}
