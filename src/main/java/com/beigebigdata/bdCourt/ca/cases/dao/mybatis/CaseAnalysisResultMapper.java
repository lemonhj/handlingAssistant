package com.beigebigdata.bdCourt.ca.cases.dao.mybatis;

import com.beigebigdata.bdCourt.ca.cases.dao.CaseAnalysisResultDao;
import com.beigebigdata.bdCourt.ca.cases.entity.CaseAnalysisResult;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CaseAnalysisResultMapper
 * @Description: 案件分析映射
 * @date 16/10/21
 */
@Repository("caseAnalysisResultDao")
public interface CaseAnalysisResultMapper extends CaseAnalysisResultDao, Mapper<CaseAnalysisResult> {
}