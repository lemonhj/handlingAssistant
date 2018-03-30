package com.beigebigdata.bdCourt.ca.law.entity;

import com.septinary.common.core.basic.dto.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "spc_law_direc_struc")
public class LawCatalog extends BaseEntity {
    /**
     * ID
     */
    @Id
    private Long id;

    /**
     * 法律法规编号
     */
    @Column(name = "law_no")
    private Integer lawNo;

    /**
     * 最后一层子节点  1.00~1000，上一层字节点 1001~1100，上上层子节点 1101~1200，父节点1201~1300,与art_no关联
     */
    private String sn;

    /**
     * 章、节..的名称
     */
    @Column(name = "cont_name")
    private String contName;

    /**
     * 父序号(上一级序号)
     */
    @Column(name = "p_sn")
    private String pSn;

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
     * 该p_sn对应下一级最小的sn，转换用
     */
    @Column(name = "min_sn")
    private Integer minSn;

    /**
     * 该p_sn对应下一级最大的sn，转换用
     */
    @Column(name = "max_sn")
    private Integer maxSn;


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
     * 获取最后一层子节点  1.00~1000，上一层字节点 1001~1100，上上层子节点 1101~1200，父节点1201~1300,与art_no关联
     *
     * @return sn - 最后一层子节点  1.00~1000，上一层字节点 1001~1100，上上层子节点 1101~1200，父节点1201~1300,与art_no关联
     */
    public String getSn() {
        return sn;
    }

    /**
     * 设置最后一层子节点  1.00~1000，上一层字节点 1001~1100，上上层子节点 1101~1200，父节点1201~1300,与art_no关联
     *
     * @param sn 最后一层子节点  1.00~1000，上一层字节点 1001~1100，上上层子节点 1101~1200，父节点1201~1300,与art_no关联
     */
    public void setSn(String sn) {
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
     * @return p_sn - 父序号(上一级序号)
     */
    public String getpSn() {
        return pSn;
    }

    /**
     * 设置父序号(上一级序号)
     *
     * @param pSn 父序号(上一级序号)
     */
    public void setpSn(String pSn) {
        this.pSn = pSn;
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

    /**
     * 获取该p_sn对应下一级最小的sn，转换用
     *
     * @return min_sn - 该p_sn对应下一级最小的sn，转换用
     */
    public Integer getMinSn() {
        return minSn;
    }

    /**
     * 设置该p_sn对应下一级最小的sn，转换用
     *
     * @param minSn 该p_sn对应下一级最小的sn，转换用
     */
    public void setMinSn(Integer minSn) {
        this.minSn = minSn;
    }

    /**
     * 获取该p_sn对应下一级最大的sn，转换用
     *
     * @return max_sn - 该p_sn对应下一级最大的sn，转换用
     */
    public Integer getMaxSn() {
        return maxSn;
    }

    /**
     * 设置该p_sn对应下一级最大的sn，转换用
     *
     * @param maxSn 该p_sn对应下一级最大的sn，转换用
     */
    public void setMaxSn(Integer maxSn) {
        this.maxSn = maxSn;
    }
}