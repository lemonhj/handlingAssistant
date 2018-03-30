package com.beigebigdata.bdCourt.ca.cause.dao.mybatis;

import com.beigebigdata.bdCourt.ca.cause.dao.CauseFactorDao;
import com.beigebigdata.bdCourt.ca.cause.entity.CauseFactor;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CauseFactorMapper
 * @Description: 罪种要素信息
 * @date 16/11/1
 */
@Repository("causeFactorDao")
public interface CauseFactorMapper extends CauseFactorDao, Mapper<CauseFactor> {
}