package com.beigebigdata.bdCourt.ca.cases.dao.mybatis;

import com.beigebigdata.bdCourt.ca.cases.dao.CaseDao;
import com.beigebigdata.bdCourt.ca.cases.entity.Case;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CaseMapper
 * @Description: 历史案件数据映射
 * @date 16/10/21
 */
@Repository("caseDao")
public interface CaseMapper extends CaseDao, Mapper<Case> {
}