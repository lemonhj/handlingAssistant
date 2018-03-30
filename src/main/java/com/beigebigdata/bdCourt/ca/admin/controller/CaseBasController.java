package com.beigebigdata.bdCourt.ca.admin.controller;

import com.beigebigdata.bdCourt.ca.admin.common.utils.DateUtil;
import com.beigebigdata.bdCourt.ca.api.entity.Member;
import com.beigebigdata.bdCourt.ca.api.service.MemberService;
import com.beigebigdata.bdCourt.ca.api.view.CaseBasView;
import com.beigebigdata.bdCourt.ca.cases.entity.CaseBase;
import com.beigebigdata.bdCourt.ca.cases.service.CaseBaseService;
import com.beigebigdata.bdCourt.ca.cause.entity.Cause;
import com.beigebigdata.bdCourt.ca.cause.service.CauseService;
import org.apache.commons.lang.StringUtils;
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
 * 用户案件列表
 * @author BD
 *
 */
@Controller
@RequestMapping("/cases")
public class CaseBasController {
	@Autowired
	private CaseBaseService caseBaseService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CauseService causeService;
	
	/**
	 * 案件列表
	 * @param model
	 * @param q_caseCreater  创建人
	 * @param q_caseCreate   开始时间
	 * @param q_caseUpdate   结束时间
	 * @param page           当前页
	 * @return
	 */
	@RequestMapping(value="casebas")
	public String caseBasHandler(ModelMap model,String q_caseCreater,String q_caseCreate,String q_caseUpdate, Integer page){
		Map<String, Object> param=new HashMap<String,Object>();
		if (StringUtils.isNotEmpty(q_caseCreater)) {
			param.put("caseCreater", q_caseCreater);
		}
		if (StringUtils.isNotEmpty(q_caseCreate)) {
			param.put("caseCreate", DateUtil.getDateFromString(q_caseCreate, "yyyy-MM-dd HH:mm:ss"));
		}
		if (StringUtils.isNotEmpty(q_caseUpdate)) {
			param.put("caseUpdate", DateUtil.getDateFromString(q_caseUpdate, "yyyy-MM-dd HH:mm:ss"));
		}
		int count=50;//每页显示的条数
		if (page==null) {
			page=0;
		}
		param.put("size",count);//查询条数
		param.put("startNo", page*count);//查询开始位置
		List<CaseBasView> caseBaseList=caseBaseService.selectCaseBasByConditions(param);
		int countzong =caseBaseService.selectConutByConditions(param);//总条数
		if (countzong <=0) {
			CaseBase caseBas=new CaseBase();
			countzong=caseBaseService.selectCount(caseBas);
		}
		double counts=Double.valueOf(countzong)/Double.valueOf(count);//总页数
		//获取所有查询案件状态
		List<CaseBasView> caseStatusList=caseBaseService.selectCaseStatus();
		//获取所有的创建人
		List<Member> memberList=memberService.selectAll();
		//获取所有的案件类型（案由）关联bc_cause_config.cause_no  caseType
		List<Cause> causeList=causeService.selectAll();
		model.addAttribute("present", page);//当前页
		model.addAttribute("count",Math.ceil(counts));
		model.addAttribute("caseBaseList",caseBaseList);
		model.addAttribute("q_caseCreate",q_caseCreate);
		model.addAttribute("q_caseCreater",q_caseCreater);
		model.addAttribute("q_caseUpdate",q_caseUpdate);
		model.addAttribute("caseStatusList",caseStatusList);
		model.addAttribute("memberList",memberList);
		model.addAttribute("causeList",causeList);
		return "tab/casebas";
	}
	
	/**
	 * 新增
	 * @param caseCreater_i  创建人  
	 * @param caseType_i     案件类型
	 * @param caseStatus_i   案件状态
	 * @param caseName_i     案件名称
	 * @param caseNo_i       案件编号  
	 * @param caseCode_i     案件code
	 * @param caseMemo_i     案件最后处理备注 
	 * @return
	 */
	@RequestMapping(value="insertCaseBas")
	public @ResponseBody
	int insertCaseBasHandler(String caseCreater_i,Integer caseType_i,Integer caseStatus_i,String caseName_i,String caseNo_i,String caseCode_i,String caseMemo_i){
		CaseBase caseBase=new CaseBase();
		caseBase.setCaseCreater(caseCreater_i);
		caseBase.setCaseType(caseType_i);
		caseBase.setCaseStatus(caseStatus_i);
		caseBase.setCaseName(caseName_i);
		caseBase.setCaseNo(caseNo_i);
		caseBase.setCaseCode(caseCode_i);
		caseBase.setCaseMemo(caseMemo_i);
		caseBase.setCaseUpdate(new Date());
		caseBase.setCaseCreate(new Date());
		caseBaseService.insertSelective(caseBase);
		return WebMessage.SUCCESS;
	}

	/**
	 * 编辑之前先查看
	 * @param model
	 * @param id   --案件id
	 * @return
	 */
	@RequestMapping(value="editCaseBas")
	public @ResponseBody
	CaseBase editCaseBasHandler(ModelMap model,Integer id){
		CaseBase caseBase=new CaseBase();
		caseBase.setId(id);
		return caseBaseService.selectOne(caseBase);
	}
	
	/**
	 * 编辑
	 * @param id
	 * @param caseCreater_e  创建人  
	 * @param caseType_e     案件类型
	 * @param caseStatus_e   案件状态
	 * @param caseName_e     案件名称
	 * @param caseNo_e       案件编号  
	 * @param caseCode_e     案件code
	 * @param caseMemo_e     案件最后处理备注 
	 * @return
	 */
	@RequestMapping(value="updateCaseBas")
	public @ResponseBody
	int updateCaseBasHandler(Integer id, String caseCreater_e,Integer caseType_e,Integer caseStatus_e,String caseName_e,String caseNo_e,String caseCode_e,String caseMemo_e){
		CaseBase caseBase=new CaseBase();
		caseBase.setId(id);
		CaseBase caseBaseOne=caseBaseService.selectOne(caseBase);
		if (caseBaseOne != null) {
			caseBaseOne.setCaseCreater(caseCreater_e);
			caseBaseOne.setCaseType(caseType_e);
			caseBaseOne.setCaseStatus(caseStatus_e);
			caseBaseOne.setCaseName(caseName_e);
			caseBaseOne.setCaseNo(caseNo_e);
			caseBaseOne.setCaseCode(caseCode_e);
			caseBaseOne.setCaseMemo(caseMemo_e);
			caseBaseOne.setCaseUpdate(new Date());
			caseBaseService.updateByPrimaryKeySelective(caseBaseOne);
			return WebMessage.SUCCESS;
		}
		return WebMessage.ERROR;
	}
	
	
}
