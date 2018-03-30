package com.beigebigdata.bdCourt.ca.news.service.impl;

import com.beigebigdata.bdCourt.ca.news.dao.NewsTabDao;
import com.beigebigdata.bdCourt.ca.news.entity.NewsTab;
import com.beigebigdata.bdCourt.ca.news.service.NewsTabService;
import com.septinary.common.web.basic.dao.IBaseDao;
import com.septinary.common.web.basic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("newsTabService")
public class NewsTabServiceImpl extends BaseService<NewsTab,Long> implements NewsTabService {

    @Autowired
    private NewsTabDao newsTabDao;
    
	@Override
	public IBaseDao<NewsTab, Long> getBaseDao() {
		// TODO Auto-generated method stub
		return this.newsTabDao;
	}


	@Override
	public List<NewsTab> fetchEnableTabs() {
		return newsTabDao.fetchEnableTabs();
	}
	

	/**
	 * 条件查询
	 * @param param
	 * @return
	 */
	public List<NewsTab> selectNewsTabsByConditions(Map<String, Object> param){
		return newsTabDao.selectNewsTabsByConditions(param);
	}
}
