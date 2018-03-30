package com.beigebigdata.bdCourt.ca.api.controller;


import com.beigebigdata.bdCourt.ca.api.entity.Banner;
import com.beigebigdata.bdCourt.ca.api.service.BannerService;
import com.septinary.common.util.Assert;
import com.septinary.common.util.VerifyUtils;
import com.septinary.common.util.ViewJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;


/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: BannerController
 * @Description:
 * @date 17/6/22
 */
@RestController
@RequestMapping(value = "/v2.0/banners")
public class BannerController {

    @Autowired
    private BannerService bannerService;


    /**
     * 添加banner
     * @param banner
     * @return
     *
     */
    @PostMapping(value = "/banner")
    public ViewJSON<Object> addBanner(Banner banner){
        VerifyUtils.verifyReviseInfo(banner.getImgUrl(), banner.getActionType(), banner.getBannerType());
        banner.setCreateTime(new Date());
        banner = bannerService.insertSelective(banner);
        if (banner == null) return new ViewJSON<>("500","SERVER ERROR","banner保存异常");
        return new ViewJSON<>("200","success");
    }


    /**
     * 启用、禁用banner
     * @param id
     * @param order
     * @return
     */
    @PutMapping(value = "/banner/{id}/status")
    public ViewJSON<Object> enableNewsTab(@PathVariable(value = "id")long id,float order,boolean isShow){
        Assert.NotNull(id, "页签id不能null！");
        Assert.NotNull(order, "页签排序编号不能null！");
        Banner banner = new Banner();
        banner.setIsShow(isShow);
        banner.setId(id);
        banner.setShowOrder(order);
        banner.setUpdateTime(new Date());

        banner = bannerService.updateByPrimaryKeySelective(banner);
        if (banner == null) return new ViewJSON<>("500","SERVER ERROR","banner状态变更失败");
        return new ViewJSON<>("200","success");
    }


    /**
     * @api {get} /v2.0/banners/:bannerType/banners 获取显示的轮播图
     * @apiVersion 0.2.0
     * @apiName get banners
     * @apiGroup Banners
     *
     * @apiParam {Number} bannerType 轮播类型.
     *
     * @apiSuccess {String} code 返回状态码.
     * @apiSuccess {String} info 返回信息.
     * @apiSuccess {String} memo 返回提示.
     * @apiSuccess {String} data 返回数据.
     * @apiSuccess {String} hint 状态描述.
     * @apiSuccess {Number} id 轮播图ID.
     * @apiSuccess {String} imgUrl 轮播图地址.
     * @apiSuccess {Number} actionType 动作类型.
     * @apiSuccess {Number} bannerType 轮播图类型.
     * @apiSuccess {Number} showOrder 轮播图位置位置.
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
     *              "imgUrl": "/images/banner/timg2.jpg",
     *              "actionType": 0,
     *              "bannerType": 0,
     *              "showOrder": 1
     *          },
     *          {
     *              "id": 2,
     *              "imgUrl": "/images/banner/timg.jpg",
     *              "actionType": 0,
     *              "bannerType": 0,
     *              "showOrder": 2
     *          }],
     *       "hint": "success"
     *      }
     */
    @GetMapping(value = "/{bannerType}/banners")
    public ViewJSON<List<Banner>> banners(@PathVariable(value = "bannerType")int bannerType){
        List<Banner> banners = bannerService.fetchWithType(bannerType);

        if (banners == null) return new ViewJSON<>("500","SERVER ERROR","获取页签失败");
        return new ViewJSON<>("200","success",banners);
    }

    
    @GetMapping(value = "/bann")
    public String teatJsp(){

        return "test";
    }


}
