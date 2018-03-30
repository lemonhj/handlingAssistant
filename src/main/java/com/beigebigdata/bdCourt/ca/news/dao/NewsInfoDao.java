package com.beigebigdata.bdCourt.ca.news.dao;

import com.beigebigdata.bdCourt.ca.news.entity.NewsInfo;
import com.septinary.common.web.basic.dao.IBaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: NewsInfoDao
 * @Description:
 * @date 17/6/22
 */
public interface NewsInfoDao extends IBaseDao<NewsInfo,Long> {


    /**
     * 获取指定类型的增量数据
     * @param newsType 新闻类型
     * @return
     */
    List<NewsInfo> fetchUpdateNews(@Param("newsType") int newsType, @Param("count") int count, @Param("page") int page);

    /**
     * 获取更多新闻
     * @param newsType
     * @return
     */
    List<NewsInfo> fetchMoreNews(@Param("newsType") int newsType, @Param("count") int count, @Param("max") Date max, @Param("page") int page);

    /**
     * 收藏新闻
     * @param memCode
     * @param newsId
     * @return
     */
    List<NewsInfo> addCollectionNews(@Param("memCode") String memCode, @Param("newsId") int newsId);
    
    /**
     * 分页查询资讯
     * @param param
     * @return
     */
    List<NewsInfo> pagingNews(Map<String, Object> param);

    /**
     * 分页查询资讯总数
     * @param param
     * @return
     */
   int pagingNewsCount(Map<String, Object> param);
    
}
