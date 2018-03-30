package com.beigebigdata.bdCourt.ca.law.dao;

import com.beigebigdata.bdCourt.ca.law.entity.LawBase;
import com.beigebigdata.bdCourt.ca.law.view.LawBookView;
import com.septinary.common.web.basic.dao.IBaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: LawBaseDao
 * @Description:法律法规基本信息
 * @date 16/10/31
 */
public interface LawBaseDao extends IBaseDao<LawBase,Long> {

	/**
	 * 查询法律典籍列表-根据案由编号
	 * @param causeNo
	 * @return
	 */
	public List<LawBookView> queryLawBooksByCauseNo(Integer causeNo);

	/**
	 * 查询法律典籍编号列表-根据案由编号
	 * @param causeNo
	 * @return
	 */
	List<HashMap<String,Integer>> queryLawBookNosByCauseNo(Integer causeNo);

	/**
	 * 查询法律典籍列表-根据法典编号
	 * @param bookNOs
	 * @return
	 */
	List<LawBookView> queryLawBooksByBookNOs(@Param("bookNOs") Set<Integer> bookNOs);

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
	List<LawBookView> queryLawBooksType(@Param("bookNOs") Set<Integer> bookNOs);

	/**********************V1.1版本**********************/
	/**
	 * 获取法律类型
	 * @return
	 */
	public List<Integer> queryLayType();
	/**********************V1.1版本 END**********************/
	/**
	 * 查询法律标准编号列表-根据案由编号
	 * @param causeNo
	 * @return
	 */
	List<HashMap<String,Integer>> queryStandardBookNosByCauseNo(Integer causeNo);

	public List<LawBookView> queryLaysDataListByMap(HashMap<String, Object> map);
}
