package com.septinary.push.controller;


import com.gexin.rp.sdk.base.uitls.AppConditions;
import com.septinary.api.annotation.SysTraceLog;
import com.septinary.push.entity.Message;
import com.septinary.push.entity.PushCondition;
import com.septinary.push.entity.PushType;
import com.septinary.push.service.SendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lin.tb lin.tb@septinary.com
 * @ClassName: PushController
 * @Description:
 * @date 16/3/28
 */
@Controller
@RequestMapping("v1.0/push")
public class PushController {
    @Autowired
    private SendingService sendingService;


    @RequestMapping(value = "/toSingle/{cid}",method = RequestMethod.GET)
    @ResponseBody
    public void push2Single(@PathVariable("cid") String cid, HttpServletRequest request){
        Message message = new Message();
        message.setContent("12312");
        message.setTitle("123");

        try{
            sendingService.push2Single(request,cid,message,PushType.TRANSMISSIONMSG);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 按条件推送
     * @param pushCondition
     * @param message
     * @param request
     */
    @RequestMapping(value = "/conditions",method = RequestMethod.POST)
    @ResponseBody
    @SysTraceLog(description = "按条件推送")
    public void pushWithConditions(PushCondition pushCondition, Message message, HttpServletRequest request){

        AppConditions appConditions = new AppConditions();
        appConditions = constructCondition(pushCondition,appConditions);
        try{
            sendingService.pushWithConditions(request,appConditions,message,PushType.TRANSMISSIONMSG);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 构建条件
     * @param pushCondition
     * @param appConditions
     * @return
     */
    private AppConditions constructCondition(PushCondition pushCondition, AppConditions appConditions) {
        if (pushCondition.getTag() != null) appConditions.addCondition("tag",pushCondition.getTag());
        if (pushCondition.getPhoneType() != null) appConditions.addCondition("phoneType",pushCondition.getPhoneType());
        if (pushCondition.getRegion() != null) appConditions.addCondition("region",pushCondition.getRegion());
        return appConditions;
    }


    /**
     * 推送个人
     * @param request
     */
    @RequestMapping(value = "/toSingle",method = RequestMethod.GET)
    @ResponseBody
    public void pushSingle( HttpServletRequest request){
        System.out.print("adf");
    }

}
