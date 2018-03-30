package com.beigebigdata.bdCourt.ca.law.service;

import com.beigebigdata.bdCourt.ca.law.entity.LawBase;
import com.beigebigdata.bdCourt.ca.law.view.LawBookView;
import com.septinary.common.web.basic.service.IBaseService;

import java.util.HashMap;
import java.util.List;
import java.util.Set;


/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CaseEvidenceService
 * @Description:
 * @date 16/10/21
 */
public interface LawBaseService extends IBaseService<LawBase,Long> {

	/**
	 * 查询法律典籍列表-根据案由编号
	 * @param causeNo
	 * @return
	 */
	List<LawBookView> queryLawBooksByCauseNo(Integer causeNo);

	/**
	 * 查询法律典籍编号列表-根据案由编号
	 * @param causeNo
	 * @return
	 */
	List<HashMap<String,Integer>> queryLawBookNosByCauseNo(Integer causeNo);


	/**
	 * 查询法律典籍列表-根据法典编号
	 * @return
	 * @param bookNOs
	 */
	List<LawBookView> queryLawBooksByBookNOs(Set<Integer> bookNOs);


	/**
	 * 根据编号获取法典基本信息
	 * @param lawNo
	 * @return
	 */
	LawBase fetchCodeInfo(Long lawNo);


	/**
	 * 查询法律典籍类型
	 * @param bookNOs
	 * @return
	 */
	List<LawBookView> queryLawBooksType(Set<Integer> bookNOs);

	/**
	 * 获取法律类型 V1.1
	 * @return
	 */
	List<Integer> getLayTypeList();

	List<LawBookView> getLaysDataListByMap(HashMap<String, Object> map);

}
