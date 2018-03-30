package com.beigebigdata.bdCourt.ca.api.service;


import com.beigebigdata.bdCourt.ca.api.entity.Banner;
import com.septinary.common.web.basic.service.IBaseService;

import java.util.List;
import java.util.Map;


public interface BannerService extends IBaseService<Banner,Long> {


    /**
     * 获取相应类型的banner
     * @param bannerType banner类型
     * @return
     */
    List<Banner> fetchWithType(int bannerType);
    
    /**
     * 根据是否显示、显示顺序、触发类型、跳转链接的管理获取相应类型的banner
     * @param
     * @return
     */
    List<Banner> list(Map<String, Object> map);
}
