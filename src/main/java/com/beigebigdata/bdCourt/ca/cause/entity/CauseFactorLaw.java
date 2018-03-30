package com.beigebigdata.bdCourt.ca.cause.entity;

import com.septinary.common.core.basic.dto.BaseEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CauseFactorLaw
 * @Description: 罪种要素与法律关系
 * @date 16/10/21
 */
@Table(name = "spc_cause_fctr_law")
public class CauseFactorLaw extends BaseEntity {
    /**
     * ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 要素编码
     */
    @Column(name = "fctr_no")
    private Integer fctrNo;

    /**
     * 对应法规编号
     */
    @Column(name = "law_no")
    private Integer lawNo;

    /**
     * 对应条款编号
     */
    @Column(name = "art_no")
    private String artNo;

    /**
     * 对应款项编号
     */
    @Column(name = "itm_no")
    private Integer itmNo;

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
     * 获取要素编码
     *
     * @return fctr_no - 要素编码
     */
    public Integer getFctrNo() {
        return fctrNo;
    }

    /**
     * 设置要素编码
     *
     * @param fctrNo 要素编码
     */
    public void setFctrNo(Integer fctrNo) {
        this.fctrNo = fctrNo;
    }

    /**
     * 获取对应法规编号
     *
     * @return law_no - 对应法规编号
     */
    public Integer getLawNo() {
        return lawNo;
    }

    /**
     * 设置对应法规编号
     *
     * @param lawNo 对应法规编号
     */
    public void setLawNo(Integer lawNo) {
        this.lawNo = lawNo;
    }

    /**
     * 获取对应条款编号
     *
     * @return art_no - 对应条款编号
     */
    public String getArtNo() {
        return artNo;
    }

    /**
     * 设置对应条款编号
     *
     * @param artNo 对应条款编号
     */
    public void setArtNo(String artNo) {
        this.artNo = artNo;
    }

    /**
     * 获取对应款项编号
     *
     * @return itm_no - 对应款项编号
     */
    public Integer getItmNo() {
        return itmNo;
    }

    /**
     * 设置对应款项编号
     *
     * @param itmNo 对应款项编号
     */
    public void setItmNo(Integer itmNo) {
        this.itmNo = itmNo;
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
}