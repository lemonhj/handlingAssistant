package com.beigebigdata.bdCourt.ca.law.dao.mybatis;

import com.beigebigdata.bdCourt.ca.law.dao.LawBaseDao;
import com.beigebigdata.bdCourt.ca.law.entity.LawBase;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: LawBaseMapper
 * @Description:法律法规基本信息
 * @date 16/10/31
 */
@Repository("lawBaseMapper")
public interface LawBaseMapper extends LawBaseDao, Mapper<LawBase> {
}