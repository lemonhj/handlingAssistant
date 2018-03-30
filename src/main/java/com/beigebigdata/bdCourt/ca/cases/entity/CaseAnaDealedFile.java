package com.beigebigdata.bdCourt.ca.cases.entity;

import com.septinary.common.core.basic.dto.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "bc_case_ana_dealed_file")
public class CaseAnaDealedFile extends BaseEntity {
    /**
     * 主键
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
     * 文件类型 1091常量
     */
    @Column(name = "file_type")
    private Integer fileType;

    /**
     * 处理后的文件内容
     */
    @Column(name = "dealed_file_cont")
    private String dealedFileCont;

    /**
     * 处理时间
     */
    @Column(name = "dealed_time")
    private Date dealedTime;

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
     * 获取文件类型 1091常量
     *
     * @return file_type - 文件类型 1091常量
     */
    public Integer getFileType() {
        return fileType;
    }

    /**
     * 设置文件类型 1091常量
     *
     * @param fileType 文件类型 1091常量
     */
    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    /**
     * 获取处理后的文件内容
     *
     * @return dealed_file_cont - 处理后的文件内容
     */
    public String getDealedFileCont() {
        return dealedFileCont;
    }

    /**
     * 设置处理后的文件内容
     *
     * @param dealedFileCont 处理后的文件内容
     */
    public void setDealedFileCont(String dealedFileCont) {
        this.dealedFileCont = dealedFileCont;
    }

    /**
     * 获取处理时间
     *
     * @return dealed_time - 处理时间
     */
    public Date getDealedTime() {
        return dealedTime;
    }

    /**
     * 设置处理时间
     *
     * @param dealedTime 处理时间
     */
    public void setDealedTime(Date dealedTime) {
        this.dealedTime = dealedTime;
    }
}