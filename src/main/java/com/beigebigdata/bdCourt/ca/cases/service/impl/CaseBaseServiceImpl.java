package com.beigebigdata.bdCourt.ca.cases.service.impl;

import com.beigebigdata.bdCourt.ca.api.view.CaseBasView;
import com.beigebigdata.bdCourt.ca.cases.dao.CaseBaseDao;
import com.beigebigdata.bdCourt.ca.cases.dto.CaseConditions;
import com.beigebigdata.bdCourt.ca.cases.entity.CaseBase;
import com.beigebigdata.bdCourt.ca.cases.service.CaseBaseService;
import com.septinary.common.web.basic.dao.IBaseDao;
import com.septinary.common.web.basic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CaseBaseServiceImpl
 * @Description:
 * @date 16/10/24
 */
@Service("caseBaseService")
public class CaseBaseServiceImpl extends BaseService<CaseBase,Long> implements CaseBaseService {

    @Autowired
    private CaseBaseDao caseBaseDao;


    @Override
    public IBaseDao<CaseBase, Long> getBaseDao() {
        return caseBaseDao;
    }

    @Override
    public List<HashMap<String,Object>> fetchSimilarCase(String caseCode, CaseConditions caseConditions, Integer startNo, Integer size) {
        CaseBase caseBase = new CaseBase();
        caseBase.setCaseCode(caseCode);
        caseBase = this.selectOne(caseBase);
        if (caseBase == null) return new ArrayList<>();

        return caseBaseDao.fetchSimilarCase(caseBase.getCaseType() + "", caseCode, caseConditions.getCaseCloseSt(), caseConditions.getCaseCloseEt(), startNo, size);
    }

	@Override
	public Boolean processCaseStatus(CaseBase caseBase, Integer status, String memo) {
		
		CaseBase cb = new CaseBase();
		cb.setId(caseBase.getId());
		cb.setCaseStatus(status);
		cb.setCaseMemo(memo);
		this.updateByPrimaryKeySelective(cb);
		
		return true;
	}

    @Override
    public List<HashMap<String, Object>> fetchSimilarCaseByConditions(String caseCode, CaseConditions caseConditions, Integer startNo, Integer size) {
        CaseBase caseBase = new CaseBase();
        caseBase.setCaseCode(caseCode);
        caseBase = this.selectOne(caseBase);
        List<Integer> casueNos = caseConditions.getCauseNos() == null ? null : Arrays.asList(caseConditions.getCauseNos());
        return caseBaseDao.fetchSimilarCaseByConditions(caseBase.getCaseType(),casueNos,caseConditions.getCaseCloseSt(),caseConditions.getCaseCloseEt(),startNo,size);
    }

    @Override
    public List<HashMap<String,Object>> fetchHistoryCaseBases(String memCode) {
        return caseBaseDao.fetchHistoryCaseBases(memCode);
    }

    @Override
    public List<HashMap<String, Object>> fetchMemCases(String memCode, Integer caseStatus) {
        return caseBaseDao.fetchMemCases(memCode,caseStatus);
    }

    /*****************************V1.1版本 start*************************/
    /**
     * 获取相类似案件V1.1
     * @param causeNo 案件编号
     * @return
     */
	@Override
	public List<HashMap<String, Object>> fetchSimilarCaseV11(String causeNo, CaseConditions caseConditions,
			Integer startNo, Integer size) {
		return caseBaseDao.fetchSimilarCaseV11(causeNo, caseConditions.getCaseCloseSt(), caseConditions.getCaseCloseEt(), startNo, size);
	}

    /**
     * 根据筛选条件来获取历史案件V1.1
     * @param causeNo 案件编号
     * @param caseConditions 筛选条件
     * @param startNo 查询开始位置
     * @param size 查询条数
     * @return
     */
	@Override
	public List<HashMap<String, Object>> fetchSimilarCaseByConditionsV11(String causeNo, CaseConditions caseConditions,
			Integer startNo, Integer size) {
		List<Integer> casueNos = caseConditions.getCauseNos() == null ? null : Arrays.asList(caseConditions.getCauseNos());
        return caseBaseDao.fetchSimilarCaseByConditionsV11(causeNo,casueNos,caseConditions.getCaseCloseSt(),caseConditions.getCaseCloseEt(),startNo,size);
	}

    @Override
    public List<HashMap<String, Object>> fetchSimilarByCauseNo(String causeNo, Integer startNo, Integer size) {
        return caseBaseDao.fetchSimilarByCauseNo(causeNo,startNo,size);
    }
    /*****************************V1.1版本 end*************************/
   
    //模糊条件查询
	@Override
	public List<CaseBasView> selectCaseBasByConditions(Map<String, Object> param) {
		return caseBaseDao.selectCaseBasByConditions(param);
	}
	
	//条件查询条数
	@Override
	public Integer selectConutByConditions(Map<String, Object> param){
		return caseBaseDao.selectConutByConditions(param);
	}
	
	//查询案件状态
    public List<CaseBasView> selectCaseStatus(){
    	return caseBaseDao.selectCaseStatus();
    }
    
}
