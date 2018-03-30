package com.beigebigdata.bdCourt.ca.cases.controller;

import com.beigebigdata.bdCourt.ca.api.entity.Member;
import com.beigebigdata.bdCourt.ca.api.service.MemberService;
import com.beigebigdata.bdCourt.ca.cases.dto.CaseConditions;
import com.beigebigdata.bdCourt.ca.cases.entity.CaseBase;
import com.beigebigdata.bdCourt.ca.cases.service.CaseBaseService;
import com.beigebigdata.bdCourt.ca.common.service.MakerCodeService;
import com.beigebigdata.bdCourt.ca.evidence.service.CauseEvidenceService;
import com.beigebigdata.bdCourt.ca.law.service.LawBaseService;
import com.beigebigdata.bdCourt.ca.law.view.LawBookView;
import com.septinary.api.annotation.SysTraceLog;
import com.septinary.common.util.Assert;
import com.septinary.common.util.VerifyUtils;
import com.septinary.common.util.ViewJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CaseController
 * @Description:提供的案件访问接口
 * @date 16/10/21
 */
@Controller
@RequestMapping("v1.0/cases")
public class CaseBaseController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private CaseBaseService caseBaseService;

    @Autowired
    private MakerCodeService makerCodeService;

    @Autowired
    private CauseEvidenceService causeEvidenceService;

    @Autowired
    private LawBaseService lawBaseService;


    /**
     * 添加案件
     * @param caseBase
     * @return
     */
    @RequestMapping(value = "/{memCode}/case",method = RequestMethod.POST)
    @ResponseBody
    @SysTraceLog(description = "添加案件")
    public ViewJSON<Object> createCase(CaseBase caseBase,@PathVariable(value = "memCode")String memCode,HttpServletRequest request){
        VerifyUtils.verifyReviseInfo(caseBase.getCaseName(), caseBase.getCaseNo(), caseBase.getCaseType(), memCode);
        Member member = new Member();

        member.setMemCode(memCode);
        member = memberService.selectOne(member);
        if (member == null) return new ViewJSON<>("404","USER NOT FOUND","用户不存在！");

        CaseBase verifyCase = new CaseBase();
        verifyCase.setCaseCreater(memCode);
        verifyCase.setCaseNo(caseBase.getCaseNo());
        List<CaseBase> caseBases = caseBaseService.select(verifyCase);
        if (caseBases.size() > 0) return new ViewJSON<>("400","CASENO REPEAT","案件编号重复！");

        caseBase.setCaseCreater(memCode);

//        boolean isRepeat = isCaseNoRepeat(caseBase.getCaseCode());
//        if (isRepeat) return new ViewJSON<>("401","REPEAT","案件编号已经存在！");

        caseBase.setCaseCode(makerCodeService.makeCode("CaseCode", null));
        caseBase.setCaseCreate(new Date());
        caseBase.setCaseStatus(0);
        caseBaseService.insertSelective(caseBase);

        return new ViewJSON<>("200","success");

    }

    /**
     * 案件编号是否重复
     * @param caseNo
     */
    private boolean isCaseNoRepeat(String caseNo) {
        CaseBase caseBase = new CaseBase();
        caseBase.setCaseNo(caseNo);
        caseBase = caseBaseService.selectOne(caseBase);
        return caseBase == null ? false : true;
    }

    /**
     * 获取案件证据证据类别
     * @param caseCode 证据code
     * @return
     */
    @RequestMapping(value = "/{caseCode}/evidenceCategory",method = RequestMethod.GET)
    @ResponseBody
    @SysTraceLog(description = "获取案件证据证据类别")
    public ViewJSON<List<CaseBase>> fetchCaseEvidenceCategory(@PathVariable(value = "caseCode")String caseCode){
        VerifyUtils.verifyReviseInfo(caseCode);
        CaseBase caseBase = new CaseBase();
        caseBase.setCaseCode(caseCode);
        caseBase = caseBaseService.selectOne(caseBase);
        if (caseBase == null) return new ViewJSON<>("404","CASE NOT FOUND","此案件不存在！");
        List<CaseBase> caseBases = causeEvidenceService.fetchCaseEvidenceCategory(caseBase.getCaseType());

        return new ViewJSON<>("200","success",caseBases);

    }


    /**
     * 根据筛选条件来获取相似案件
     * @param caseCode
     * @return
     */
    @RequestMapping(value = "/{caseCode}/similarCases",method = RequestMethod.GET)
    @ResponseBody
    @SysTraceLog(description = "根据筛选条件来获取历史案件")
    public ViewJSON<List<HashMap<String,Object>>> fetchCasesByConditions(@PathVariable(value = "caseCode")String caseCode,CaseConditions caseConditions,Integer startNo,Integer size){
        VerifyUtils.verifyReviseInfo(caseCode);
        List<HashMap<String,Object>> caseBases;
        if (caseConditions.getCauseNos() == null){
            caseBases = caseBaseService.fetchSimilarCase(caseCode,caseConditions,startNo,size);
        }else{
            caseBases = caseBaseService.fetchSimilarCaseByConditions(caseCode, caseConditions, startNo, size);
        }
        return new ViewJSON<>("200","success",caseBases);
    }


    /**
     * 案件进度状态操作
     * @param caseCode
     * @param status
     * @return
     */
    @RequestMapping(value="/{caseCode}/status/{status}", method= RequestMethod.PUT)
    @ResponseBody
    public ViewJSON<Void> processCase(@PathVariable(value="caseCode")String caseCode, @PathVariable(value="status")Integer status, String memo) {
    	Assert.IsTrue(0 == status || 1 == status || 2 == status, "参数错误：状态取值范围(0,1,2)!");
    	
    	CaseBase caseBase = new CaseBase();
        caseBase.setCaseCode(caseCode);
        caseBase = this.caseBaseService.selectOne(caseBase);
		Assert.NotNull(caseBase, "目标案件caseCode=" + caseCode + "不存在！");
		
    	this.caseBaseService.processCaseStatus(caseBase, status, memo);
    	
    	return new ViewJSON<>("200");
    }

    /**
     * 加载案件相关法律典籍
     * @param caseCode
     * @return
     */
    @RequestMapping(value="/{caseCode}/loadRelatedLawBooks", method= RequestMethod.GET)
    @ResponseBody
    public ViewJSON<List<LawBookView>> loadRelatedLawBooks(@PathVariable(value="caseCode")String caseCode) {
        VerifyUtils.verifyReviseInfo(caseCode);
    	
    	CaseBase caseBase = new CaseBase();
        caseBase.setCaseCode(caseCode);
        caseBase = this.caseBaseService.selectOne(caseBase);
		Assert.NotNull(caseBase, "目标案件caseCode=" + caseCode + "不存在！");

        Set<Integer> bookNOs = new HashSet<>();
        List<HashMap<String,Integer>> lawBookNos = lawBaseService.queryLawBookNosByCauseNo(caseBase.getCaseType());
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
		return new ViewJSON<>("200","success",books);
    }


    /**
     * 加载案件相关法律标准
     * @param caseCode
     * @return
     */
    @RequestMapping(value="/{caseCode}/loadRelatedStandardBooks", method= RequestMethod.GET)
    @ResponseBody
    public ViewJSON<List<LawBookView>> loadRelatedStandardBooks(@PathVariable(value="caseCode")String caseCode) {
        VerifyUtils.verifyReviseInfo(caseCode);

        CaseBase caseBase = new CaseBase();
        caseBase.setCaseCode(caseCode);
        caseBase = this.caseBaseService.selectOne(caseBase);
        Assert.NotNull(caseBase, "目标案件caseCode=" + caseCode + "不存在！");

        Set<Integer> bookNOs = new HashSet<>();
        List<HashMap<String,Integer>> lawBookNos = lawBaseService.queryLawBookNosByCauseNo(caseBase.getCaseType());
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
        return new ViewJSON<>("200","success",books);
    }



}
