package com.beigebigdata.bdCourt.ca.api.dao;

import com.beigebigdata.bdCourt.ca.api.entity.Banner;
import com.septinary.common.web.basic.dao.IBaseDao;

import java.util.List;
import java.util.Map;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: BannerDao
 * @Description:
 * @date 17/6/22
 */
public interface BannerDao extends IBaseDao<Banner,Long> {
    /**
     * 获取相应类型的banner
     * @param bannerType banner类型
     * @return
     */
    List<Banner> fetchWithType(int bannerType);
    
    /**
     * 根据是否显示、显示顺序、触发类型、跳转链接的管理获取相应类型的banner
     * @param bannerType banner类型
     * @return
     */
    List<Banner> list(Map<String, Object> map);
    
}
