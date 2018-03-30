package com.beigebigdata.bdCourt.ca.api.dao;

import com.beigebigdata.bdCourt.ca.api.entity.Member;
import com.beigebigdata.bdCourt.ca.api.view.MemberView;
import com.beigebigdata.bdCourt.ca.news.entity.NewsInfo;
import com.septinary.common.web.basic.dao.IBaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: MemberDao
 * @Description: 会员数据操作类
 * @date 16/10/21
 */

public interface MemberDao extends IBaseDao<Member,Long> {
	
    /***********************个人中心相关接口(lihuayang) START***************************/
	/**
	 * 会员信息查询
	 * @return
	 */
	public MemberView selectMemberViewInfo(String code);

	/**
	 * 通过用户的code查询用户数据
	 * @return
	 */
	public Member selectMemberInfoByMap(HashMap<String, Object> map);
	
	/**
	 * 根据修改的类型修改个人信息数据
	 * @param map
	 * @return int
	 */
	public int updateMemberInfoByMap(HashMap<String, Object> map);

	List<NewsInfo> fetchCollectionNews(@Param("memCode") String memCode);

	/***********************个人中心相关接口 END****************************************/
}
