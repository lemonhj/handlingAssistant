package com.beigebigdata.bdCourt.ca.news.service;

import com.beigebigdata.bdCourt.ca.news.entity.NewsTab;
import com.septinary.common.web.basic.service.IBaseService;

import java.util.List;
import java.util.Map;


public interface NewsTabService extends IBaseService<NewsTab,Long> {

    /**
     * 获取显示的资讯页签
     * @return 资讯页签列表
     */
    List<NewsTab> fetchEnableTabs();
    
    /**
     * 条件查询
     * @param param
     * @return
     */
	List<NewsTab> selectNewsTabsByConditions(Map<String, Object> param);

}
