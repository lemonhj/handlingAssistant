package com.beigebigdata.bdCourt.ca.cases.controller;

import com.beigebigdata.bdCourt.ca.cases.entity.CaseAnaDealedFile;
import com.beigebigdata.bdCourt.ca.cases.service.CaseAnaDealedFileService;
import com.septinary.api.annotation.SysTraceLog;
import com.septinary.common.util.VerifyUtils;
import com.septinary.common.util.ViewJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CaseController
 * @Description:提供的案件分析后文件的访问接口
 * @date 16/10/21
 */
@Controller
@RequestMapping("v1.0/caseAnaDealedFiles")
public class CaseAnaDealedFileController {

    @Autowired
    private CaseAnaDealedFileService caseAnaDealedFileService;


    /**
     * 根据案件编号获取案件的裁判文书
     * @param caseNo 案件编号
     * @return
     */
    @RequestMapping(value = "/{caseNo}/judgment",method = RequestMethod.GET)
    @ResponseBody
    @SysTraceLog(description = "根据案件编号获取案件的裁判文书")
    public ViewJSON<String> fetchJudgment(@PathVariable(value = "caseNo")Long caseNo){
        VerifyUtils.verifyReviseInfo(caseNo);

        CaseAnaDealedFile caseAnaDealedFile= new CaseAnaDealedFile();
        caseAnaDealedFile.setCaseNo(caseNo);
        caseAnaDealedFile = caseAnaDealedFileService.selectOne(caseAnaDealedFile);

        if (caseAnaDealedFile == null) return new ViewJSON<>("404","CASE_NO NOT FOUND","案件不存在！","","");

        return new ViewJSON<>("200","success","","",caseAnaDealedFile.getDealedFileCont());
    }
}
