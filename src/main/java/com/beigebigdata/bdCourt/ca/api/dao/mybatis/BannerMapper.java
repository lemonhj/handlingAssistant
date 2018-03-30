package com.beigebigdata.bdCourt.ca.api.dao.mybatis;



import com.beigebigdata.bdCourt.ca.api.dao.BannerDao;
import com.beigebigdata.bdCourt.ca.api.entity.Banner;
import tk.mybatis.mapper.common.Mapper;

public interface BannerMapper extends BannerDao, Mapper<Banner> {
}