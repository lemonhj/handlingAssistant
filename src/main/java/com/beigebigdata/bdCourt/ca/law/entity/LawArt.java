package com.beigebigdata.bdCourt.ca.law.entity;

import com.septinary.common.core.basic.dto.BaseEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.util.Date;

@SuppressWarnings("serial")
@Table(name = "spc_law_art")
public class LawArt extends BaseEntity {
    /**
     * ID
     */
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 法律法规编号
     */
    @Column(name = "law_no")
    private Integer lawNo;

    /**
     * 条款编号
     */
    @Column(name = "art_no")
    private String artNo;

    /**
     * 条款内容
     */
    @Column(name = "art_cont")
    private String artCont;

    /**
     * 条款解释
     */
    @Column(name = "art_expl")
    private String artExpl;

    /**
     * 条款编号说明
     */
    @Column(name = "art_no_desc")
    private String artNoDesc;

    /**
     * 条款是否有效
     */
    @Column(name = "is_valid")
    private Integer isValid;

    /**
     * 条款失效日期
     */
    @Column(name = "canl_dt")
    private Date canlDt;

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

    @Column(name = "bgn_dt")
    private Date bgnDt;

    @Column(name = "itm_no")
    private Integer itmNo;

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
     * 获取法律法规编号
     *
     * @return law_no - 法律法规编号
     */
    public Integer getLawNo() {
        return lawNo;
    }

    /**
     * 设置法律法规编号
     *
     * @param lawNo 法律法规编号
     */
    public void setLawNo(Integer lawNo) {
        this.lawNo = lawNo;
    }

    /**
     * 获取条款编号
     *
     * @return art_no - 条款编号
     */
    public String getArtNo() {
        return artNo;
    }

    /**
     * 设置条款编号
     *
     * @param artNo 条款编号
     */
    public void setArtNo(String artNo) {
        this.artNo = artNo;
    }

    /**
     * 获取条款内容
     *
     * @return art_cont - 条款内容
     */
    public String getArtCont() {
        return artCont;
    }

    /**
     * 设置条款内容
     *
     * @param artCont 条款内容
     */
    public void setArtCont(String artCont) {
        this.artCont = artCont;
    }

    /**
     * 获取条款解释
     *
     * @return art_expl - 条款解释
     */
    public String getArtExpl() {
        return artExpl;
    }

    /**
     * 设置条款解释
     *
     * @param artExpl 条款解释
     */
    public void setArtExpl(String artExpl) {
        this.artExpl = artExpl;
    }

    /**
     * 获取条款编号说明
     *
     * @return art_no_desc - 条款编号说明
     */
    public String getArtNoDesc() {
        return artNoDesc;
    }

    /**
     * 设置条款编号说明
     *
     * @param artNoDesc 条款编号说明
     */
    public void setArtNoDesc(String artNoDesc) {
        this.artNoDesc = artNoDesc;
    }

    /**
     * 获取条款是否有效
     *
     * @return is_valid - 条款是否有效
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * 设置条款是否有效
     *
     * @param isValid 条款是否有效
     */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    /**
     * 获取条款失效日期
     *
     * @return canl_dt - 条款失效日期
     */
    public Date getCanlDt() {
        return canlDt;
    }

    /**
     * 设置条款失效日期
     *
     * @param canlDt 条款失效日期
     */
    public void setCanlDt(Date canlDt) {
        this.canlDt = canlDt;
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
     * @return bgn_dt
     */
    public Date getBgnDt() {
        return bgnDt;
    }

    /**
     * @param bgnDt
     */
    public void setBgnDt(Date bgnDt) {
        this.bgnDt = bgnDt;
    }

    /**
     * @return itm_no
     */
    public Integer getItmNo() {
        return itmNo;
    }

    /**
     * @param itmNo
     */
    public void setItmNo(Integer itmNo) {
        this.itmNo = itmNo;
    }
}