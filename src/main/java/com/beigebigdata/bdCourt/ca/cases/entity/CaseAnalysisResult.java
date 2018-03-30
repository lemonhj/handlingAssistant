package com.beigebigdata.bdCourt.ca.cases.entity;

import com.septinary.common.core.basic.dto.BaseEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CaseAnalysisResult
 * @Description:案件分析结果
 * @date 16/11/2
 */
@Table(name = "bc_case_ana_info")
public class CaseAnalysisResult extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 案件编号
     */
    @Column(name = "case_no")
    private Long caseNo;

    /**
     * 案件偏离度
     */
    @Column(name = "case_deviation")
    private BigDecimal caseDeviation;

    /**
     * 分析时间
     */
    @Column(name = "analyse_time")
    private Date analyseTime;

    /**
     * 分析结果 1-成功 2-无裁判文书 3-基础刑期分析失败 9-异常
     */
    @Column(name = "analyse_result")
    private Integer analyseResult;

    @Column(name = "case_fctr_array")
    private Object caseFctrArray;

    /**
     * 案由编号
     */
    @Column(name = "cause_no")
    private Integer causeNo;

    /**
     * 分析结果信息
     */
    @Column(name = "analyse_msg")
    private String analyseMsg;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取案件编号
     *
     * @return case_no - 案件编号
     */
    public Long getCaseNo() {
        return caseNo;
    }

    /**
     * 设置案件编号
     *
     * @param caseNo 案件编号
     */
    public void setCaseNo(Long caseNo) {
        this.caseNo = caseNo;
    }

    /**
     * 获取案件偏离度
     *
     * @return case_deviation - 案件偏离度
     */
    public BigDecimal getCaseDeviation() {
        return caseDeviation;
    }

    /**
     * 设置案件偏离度
     *
     * @param caseDeviation 案件偏离度
     */
    public void setCaseDeviation(BigDecimal caseDeviation) {
        this.caseDeviation = caseDeviation;
    }

    /**
     * 获取分析时间
     *
     * @return analyse_time - 分析时间
     */
    public Date getAnalyseTime() {
        return analyseTime;
    }

    /**
     * 设置分析时间
     *
     * @param analyseTime 分析时间
     */
    public void setAnalyseTime(Date analyseTime) {
        this.analyseTime = analyseTime;
    }

    /**
     * 获取分析结果 1-成功 2-无裁判文书 3-基础刑期分析失败 9-异常
     *
     * @return analyse_result - 分析结果 1-成功 2-无裁判文书 3-基础刑期分析失败 9-异常
     */
    public Integer getAnalyseResult() {
        return analyseResult;
    }

    /**
     * 设置分析结果 1-成功 2-无裁判文书 3-基础刑期分析失败 9-异常
     *
     * @param analyseResult 分析结果 1-成功 2-无裁判文书 3-基础刑期分析失败 9-异常
     */
    public void setAnalyseResult(Integer analyseResult) {
        this.analyseResult = analyseResult;
    }

    /**
     * @return case_fctr_array
     */
    public Object getCaseFctrArray() {
        return caseFctrArray;
    }

    /**
     * @param caseFctrArray
     */
    public void setCaseFctrArray(Object caseFctrArray) {
        this.caseFctrArray = caseFctrArray;
    }

    /**
     * 获取案由编号
     *
     * @return cause_no - 案由编号
     */
    public Integer getCauseNo() {
        return causeNo;
    }

    /**
     * 设置案由编号
     *
     * @param causeNo 案由编号
     */
    public void setCauseNo(Integer causeNo) {
        this.causeNo = causeNo;
    }

    /**
     * 获取分析结果信息
     *
     * @return analyse_msg - 分析结果信息
     */
    public String getAnalyseMsg() {
        return analyseMsg;
    }

    /**
     * 设置分析结果信息
     *
     * @param analyseMsg 分析结果信息
     */
    public void setAnalyseMsg(String analyseMsg) {
        this.analyseMsg = analyseMsg;
    }
}