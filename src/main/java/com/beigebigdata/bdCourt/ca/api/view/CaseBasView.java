package com.beigebigdata.bdCourt.ca.api.view;

import java.util.Date;

/**
 * 用户案件列表视图
 * @author BD
 *
 */
public class CaseBasView {
    private Integer id;//案件id
    private String caseNo;// 案件编号
    private Date caseCreate;//创建时间
    private Date caseUpdate;//更新时间
    private Integer caseType;//案件类型（案由）关联bc_cause_config.cause_no
    private String caseCreater;//创建人  关联t_member.mem_code
    private String caseName;//案件名称
    private String caseStatus;//案件状态  devel_field_name CaseStatus
    private String caseCode;//案件code
    private String caseMemo;//案件最后处理备注
    private String memUsername; //表：t_member ，mem_username--创建人名称
    private Integer causeFctrType; //表：bc_cause_config， cause_fctr_type --要素适用类型 2-重案 3-轻案 4-特殊
    private String fvValue;//表：devel_field_value，fv_value
    private String fvParse;//表：devel_field_value，fv_parse
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public Date getCaseCreate() {
		return caseCreate;
	}
	public void setCaseCreate(Date caseCreate) {
		this.caseCreate = caseCreate;
	}
	public Date getCaseUpdate() {
		return caseUpdate;
	}
	public void setCaseUpdate(Date caseUpdate) {
		this.caseUpdate = caseUpdate;
	}
	
	public String getCaseCreater() {
		return caseCreater;
	}
	public void setCaseCreater(String caseCreater) {
		this.caseCreater = caseCreater;
	}
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public Integer getCaseType() {
		return caseType;
	}
	public void setCaseType(Integer caseType) {
		this.caseType = caseType;
	}
	public String getCaseStatus() {
		return caseStatus;
	}
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}
	public String getCaseCode() {
		return caseCode;
	}
	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}
	public String getCaseMemo() {
		return caseMemo;
	}
	public void setCaseMemo(String caseMemo) {
		this.caseMemo = caseMemo;
	}
	public String getMemUsername() {
		return memUsername;
	}
	public void setMemUsername(String memUsername) {
		this.memUsername = memUsername;
	}
	public Integer getCauseFctrType() {
		return causeFctrType;
	}
	public void setCauseFctrType(Integer causeFctrType) {
		this.causeFctrType = causeFctrType;
	}
	public String getFvValue() {
		return fvValue;
	}
	public void setFvValue(String fvValue) {
		this.fvValue = fvValue;
	}
	public String getFvParse() {
		return fvParse;
	}
	public void setFvParse(String fvParse) {
		this.fvParse = fvParse;
	}

    
}
