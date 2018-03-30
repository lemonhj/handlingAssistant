package com.beigebigdata.bdCourt.ca.cause.entity;

import com.septinary.common.core.basic.dto.BaseEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: CauseFactorMapper
 * @Description: 罪种要素信息
 * @date 16/11/1
 */
@Table(name = "spc_cause_fctr")
public class CauseFactor extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 要素名称
     */
    @Column(name = "fctr_name")
    private String fctrName;

    /**
     * 要素种类 1-通用 2-重案 3-轻案 4-特殊
     */
    @Column(name = "fctr_type")
    private Integer fctrType;

    /**
     * 要素编码
     */
    @Column(name = "fctr_no")
    private Integer fctrNo;

    /**
     * 上级要素编码
     */
    @Column(name = "p_fctr_no")
    private Integer pFctrNo;

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
     * 适用区域
     */
    @Column(name = "appl_area")
    private Integer applArea;

    /**
     * 要素说明
     */
    @Column(name = "fctr_desc")
    private String fctrDesc;

    /**
     * 刑罚量说明
     */
    @Column(name = "pun_desc")
    private String punDesc;

    /**
     * 刑罚量表达式
     */
    @Column(name = "pun_expr")
    private String punExpr;

    /**
     * 量刑类型 1-法定 2-酌定
     */
    @Column(name = "pun_type")
    private Integer punType;

    /**
     * 是否必要要素 0-否 1-是
     */
    @Column(name = "is_necessary")
    private Integer isNecessary;

    /**
     * 是否在导图中展示 0-否 1-是
     */
    @Column(name = "is_show_picture")
    private Integer isShowPicture;

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
     * 有效期开始日期
     */
    @Column(name = "start_date")
    private Date startDate;

    /**
     * 有效期结束日期
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * 判决描述
     */
    @Column(name = "decision_desc")
    private String decisionDesc;

    /**
     * 判决描述类型 1-整案 2-个人
     */
    @Column(name = "decision_type")
    private Integer decisionType;

    /**
     * 要素对应案由种类 1-刑事 2-民事 3-行政 4-减刑假释
     */
    @Column(name = "fctr_category")
    private Integer fctrCategory;

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
     * 获取要素名称
     *
     * @return fctr_name - 要素名称
     */
    public String getFctrName() {
        return fctrName;
    }

    /**
     * 设置要素名称
     *
     * @param fctrName 要素名称
     */
    public void setFctrName(String fctrName) {
        this.fctrName = fctrName;
    }

    /**
     * 获取要素种类 1-通用 2-重案 3-轻案 4-特殊
     *
     * @return fctr_type - 要素种类 1-通用 2-重案 3-轻案 4-特殊
     */
    public Integer getFctrType() {
        return fctrType;
    }

    /**
     * 设置要素种类 1-通用 2-重案 3-轻案 4-特殊
     *
     * @param fctrType 要素种类 1-通用 2-重案 3-轻案 4-特殊
     */
    public void setFctrType(Integer fctrType) {
        this.fctrType = fctrType;
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
     * 获取上级要素编码
     *
     * @return p_fctr_no - 上级要素编码
     */
    public Integer getpFctrNo() {
        return pFctrNo;
    }

    /**
     * 设置上级要素编码
     *
     * @param pFctrNo 上级要素编码
     */
    public void setpFctrNo(Integer pFctrNo) {
        this.pFctrNo = pFctrNo;
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
     * 获取适用区域
     *
     * @return appl_area - 适用区域
     */
    public Integer getApplArea() {
        return applArea;
    }

    /**
     * 设置适用区域
     *
     * @param applArea 适用区域
     */
    public void setApplArea(Integer applArea) {
        this.applArea = applArea;
    }

    /**
     * 获取要素说明
     *
     * @return fctr_desc - 要素说明
     */
    public String getFctrDesc() {
        return fctrDesc;
    }

    /**
     * 设置要素说明
     *
     * @param fctrDesc 要素说明
     */
    public void setFctrDesc(String fctrDesc) {
        this.fctrDesc = fctrDesc;
    }

    /**
     * 获取刑罚量说明
     *
     * @return pun_desc - 刑罚量说明
     */
    public String getPunDesc() {
        return punDesc;
    }

    /**
     * 设置刑罚量说明
     *
     * @param punDesc 刑罚量说明
     */
    public void setPunDesc(String punDesc) {
        this.punDesc = punDesc;
    }

    /**
     * 获取刑罚量表达式
     *
     * @return pun_expr - 刑罚量表达式
     */
    public String getPunExpr() {
        return punExpr;
    }

    /**
     * 设置刑罚量表达式
     *
     * @param punExpr 刑罚量表达式
     */
    public void setPunExpr(String punExpr) {
        this.punExpr = punExpr;
    }

    /**
     * 获取量刑类型 1-法定 2-酌定
     *
     * @return pun_type - 量刑类型 1-法定 2-酌定
     */
    public Integer getPunType() {
        return punType;
    }

    /**
     * 设置量刑类型 1-法定 2-酌定
     *
     * @param punType 量刑类型 1-法定 2-酌定
     */
    public void setPunType(Integer punType) {
        this.punType = punType;
    }

    /**
     * 获取是否必要要素 0-否 1-是
     *
     * @return is_necessary - 是否必要要素 0-否 1-是
     */
    public Integer getIsNecessary() {
        return isNecessary;
    }

    /**
     * 设置是否必要要素 0-否 1-是
     *
     * @param isNecessary 是否必要要素 0-否 1-是
     */
    public void setIsNecessary(Integer isNecessary) {
        this.isNecessary = isNecessary;
    }

    /**
     * 获取是否在导图中展示 0-否 1-是
     *
     * @return is_show_picture - 是否在导图中展示 0-否 1-是
     */
    public Integer getIsShowPicture() {
        return isShowPicture;
    }

    /**
     * 设置是否在导图中展示 0-否 1-是
     *
     * @param isShowPicture 是否在导图中展示 0-否 1-是
     */
    public void setIsShowPicture(Integer isShowPicture) {
        this.isShowPicture = isShowPicture;
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
     * 获取有效期开始日期
     *
     * @return start_date - 有效期开始日期
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置有效期开始日期
     *
     * @param startDate 有效期开始日期
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取有效期结束日期
     *
     * @return end_date - 有效期结束日期
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置有效期结束日期
     *
     * @param endDate 有效期结束日期
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取判决描述
     *
     * @return decision_desc - 判决描述
     */
    public String getDecisionDesc() {
        return decisionDesc;
    }

    /**
     * 设置判决描述
     *
     * @param decisionDesc 判决描述
     */
    public void setDecisionDesc(String decisionDesc) {
        this.decisionDesc = decisionDesc;
    }

    /**
     * 获取判决描述类型 1-整案 2-个人
     *
     * @return decision_type - 判决描述类型 1-整案 2-个人
     */
    public Integer getDecisionType() {
        return decisionType;
    }

    /**
     * 设置判决描述类型 1-整案 2-个人
     *
     * @param decisionType 判决描述类型 1-整案 2-个人
     */
    public void setDecisionType(Integer decisionType) {
        this.decisionType = decisionType;
    }

    /**
     * 获取要素对应案由种类 1-刑事 2-民事 3-行政 4-减刑假释
     *
     * @return fctr_category - 要素对应案由种类 1-刑事 2-民事 3-行政 4-减刑假释
     */
    public Integer getFctrCategory() {
        return fctrCategory;
    }

    /**
     * 设置要素对应案由种类 1-刑事 2-民事 3-行政 4-减刑假释
     *
     * @param fctrCategory 要素对应案由种类 1-刑事 2-民事 3-行政 4-减刑假释
     */
    public void setFctrCategory(Integer fctrCategory) {
        this.fctrCategory = fctrCategory;
    }
}