package com.beigebigdata.bdCourt.ca.news.dao;

import com.beigebigdata.bdCourt.ca.news.entity.NewsTab;
import com.septinary.common.web.basic.dao.IBaseDao;

import java.util.List;
import java.util.Map;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: NewsTabDao
 * @Description:
 * @date 17/6/22
 */
public interface NewsTabDao extends IBaseDao<NewsTab,Long> {
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
