package com.beigebigdata.bdCourt.ca.admin.controller;


import com.beigebigdata.bdCourt.ca.news.entity.NewsTab;
import com.beigebigdata.bdCourt.ca.news.service.NewsTabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 首页页签
 * @author BD
 *
 */
@Controller
@RequestMapping("/tab")
public class TabController {
	
	@Autowired
    private NewsTabService newsTabService;
	
	/**
	 * 查询所有
	 * @param model
	 * @param q_isShow -是否显示
	 * @return
	 */
	@RequestMapping(value = "newstab")
	public String rcommonproblemlistHandler(ModelMap model,String q_isShow){
		Map<String, Object> param=new HashMap<String, Object>();
		if ("".equals(q_isShow)) {
			param.put("isShow", null);
		}else if ("false".equals(q_isShow)) {
			param.put("notShow", "not");
		}else{
			param.put("isShow", true);
		}	
	
		List<NewsTab> list =  newsTabService.selectNewsTabsByConditions(param);
		model.addAttribute("tablist", list);
		model.addAttribute("q_isShow", q_isShow);
		return "tab/newstab";
	}
	
	
	/**
	 * 新增
	 * @param isShow_i    --是否显示
	 * @param showOrder_i --显示顺序
	 * @param tabName_i   --页签名
	 * @return
	 */
	@RequestMapping(value="insertNewstab")
	public @ResponseBody
	int insertNewstabHandler(String isShow_i,Float showOrder_i,String tabName_i){
		NewsTab newsTab= new NewsTab();
		/*if ("false".equals(isDelete_i)) {
			newsTab.setIsDelete(false);
		}else if ("true".equals(isDelete_i)) {
			newsTab.setIsDelete(true);
		}*/
		if ("false".equals(isShow_i)) {
			newsTab.setIsShow(false);
		}else if ("true".equals(isShow_i)) {
			newsTab.setIsShow(true);
		}
		newsTab.setTabName(tabName_i);
		newsTab.setShowOrder(showOrder_i);
		newsTab.setUpdateTime(new Date());
		newsTab.setCreateTime(new Date());
		newsTabService.insertSelective(newsTab);
		return WebMessage.SUCCESS;
		
	}
	
	/**
	 * 编辑之前先查看
	 * @param model
	 * @param id  --编号
	 * @return
	 */
	@RequestMapping(value="selectNewsTabById")
	public @ResponseBody
	NewsTab selectNewsTabByIdHandler(ModelMap model,long id){
		NewsTab newsTab=newsTabService.selectByPrimaryKey(id);
		return newsTab;
	}
	
	/**
	 * 编辑
	 * @param id          --编号
	 * @param isShow_e    --是否显示
	 * @param showOrder_e --显示顺序
	 * @param tabName_e   --页签名
	 * @return
	 */
	@RequestMapping(value="updateNewstab")
	public @ResponseBody
	int updateNewstabHandler(long id,String isShow_e,Float showOrder_e,String tabName_e){
		NewsTab newsTab=newsTabService.selectByPrimaryKey(id);
		if (newsTab != null) {
			/*if ("false".equals(isDelete_e)) {
				newsTab.setIsDelete(false);
			}else if ("true".equals(isDelete_e)) {
				newsTab.setIsDelete(true);
			}*/
			if ("false".equals(isShow_e)) {
				newsTab.setIsShow(false);
			}else if ("true".equals(isShow_e)) {
				newsTab.setIsShow(true);
			}
			newsTab.setUpdateTime(new Date());
			newsTab.setShowOrder(showOrder_e);
			newsTab.setTabName(tabName_e);
			newsTabService.updateByPrimaryKeySelective(newsTab);
			return WebMessage.SUCCESS;
		}
		return WebMessage.ERROR;
	}
	
	/**
	 * 是否删除-修改
	 * @param id       --编号
	 * @return
	 */
	@RequestMapping(value = "updateIsDelete")
	public @ResponseBody
	int updateIsDeleteHandler(long id){
		NewsTab newsTab=newsTabService.selectByPrimaryKey(id);
		if (newsTab != null) {
			newsTab.setIsDelete(true);
			newsTab.setIsShow(false);
			newsTab.setUpdateTime(new Date());
			newsTabService.updateByPrimaryKey(newsTab);
			return WebMessage.SUCCESS;
		}else{
			return WebMessage.ERROR;
		}
		 
	}

	/**
	 * 是否显示-修改
	 * @param id     --编号
	 * @param isShow --是否显示
	 * @return
	 */
	@RequestMapping(value = "updateIsShow")
	public @ResponseBody
	int updateIsShowHandler(long id,String isShow){
		NewsTab newsTab=newsTabService.selectByPrimaryKey(id);
		if (newsTab != null) {
			if ("0".equals(isShow)) {
				newsTab.setIsShow(true);
			}
			if ("1".equals(isShow)){
				newsTab.setIsShow(false);
			}
			newsTab.setUpdateTime(new Date());
			newsTabService.updateByPrimaryKey(newsTab);
			return WebMessage.SUCCESS;
		}else{
			return WebMessage.ERROR;
		}
		 
	}

}



