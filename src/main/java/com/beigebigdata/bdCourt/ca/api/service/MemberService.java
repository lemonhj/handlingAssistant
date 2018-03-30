package com.beigebigdata.bdCourt.ca.api.service;


import com.beigebigdata.bdCourt.ca.api.entity.Member;
import com.beigebigdata.bdCourt.ca.api.view.MemberView;
import com.beigebigdata.bdCourt.ca.news.entity.NewsInfo;
import com.septinary.common.util.ViewJSON;
import com.septinary.common.web.basic.service.IBaseService;

import java.util.HashMap;
import java.util.List;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: MemberService
 * @Description:
 * @date 16/10/21
 */
public interface MemberService extends IBaseService<Member,Long> {

    String password(String password, String privateKey);

    /**
     * 判断是否已经注册
     * @param member
     * @return 1：用户名已经注册  2：手机号码已经注册
     */
    int isTelRegisted(Member member);

	/**
	 * 获取收藏的新闻
	 * @param memCode
	 * @return
	 */
	List<NewsInfo> fetchCollectionNews(String memCode);
    
    
    /***********************个人中心相关接口(lihuayang) START***************************/
	/**
	 * 查询会员信息，包括会员类型等
	 * @return
	 */
	public MemberView getMemberViewInfo(String code);

	/**
	 * 修改会员信息
	 * @param code
	 * @param type 类型：包括修改身份(identity),手机绑定(tel),密码(psd)
	 * @param value
	 * @param psdOriginal
	 * @return
	 */
	public ViewJSON<Void> updataMemberInfo(String code, String type, String value, String psdOriginal);

	/***
	 * 找回密码，通过手机号码
	 * @param tel
	 * @param psd
	 * @return
	 */
	public ViewJSON<Void> updateMemberPsdByTel(String tel, String psd);

	/**
	 * 根据手机号码匹配用户信息，返回用户的部分信息
	 * @return
	 */
	Member findMemberInfoByMap(HashMap<String, Object> map);


	/***********************个人中心相关接口 END****************************************/
}
