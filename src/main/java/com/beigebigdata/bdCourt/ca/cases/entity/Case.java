package com.beigebigdata.bdCourt.ca.cases.entity;

import com.septinary.common.core.basic.dto.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: Case
 * @Description:案件类:历史案件
 * @date 16/11/2
 */
@Table(name = "spc_case_bas")
public class Case extends BaseEntity {
    /**
     * ID
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 案件编号
     */
    @Column(name = "case_no")
    private Long caseNo;

    /**
     * 案号
     */
    @Column(name = "case_num")
    private String caseNum;

    /**
     * select cst_no,cst_desc
from spc_cst_info
where cst_typ = 100001
     */
    @Column(name = "cause_no")
    private Integer causeNo;

    /**
     * 收到诉状日期
     */
    @Column(name = "rev_file_dt")
    private Date revFileDt;

    /**
     * 审查日期
     */
    @Column(name = "aud_dt")
    private Date audDt;

    /**
     * 立案审批日期
     */
    @Column(name = "file_aud_dt")
    private Date fileAudDt;

    /**
     * 立案日期
     */
    @Column(name = "file_dt")
    private Date fileDt;

    /**
     * 案件大小影响
     */
    @Column(name = "efct_lvl")
    private Integer efctLvl;

    /**
     * 案发起始日期
     */
    @Column(name = "bgn_dt")
    private Date bgnDt;

    /**
     * 案发截止日期
     */
    @Column(name = "end_dt")
    private Date endDt;

    /**
     * 共同犯罪
     */
    @Column(name = "com_guid")
    private Integer comGuid;

    /**
     * 犯罪进程
     */
    @Column(name = "proc_lvl")
    private String procLvl;

    /**
     * 是否造成损害结果
     */
    @Column(name = "is_injury_rslt")
    private String isInjuryRslt;

    /**
     * 是否需要赔偿
     */
    @Column(name = "is_pay")
    private String isPay;

    /**
     * 被害人过错程度
     */
    @Column(name = "fault_lvl")
    private String faultLvl;

    /**
     * select cst_no,cst_desc
from spc_cst_info
where cst_typ = 100005
     */
    @Column(name = "prov_code")
    private Integer provCode;

    /**
     * select cst_no,cst_desc
from spc_cst_info
where cst_typ = 100005
     */
    @Column(name = "city_code")
    private Integer cityCode;

    /**
     * select cst_no,cst_desc
from spc_cst_info
where cst_typ = 100005
     */
    @Column(name = "county_code")
    private Integer countyCode;

    /**
     * select cst_no,cst_desc
     */
    @Column(name = "court_no")
    private Integer courtNo;

    /**
     * 涉及法院名称
     */
    @Column(name = "court_name")
    private String courtName;

    /**
     * select cst_no,cst_desc
     */
    @Column(name = "judge_no")
    private Integer judgeNo;

    /**
     * 主审法官姓名
     */
    @Column(name = "judge_name")
    private String judgeName;

    /**
     * 记录进表时间
     */
    @Column(name = "ent_time")
    private Date entTime;

    /**
     * 记录修改时间
     */
    @Column(name = "upd_time")
    private Date updTime;

    /**
     * 记录落地时间
     */
    @Column(name = "grd_time")
    private Date grdTime;

    /**
     * 来源标识
     */
    @Column(name = "rs_id")
    private String rsId;

    /**
     * 来源记录
     */
    @Column(name = "rec_id")
    private String recId;

    /**
     * 记录更新标识
     */
    @Column(name = "upd_id")
    private Long updId;

    /**
     * 结案日期
     */
    @Column(name = "case_end_dt")
    private Date caseEndDt;

    /**
     * 案件类型
     */
    @Column(name = "case_typ")
    private Integer caseTyp;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
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
     * 获取案号
     *
     * @return case_num - 案号
     */
    public String getCaseNum() {
        return caseNum;
    }

    /**
     * 设置案号
     *
     * @param caseNum 案号
     */
    public void setCaseNum(String caseNum) {
        this.caseNum = caseNum;
    }

    /**
     * 获取select cst_no,cst_desc
from spc_cst_info
where cst_typ = 100001
     *
     * @return cause_no - select cst_no,cst_desc
from spc_cst_info
where cst_typ = 100001
     */
    public Integer getCauseNo() {
        return causeNo;
    }

    /**
     * 设置select cst_no,cst_desc
from spc_cst_info
where cst_typ = 100001
     *
     * @param causeNo select cst_no,cst_desc
from spc_cst_info
where cst_typ = 100001
     */
    public void setCauseNo(Integer causeNo) {
        this.causeNo = causeNo;
    }

    /**
     * 获取收到诉状日期
     *
     * @return rev_file_dt - 收到诉状日期
     */
    public Date getRevFileDt() {
        return revFileDt;
    }

    /**
     * 设置收到诉状日期
     *
     * @param revFileDt 收到诉状日期
     */
    public void setRevFileDt(Date revFileDt) {
        this.revFileDt = revFileDt;
    }

    /**
     * 获取审查日期
     *
     * @return aud_dt - 审查日期
     */
    public Date getAudDt() {
        return audDt;
    }

    /**
     * 设置审查日期
     *
     * @param audDt 审查日期
     */
    public void setAudDt(Date audDt) {
        this.audDt = audDt;
    }

    /**
     * 获取立案审批日期
     *
     * @return file_aud_dt - 立案审批日期
     */
    public Date getFileAudDt() {
        return fileAudDt;
    }

    /**
     * 设置立案审批日期
     *
     * @param fileAudDt 立案审批日期
     */
    public void setFileAudDt(Date fileAudDt) {
        this.fileAudDt = fileAudDt;
    }

    /**
     * 获取立案日期
     *
     * @return file_dt - 立案日期
     */
    public Date getFileDt() {
        return fileDt;
    }

    /**
     * 设置立案日期
     *
     * @param fileDt 立案日期
     */
    public void setFileDt(Date fileDt) {
        this.fileDt = fileDt;
    }

    /**
     * 获取案件大小影响
     *
     * @return efct_lvl - 案件大小影响
     */
    public Integer getEfctLvl() {
        return efctLvl;
    }

    /**
     * 设置案件大小影响
     *
     * @param efctLvl 案件大小影响
     */
    public void setEfctLvl(Integer efctLvl) {
        this.efctLvl = efctLvl;
    }

    /**
     * 获取案发起始日期
     *
     * @return bgn_dt - 案发起始日期
     */
    public Date getBgnDt() {
        return bgnDt;
    }

    /**
     * 设置案发起始日期
     *
     * @param bgnDt 案发起始日期
     */
    public void setBgnDt(Date bgnDt) {
        this.bgnDt = bgnDt;
    }

    /**
     * 获取案发截止日期
     *
     * @return end_dt - 案发截止日期
     */
    public Date getEndDt() {
        return endDt;
    }

    /**
     * 设置案发截止日期
     *
     * @param endDt 案发截止日期
     */
    public void setEndDt(Date endDt) {
        this.endDt = endDt;
    }

    /**
     * 获取共同犯罪
     *
     * @return com_guid - 共同犯罪
     */
    public Integer getComGuid() {
        return comGuid;
    }

    /**
     * 设置共同犯罪
     *
     * @param comGuid 共同犯罪
     */
    public void setComGuid(Integer comGuid) {
        this.comGuid = comGuid;
    }

    /**
     * 获取犯罪进程
     *
     * @return proc_lvl - 犯罪进程
     */
    public String getProcLvl() {
        return procLvl;
    }

    /**
     * 设置犯罪进程
     *
     * @param procLvl 犯罪进程
     */
    public void setProcLvl(String procLvl) {
        this.procLvl = procLvl;
    }

    /**
     * 获取是否造成损害结果
     *
     * @return is_injury_rslt - 是否造成损害结果
     */
    public String getIsInjuryRslt() {
        return isInjuryRslt;
    }

    /**
     * 设置是否造成损害结果
     *
     * @param isInjuryRslt 是否造成损害结果
     */
    public void setIsInjuryRslt(String isInjuryRslt) {
        this.isInjuryRslt = isInjuryRslt;
    }

    /**
     * 获取是否需要赔偿
     *
     * @return is_pay - 是否需要赔偿
     */
    public String getIsPay() {
        return isPay;
    }

    /**
     * 设置是否需要赔偿
     *
     * @param isPay 是否需要赔偿
     */
    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }

    /**
     * 获取被害人过错程度
     *
     * @return fault_lvl - 被害人过错程度
     */
    public String getFaultLvl() {
        return faultLvl;
    }

    /**
     * 设置被害人过错程度
     *
     * @param faultLvl 被害人过错程度
     */
    public void setFaultLvl(String faultLvl) {
        this.faultLvl = faultLvl;
    }

    /**
     * 获取select cst_no,cst_desc
from spc_cst_info
where cst_typ = 100005
     *
     * @return prov_code - select cst_no,cst_desc
from spc_cst_info
where cst_typ = 100005
     */
    public Integer getProvCode() {
        return provCode;
    }

    /**
     * 设置select cst_no,cst_desc
from spc_cst_info
where cst_typ = 100005
     *
     * @param provCode select cst_no,cst_desc
from spc_cst_info
where cst_typ = 100005
     */
    public void setProvCode(Integer provCode) {
        this.provCode = provCode;
    }

    /**
     * 获取select cst_no,cst_desc
from spc_cst_info
where cst_typ = 100005
     *
     * @return city_code - select cst_no,cst_desc
from spc_cst_info
where cst_typ = 100005
     */
    public Integer getCityCode() {
        return cityCode;
    }

    /**
     * 设置select cst_no,cst_desc
from spc_cst_info
where cst_typ = 100005
     *
     * @param cityCode select cst_no,cst_desc
from spc_cst_info
where cst_typ = 100005
     */
    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * 获取select cst_no,cst_desc
from spc_cst_info
where cst_typ = 100005
     *
     * @return county_code - select cst_no,cst_desc
from spc_cst_info
where cst_typ = 100005
     */
    public Integer getCountyCode() {
        return countyCode;
    }

    /**
     * 设置select cst_no,cst_desc
from spc_cst_info
where cst_typ = 100005
     *
     * @param countyCode select cst_no,cst_desc
from spc_cst_info
where cst_typ = 100005
     */
    public void setCountyCode(Integer countyCode) {
        this.countyCode = countyCode;
    }

    /**
     * 获取select cst_no,cst_desc
     *
     * @return court_no - select cst_no,cst_desc
     */
    public Integer getCourtNo() {
        return courtNo;
    }

    /**
     * 设置select cst_no,cst_desc
     *
     * @param courtNo select cst_no,cst_desc
     */
    public void setCourtNo(Integer courtNo) {
        this.courtNo = courtNo;
    }

    /**
     * 获取涉及法院名称
     *
     * @return court_name - 涉及法院名称
     */
    public String getCourtName() {
        return courtName;
    }

    /**
     * 设置涉及法院名称
     *
     * @param courtName 涉及法院名称
     */
    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    /**
     * 获取select cst_no,cst_desc
     *
     * @return judge_no - select cst_no,cst_desc
     */
    public Integer getJudgeNo() {
        return judgeNo;
    }

    /**
     * 设置select cst_no,cst_desc
     *
     * @param judgeNo select cst_no,cst_desc
     */
    public void setJudgeNo(Integer judgeNo) {
        this.judgeNo = judgeNo;
    }

    /**
     * 获取主审法官姓名
     *
     * @return judge_name - 主审法官姓名
     */
    public String getJudgeName() {
        return judgeName;
    }

    /**
     * 设置主审法官姓名
     *
     * @param judgeName 主审法官姓名
     */
    public void setJudgeName(String judgeName) {
        this.judgeName = judgeName;
    }

    /**
     * 获取记录进表时间
     *
     * @return ent_time - 记录进表时间
     */
    public Date getEntTime() {
        return entTime;
    }

    /**
     * 设置记录进表时间
     *
     * @param entTime 记录进表时间
     */
    public void setEntTime(Date entTime) {
        this.entTime = entTime;
    }

    /**
     * 获取记录修改时间
     *
     * @return upd_time - 记录修改时间
     */
    public Date getUpdTime() {
        return updTime;
    }

    /**
     * 设置记录修改时间
     *
     * @param updTime 记录修改时间
     */
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    /**
     * 获取记录落地时间
     *
     * @return grd_time - 记录落地时间
     */
    public Date getGrdTime() {
        return grdTime;
    }

    /**
     * 设置记录落地时间
     *
     * @param grdTime 记录落地时间
     */
    public void setGrdTime(Date grdTime) {
        this.grdTime = grdTime;
    }

    /**
     * 获取来源标识
     *
     * @return rs_id - 来源标识
     */
    public String getRsId() {
        return rsId;
    }

    /**
     * 设置来源标识
     *
     * @param rsId 来源标识
     */
    public void setRsId(String rsId) {
        this.rsId = rsId;
    }

    /**
     * 获取来源记录
     *
     * @return rec_id - 来源记录
     */
    public String getRecId() {
        return recId;
    }

    /**
     * 设置来源记录
     *
     * @param recId 来源记录
     */
    public void setRecId(String recId) {
        this.recId = recId;
    }

    /**
     * 获取记录更新标识
     *
     * @return upd_id - 记录更新标识
     */
    public Long getUpdId() {
        return updId;
    }

    /**
     * 设置记录更新标识
     *
     * @param updId 记录更新标识
     */
    public void setUpdId(Long updId) {
        this.updId = updId;
    }

    /**
     * 获取结案日期
     *
     * @return case_end_dt - 结案日期
     */
    public Date getCaseEndDt() {
        return caseEndDt;
    }

    /**
     * 设置结案日期
     *
     * @param caseEndDt 结案日期
     */
    public void setCaseEndDt(Date caseEndDt) {
        this.caseEndDt = caseEndDt;
    }

    /**
     * 获取案件类型
     *
     * @return case_typ - 案件类型
     */
    public Integer getCaseTyp() {
        return caseTyp;
    }

    /**
     * 设置案件类型
     *
     * @param caseTyp 案件类型
     */
    public void setCaseTyp(Integer caseTyp) {
        this.caseTyp = caseTyp;
    }
}