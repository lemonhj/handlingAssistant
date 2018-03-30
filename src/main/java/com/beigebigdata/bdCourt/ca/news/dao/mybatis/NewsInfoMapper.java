package com.beigebigdata.bdCourt.ca.news.dao.mybatis;

import com.beigebigdata.bdCourt.ca.news.dao.NewsInfoDao;
import com.beigebigdata.bdCourt.ca.news.entity.NewsInfo;
import tk.mybatis.mapper.common.Mapper;

public interface NewsInfoMapper extends NewsInfoDao, Mapper<NewsInfo> {
}