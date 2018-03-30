package com.beigebigdata.bdCourt.ca.api.dao.mybatis;

import com.beigebigdata.bdCourt.ca.api.dao.MemberDao;
import com.beigebigdata.bdCourt.ca.api.entity.Member;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: MemberMapper
 * @Description:会员映射
 * @date 16/10/21
 */
@Repository("memberDao")
public interface MemberMapper extends MemberDao, Mapper<Member> {
}