package com.beigebigdata.bdCourt.ca.evidence.entity;

import com.septinary.common.core.basic.dto.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_case_evid")
public class CaseEvidence extends BaseEntity {
    /**
     * id
     */
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 案件编号
     */
    @Column(name = "case_code")
    private String caseCode;

    /**
     * 是否提供
     */
    @Column(name = "ce_issupply")
    private Boolean ceIssupply;

    /**
     * 提供时间
     */
    @Column(name = "ce_supply_time")
    private Date ceSupplyTime;

    /**
     * 更新时间
     */
    @Column(name = "ce_update")
    private Date ceUpdate;

    /**
     * 对应的证据类别名称
     */
    @Column(name = "ce_evid_no")
    private Integer ceEvidNo;

    /**
     * 父证据类型编号
     */
    @Column(name = "ce_p_evid_no")
    private Integer cePEvidNo;


    /**
     * 案件证据编号
     */
    @Column(name = "case_evid_code")
    private String caseEvidCode;

    public String getCaseEvidCode() {
        return caseEvidCode;
    }

    public void setCaseEvidCode(String caseEvidCode) {
        this.caseEvidCode = caseEvidCode;
    }

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取案件编号
     *
     * @return case_no - 案件编号
     */
    public String getCaseCode() {
        return caseCode;
    }

    /**
     * 设置案件编号
     *
     * @param caseCode 案件编号
     */
    public void setCaseCode(String caseCode) {
        this.caseCode = caseCode;
    }

    /**
     * 获取是否提供
     *
     * @return ce_issupply - 是否提供
     */
    public Boolean getCeIssupply() {
        return ceIssupply;
    }

    /**
     * 设置是否提供
     *
     * @param ceIssupply 是否提供
     */
    public void setCeIssupply(Boolean ceIssupply) {
        this.ceIssupply = ceIssupply;
    }

    /**
     * 获取提供时间
     *
     * @return ce_supply_time - 提供时间
     */
    public Date getCeSupplyTime() {
        return ceSupplyTime;
    }

    /**
     * 设置提供时间
     *
     * @param ceSupplyTime 提供时间
     */
    public void setCeSupplyTime(Date ceSupplyTime) {
        this.ceSupplyTime = ceSupplyTime;
    }

    /**
     * 获取更新时间
     *
     * @return ce_update - 更新时间
     */
    public Date getCeUpdate() {
        return ceUpdate;
    }

    /**
     * 设置更新时间
     *
     * @param ceUpdate 更新时间
     */
    public void setCeUpdate(Date ceUpdate) {
        this.ceUpdate = ceUpdate;
    }

    /**
     * 获取对应的证据类别名称
     *
     * @return ce_evid_no - 对应的证据类别名称
     */
    public Integer getCeEvidNo() {
        return ceEvidNo;
    }

    /**
     * 设置对应的证据类别名称
     *
     * @param ceEvidNo 对应的证据类别名称
     */
    public void setCeEvidNo(Integer ceEvidNo) {
        this.ceEvidNo = ceEvidNo;
    }

    /**
     * 获取父证据类型编号
     *
     * @return ce_p_evid_no - 父证据类型编号
     */
    public Integer getCePEvidNo() {
        return cePEvidNo;
    }

    /**
     * 设置父证据类型编号
     *
     * @param cePEvidNo 父证据类型编号
     */
    public void setCePEvidNo(Integer cePEvidNo) {
        this.cePEvidNo = cePEvidNo;
    }
}