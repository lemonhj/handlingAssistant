package com.beigebigdata.bdCourt.ca.news.service.impl;

import com.beigebigdata.bdCourt.ca.admin.common.page.PageList;
import com.beigebigdata.bdCourt.ca.admin.common.page.PageProperty;
import com.beigebigdata.bdCourt.ca.admin.common.page.PageUtil;
import com.beigebigdata.bdCourt.ca.api.entity.Member;
import com.beigebigdata.bdCourt.ca.api.service.MemberService;
import com.beigebigdata.bdCourt.ca.common.entity.UpdateEntity;
import com.beigebigdata.bdCourt.ca.news.dao.NewsInfoDao;
import com.beigebigdata.bdCourt.ca.news.entity.MemberNews;
import com.beigebigdata.bdCourt.ca.news.entity.NewsInfo;
import com.beigebigdata.bdCourt.ca.news.service.MemberNewsService;
import com.beigebigdata.bdCourt.ca.news.service.NewsInfoService;
import com.septinary.common.web.basic.dao.IBaseDao;
import com.septinary.common.web.basic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service("newsInfoService")
public class NewsInfoServiceImpl extends BaseService<NewsInfo,Long> implements NewsInfoService {

    @Autowired
    private NewsInfoDao newsInfoDao;

	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberNewsService memberNewsService;
    
	@Override
	public IBaseDao<NewsInfo, Long> getBaseDao() {
		// TODO Auto-generated method stub
		return this.newsInfoDao;
	}


	@Override
	public List<NewsInfo> fetchUpdateNews(int newsType, UpdateEntity updateEntity) {
		return newsInfoDao.fetchUpdateNews(newsType,updateEntity.getCount(),updateEntity.getPage()*updateEntity.getCount());
	}

	@Override
	public List<NewsInfo> fetchMoreNews(int newsType, UpdateEntity updateEntity) {
		SimpleDateFormat format =  new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		try {
			date = format.parse(format.format(updateEntity.getMax()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newsInfoDao.fetchMoreNews(newsType,updateEntity.getCount(),date,updateEntity.getPage()*updateEntity.getCount());
	}

	@Override
	public List<NewsInfo> addCollectionNews(String memCode, int newsId) {
		return newsInfoDao.addCollectionNews(memCode,newsId);
	}

	@Override
	public MemberNews collectNews(String memCode, long newsId) {

		Member member = new Member();
		member.setMemCode(memCode);
		member = memberService.selectOne(member);
		MemberNews memberNews = new MemberNews();
		memberNews.setMemId(member.getMemId());
		memberNews.setNewsId(newsId);
		memberNews.setIsDelete(false);
		MemberNews mn = memberNewsService.selectOne(memberNews);
		if (mn != null) return new MemberNews();
		memberNews.setCreateTime(new Date());
		memberNews = memberNewsService.insertSelective(memberNews);
		return memberNews;
	}

	@Override
	public MemberNews removeCollectNews(String memCode, long newsId) {
		Member member = new Member();
		member.setMemCode(memCode);
		member = memberService.selectOne(member);

		MemberNews memberNews = new MemberNews();
		memberNews.setMemId(member.getMemId());
		memberNews.setNewsId(newsId);
		memberNews.setIsDelete(false);
		memberNews = memberNewsService.selectOne(memberNews);
		if (memberNews == null) return new MemberNews();
		memberNews.setIsDelete(true);
		memberNews.setDeleteTime(new Date());
		memberNews = memberNewsService.updateByPrimaryKey(memberNews);
		return memberNews;
	}

	@Override
	public MemberNews collectNewsStatus(String memCode, long newsId) {
		Member member = new Member();
		member.setMemCode(memCode);
		member = memberService.selectOne(member);

		MemberNews memberNews = new MemberNews();
		memberNews.setMemId(member.getMemId());
		memberNews.setNewsId(newsId);
		memberNews.setIsDelete(false);
		memberNews = memberNewsService.selectOne(memberNews);
		return memberNews;
	}


//	@Override
///	public List<NewsInfo> pagingNews(Map<String, Object> param) {
		// TODO Auto-generated method stub
//		return newsInfoDao.pagingNews(param);
//	}
	@Override
	public PageList<NewsInfo> pagingNews(PageProperty pp) {
		int count = newsInfoDao.pagingNewsCount(pp.getParamMap());
		int start = PageUtil.getStart(pp.getNpage(), count, pp.getNpagesize());
		int end = pp.getNpagesize();//pp.getNpagesize();
		pp.putParamMap("page", start);
		pp.putParamMap("count", end);
		PageList<NewsInfo> pageList = new PageList<NewsInfo>(pp, count, newsInfoDao.pagingNews(pp.getParamMap()));
		return pageList;
	}


}
