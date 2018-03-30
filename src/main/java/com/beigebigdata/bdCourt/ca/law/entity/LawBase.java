package com.beigebigdata.bdCourt.ca.law.entity;

import com.septinary.common.core.basic.dto.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: LawBaseDao
 * @Description:法律法规基本信息
 * @date 16/10/31
 */
@Table(name = "spc_law_bas")
public class LawBase extends BaseEntity {
    /**
     * ID
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 法律法规编号
     */
    @Column(name = "law_no")
    private Long lawNo;

    /**
     * 发布机构
     */
    @Column(name = "com_name")
    private String comName;

    /**
     * 批准机构
     */
    @Column(name = "appr_name")
    private String apprName;

    /**
     * 信息发布日期
     */
    @Column(name = "pub_dt")
    private Date pubDt;

    /**
     * 信息来源
     */
    @Column(name = "info_sour")
    private String infoSour;

    /**
     * 标题
     */
    private String tit;

    /**
     * 发文字号
     */
    @Column(name = "ver_num")
    private String verNum;

    /**
     * 法律法规类型
     */
    @Column(name = "typ_code")
    private Integer typCode;

    /**
     * 批准日期
     */
    @Column(name = "appr_dt")
    private Date apprDt;

    /**
     * 生效日期
     */
    @Column(name = "bgn_dt")
    private Date bgnDt;

    /**
     * 效力级别
     */
    @Column(name = "info_lvl")
    private Integer infoLvl;

    /**
     * 是否有效
     */
    @Column(name = "is_valid")
    private Integer isValid;

    /**
     * 废止日期
     */
    @Column(name = "canl_dt")
    private Date canlDt;

    /**
     * 废止原因
     */
    @Column(name = "canl_rsn")
    private String canlRsn;

    /**
     * 备注说明
     */
    private String rmk;

    /**
     * 链接地址
     */
    @Column(name = "lnk_addr")
    private String lnkAddr;

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
     * 法律更正记录编号
     */
    @Column(name = "law_sn")
    private Integer lawSn;

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
    public Long getLawNo() {
        return lawNo;
    }

    /**
     * 设置法律法规编号
     *
     * @param lawNo 法律法规编号
     */
    public void setLawNo(Long lawNo) {
        this.lawNo = lawNo;
    }

    /**
     * 获取发布机构
     *
     * @return com_name - 发布机构
     */
    public String getComName() {
        return comName;
    }

    /**
     * 设置发布机构
     *
     * @param comName 发布机构
     */
    public void setComName(String comName) {
        this.comName = comName;
    }

    /**
     * 获取批准机构
     *
     * @return appr_name - 批准机构
     */
    public String getApprName() {
        return apprName;
    }

    /**
     * 设置批准机构
     *
     * @param apprName 批准机构
     */
    public void setApprName(String apprName) {
        this.apprName = apprName;
    }

    /**
     * 获取信息发布日期
     *
     * @return pub_dt - 信息发布日期
     */
    public Date getPubDt() {
        return pubDt;
    }

    /**
     * 设置信息发布日期
     *
     * @param pubDt 信息发布日期
     */
    public void setPubDt(Date pubDt) {
        this.pubDt = pubDt;
    }

    /**
     * 获取信息来源
     *
     * @return info_sour - 信息来源
     */
    public String getInfoSour() {
        return infoSour;
    }

    /**
     * 设置信息来源
     *
     * @param infoSour 信息来源
     */
    public void setInfoSour(String infoSour) {
        this.infoSour = infoSour;
    }

    /**
     * 获取标题
     *
     * @return tit - 标题
     */
    public String getTit() {
        return tit;
    }

    /**
     * 设置标题
     *
     * @param tit 标题
     */
    public void setTit(String tit) {
        this.tit = tit;
    }

    /**
     * 获取发文字号
     *
     * @return ver_num - 发文字号
     */
    public String getVerNum() {
        return verNum;
    }

    /**
     * 设置发文字号
     *
     * @param verNum 发文字号
     */
    public void setVerNum(String verNum) {
        this.verNum = verNum;
    }

    /**
     * 获取法律法规类型
     *
     * @return typ_code - 法律法规类型
     */
    public Integer getTypCode() {
        return typCode;
    }

    /**
     * 设置法律法规类型
     *
     * @param typCode 法律法规类型
     */
    public void setTypCode(Integer typCode) {
        this.typCode = typCode;
    }

    /**
     * 获取批准日期
     *
     * @return appr_dt - 批准日期
     */
    public Date getApprDt() {
        return apprDt;
    }

    /**
     * 设置批准日期
     *
     * @param apprDt 批准日期
     */
    public void setApprDt(Date apprDt) {
        this.apprDt = apprDt;
    }

    /**
     * 获取生效日期
     *
     * @return bgn_dt - 生效日期
     */
    public Date getBgnDt() {
        return bgnDt;
    }

    /**
     * 设置生效日期
     *
     * @param bgnDt 生效日期
     */
    public void setBgnDt(Date bgnDt) {
        this.bgnDt = bgnDt;
    }

    /**
     * 获取效力级别
     *
     * @return info_lvl - 效力级别
     */
    public Integer getInfoLvl() {
        return infoLvl;
    }

    /**
     * 设置效力级别
     *
     * @param infoLvl 效力级别
     */
    public void setInfoLvl(Integer infoLvl) {
        this.infoLvl = infoLvl;
    }

    /**
     * 获取是否有效
     *
     * @return is_valid - 是否有效
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * 设置是否有效
     *
     * @param isValid 是否有效
     */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    /**
     * 获取废止日期
     *
     * @return canl_dt - 废止日期
     */
    public Date getCanlDt() {
        return canlDt;
    }

    /**
     * 设置废止日期
     *
     * @param canlDt 废止日期
     */
    public void setCanlDt(Date canlDt) {
        this.canlDt = canlDt;
    }

    /**
     * 获取废止原因
     *
     * @return canl_rsn - 废止原因
     */
    public String getCanlRsn() {
        return canlRsn;
    }

    /**
     * 设置废止原因
     *
     * @param canlRsn 废止原因
     */
    public void setCanlRsn(String canlRsn) {
        this.canlRsn = canlRsn;
    }

    /**
     * 获取备注说明
     *
     * @return rmk - 备注说明
     */
    public String getRmk() {
        return rmk;
    }

    /**
     * 设置备注说明
     *
     * @param rmk 备注说明
     */
    public void setRmk(String rmk) {
        this.rmk = rmk;
    }

    /**
     * 获取链接地址
     *
     * @return lnk_addr - 链接地址
     */
    public String getLnkAddr() {
        return lnkAddr;
    }

    /**
     * 设置链接地址
     *
     * @param lnkAddr 链接地址
     */
    public void setLnkAddr(String lnkAddr) {
        this.lnkAddr = lnkAddr;
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
     * 获取法律更正记录编号
     *
     * @return law_sn - 法律更正记录编号
     */
    public Integer getLawSn() {
        return lawSn;
    }

    /**
     * 设置法律更正记录编号
     *
     * @param lawSn 法律更正记录编号
     */
    public void setLawSn(Integer lawSn) {
        this.lawSn = lawSn;
    }
}