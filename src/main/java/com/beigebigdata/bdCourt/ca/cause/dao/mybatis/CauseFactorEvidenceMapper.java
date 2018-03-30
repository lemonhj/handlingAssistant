package com.beigebigdata.bdCourt.ca.cause.dao.mybatis;

import com.beigebigdata.bdCourt.ca.cause.dao.CauseFactorEvidenceDao;
import com.beigebigdata.bdCourt.ca.cause.entity.CauseFactorEvidence;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;


/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CauseFactorEvidenceMapper
 * @Description: 罪种要素与证据关系映射
 * @date 16/10/21
 */
@Repository("causeFactorEvidenceDao")
public interface CauseFactorEvidenceMapper extends CauseFactorEvidenceDao, Mapper<CauseFactorEvidence> {
}