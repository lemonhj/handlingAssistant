package com.beigebigdata.bdCourt.ca.cases.dao;

import com.beigebigdata.bdCourt.ca.cases.entity.CaseDefendant;
import com.septinary.common.web.basic.dao.IBaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CaseDefendantDao
 * @Description:
 * @date 16/11/3
 */
public interface CaseDefendantDao extends IBaseDao<CaseDefendant,Long> {

    List<CaseDefendant> fetchDefendantInfo(@Param("caseNo") Long caseNo);

}
