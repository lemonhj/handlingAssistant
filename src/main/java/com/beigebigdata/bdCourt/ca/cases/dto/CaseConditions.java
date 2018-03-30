package com.beigebigdata.bdCourt.ca.cases.dto;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CaseConditions
 * @Description:
 * @date 16/11/9
 */
public class CaseConditions {
    /**
     * 案件要素数组
     */
    private Integer[] causeNos;

    /**
     * 案件结案开始时间
     */
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date caseCloseSt;

    /**
     * 案件结案结束时间
     */
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date caseCloseEt;

    public Integer[] getCauseNos() {
        return causeNos;
    }

    public void setCauseNos(Integer[] causeNos) {
        this.causeNos = causeNos;
    }

    public Date getCaseCloseSt() {
        return caseCloseSt;
    }

    public void setCaseCloseSt(Date caseCloseSt) {
        this.caseCloseSt = caseCloseSt;
    }

    public Date getCaseCloseEt() {
        return caseCloseEt;
    }

    public void setCaseCloseEt(Date caseCloseEt) {
        this.caseCloseEt = caseCloseEt;
    }
}
