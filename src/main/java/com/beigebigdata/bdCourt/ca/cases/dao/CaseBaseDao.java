package com.beigebigdata.bdCourt.ca.cases.dao;

import com.beigebigdata.bdCourt.ca.api.view.CaseBasView;
import com.beigebigdata.bdCourt.ca.cases.entity.CaseBase;
import com.septinary.common.web.basic.dao.IBaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CaseBaseDao
 * @Description: 案件数据操作类
 * @date 16/10/21
 */

public interface CaseBaseDao extends IBaseDao<CaseBase,Long> {

    List<HashMap<String,Object>> fetchSimilarCase(@Param("caseType") String caseType, @Param("caseCode") String caseCode, @Param("caseCloseSt") Date caseCloseSt, @Param("caseCloseEt") Date caseCloseEt, @Param("startNo") Integer startNo, @Param("size") Integer size);

    List<HashMap<String,Object>> fetchSimilarCaseByConditions(@Param("caseType") Integer caseType, @Param("causeNos") List<Integer> causeNos, @Param("caseCloseSt") Date caseCloseSt, @Param("caseCloseEt") Date caseCloseEt, @Param("startNo") Integer startNo, @Param("size") Integer size);

    List<HashMap<String,Object>> fetchHistoryCaseBases(@Param("caseCode") String memCode);

    List<HashMap<String,Object>> fetchMemCases(@Param("caseCode") String memCode, @Param("caseStatus") Integer caseStatus);

    /*****************************V1.1版本 start*************************/
    
	List<HashMap<String, Object>> fetchSimilarCaseV11(@Param("caseCode") String causeNo, @Param("caseCloseSt") Date caseCloseSt, @Param("caseCloseEt") Date caseCloseEt, @Param("startNo") Integer startNo, @Param("size") Integer size);

	List<HashMap<String, Object>> fetchSimilarCaseByConditionsV11(@Param("causeNo") String causeNo, @Param("causeNos") List<Integer> casueNos, @Param("caseCloseSt") Date caseCloseSt, @Param("caseCloseEt") Date caseCloseEt, @Param("startNo") Integer startNo, @Param("size") Integer size);

    List<HashMap<String,Object>> fetchSimilarByCauseNo(@Param("causeNo") String causeNo, @Param("startNo") Integer startNo, @Param("size") Integer size);

    /*****************************V1.1版本 end*************************/
    
    //模糊条件查询
	List<CaseBasView> selectCaseBasByConditions(Map<String, Object> param);
	
	//条件查询条数
	Integer selectConutByConditions(Map<String, Object> param);
	
	//查询案件状态
	List<CaseBasView> selectCaseStatus();
	
}
