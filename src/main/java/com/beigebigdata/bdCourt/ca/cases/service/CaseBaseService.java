package com.beigebigdata.bdCourt.ca.cases.service;

import com.beigebigdata.bdCourt.ca.api.view.CaseBasView;
import com.beigebigdata.bdCourt.ca.cases.dto.CaseConditions;
import com.beigebigdata.bdCourt.ca.cases.entity.CaseBase;
import com.septinary.common.web.basic.service.IBaseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CaseBaseService
 * @Description:
 * @date 16/10/21
 */
public interface CaseBaseService extends IBaseService<CaseBase,Long> {

    /**
     * 获取相类似案件
     * @param caseCode 案件编号
     * @return
     */
    List<HashMap<String,Object>> fetchSimilarCase(String caseCode, CaseConditions caseConditions, Integer page, Integer size);
    
    /**
     * 案件进度状态处理
     * @param caseBase 案件
     * @param status 进度状态
     * @return
     */
    Boolean processCaseStatus(CaseBase caseBase, Integer status, String memo);

    /**
     * 根据筛选条件来获取历史案件
     * @param caseCode 案件编号
     * @param caseConditions 筛选条件
     * @param startNo 查询开始位置
     * @param size 查询条数
     * @return
     */
    List<HashMap<String,Object>> fetchSimilarCaseByConditions(String caseCode, CaseConditions caseConditions, Integer startNo, Integer size);

    /**
     * 获取指定用户办理的历史案件(包括终止和完成的)
     * @param memCode 用户code
     * @return
     */
    List<HashMap<String,Object>> fetchHistoryCaseBases(String memCode);

    /**
     * 获取用户自己创建的案件列表
     * @param memCode
     * @return
     */
    List<HashMap<String,Object>> fetchMemCases(String memCode, Integer caseStatus);

    /*********************************V1.1版本 start*****************************************/
    /**
     * 获取相类似案件V1.1
     * @param causeNo 案件编号
     * @return
     */
	List<HashMap<String, Object>> fetchSimilarCaseV11(String causeNo, CaseConditions caseConditions, Integer startNo,
                                                      Integer size);

    /**
     * 根据筛选条件来获取历史案件V1.1
     * @param causeNo 案件编号
     * @param caseConditions 筛选条件
     * @param startNo 查询开始位置
     * @param size 查询条数
     * @return
     */
	List<HashMap<String, Object>> fetchSimilarCaseByConditionsV11(String causeNo, CaseConditions caseConditions,
                                                                  Integer startNo, Integer size);

    List<HashMap<String,Object>> fetchSimilarByCauseNo(String causeNo, Integer startNo, Integer size);

    /*********************************V1.1版本 end*****************************************/
    
	//模糊条件查询
    List<CaseBasView> selectCaseBasByConditions(Map<String, Object> param);

    //条件查询条数
    Integer selectConutByConditions(Map<String, Object> param);
    
    //查询案件状态
    List<CaseBasView> selectCaseStatus();

}
