package com.beigebigdata.bdCourt.ca.news.controller;

import com.beigebigdata.bdCourt.ca.common.entity.UpdateEntity;
import com.beigebigdata.bdCourt.ca.news.entity.MemberNews;
import com.beigebigdata.bdCourt.ca.news.entity.NewsInfo;
import com.beigebigdata.bdCourt.ca.news.service.MemberNewsService;
import com.beigebigdata.bdCourt.ca.news.service.NewsInfoService;
import com.septinary.common.util.Assert;
import com.septinary.common.util.VerifyUtils;
import com.septinary.common.util.ViewJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;


/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: newsInfoController
 * @Description:
 * @date 17/6/22
 */
@Controller
@RequestMapping("v2.0/news")
public class NewsInfoController {

    @Autowired
    private NewsInfoService newsInfoService;

    @Autowired
    private MemberNewsService memberNewsService;


    /**
     * 新建新闻
     * @param newsInfo
     * @return
     */
    @RequestMapping(value = "/newsInfo",method = RequestMethod.POST)
    @ResponseBody
    public ViewJSON<Object> addNews(NewsInfo newsInfo){
        VerifyUtils.verifyReviseInfo(newsInfo.getPublishTime(), newsInfo.getTitle(), newsInfo.getType());
        newsInfo.setCreateTime(new Date());
        newsInfo = newsInfoService.insertSelective(newsInfo);
        if (newsInfo == null) return new ViewJSON<>("500","SERVER ERROR","新闻保存异常");
        return new ViewJSON<>("200","success");
    }


    /**
     * 启用、禁用新闻
     * @param id
     * @param order
     * @return
     */
    @RequestMapping(value = "/{id}/status",method = RequestMethod.PUT)
    @ResponseBody
    public ViewJSON<Object> enablenewsInfo(@PathVariable(value = "id")long id,float order,boolean isShow){
        Assert.NotNull(id, "页签id不能null！");
        Assert.NotNull(order, "页签排序编号不能null！");
        NewsInfo newsInfo = new NewsInfo();
        newsInfo.setIsShow(isShow);
        newsInfo.setId(id);
        newsInfo.setUpdateTime(new Date());

        newsInfo = newsInfoService.updateByPrimaryKeySelective(newsInfo);
        if (newsInfo == null) return new ViewJSON<>("500","SERVER ERROR","页签状态变更失败");
        return new ViewJSON<>("200","success");
    }


    /**
     * 获取新闻
     * @return
     */
    @RequestMapping(value = "/newsInfo",method = RequestMethod.GET)
    @ResponseBody
    public ViewJSON<List<NewsInfo>> newsInfo(){
        NewsInfo newsInfo = new NewsInfo();
        newsInfo.setIsShow(true);
        newsInfo.setIsDelete(false);

        List<NewsInfo> newsInfos = newsInfoService.select(newsInfo);
        return new ViewJSON<>("200","success",newsInfos);
    }

    /**
     * 获取指定新闻
     * @return
     */
    @RequestMapping(value = "/news/detail",method = RequestMethod.GET)
    @ResponseBody
    public ViewJSON<NewsInfo> newsInfo(long id){

        NewsInfo newsInfo = newsInfoService.selectByPrimaryKey(id);
        return new ViewJSON<>("200","success",newsInfo);
    }

    /**
     * @api {get} /v2.0/news/:newsType/newsInfos 刷新资讯列表
     * @apiVersion 0.2.0
     * @apiName flush Information
     * @apiGroup Informations
     *
     * @apiParam {Number} newsType 资讯类型.
     * @apiParam {Number} count 需要获取的条数.
     *
     * @apiSuccess {String} code 返回状态码.
     * @apiSuccess {String} info 返回信息.
     * @apiSuccess {String} memo 返回提示.
     * @apiSuccess {String} data 返回数据.
     * @apiSuccess {String} hint 状态描述.
     * @apiSuccess {String} id 资讯ID.
     * @apiSuccess {String} title 资讯标题.
     * @apiSuccess {String} thumPic 资讯缩略图.
     * @apiSuccess {String} updateTime 资讯更新时间.
     * @apiSuccess {String} type 资讯类型.
     *
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *      "code": "200",
     *      "info": "OK",
     *      "memo": "服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）",
     *      "data":[
     *         {
     *          "id": 10,
     *          "title": "周强：为建设平安中国法治中国作出积极贡献",
     *          "thumPic": "/images/news/yw5-0.jpg",
     *          "updateTime": "2017-06-23 15:12:14",
     *          "type": 2
     *         }],
     *         "hint": "success"
     *      }
     *
     */
    @RequestMapping(value = "/{newsType}/newsInfos",method = RequestMethod.GET)
    @ResponseBody
    public ViewJSON<List<NewsInfo>> newsInfos(@PathVariable(value = "newsType")int newsType,UpdateEntity updateEntity){
        VerifyUtils.verifyReviseInfo(updateEntity.getCount());

        List<NewsInfo> newsInfos = newsInfoService.fetchUpdateNews(newsType,updateEntity);
        return new ViewJSON<>("200","success",newsInfos);
    }

    /**
     * @api {get} /v2.0/news/:newsType/moreNewsInfos 获取更多资讯
     * @apiVersion 0.2.0
     * @apiName More Informations
     * @apiGroup Informations
     *
     * @apiParam {Number} newsType 资讯类型.
     * @apiParam {Number} count 需要获取的条数.
     * @apiParam {Date} max 当前最后一头咨询的update_time.
     *
     * @apiSuccess {String} code 返回状态码.
     * @apiSuccess {String} info 返回信息.
     * @apiSuccess {String} memo 返回提示.
     * @apiSuccess {String} data 返回数据.
     * @apiSuccess {String} hint 状态描述.
     *
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *      "code": "200",
     *      "info": "OK",
     *      "memo": "服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）",
     *      "data":[
     *         {
     *          "id": 10,
     *          "title": "周强：为建设平安中国法治中国作出积极贡献",
     *          "thumPic": "/images/news/yw5-0.jpg",
     *          "updateTime": "2017-06-23 15:12:14",
     *          "type": 2
     *         }],
     *         "hint": "success"
     *      }
     */
    @RequestMapping(value = "/{newsType}/moreNewsInfos",method = RequestMethod.GET)
    @ResponseBody
    public ViewJSON<List<NewsInfo>> moreNewsInfos(@PathVariable(value = "newsType")int newsType,UpdateEntity updateEntity){
        VerifyUtils.verifyReviseInfo(updateEntity.getCount(), updateEntity.getMax());

        List<NewsInfo> newsInfos = newsInfoService.fetchMoreNews(newsType, updateEntity);

        return new ViewJSON<>("200","success",newsInfos);
    }

    /**
     * @api {post} /v2.0/news/:memCode/collections/:newsId 收藏资讯
     * @apiVersion 0.2.0
     * @apiName collect information
     * @apiGroup Informations
     *
     * @apiParam {String} memCode 当前用户Code.
     * @apiParam {Number} newsId 收藏资讯ID.
     *
     * @apiSuccess {String} code 返回状态码.
     * @apiSuccess {String} info 返回信息.
     * @apiSuccess {String} memo 返回提示.
     * @apiSuccess {String} data 返回数据.
     * @apiSuccess {String} hint 状态描述.
     *
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *      "code": "200",
     *      "info": "OK",
     *      "memo": "服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）",
     *      "hint": "success"
     *      }
     *
     * @apiErrorExample Error-Response:
     *     HTTP/1.1 200 OK
     *     {
     *      "code": "1102",
     *      "info": "调用时机不当",
     *      "memo": "此资讯已收藏过",
     *      "hint": ""
     *      }
     *
     */
    @RequestMapping(value = "/{memCode}/collections/{newsId}",method = RequestMethod.POST)
    @ResponseBody
    public ViewJSON<List<NewsInfo>> collectNews(@PathVariable(value = "memCode")String memCode, @PathVariable(value = "newsId")long newsId){
        VerifyUtils.verifyReviseInfo(memCode);

        MemberNews memberNews = newsInfoService.collectNews(memCode,newsId);
        if (memberNews.getCreateTime() == null) return new ViewJSON<>("1102","","此新闻已收藏过");
        if (memberNews == null) return new ViewJSON<>("500","SERVER ERROR","收藏新闻失败");
        return new ViewJSON<>("200","success");
    }


    /**
     * @api {delete} /v2.0/news/:memCode/collections/:newsId 移除收藏
     * @apiVersion 0.2.0
     * @apiName remove collect information
     * @apiGroup Informations
     *
     * @apiParam {String} memCode 当前用户Code.
     * @apiParam {Number} newsId 收藏资讯ID.
     *
     * @apiSuccess {String} code 返回状态码.
     * @apiSuccess {String} info 返回信息.
     * @apiSuccess {String} memo 返回提示.
     * @apiSuccess {String} data 返回数据.
     * @apiSuccess {String} hint 状态描述.
     * @apiSuccess {String} id 资讯ID.
     * @apiSuccess {String} title 资讯标题.
     * @apiSuccess {String} thumPic 资讯缩略图.
     * @apiSuccess {String} updateTime 资讯更新时间.
     * @apiSuccess {String} type 资讯类型.
     *
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *      "code": "200",
     *      "info": "OK",
     *      "memo": "服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）",
     *      "hint": "success"
     *      }
     *
     * @apiErrorExample Error-Response:
     *     HTTP/1.1 200 OK
     *     {
     *      "code": "1102",
     *      "info": "调用时机不当",
     *      "memo": "此资讯未被收藏过",
     *      "hint": ""
     *      }
     *
     */
    @RequestMapping(value = "/{memCode}/collections/{newsId}",method = RequestMethod.DELETE)
    @ResponseBody
    public ViewJSON<List<NewsInfo>> removeCollectNews(@PathVariable(value = "memCode")String memCode,@PathVariable(value = "newsId")long newsId){
        VerifyUtils.verifyReviseInfo(memCode, newsId);

        MemberNews memberNews = newsInfoService.removeCollectNews(memCode, newsId);
        if (memberNews.getCreateTime() == null) return new ViewJSON<>("1102","","此新闻未被收藏过");
        if (memberNews == null) return new ViewJSON<>("500","SERVER ERROR","移除收藏失败");
        return new ViewJSON<>("200","success");
    }


    /**
     * @api {get} /v2.0/news/:memCode/collections/:newsId 获取此资讯的收藏状态
     * @apiVersion 0.2.0
     * @apiName collect information status
     * @apiGroup Informations
     *
     * @apiParam {String} memCode 当前用户Code.
     * @apiParam {Number} newsId 收藏资讯ID.
     *
     * @apiSuccess {String} code 返回状态码.
     * @apiSuccess {String} info 返回信息.
     * @apiSuccess {String} memo 返回提示.
     * @apiSuccess {String} data 返回数据.
     * @apiSuccess {String} hint 状态描述.
     *
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *      "code": "200",
     *      "info": "OK",
     *      "memo": "服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）",
     *      "data": false,
     *      "hint": "success"
     *      }
     *
     */
    @RequestMapping(value = "/{memCode}/collections/{newsId}",method = RequestMethod.GET)
    @ResponseBody
    public ViewJSON<Boolean> collectNewsStatus(@PathVariable(value = "memCode")String memCode,@PathVariable(value = "newsId")long newsId){
        VerifyUtils.verifyReviseInfo(memCode, newsId);

        MemberNews memberNews = newsInfoService.collectNewsStatus(memCode, newsId);
        boolean collectionStatus = memberNews == null ? false : true;
        return new ViewJSON<>("200","success",collectionStatus);
    }




}
