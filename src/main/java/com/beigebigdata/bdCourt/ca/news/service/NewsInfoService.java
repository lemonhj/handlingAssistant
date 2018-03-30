package com.beigebigdata.bdCourt.ca.news.service;

import com.beigebigdata.bdCourt.ca.admin.common.page.PageList;
import com.beigebigdata.bdCourt.ca.admin.common.page.PageProperty;
import com.beigebigdata.bdCourt.ca.common.entity.UpdateEntity;
import com.beigebigdata.bdCourt.ca.news.entity.MemberNews;
import com.beigebigdata.bdCourt.ca.news.entity.NewsInfo;
import com.septinary.common.web.basic.service.IBaseService;

import java.util.List;


public interface NewsInfoService extends IBaseService<NewsInfo,Long> {


    /**
     * 获取指定类型的增量数据
     * @param newsType 新闻类型
     * @param updateEntity
     * @return
     */
    List<NewsInfo> fetchUpdateNews(int newsType, UpdateEntity updateEntity);


    /**
     * 获取更多新闻
     * @param newsType
     * @param updateEntity
     * @return
     */
    List<NewsInfo> fetchMoreNews(int newsType, UpdateEntity updateEntity);

    /**
     * 收藏新闻
     * @param memCode
     * @param newsId
     * @return
     */
    List<NewsInfo> addCollectionNews(String memCode, int newsId);


    MemberNews collectNews(String memCode, long newsId);

    MemberNews removeCollectNews(String memCode, long newsId);

    /**
     * 获取此新闻的收藏状态
     * @param memCode
     * @param newsId
     * @return
     */
    MemberNews collectNewsStatus(String memCode, long newsId);
    
    /**
     * 分页查询资讯
     * @param newsType
     * @return
     */
    PageList<NewsInfo> pagingNews(PageProperty pp);
    
}
