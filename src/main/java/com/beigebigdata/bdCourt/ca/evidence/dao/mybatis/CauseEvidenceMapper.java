package com.beigebigdata.bdCourt.ca.evidence.dao.mybatis;


import com.beigebigdata.bdCourt.ca.evidence.dao.CauseEvidenceDao;
import com.beigebigdata.bdCourt.ca.evidence.entity.CauseEvidence;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CauseEvidenceMapper
 * @Description: 案由要素与证据关系数据映射
 * @date 16/10/25
 */
public interface CauseEvidenceMapper extends CauseEvidenceDao, Mapper<CauseEvidence> {

}