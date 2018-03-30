package com.beigebigdata.bdCourt.ca.api.service.impl;

import com.beigebigdata.bdCourt.ca.api.dao.BannerDao;
import com.beigebigdata.bdCourt.ca.api.entity.Banner;
import com.beigebigdata.bdCourt.ca.api.service.BannerService;
import com.septinary.common.web.basic.dao.IBaseDao;
import com.septinary.common.web.basic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("bannerService")
public class BannerServiceImpl extends BaseService<Banner,Long> implements BannerService {

    @Autowired
    private BannerDao bannerDao;
    
	@Override
	public IBaseDao<Banner, Long> getBaseDao() {
		// TODO Auto-generated method stub
		return this.bannerDao;
	}


	@Override
	public List<Banner> fetchWithType(int bannerType) {
		return bannerDao.fetchWithType(bannerType);
	}


	@Override
	public List<Banner> list(Map<String, Object> map) {
		return bannerDao.list(map);
	}
}
