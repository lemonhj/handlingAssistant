package com.beigebigdata.bdCourt.ca.admin.controller;

import com.beigebigdata.bdCourt.ca.admin.common.page.PageList;
import com.beigebigdata.bdCourt.ca.admin.common.page.PageProperty;
import com.beigebigdata.bdCourt.ca.admin.common.page.PageUtil;
import com.beigebigdata.bdCourt.ca.admin.common.utils.*;
import com.beigebigdata.bdCourt.ca.news.entity.NewsInfo;
import com.beigebigdata.bdCourt.ca.news.entity.NewsTab;
import com.beigebigdata.bdCourt.ca.news.service.NewsInfoService;
import com.beigebigdata.bdCourt.ca.news.service.NewsTabService;
import com.septinary.common.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;


@Controller("adminNewsInfo")
public class NewsInfoController extends BasicController {
	 
	@Autowired
	private NewsInfoService newsInfoService;
	@Autowired
	private NewsTabService newsTabService;
	//查看资讯
		@RequestMapping(value = "newsInfoList")
		public String newsInfoListHandler(ModelMap modelMap,String q_caseCreate,String q_caseUpdate,Integer search_type,Boolean search_isShow,String search_title,String pageNo, String pageSize){
	        PageProperty pp = new PageProperty();
			Integer pageNum = 0;
			Integer pageSizeNum = 0;
			if (!"".equals(pageNo)) {
				pageNum = Utils.parseInt(pageNo, 1); // 将字符串数字转化为int型数字,把pageNo传进去，转换为整型，默认为1
			}
			if (!"".equals(pageSize)) {
				pageSizeNum = Utils.parseInt(pageSize, PageUtil.PAGE_SIZE);
			}
			pp.setNpage(pageNum);
			pp.setNpagesize(pageSizeNum);
			if (StringUtils.isNotEmpty(q_caseCreate)) {
				pp.putParamMap("caseCreate", DateUtil.getDateFromString(q_caseCreate, "yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtils.isNotEmpty(q_caseUpdate)) {
				pp.putParamMap("caseUpdate", DateUtil.getDateFromString(q_caseUpdate, "yyyy-MM-dd HH:mm:ss"));
			}
			pp.putParamMap("isShow", search_isShow);
			pp.putParamMap("newsType", search_type);
			pp.putParamMap("title", search_title);
	        PageList<NewsInfo> newsInfolist = newsInfoService.pagingNews(pp);
			
			NewsTab nTable=new NewsTab();
			nTable.setIsDelete(false);
			nTable.setIsShow(true);
			List<NewsTab> newsTabelist=newsTabService.select(nTable);
			modelMap.addAttribute("q_caseCreate",q_caseCreate);		
			modelMap.addAttribute("q_caseUpdate",q_caseUpdate);			
			modelMap.addAttribute("datalist", newsInfolist);//资讯信息
			modelMap.addAttribute("search_type", search_type);//资讯显示状态
			modelMap.addAttribute("search_isShow", search_isShow);//资讯类型状态
			modelMap.addAttribute("search_title", search_title);//模糊查询标题		
			modelMap.addAttribute("newsTabelist", newsTabelist);//资讯类型信息
			return  "newsInfo/newsInfoList";
		}
	//资讯编辑
	@RequestMapping(value = "newsInfoGet")
	public @ResponseBody
	WebMessage newsInfogetHandler(ModelMap model,Long id ){
		NewsInfo newsinfo=new NewsInfo();
        newsinfo.setId(id);      
		NewsInfo newsinfos =newsInfoService.selectOne(newsinfo);
		return saveSuccess(newsinfos);
	}
	//编辑资讯
	@RequestMapping(value = "newsInfoEdit",method = RequestMethod.POST)
	public String newsInfoeditHandler(ModelMap modelMap,MultipartHttpServletRequest request,RedirectAttributes attr){
		String edit_title=request.getParameter("edit_title");
		String edit_hits=request.getParameter("edit_hits");
		String edit_type=request.getParameter("edit_type");	
		String edit_source=request.getParameter("edit_source");
		String edit_introduction=request.getParameter("edit_introduction");
		String edit_content=request.getParameter("edit_content");
		String edit_isShow=request.getParameter("edit_isShow");
		String edit_newsId=request.getParameter("edit_newsId");
		//分页参数
		String pageNo=request.getParameter("pageNo");
		String pageSize=request.getParameter("pageSize");
		//搜索参数
		String q_caseCreate_edit=request.getParameter("q_caseCreate_edit");
		String q_caseUpdate_edit=request.getParameter("q_caseUpdate_edit");
		String search_type_edit=request.getParameter("search_type_edit");
		String search_isShow_edit=request.getParameter("search_isShow_edit");
		String search_title_edit=request.getParameter("search_title_edit"); 
		
		NewsInfo newsinfo=new NewsInfo();
		newsinfo.setUpdateTime(new Date());//修改更新时间
		if(StringUtils.isNotEmpty(edit_title)){
			newsinfo.setTitle(edit_title); 
		}
		if(StringUtils.isNotEmpty(edit_source)){
			newsinfo.setSource(edit_source);
		}
		if(StringUtils.isNotEmpty(edit_content)){
			newsinfo.setContent(edit_content);
		}
		if(StringUtils.isNotEmpty(edit_introduction)){
			newsinfo.setIntroduction(edit_introduction);
		}
		if(StringUtils.isNotEmpty(edit_newsId)){
			newsinfo.setId(Long.valueOf(edit_newsId));
		}
		if(StringUtils.isNotEmpty(edit_hits)){
			newsinfo.setHits(Long.valueOf(edit_hits));
		}
		if(StringUtils.isNotEmpty(edit_type)){
			newsinfo.setType(Integer.valueOf(edit_type));	
		}
		if(StringUtils.isNotEmpty(edit_isShow)){
			//如果显示则修改发布时间
			if(edit_isShow.equals("true")){
				 newsinfo.setPublishTime(new Date());
			 }
			newsinfo.setIsShow(Boolean.valueOf(edit_isShow));
		}
		  
		List<MultipartFile> files = request.getFiles("edit_thumPic");
		for (MultipartFile multipartFile : files) {
			if (!multipartFile.isEmpty()) {
				String path = this.getDefaultPath();
				String fileName = this.getGenerateFilename(multipartFile.getOriginalFilename());
				String filePath = path + "/" + fileName;
				String realPath = PropertiesConfig.getConfigKeyValue("img_newsfile_path") + "/" + filePath;
				File file = new File(realPath);
				if (!file.exists()) {
					file.mkdirs();
				}
				try {
					multipartFile.transferTo(file);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				newsinfo.setThumPic("/"+realPath.substring(realPath.indexOf("images")).replace('\\','/'));
			}
		}
		
		newsInfoService.updateByPrimaryKeySelective(newsinfo);
		//分页参数
		attr.addAttribute("pageNo", pageNo);
		attr.addAttribute("pageSize", pageSize);
		//搜索参数
		attr.addAttribute("q_caseCreate", q_caseCreate_edit);
		attr.addAttribute("q_caseUpdate", q_caseUpdate_edit);
		attr.addAttribute("search_type", search_type_edit);
		attr.addAttribute("search_isShow", search_isShow_edit);
		attr.addAttribute("search_title", search_title_edit);
		return  "redirect:newsInfoList";
	}	
	
/*	//删除资讯
	@RequestMapping(value = "newsInfoDelete")
	public String newsInfoDeleteHandler(ModelMap modelMap,Long id){		
		NewsInfo newsinfo=new NewsInfo();
        newsinfo.setId(id);           
        newsinfo.setIsDelete(true);
		newsInfoService.updateByPrimaryKeySelective(newsinfo);
		return  "redirect:newsInfoList";
	}*/
	//显示状态
	@RequestMapping(value = "editisShow")
	public String editisShowHandler(ModelMap modelMap,Long id,Boolean edit_isShow,String q_caseCreate,
			String q_caseUpdate,Integer search_type,Boolean search_isShow,String search_title,String pageNo,
			String pageSize,RedirectAttributes attr){
		if (StringUtil.IsNotEmpty(search_title)) {
			try {
				search_title=new String(search_title.getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}  
		NewsInfo newsinfo=new NewsInfo();
	    newsinfo.setId(id); 
	    if(edit_isShow==false){
	    	edit_isShow=true;
	    	newsinfo.setPublishTime(new Date());//如果修改为显示状态则修改发布时间
	    }else{
	    	edit_isShow=false;	    	
	    }
	    newsinfo.setUpdateTime(new Date());//修改更新时间
	    newsinfo.setIsShow(edit_isShow);
	    newsInfoService.updateByPrimaryKeySelective(newsinfo);
	  //分页参数
	  	attr.addAttribute("pageNo", pageNo);
	  	attr.addAttribute("pageSize", pageSize);
	  //搜索参数
	  	attr.addAttribute("q_caseCreate", q_caseCreate);
	  	attr.addAttribute("q_caseUpdate", q_caseUpdate);
	  	attr.addAttribute("search_type", search_type);
	  	attr.addAttribute("search_isShow", search_isShow);
	  	attr.addAttribute("search_title", search_title);
	    return  "redirect:newsInfoList";
	}	
	
	//添加资讯
	@RequestMapping("newsInfoAdd")
	public String newsInfoAddShowHandler(ModelMap modelMap,MultipartHttpServletRequest request){
		String add_title=request.getParameter("add_title");
		String add_hits=request.getParameter("add_hits");
		String add_type=request.getParameter("add_type");
		String add_source=request.getParameter("add_source");
		String add_introduction=request.getParameter("add_introduction");
		String add_content=request.getParameter("add_content");		
		String add_isShow=request.getParameter("add_isShow");
		NewsInfo newsinfo=new NewsInfo();
		newsinfo.setCreateTime(new Date());//创建时间
		newsinfo.setUpdateTime(new Date());//更新时间
		if (StringUtils.isNotEmpty(add_title)) {
			newsinfo.setTitle(add_title);
		}		
		if (StringUtils.isNotEmpty(add_hits)) {
			newsinfo.setHits(Long.valueOf(add_hits) );
		}
		if (StringUtils.isNotEmpty(add_type)) {
			newsinfo.setType(Integer.valueOf(add_type) );
		}
		if (StringUtils.isNotEmpty(add_isShow)) {
			 newsinfo.setIsShow(Boolean.valueOf(add_isShow) );
			 //如果显示则添加发布时间
			 if(add_isShow.equals("true")){
				 newsinfo.setPublishTime(new Date());
			 }
		}
		if (StringUtils.isNotEmpty(add_source)) {
			newsinfo.setSource( add_source);
		}
		if (StringUtils.isNotEmpty(add_introduction)) {
			newsinfo.setIntroduction(add_introduction);
		}
		if (StringUtils.isNotEmpty(add_content)) {
			newsinfo.setContent(add_content);
		}
		List<MultipartFile> files = request.getFiles("add_thumPic");
		for (MultipartFile multipartFile : files) {
			if (!multipartFile.isEmpty()) {
				String path = this.getDefaultPath();
				String fileName = this.getGenerateFilename(multipartFile.getOriginalFilename());
				String filePath = path + "/" + fileName;
				String realPath = PropertiesConfig.getConfigKeyValue("img_newsfile_path") + "/" + filePath;
				File file = new File(realPath);
				if (!file.exists()) {
					file.mkdirs();
				}
				try {
					multipartFile.transferTo(file);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				newsinfo.setThumPic("/"+realPath.substring(realPath.indexOf("images")).replace('\\','/'));
			}
		}

		newsInfoService.insertSelective(newsinfo);
		 return  "redirect:newsInfoList";
	}
	
	//资讯详情
	@RequestMapping(value = "newsDetails")
	public  @ResponseBody
	WebMessage newsDetailsHandler(ModelMap model,Long id){
		NewsInfo newsinfo=newsInfoService.selectByPrimaryKey(id);
		model.addAttribute("newsinfo", newsinfo);
		return saveSuccess(newsinfo);
	}

	
	@RequestMapping("ckUpload/ck_upload.do")
	public void ckUploadHandler(HttpServletRequest request,HttpServletResponse response, ModelMap model) throws ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		MultipartFile file = multipartRequest.getFile("upload");
		try {
			String originalFileName = file.getOriginalFilename();
			String path = this.getDefaultPath();
			String fileName = this.getGenerateFilename(originalFileName);
			String filePath = path + "/" + fileName;
			String realPath = PropertiesConfig.getConfigKeyValue("img_newsfile_path") ;		
			String realPathURL = PropertiesConfig.getConfigKeyValue("img_newsfile_url");
			ImageUtils.compressionOfProportion(realPath + "/" + filePath, 600, 600, file.getInputStream());
			
			//设置返回图像选项卡  
			response.setContentType("text/html");
			response.setCharacterEncoding("GBK");  
			PrintWriter out = response.getWriter();  

			String callback = getParameter(request, "CKEditorFuncNum");    
			filePath = realPath.substring(realPath.indexOf("images")).replace('\\','/')+"/"+filePath;
			String imgUrl =  realPathURL + "/" +filePath;
			
			
			out.println("<script type=\"text/javascript\">");    
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + imgUrl + "','')");    
			out.println("</script>");  

		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	
	public String getDefaultPath() {
		return DatetimeUtil.convertDateToString("yyyy/MM", new Date());
	}

	public String getGenerateFilename(String originalFileName) {
		String filename = DatetimeUtil.convertDateToString("yyyyMMddHHmmss", new Date())+Utils.randomAlphanumeric(5);
		originalFileName = Utils.trim(originalFileName);
		if(originalFileName.lastIndexOf(".")>0){
			filename = filename + "." + originalFileName.substring(originalFileName.lastIndexOf(".")+1);
		}
		return filename;
	}
	
}
