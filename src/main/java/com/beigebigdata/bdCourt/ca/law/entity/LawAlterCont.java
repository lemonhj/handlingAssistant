package com.beigebigdata.bdCourt.ca.law.entity;

import com.septinary.common.core.basic.dto.BaseEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.util.Date;

@SuppressWarnings("serial")
@Table(name = "spc_law_alter_cont")
public class LawAlterCont extends BaseEntity {
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
     * 1 总则 ,2 分则,章序号10~99,节序号100~999,条文序号1000~9999
     */
    private Integer sn;

    /**
     * 章、节..的名称
     */
    @Column(name = "cont_name")
    private String contName;

    /**
     * 父序号(上一级序号)
     */
    private Integer psn;

    /**
     * 条文内容 ， 除xh为1000~9999  其他情况为null
     */
    @Column(name = "art_cont")
    private String artCont;

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
     * 获取1 总则 ,2 分则,章序号10~99,节序号100~999,条文序号1000~9999
     *
     * @return sn - 1 总则 ,2 分则,章序号10~99,节序号100~999,条文序号1000~9999
     */
    public Integer getSn() {
        return sn;
    }

    /**
     * 设置1 总则 ,2 分则,章序号10~99,节序号100~999,条文序号1000~9999
     *
     * @param sn 1 总则 ,2 分则,章序号10~99,节序号100~999,条文序号1000~9999
     */
    public void setSn(Integer sn) {
        this.sn = sn;
    }

    /**
     * 获取章、节..的名称
     *
     * @return cont_name - 章、节..的名称
     */
    public String getContName() {
        return contName;
    }

    /**
     * 设置章、节..的名称
     *
     * @param contName 章、节..的名称
     */
    public void setContName(String contName) {
        this.contName = contName;
    }

    /**
     * 获取父序号(上一级序号)
     *
     * @return psn - 父序号(上一级序号)
     */
    public Integer getPsn() {
        return psn;
    }

    /**
     * 设置父序号(上一级序号)
     *
     * @param psn 父序号(上一级序号)
     */
    public void setPsn(Integer psn) {
        this.psn = psn;
    }

    /**
     * 获取条文内容 ， 除xh为1000~9999  其他情况为null
     *
     * @return art_cont - 条文内容 ， 除xh为1000~9999  其他情况为null
     */
    public String getArtCont() {
        return artCont;
    }

    /**
     * 设置条文内容 ， 除xh为1000~9999  其他情况为null
     *
     * @param artCont 条文内容 ， 除xh为1000~9999  其他情况为null
     */
    public void setArtCont(String artCont) {
        this.artCont = artCont;
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
     * @return upd_id
     */
    public Long getUpdId() {
        return updId;
    }

    /**
     * @param updId
     */
    public void setUpdId(Long updId) {
        this.updId = updId;
    }
}