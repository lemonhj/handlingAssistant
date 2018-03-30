package com.beigebigdata.bdCourt.ca.law.controller;

import com.beigebigdata.bdCourt.ca.law.service.LawBaseService;
import com.beigebigdata.bdCourt.ca.law.view.LawBookView;
import com.septinary.common.util.ViewJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * 法律相关基本信息访问控制接口：V1.2
 * @author lihuay@chinabigdata.com 2016-11-21
 *
 */
@Controller
@RequestMapping("v2.0/laws")
public class LawBaseControllerV12 {

    @Autowired
    private LawBaseService lawBaseService;

    /**
     * 获取同类法律
     * @param id
     * @return
     */
    @RequestMapping(value="/get-similar/{id}", method= RequestMethod.GET)
    @ResponseBody
    public ViewJSON<List<LawBookView>> getLawSimilarDataById(@PathVariable(value="id")Integer id) {
        HashMap<String, Object> map =new HashMap<String, Object>();
        map.put("id", id);
        map.put("flag", 0);

        List<LawBookView> list = this.lawBaseService.getLaysDataListByMap(map);
        if(list.size() > 0) {
            return new ViewJSON<>("200","success",list);
        } else {
            return new ViewJSON<>("400","failure");
        }
    }


    /**
     * 获取引用类法律
     * @param id
     * @return
     */
    @RequestMapping(value="/get-quote/{id}", method= RequestMethod.GET)
    @ResponseBody
    public ViewJSON<List<LawBookView>> getLawQuoteDataById(@PathVariable(value="id")Integer id) {
        HashMap<String, Object> map =new HashMap<String, Object>();
        map.put("id", id);
        map.put("flag", null);
        List<LawBookView> list = this.lawBaseService.getLaysDataListByMap(map);
        if(list.size() > 0) {
            return new ViewJSON<>("200","success",list);
        } else {
            return new ViewJSON<>("400","failure");
        }
    }
}
