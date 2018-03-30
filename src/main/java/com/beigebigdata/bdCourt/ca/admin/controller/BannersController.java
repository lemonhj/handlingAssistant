package com.beigebigdata.bdCourt.ca.admin.controller;

import com.beigebigdata.bdCourt.ca.admin.common.utils.DatetimeUtil;
import com.beigebigdata.bdCourt.ca.admin.common.utils.PropertiesConfig;
import com.beigebigdata.bdCourt.ca.admin.common.utils.Utils;
import com.beigebigdata.bdCourt.ca.api.entity.Banner;
import com.beigebigdata.bdCourt.ca.api.service.BannerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/banner")
public class BannersController extends BasicController {

	@Autowired
	private BannerService bannerService;

	/**
	 * 获取banner图
	 * 
	 * @param model
	 * @param id
	 * @param q_isshow
	 * @return
	 */
	@RequestMapping(value = "bannerpicture")
	public String pictureListHandler(ModelMap model, Long id, Boolean q_isshow) {
		Map<String, Object> param=new HashMap<String, Object>();
		if (null==q_isshow) {
			q_isshow=true;
		}else{
			q_isshow=null;
		}
		param.put("isShow", q_isshow);
		param.put("isDelete",false);
		List<Banner> list = bannerService.list(param);
		model.addAttribute("datalist", list);
		model.addAttribute("q_isshow", q_isshow);

		return "banner/bannerpicture";
	}

	/** 获取缩略图
	 * 
	 * @param model
	 * 
	 * @param id
	 * 
	 * @return
	 */
	@RequestMapping(value = "get")
	public @ResponseBody
	WebMessage pictureGetHandler(ModelMap model, Long id) {
		Banner banner = bannerService.selectByPrimaryKey(id);
		model.addAttribute("banner", banner);
		return saveSuccess(banner);
	}

	// 设置图片状态--不显示
	@RequestMapping(value = "unShow")
	public @ResponseBody
	WebMessage unShowHandler(ModelMap model, Long id) {
		Banner banner = bannerService.selectByPrimaryKey(id);
		banner.setIsShow(false);
		banner.setUpdateTime(new Date());
		bannerService.updateByPrimaryKeySelective(banner);
		return saveSuccess();
	}

	// 设置图片状态--显示
	@RequestMapping(value = "show")
	public @ResponseBody
	WebMessage showHandler(ModelMap model, Long id) {
		Banner banner = bannerService.selectByPrimaryKey(id);
		banner.setIsShow(true);
		banner.setUpdateTime(new Date());
		bannerService.updateByPrimaryKeySelective(banner);
		return saveSuccess();
	}
	
	// 设置图片状态--删除
		@RequestMapping(value = "del")
		public @ResponseBody
		WebMessage delHandler(ModelMap model, Long id) {
			Banner banner = bannerService.selectByPrimaryKey(id);
			banner.setIsDelete(true);
			banner.setUpdateTime(new Date());
			bannerService.updateByPrimaryKeySelective(banner);
			return saveSuccess();
		}
		
		// 设置图片状态--不删除
		@RequestMapping(value = "unDel")
		public @ResponseBody
		WebMessage unDelHandler(ModelMap model, Long id) {
			Banner banner = bannerService.selectByPrimaryKey(id);
			banner.setIsDelete(false);
			banner.setUpdateTime(new Date());
			bannerService.updateByPrimaryKeySelective(banner);
			return saveSuccess();
		}

	// 查询显示或不显示的LIST
	@RequestMapping(value = "banner")
	public String pictureBannerHandler(ModelMap model, Long id, Boolean q_isshow) {
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("isShow",q_isshow);
		param.put("isDelete",false);
		List<Banner> list = bannerService.list(param);
		model.addAttribute("datalist", list);
		model.addAttribute("q_isshow", q_isshow);

		return "banner/bannerpicture";
	}

	// 新增
	@RequestMapping(value = "addpicture")
	public String pictureAddHandler(ModelMap model, Integer id) {
		return "banner/addpicture";
	}

	@RequestMapping(value = "add")
	public String pictureAddHandler(ModelMap model, MultipartHttpServletRequest requset) {
		String bannerType = requset.getParameter("bannerType");
		String actionType = requset.getParameter("actionType");
		String showOrder = requset.getParameter("showOrder");
		String actionUrl = requset.getParameter("actionUrl");
		String isShow = requset.getParameter("isShow");
		List<MultipartFile> files = requset.getFiles("file");
		Banner banner = new Banner();
		for (MultipartFile multipartFile : files) {
			if (!multipartFile.isEmpty()) {
				String path = this.getDefaultPath();
				String fileName = this.getGenerateFilename(multipartFile.getOriginalFilename());
				String filePath = path + "/" + fileName;
				String realPath = PropertiesConfig.getConfigKeyValue("img_bannerfile_path") + "/" + filePath;
				
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
				banner.setImgUrl("/"+realPath.substring(realPath.indexOf("images")).replace('\\','/'));
			}
		}
		banner.setActionType(Integer.parseInt(actionType));
		banner.setActionUrl(actionUrl);
		banner.setBannerType(Integer.parseInt(bannerType));
		banner.setShowOrder(Float.parseFloat(showOrder));
		banner.setUpdateTime(new Date());
		banner.setCreateTime(new Date());
		banner.setIsDelete(false);
		banner.setIsShow(Boolean.parseBoolean(isShow));
		bannerService.insertSelective(banner);
		return "redirect:bannerpicture";
	}

	@RequestMapping(value = "editpicture")
	public String pictureEditHandler(ModelMap model, Long id, String q_isshow) {
		Banner banner = bannerService.selectByPrimaryKey(id);
		Integer showOrder=banner.getShowOrder().intValue();
		model.addAttribute("picture", banner);
		model.addAttribute("q_isshow", q_isshow);
		model.addAttribute("showOrder", showOrder);

		return "banner/editpicture";
	}
	
	@RequestMapping(value = "edit")
	public String pictureUpHandler(ModelMap model,MultipartHttpServletRequest request){
		try {
			String id = request.getParameter("id");
			if (StringUtils.isNotEmpty(id)) {
				Banner banner = bannerService.selectByPrimaryKey(Long.parseLong(id));
				if (banner!=null) {
					String bannerType = request.getParameter("bannerType");
					String actionType = request.getParameter("actionType");
					String showOrder = request.getParameter("showOrder");
					String actionUrl = request.getParameter("actionUrl");
					String isShow = request.getParameter("isShow");
					
					List<MultipartFile> files = request.getFiles("file");
					for (MultipartFile multipartFile : files) {
						if (!multipartFile.isEmpty()) {
							String path = this.getDefaultPath();
							String fileName = this.getGenerateFilename(multipartFile.getOriginalFilename());
							String filePath = path + "/" + fileName;
							String realPath = PropertiesConfig.getConfigKeyValue("img_bannerfile_path") + "/" + filePath;
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
							banner.setImgUrl("/"+realPath.substring(realPath.indexOf("images")).replace('\\','/'));
						}
					}
					banner.setActionType(Integer.parseInt(actionType));
					banner.setActionUrl(actionUrl);
					banner.setBannerType(Integer.parseInt(bannerType));
					banner.setShowOrder(Float.parseFloat(showOrder));
					banner.setUpdateTime(new Date());
					banner.setCreateTime(new Date());
					banner.setIsDelete(false);
					banner.setIsShow(Boolean.parseBoolean(isShow));
					bannerService.updateByPrimaryKeySelective(banner);
					return "redirect:bannerpicture?q_isshow="+isShow;
				}
			}else {
				
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public String getDefaultPath() {
		return DatetimeUtil.convertDateToString("yyyy/MM", new Date());
	}
	
	public String getGenerateFilename(String originalFileName) {
		String filename = DatetimeUtil.convertDateToString("yyyyMMddHHmmss", new Date())+ Utils.randomAlphanumeric(5);
		originalFileName = Utils.trim(originalFileName);
		if(originalFileName.lastIndexOf(".")>0){
			filename = filename + "." + originalFileName.substring(originalFileName.lastIndexOf(".")+1);
		}
		return filename;
	}
}