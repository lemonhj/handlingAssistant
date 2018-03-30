package com.beigebigdata.bdCourt.ca.cause.entity;

import com.septinary.common.core.basic.dto.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "bc_cause_fctr_query_config")
public class CauseFctrQueryConf extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 案由编号 0为通用
     */
    @Column(name = "cause_no")
    private Integer causeNo;

    /**
     * 元素名称
     */
    @Column(name = "fctr_name")
    private String fctrName;

    /**
     * 元素查询类型 1-输入框 2-单选下拉 3-多选下拉 4-复选框x 5-单选框
     */
    @Column(name = "fctr_query_type")
    private Integer fctrQueryType;

    /**
     * 要素查询内容,JSON对象
     */
    @Column(name = "fctr_query_content")
    private String fctrQueryContent;

    /**
     * 显示顺序
     */
    @Column(name = "show_order")
    private Integer showOrder;

    /**
     * 所占列数
     */
    @Column(name = "column_count")
    private Integer columnCount;

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
     * 获取案由编号 0为通用
     *
     * @return cause_no - 案由编号 0为通用
     */
    public Integer getCauseNo() {
        return causeNo;
    }

    /**
     * 设置案由编号 0为通用
     *
     * @param causeNo 案由编号 0为通用
     */
    public void setCauseNo(Integer causeNo) {
        this.causeNo = causeNo;
    }

    /**
     * 获取元素名称
     *
     * @return fctr_name - 元素名称
     */
    public String getFctrName() {
        return fctrName;
    }

    /**
     * 设置元素名称
     *
     * @param fctrName 元素名称
     */
    public void setFctrName(String fctrName) {
        this.fctrName = fctrName;
    }

    /**
     * 获取元素查询类型 1-输入框 2-单选下拉 3-多选下拉 4-复选框x 5-单选框
     *
     * @return fctr_query_type - 元素查询类型 1-输入框 2-单选下拉 3-多选下拉 4-复选框x 5-单选框
     */
    public Integer getFctrQueryType() {
        return fctrQueryType;
    }

    /**
     * 设置元素查询类型 1-输入框 2-单选下拉 3-多选下拉 4-复选框x 5-单选框
     *
     * @param fctrQueryType 元素查询类型 1-输入框 2-单选下拉 3-多选下拉 4-复选框x 5-单选框
     */
    public void setFctrQueryType(Integer fctrQueryType) {
        this.fctrQueryType = fctrQueryType;
    }

    /**
     * 获取要素查询内容,JSON对象
     *
     * @return fctr_query_content - 要素查询内容,JSON对象
     */
    public String getFctrQueryContent() {
        return fctrQueryContent;
    }

    /**
     * 设置要素查询内容,JSON对象
     *
     * @param fctrQueryContent 要素查询内容,JSON对象
     */
    public void setFctrQueryContent(String fctrQueryContent) {
        this.fctrQueryContent = fctrQueryContent;
    }

    /**
     * 获取显示顺序
     *
     * @return show_order - 显示顺序
     */
    public Integer getShowOrder() {
        return showOrder;
    }

    /**
     * 设置显示顺序
     *
     * @param showOrder 显示顺序
     */
    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    /**
     * 获取所占列数
     *
     * @return column_count - 所占列数
     */
    public Integer getColumnCount() {
        return columnCount;
    }

    /**
     * 设置所占列数
     *
     * @param columnCount 所占列数
     */
    public void setColumnCount(Integer columnCount) {
        this.columnCount = columnCount;
    }
}