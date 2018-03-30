package com.beigebigdata.bdCourt.ca.news.dao.mybatis;


import com.beigebigdata.bdCourt.ca.news.dao.MemberNewsDao;
import com.beigebigdata.bdCourt.ca.news.entity.MemberNews;
import tk.mybatis.mapper.common.Mapper;

public interface MemberNewsMapper extends MemberNewsDao, Mapper<MemberNews> {
}