package com.beigebigdata.bdCourt.ca.common.entity;

import com.septinary.common.core.basic.dto.BaseEntity;

import javax.persistence.*;

@SuppressWarnings("serial")
@Table(name = "devel_config")
public class Config extends BaseEntity {
    /**
     * ID:主键
     */
    @Id
    @Column(name = "conf_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 从属配置:配置项归属的上级配置节点~外键Config.ID
     */
    @Column(name = "conf_parent")
    private Long parent;

    /**
     * 配置排序:所在配置节点下的顺序~~用于配置项的展示顺序
     */
    @Column(name = "conf_order")
    private Float order;

    /**
     * 配置图标:配置项的图标URL地址
     */
    @Column(name = "conf_icon")
    private String icon;

    /**
     * 适用范围:配置项的适用范围路径
     */
    @Column(name = "conf_path")
    private String path;

    /**
     * 归属路径:配置项的归属X路径~自定义分隔符
     */
    @Column(name = "conf_xpath")
    private String xpath;

    /**
     * 配置导航:配置项的导航描述信息~与xpath保持一致
     */
    @Column(name = "conf_navigation")
    private String navigation;

    /**
     * 配置键名:配置项的标识~符合变量命名规则，相同parent下不能重复
     */
    @Column(name = "conf_key")
    private String key;

    /**
     * 是否必须:配置项是否必须项目？~~即如果删除或者配置无效会造成系统运行异常
     */
    @Column(name = "conf_must")
    private Boolean must;

    /**
     * 是否隐藏:配置项是否在展示时进行隐藏？~~即防止泄密
     */
    @Column(name = "conf_hidden")
    private Boolean hidden;

    /**
     * 数据类型:配置项取值的数据类型~枚举(关联FieldDataType的取值)
     */
    @Column(name = "conf_datatype")
    private Integer datatype;

    /**
     * 数据单位:配置项取值的数据单位~枚举(关联FieldDataUnit的取值)
     */
    @Column(name = "conf_dataunit")
    private Integer dataunit;

    /**
     * 取值分隔:配置项取值分隔符~~比如;,&|...等
     */
    @Column(name = "conf_seperater")
    private String seperater;

    /**
     * 值对等号:配置项取值值对等于符号~~比如"key=value","key:value","key=>value"等
     */
    @Column(name = "conf_eqtag")
    private String eqtag;

    /**
     * 删除:信息是否已被删除？
     */
    @Column(name = "conf_deleted")
    private Boolean deleted;

    /**
     * 配置键值:配置项的键值~根据datatype类型进行相应转换
     */
    @Column(name = "conf_value")
    private String value;

    /**
     * 备注信息:配置项的备注信息~即配置注释
     */
    @Column(name = "conf_memo")
    private String memo;

    /**
     * 获取ID:主键
     *
     * @return conf_id - ID:主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置ID:主键
     *
     * @param id ID:主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取从属配置:配置项归属的上级配置节点~外键Config.ID
     *
     * @return conf_parent - 从属配置:配置项归属的上级配置节点~外键Config.ID
     */
    public Long getParent() {
        return parent;
    }

    /**
     * 设置从属配置:配置项归属的上级配置节点~外键Config.ID
     *
     * @param parent 从属配置:配置项归属的上级配置节点~外键Config.ID
     */
    public void setParent(Long parent) {
        this.parent = parent;
    }

    /**
     * 获取配置排序:所在配置节点下的顺序~~用于配置项的展示顺序
     *
     * @return conf_order - 配置排序:所在配置节点下的顺序~~用于配置项的展示顺序
     */
    public Float getOrder() {
        return order;
    }

    /**
     * 设置配置排序:所在配置节点下的顺序~~用于配置项的展示顺序
     *
     * @param order 配置排序:所在配置节点下的顺序~~用于配置项的展示顺序
     */
    public void setOrder(Float order) {
        this.order = order;
    }

    /**
     * 获取配置图标:配置项的图标URL地址
     *
     * @return conf_icon - 配置图标:配置项的图标URL地址
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置配置图标:配置项的图标URL地址
     *
     * @param icon 配置图标:配置项的图标URL地址
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取适用范围:配置项的适用范围路径
     *
     * @return conf_path - 适用范围:配置项的适用范围路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置适用范围:配置项的适用范围路径
     *
     * @param path 适用范围:配置项的适用范围路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取归属路径:配置项的归属X路径~自定义分隔符
     *
     * @return conf_xpath - 归属路径:配置项的归属X路径~自定义分隔符
     */
    public String getXpath() {
        return xpath;
    }

    /**
     * 设置归属路径:配置项的归属X路径~自定义分隔符
     *
     * @param xpath 归属路径:配置项的归属X路径~自定义分隔符
     */
    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    /**
     * 获取配置导航:配置项的导航描述信息~与xpath保持一致
     *
     * @return conf_navigation - 配置导航:配置项的导航描述信息~与xpath保持一致
     */
    public String getNavigation() {
        return navigation;
    }

    /**
     * 设置配置导航:配置项的导航描述信息~与xpath保持一致
     *
     * @param navigation 配置导航:配置项的导航描述信息~与xpath保持一致
     */
    public void setNavigation(String navigation) {
        this.navigation = navigation;
    }

    /**
     * 获取配置键名:配置项的标识~符合变量命名规则，相同parent下不能重复
     *
     * @return conf_key - 配置键名:配置项的标识~符合变量命名规则，相同parent下不能重复
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置配置键名:配置项的标识~符合变量命名规则，相同parent下不能重复
     *
     * @param key 配置键名:配置项的标识~符合变量命名规则，相同parent下不能重复
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取是否必须:配置项是否必须项目？~~即如果删除或者配置无效会造成系统运行异常
     *
     * @return conf_must - 是否必须:配置项是否必须项目？~~即如果删除或者配置无效会造成系统运行异常
     */
    public Boolean getMust() {
        return must;
    }

    /**
     * 设置是否必须:配置项是否必须项目？~~即如果删除或者配置无效会造成系统运行异常
     *
     * @param must 是否必须:配置项是否必须项目？~~即如果删除或者配置无效会造成系统运行异常
     */
    public void setMust(Boolean must) {
        this.must = must;
    }

    /**
     * 获取是否隐藏:配置项是否在展示时进行隐藏？~~即防止泄密
     *
     * @return conf_hidden - 是否隐藏:配置项是否在展示时进行隐藏？~~即防止泄密
     */
    public Boolean getHidden() {
        return hidden;
    }

    /**
     * 设置是否隐藏:配置项是否在展示时进行隐藏？~~即防止泄密
     *
     * @param hidden 是否隐藏:配置项是否在展示时进行隐藏？~~即防止泄密
     */
    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * 获取数据类型:配置项取值的数据类型~枚举(关联FieldDataType的取值)
     *
     * @return conf_datatype - 数据类型:配置项取值的数据类型~枚举(关联FieldDataType的取值)
     */
    public Integer getDatatype() {
        return datatype;
    }

    /**
     * 设置数据类型:配置项取值的数据类型~枚举(关联FieldDataType的取值)
     *
     * @param datatype 数据类型:配置项取值的数据类型~枚举(关联FieldDataType的取值)
     */
    public void setDatatype(Integer datatype) {
        this.datatype = datatype;
    }

    /**
     * 获取数据单位:配置项取值的数据单位~枚举(关联FieldDataUnit的取值)
     *
     * @return conf_dataunit - 数据单位:配置项取值的数据单位~枚举(关联FieldDataUnit的取值)
     */
    public Integer getDataunit() {
        return dataunit;
    }

    /**
     * 设置数据单位:配置项取值的数据单位~枚举(关联FieldDataUnit的取值)
     *
     * @param dataunit 数据单位:配置项取值的数据单位~枚举(关联FieldDataUnit的取值)
     */
    public void setDataunit(Integer dataunit) {
        this.dataunit = dataunit;
    }

    /**
     * 获取取值分隔:配置项取值分隔符~~比如;,&|...等
     *
     * @return conf_seperater - 取值分隔:配置项取值分隔符~~比如;,&|...等
     */
    public String getSeperater() {
        return seperater;
    }

    /**
     * 设置取值分隔:配置项取值分隔符~~比如;,&|...等
     *
     * @param seperater 取值分隔:配置项取值分隔符~~比如;,&|...等
     */
    public void setSeperater(String seperater) {
        this.seperater = seperater;
    }

    /**
     * 获取值对等号:配置项取值值对等于符号~~比如"key=value","key:value","key=>value"等
     *
     * @return conf_eqtag - 值对等号:配置项取值值对等于符号~~比如"key=value","key:value","key=>value"等
     */
    public String getEqtag() {
        return eqtag;
    }

    /**
     * 设置值对等号:配置项取值值对等于符号~~比如"key=value","key:value","key=>value"等
     *
     * @param eqtag 值对等号:配置项取值值对等于符号~~比如"key=value","key:value","key=>value"等
     */
    public void setEqtag(String eqtag) {
        this.eqtag = eqtag;
    }

    /**
     * 获取删除:信息是否已被删除？
     *
     * @return conf_deleted - 删除:信息是否已被删除？
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * 设置删除:信息是否已被删除？
     *
     * @param deleted 删除:信息是否已被删除？
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取配置键值:配置项的键值~根据datatype类型进行相应转换
     *
     * @return conf_value - 配置键值:配置项的键值~根据datatype类型进行相应转换
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置配置键值:配置项的键值~根据datatype类型进行相应转换
     *
     * @param value 配置键值:配置项的键值~根据datatype类型进行相应转换
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取备注信息:配置项的备注信息~即配置注释
     *
     * @return conf_memo - 备注信息:配置项的备注信息~即配置注释
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 设置备注信息:配置项的备注信息~即配置注释
     *
     * @param memo 备注信息:配置项的备注信息~即配置注释
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }
}