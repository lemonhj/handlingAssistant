package com.beigebigdata.bdCourt.ca.api.service.impl;

import com.beigebigdata.bdCourt.ca.api.dao.MemberDao;
import com.beigebigdata.bdCourt.ca.api.entity.Member;
import com.beigebigdata.bdCourt.ca.api.service.MemberService;
import com.beigebigdata.bdCourt.ca.api.view.MemberView;
import com.beigebigdata.bdCourt.ca.news.entity.NewsInfo;
import com.septinary.common.util.ViewJSON;
import com.septinary.common.util.crypt.MD5;
import com.septinary.common.util.smart.HashMapSmart;
import com.septinary.common.web.basic.dao.IBaseDao;
import com.septinary.common.web.basic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;


/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: MemberServiceImpl
 * @Description:
 * @date 16/10/21
 */
@Service("memberService")
public class MemberServiceImpl extends BaseService<Member,Long> implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public IBaseDao<Member, Long> getBaseDao() {
        return memberDao;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public String password(String password, String privateKey) {
//		String publicKey = "";
        return new MD5().encrypt(
                password,
//			new AES().decrypt(
//				password, new HashMapSmart<>().add("key", publicKey)
//			),
                new HashMapSmart<>().add("format", "${key}${source}").add("key", privateKey).add("case", "upper")
        );
    }

    @Override
    public int isTelRegisted(Member member) {
		Member member4Db = new Member();
		member4Db.setMemUsername(member.getMemUsername());
		if (this.select(member4Db).size() >0) return 1;

        if (member.getMemTel() != null && !member.getMemTel().trim().isEmpty()){
            Member mem = new Member();
            mem.setMemTel(member.getMemTel());
            if (this.select(mem).size() > 0) return 2;
        }

        return 0;
    }

	@Override
	public List<NewsInfo> fetchCollectionNews(String memCode) {
		return memberDao.fetchCollectionNews(memCode);
	}


	/***********************个人中心相关接口(lihuayang) START***************************/
	/**
	 * 查询会员用户信息
	 */
	@Override
	public MemberView getMemberViewInfo(String code) {
		return this.memberDao.selectMemberViewInfo(code);
	}

	
	/**
	 * 修改会员信息
	 * @param code
	 * @param type 类型：包括修改身份(identity),手机绑定(tel),密码(psd)
	 * @param value
	 * @param original
	 * @return
	 */
	@Override
	public ViewJSON<Void> updataMemberInfo(String code, String type, String value, String psdOriginal) {
		HashMap<String, Object> map = new HashMap<String, Object>();//保存数据的map
		HashMap<String, Object> queryMap = new HashMap<String, Object>();//查询数据的map
		map.put("code", code);
		if("identity".equals(type)) {//修改用户身份
			map.put("memType", Integer.valueOf(value));
		} else if("tel".equals(type)) { //绑定手机
			queryMap.put("memTel", value);
			Member member = this.memberDao.selectMemberInfoByMap(queryMap);
			if(null != member) {
				return new ViewJSON<Void>("403","Forbidden", "该手机号已被绑定！");
			} else {
				map.put("memTel", value);
			}
		} else if("psd".equals(type)) { //修改密码
			queryMap.put("code", code);
			Member member = this.memberDao.selectMemberInfoByMap(queryMap);
			if(null != member) {
				String originalMd5 = this.password(psdOriginal, member.getMemSecretkey());
				if(!member.getMemPassword().equals(originalMd5)) {
					return new ViewJSON<Void>("403", "Forbidden", "旧密码输入错误！");
				} else {
					map.put("memPassword", this.password(value, member.getMemSecretkey()));
				}
			} else {
				return new ViewJSON<Void>("1105", "NOT FOUND", "此用户不存在！");
			}
		}
		map.put("flag", "upd");//mybatis XML中flag为upd，说明是个人中心修改相关的信息，用code作为更新条件
		int count = this.memberDao.updateMemberInfoByMap(map);
		if(count > 0) {
			return new ViewJSON<Void>("200", "ok", "修改成功！");
		} else {
			return new ViewJSON<Void>("400", "INVALID REQUEST", "修改失败！");
		}
	}
	
	
	/***
	 * 找回密码，通过手机号码号码
	 * @param tel
	 * @param psd
	 * @return
	 */
	@Override
	public ViewJSON<Void> updateMemberPsdByTel(String tel, String psd) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("memTel", tel);
		Member member = this.memberDao.selectMemberInfoByMap(map);
		if(null == member) {
			return new ViewJSON<Void>("404", "NOT FOUND", "该手机未绑定账户！");
		} else {
			map.put("flag", "back");//mybatis XML中flag为back，说明是找回密码，用电话号码作为更新条件
			map.put("memPassword", this.password(psd, member.getMemSecretkey()));
			int count = this.memberDao.updateMemberInfoByMap(map);
			if(count > 0) {
				return new ViewJSON<Void>("200", "ok", "修改成功！");
			} else {
				return new ViewJSON<Void>("400", "INVALID REQUEST", "找回失败！");
			}
		}
	}

	
	/**
	 * 根据手机号码匹配用户信息，返回用户的部分信息
	 * @param map
	 * @return
	 */
	@Override
	public Member findMemberInfoByMap(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return this.memberDao.selectMemberInfoByMap(map);
	}
    
    /***********************个人中心相关接口 END****************************************/
}
