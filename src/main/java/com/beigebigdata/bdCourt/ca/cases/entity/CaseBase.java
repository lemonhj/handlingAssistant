package com.beigebigdata.bdCourt.ca.cases.entity;

import com.septinary.common.core.basic.dto.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_case_bas")
public class CaseBase extends BaseEntity {
    /**
     * 案件id
     */
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 案件编号
     */
    @Column(name = "case_no")
    private String caseNo;

    /**
     * 创建时间
     */
    @Column(name = "case_create")
    private Date caseCreate;

    /**
     * 更新时间
     */
    @Column(name = "case_update")
    private Date caseUpdate;

    /**
     * 案件类型（案由）关联bc_cause_config.cause_no
     */
    @Column(name = "case_type")
    private Integer caseType;

    /**
     * 创建人  关联t_member.mem_code
     */
    @Column(name = "case_creater")
    private String caseCreater;

    /**
     * 案件名称
     */
    @Column(name = "case_name")
    private String caseName;

    /**
     * 案件状态  devel_field_name CaseStatus
     */
    @Column(name = "case_status")
    private Integer caseStatus;

    /**
     * 案件code
     */
    @Column(name = "case_code")
    private String caseCode;
    
    @Column(name = "case_memo")
    private String caseMemo;

    /**
     * 获取案件id
     *
     * @return id - 案件id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置案件id
     *
     * @param id 案件id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取案件编号
     *
     * @return case_no - 案件编号
     */
    public String getCaseNo() {
        return caseNo;
    }

    /**
     * 设置案件编号
     *
     * @param caseNo 案件编号
     */
    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    /**
     * 获取创建时间
     *
     * @return case_create - 创建时间
     */
    public Date getCaseCreate() {
        return caseCreate;
    }

    /**
     * 设置创建时间
     *
     * @param caseCreate 创建时间
     */
    public void setCaseCreate(Date caseCreate) {
        this.caseCreate = caseCreate;
    }

    /**
     * 获取更新时间
     *
     * @return case_update - 更新时间
     */
    public Date getCaseUpdate() {
        return caseUpdate;
    }

    /**
     * 设置更新时间
     *
     * @param caseUpdate 更新时间
     */
    public void setCaseUpdate(Date caseUpdate) {
        this.caseUpdate = caseUpdate;
    }

    /**
     * 获取案件类型（案由）关联bc_cause_config.cause_no
     *
     * @return case_type - 案件类型（案由）关联bc_cause_config.cause_no
     */
    public Integer getCaseType() {
        return caseType;
    }

    /**
     * 设置案件类型（案由）关联bc_cause_config.cause_no
     *
     * @param caseType 案件类型（案由）关联bc_cause_config.cause_no
     */
    public void setCaseType(Integer caseType) {
        this.caseType = caseType;
    }

    /**
     * 获取创建人  关联t_member.mem_code
     *
     * @return case_creater - 创建人  关联t_member.mem_code
     */
    public String getCaseCreater() {
        return caseCreater;
    }

    /**
     * 设置创建人  关联t_member.mem_code
     *
     * @param caseCreater 创建人  关联t_member.mem_code
     */
    public void setCaseCreater(String caseCreater) {
        this.caseCreater = caseCreater;
    }

    /**
     * 获取案件名称
     *
     * @return case_name - 案件名称
     */
    public String getCaseName() {
        return caseName;
    }

    /**
     * 设置案件名称
     *
     * @param caseName 案件名称
     */
    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    /**
     * 获取案件状态  devel_field_name CaseStatus
     *
     * @return case_status - 案件状态  devel_field_name CaseStatus
     */
    public Integer getCaseStatus() {
        return caseStatus;
    }

    /**
     * 设置案件状态  devel_field_name CaseStatus
     *
     * @param caseStatus 案件状态  devel_field_name CaseStatus
     */
    public void setCaseStatus(Integer caseStatus) {
        this.caseStatus = caseStatus;
    }

    /**
     * 获取案件code
     *
     * @return case_code - 案件code
     */
    public String getCaseCode() {
        return caseCode;
    }

    /**
     * 设置案件code
     *
     * @param caseCode 案件code
     */
    public void setCaseCode(String caseCode) {
        this.caseCode = caseCode;
    }

	public String getCaseMemo() {
		return caseMemo;
	}

	public void setCaseMemo(String caseMemo) {
		this.caseMemo = caseMemo;
	}
}