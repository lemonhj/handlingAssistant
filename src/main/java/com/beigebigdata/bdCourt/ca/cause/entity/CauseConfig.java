package com.beigebigdata.bdCourt.ca.cause.entity;

import com.septinary.common.core.basic.dto.BaseEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "bc_cause_config")
public class CauseConfig extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 案由编号
     */
    @Column(name = "cause_no")
    private Integer causeNo;

    /**
     * 案由信息组装BEAN
     */
    @Column(name = "cause_assembler_bean")
    private String causeAssemblerBean;

    /**
     * 案由分析BEAN
     */
    @Column(name = "cause_analser_bean")
    private String causeAnalserBean;

    /**
     * 要素适用类型 2-重案 3-轻案 4-特殊
     */
    @Column(name = "cause_fctr_type")
    private Integer causeFctrType;

    /**
     * 案由名称
     */
    @Column(name = "cause_name")
    private String causeName;

    /**
     * 案由种类 1-刑事 2-民事 3-行政 4-减刑假释
     */
    @Column(name = "cause_category")
    private Integer causeCategory;

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
     * 获取案由编号
     *
     * @return cause_no - 案由编号
     */
    public Integer getCauseNo() {
        return causeNo;
    }

    /**
     * 设置案由编号
     *
     * @param causeNo 案由编号
     */
    public void setCauseNo(Integer causeNo) {
        this.causeNo = causeNo;
    }

    /**
     * 获取案由信息组装BEAN
     *
     * @return cause_assembler_bean - 案由信息组装BEAN
     */
    public String getCauseAssemblerBean() {
        return causeAssemblerBean;
    }

    /**
     * 设置案由信息组装BEAN
     *
     * @param causeAssemblerBean 案由信息组装BEAN
     */
    public void setCauseAssemblerBean(String causeAssemblerBean) {
        this.causeAssemblerBean = causeAssemblerBean;
    }

    /**
     * 获取案由分析BEAN
     *
     * @return cause_analser_bean - 案由分析BEAN
     */
    public String getCauseAnalserBean() {
        return causeAnalserBean;
    }

    /**
     * 设置案由分析BEAN
     *
     * @param causeAnalserBean 案由分析BEAN
     */
    public void setCauseAnalserBean(String causeAnalserBean) {
        this.causeAnalserBean = causeAnalserBean;
    }

    /**
     * 获取要素适用类型 2-重案 3-轻案 4-特殊
     *
     * @return cause_fctr_type - 要素适用类型 2-重案 3-轻案 4-特殊
     */
    public Integer getCauseFctrType() {
        return causeFctrType;
    }

    /**
     * 设置要素适用类型 2-重案 3-轻案 4-特殊
     *
     * @param causeFctrType 要素适用类型 2-重案 3-轻案 4-特殊
     */
    public void setCauseFctrType(Integer causeFctrType) {
        this.causeFctrType = causeFctrType;
    }

    /**
     * 获取案由名称
     *
     * @return cause_name - 案由名称
     */
    public String getCauseName() {
        return causeName;
    }

    /**
     * 设置案由名称
     *
     * @param causeName 案由名称
     */
    public void setCauseName(String causeName) {
        this.causeName = causeName;
    }

    /**
     * 获取案由种类 1-刑事 2-民事 3-行政 4-减刑假释
     *
     * @return cause_category - 案由种类 1-刑事 2-民事 3-行政 4-减刑假释
     */
    public Integer getCauseCategory() {
        return causeCategory;
    }

    /**
     * 设置案由种类 1-刑事 2-民事 3-行政 4-减刑假释
     *
     * @param causeCategory 案由种类 1-刑事 2-民事 3-行政 4-减刑假释
     */
    public void setCauseCategory(Integer causeCategory) {
        this.causeCategory = causeCategory;
    }
}