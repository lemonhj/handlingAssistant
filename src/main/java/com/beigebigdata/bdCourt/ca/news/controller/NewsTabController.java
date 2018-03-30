package com.beigebigdata.bdCourt.ca.news.controller;

import com.beigebigdata.bdCourt.ca.news.entity.NewsTab;
import com.beigebigdata.bdCourt.ca.news.service.NewsTabService;
import com.septinary.common.util.Assert;
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
 * @ClassName: NewsTabController
 * @Description:
 * @date 17/6/22
 */
@Controller
@RequestMapping("v2.0/newsTabs")
public class NewsTabController {

    @Autowired
    private NewsTabService newsTabService;


    /**
     * 新建新闻页签
     * @param tabName
     * @return
     */
    @RequestMapping(value = "/newsTab",method = RequestMethod.POST)
    @ResponseBody
    public ViewJSON<Object> addTab(String tabName){
        Assert.NotNull(tabName, "页签名称不能null！");
        NewsTab newsTab = new NewsTab();
        newsTab.setCreateTime(new Date());
        newsTab.setIsDelete(false);
        newsTab.setTabName(tabName);

        newsTab = newsTabService.insertSelective(newsTab);
        if (newsTab == null) return new ViewJSON<>("500","SERVER ERROR","tab保存异常");
        return new ViewJSON<>("200","success");
    }


    /**
     * 启用、禁用页签
     * @param id
     * @param order
     * @return
     */
    @RequestMapping(value = "/newsTab/{id}/status",method = RequestMethod.PUT)
    @ResponseBody
    public ViewJSON<Object> enableNewsTab(@PathVariable(value = "id")long id,float order,boolean isShow){
        Assert.NotNull(id, "页签id不能null！");
        Assert.NotNull(order, "页签排序编号不能null！");
        NewsTab newsTab = new NewsTab();
        newsTab.setIsShow(isShow);
        newsTab.setId(id);
        newsTab.setShowOrder(order);
        newsTab.setUpdateTime(new Date());

        newsTab = newsTabService.updateByPrimaryKeySelective(newsTab);
        if (newsTab == null) return new ViewJSON<>("500","SERVER ERROR","页签状态变更失败");
        return new ViewJSON<>("200","success");
    }


    /**
     * @api {get} /v2.0/newsTabs/tabs 获取显示的页签
     * @apiVersion 0.2.0
     * @apiName get tabs
     * @apiGroup Tabs
     *
     * @apiSuccess {String} code 返回状态码.
     * @apiSuccess {String} info 返回信息.
     * @apiSuccess {String} memo 返回提示.
     * @apiSuccess {String} data 返回数据.
     * @apiSuccess {String} hint 状态描述.
     * @apiSuccess {Number} id 页签ID.
     * @apiSuccess {String} tabName 页签名字.
     * @apiSuccess {Number} showOrder 页签位置.
     *
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *      "code": "200",
     *      "info": "OK",
     *      "memo": "服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）",
     *      "data": [
     *          {
     *              "id": 1,
     *              "tabName": "综合",
     *              "showOrder": 1
     *          },
     *          {
     *              "id": 2,
     *              "tabName": "新闻",
     *              "showOrder": 2
     *          },
     *          {
     *              "id": 3,
     *              "tabName": "评论",
     *              "showOrder": 3
     *          },
     *          {
     *              "id": 4,
     *              "tabName": "案例",
     *              "showOrder": 4
     *          },
     *          {
     *              "id": 5,
     *              "tabName": "知识",
     *              "showOrder": 5
     *          }],
     *       "hint": "success"
     *      }
     */
    @RequestMapping(value = "/tabs",method = RequestMethod.GET)
    @ResponseBody
    public ViewJSON<List<NewsTab>> newsTabs(){
        NewsTab newsTab = new NewsTab();
        newsTab.setIsShow(true);
        newsTab.setIsDelete(false);

        //List<NewsTab> newsTabs = newsTabService.select(newsTab);
        List<NewsTab> newsTabs = newsTabService.fetchEnableTabs();
        if (newsTabs == null) return new ViewJSON<>("500","SERVER ERROR","获取页签失败");
        return new ViewJSON<>("200","success",newsTabs);
    }



}
