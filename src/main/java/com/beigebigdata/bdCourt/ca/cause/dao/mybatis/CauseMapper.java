package com.beigebigdata.bdCourt.ca.cause.dao.mybatis;

import com.beigebigdata.bdCourt.ca.cause.dao.CauseDao;
import com.beigebigdata.bdCourt.ca.cause.entity.Cause;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CauseMapper
 * @Description: 案由数据映射
 * @date 16/10/21
 */
@Repository("causeDao")
public interface CauseMapper extends CauseDao, Mapper<Cause> {
}