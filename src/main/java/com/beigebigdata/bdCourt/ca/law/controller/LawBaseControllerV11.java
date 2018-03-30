package com.beigebigdata.bdCourt.ca.law.controller;

import com.beigebigdata.bdCourt.ca.law.service.LawBaseService;
import com.beigebigdata.bdCourt.ca.law.view.LawBookView;
import com.septinary.common.util.Assert;
import com.septinary.common.util.ViewJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 法律相关基本信息访问控制接口：V1.1
 * @author lihuay@chinabigdata.com 2016-11-21
 *
 */
@Controller
@RequestMapping("v1.1/laws")
public class LawBaseControllerV11 {
	
	@Autowired
	private LawBaseService lawBaseService;
    
    /**
     * 加载案件相关法律典籍
     * @param causeNo
     * @return
     */
    @RequestMapping(value="/{causeNo}/loadRelatedLawBooks", method= RequestMethod.GET)
    @ResponseBody
    public ViewJSON<List<LawBookView>> loadRelatedLawBooks(@PathVariable(value="causeNo")Integer causeNo, Integer type) {
    	Assert.NotNull(causeNo, "案由编号不能空！");
    	
        Set<Integer> bookNOs = new HashSet<>();
        List<HashMap<String,Integer>> lawBookNos = lawBaseService.queryLawBookNosByCauseNo(causeNo);
        for (Iterator<HashMap<String,Integer>> iterator = lawBookNos.iterator();iterator.hasNext();){
            HashMap<String,Integer> bookMap = iterator.next();
            for (Iterator<String> iter = bookMap.keySet().iterator();iter.hasNext();){
                String bookNO = iter.next();
                bookNOs.add(bookMap.get(bookNO));
            }
        }
        
        List<LawBookView> books = new ArrayList<>();
        if (!bookNOs.isEmpty()) {
            List<LawBookView> bookTypeList = lawBaseService.queryLawBooksType(bookNOs);
            books = lawBaseService.queryLawBooksByBookNOs(bookNOs);
            for (Iterator<LawBookView> iterator = books.iterator();iterator.hasNext();){
                LawBookView lawBookView = iterator.next();
                for (Iterator<LawBookView> iter = bookTypeList.iterator();iter.hasNext();){
                    LawBookView lawBook  = iter.next();
                    if (lawBookView.getId().intValue() == lawBook.getId().intValue()){
                        lawBookView.setType(lawBook.getType());
                    }
                }
            }
        }
        if(null != type && !type.equals("")) {
        	List<LawBookView> books1 = new ArrayList<>();
        	for(int i = 0; i < books.size(); i++) {
        		LawBookView view = books.get(i);
        		if(view.getType().equals(type)) {
        			books1.add(view);
        		} else {
        			continue;
        		}
        	}
        	return new ViewJSON<>("200","success",books1);
        } else {
        	return new ViewJSON<>("200","success",books);
        }
    }
    
    /**
     * 获取法律筛选条件
     * @return
     */
    @RequestMapping(value="/typs", method= RequestMethod.GET)
    @ResponseBody
    public ViewJSON<List<HashMap<String, Object>>> getLawType(){
    	List<Integer> list = this.lawBaseService.getLayTypeList();
    	List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
    	if(list.size() > 0) {
    		for(Integer i : list) {
    			HashMap<String, Object> map = new HashMap<String, Object>();
    			 if(i == 1) {
    				 map.put("name", "法律");
    				 map.put("type", i);
    				 resultList.add(map);
    			 } else if(i == 2) {
    				 map.put("name", "地方法规");
    				 map.put("type", i);
    				 resultList.add(map);
    			 } else if(i == 3) {
    				 map.put("name", "指引");
    				 map.put("type", i);
    				 resultList.add(map); 
    			 }
    		}
    	}
       return new ViewJSON<>("200","success",resultList);
    }

}