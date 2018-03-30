package com.beigebigdata.bdCourt.ca.cause.dao.mybatis;

import com.beigebigdata.bdCourt.ca.cause.dao.CauseFactorLawDao;
import com.beigebigdata.bdCourt.ca.cause.entity.CauseFactorLaw;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CauseFactorEvidenceMapper
 * @Description: 罪种要素与法律关系映射
 * @date 16/10/21
 */
@Repository("causeFactorLawDao")
public interface CauseFactorLawMapper extends CauseFactorLawDao, Mapper<CauseFactorLaw> {
}