package com.beigebigdata.bdCourt.ca.law.service.impl;

import com.beigebigdata.bdCourt.ca.law.dao.LawBaseDao;
import com.beigebigdata.bdCourt.ca.law.entity.LawBase;
import com.beigebigdata.bdCourt.ca.law.service.LawBaseService;
import com.beigebigdata.bdCourt.ca.law.view.LawBookView;
import com.septinary.common.web.basic.dao.IBaseDao;
import com.septinary.common.web.basic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: LawBaseServiceImpl
 * @Description:
 * @date 16/10/31
 */
@Service("lawBaseService")
public class LawBaseServiceImpl extends BaseService<LawBase,Long> implements LawBaseService {

    @Autowired
    private LawBaseDao lawBaseDao;

    @Override
    public IBaseDao<LawBase, Long> getBaseDao() {
        return lawBaseDao;
    }

	@Override
	public List<LawBookView> queryLawBooksByCauseNo(Integer causeNo) {

		return this.lawBaseDao.queryLawBooksByCauseNo(causeNo);
	}

    @Override
    public List<HashMap<String, Integer>> queryLawBookNosByCauseNo(Integer causeNo) {
        return lawBaseDao.queryLawBookNosByCauseNo(causeNo);
    }

    @Override
    public List<LawBookView> queryLawBooksByBookNOs(Set<Integer> bookNOs) {
        return lawBaseDao.queryLawBooksByBookNOs(bookNOs);
    }

    @Override
    public LawBase fetchCodeInfo(Long lawNo) {
        return lawBaseDao.fetchCodeInfo(lawNo);
    }

    @Override
    public List<LawBookView> queryLawBooksType(Set<Integer> bookNOs) {
        return lawBaseDao.queryLawBooksType(bookNOs);
    }

	/**
	 * 获取法律类型 V1.1
	 * @return
	 */
	@Override
	public List<Integer> getLayTypeList() {
		return lawBaseDao.queryLayType();
	}

    @Override
    public List<LawBookView> getLaysDataListByMap(HashMap<String, Object> map) {
        // TODO Auto-generated method stub
        return lawBaseDao.queryLaysDataListByMap(map);
    }

}
