package com.beigebigdata.bdCourt.ca.evidence.entity;

import com.septinary.common.core.basic.dto.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "spc_cause_evid")
public class CauseEvidence extends BaseEntity {
    /**
     * ID
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 案由编码
     */
    @Column(name = "cause_no")
    private Integer causeNo;

    /**
     * 序号
     */
    private Integer sn;

    /**
     * 证据类别编号
     */
    @Column(name = "evid_no")
    private Integer evidNo;

    /**
     * 证据类别名称
     */
    @Column(name = "evid_name")
    private String evidName;

    /**
     * 上级证据类别编号
     */
    @Column(name = "p_evid_no")
    private Integer pEvidNo;

    /**
     * 必要证据标识 必要1，非必要0
     */
    @Column(name = "is_essential")
    private Integer isEssential;

    /**
     * 备注
     */
    private String rmk;

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

    @Column(name = "evid_typ")
    private Integer evidTyp;

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
     * 获取案由编码
     *
     * @return cause_no - 案由编码
     */
    public Integer getCauseNo() {
        return causeNo;
    }

    /**
     * 设置案由编码
     *
     * @param causeNo 案由编码
     */
    public void setCauseNo(Integer causeNo) {
        this.causeNo = causeNo;
    }

    /**
     * 获取序号
     *
     * @return sn - 序号
     */
    public Integer getSn() {
        return sn;
    }

    /**
     * 设置序号
     *
     * @param sn 序号
     */
    public void setSn(Integer sn) {
        this.sn = sn;
    }

    /**
     * 获取证据类别编号
     *
     * @return evid_no - 证据类别编号
     */
    public Integer getEvidNo() {
        return evidNo;
    }

    /**
     * 设置证据类别编号
     *
     * @param evidNo 证据类别编号
     */
    public void setEvidNo(Integer evidNo) {
        this.evidNo = evidNo;
    }

    /**
     * 获取证据类别名称
     *
     * @return evid_name - 证据类别名称
     */
    public String getEvidName() {
        return evidName;
    }

    /**
     * 设置证据类别名称
     *
     * @param evidName 证据类别名称
     */
    public void setEvidName(String evidName) {
        this.evidName = evidName;
    }

    /**
     * 获取上级证据类别编号
     *
     * @return p_evid_no - 上级证据类别编号
     */
    public Integer getpEvidNo() {
        return pEvidNo;
    }

    /**
     * 设置上级证据类别编号
     *
     * @param pEvidNo 上级证据类别编号
     */
    public void setpEvidNo(Integer pEvidNo) {
        this.pEvidNo = pEvidNo;
    }

    /**
     * 获取必要证据标识 必要1，非必要0
     *
     * @return is_essential - 必要证据标识 必要1，非必要0
     */
    public Integer getIsEssential() {
        return isEssential;
    }

    /**
     * 设置必要证据标识 必要1，非必要0
     *
     * @param isEssential 必要证据标识 必要1，非必要0
     */
    public void setIsEssential(Integer isEssential) {
        this.isEssential = isEssential;
    }

    /**
     * 获取备注
     *
     * @return rmk - 备注
     */
    public String getRmk() {
        return rmk;
    }

    /**
     * 设置备注
     *
     * @param rmk 备注
     */
    public void setRmk(String rmk) {
        this.rmk = rmk;
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
     * @return evid_typ
     */
    public Integer getEvidTyp() {
        return evidTyp;
    }

    /**
     * @param evidTyp
     */
    public void setEvidTyp(Integer evidTyp) {
        this.evidTyp = evidTyp;
    }
}