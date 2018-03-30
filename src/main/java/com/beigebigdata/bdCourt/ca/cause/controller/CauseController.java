package com.beigebigdata.bdCourt.ca.cause.controller;

import com.beigebigdata.bdCourt.ca.cause.entity.Cause;
import com.beigebigdata.bdCourt.ca.cause.service.CauseService;
import com.septinary.api.annotation.SysTraceLog;
import com.septinary.common.util.ViewJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CaseController
 * @Description:提供的案由访问接口
 * @date 16/10/21
 */
@Controller
@RequestMapping("v1.0/causes")
public class CauseController {

    @Autowired
    private CauseService causeService;

    /**
     * 获取案由列表
     * @return
     */
    @RequestMapping(value = "/criminal",method = RequestMethod.GET)
    @ResponseBody
    @SysTraceLog(description = "获取案由列表")
    public ViewJSON<List<Cause>> createCase(){
        Cause cause = new Cause();
        cause.setCauseCategory(1);
        List<Cause> causes = causeService.select(cause);

        return new ViewJSON<>("200","success",causes);
    }

}
