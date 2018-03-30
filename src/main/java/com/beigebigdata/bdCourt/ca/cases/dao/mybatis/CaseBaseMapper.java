package com.beigebigdata.bdCourt.ca.cases.dao.mybatis;

import com.beigebigdata.bdCourt.ca.cases.dao.CaseBaseDao;
import com.beigebigdata.bdCourt.ca.cases.entity.CaseBase;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CaseBaseMapper
 * @Description: 案件数据映射
 * @date 16/10/21
 */
@Repository("caseBaseDao")
public interface CaseBaseMapper extends CaseBaseDao, Mapper<CaseBase> {
}