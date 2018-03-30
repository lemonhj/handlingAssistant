package com.beigebigdata.bdCourt.ca.common.entity;

import com.septinary.common.core.basic.dto.BaseEntity;

import javax.persistence.*;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: FieldName
 * @Description:
 * @date 16/10/20
 */
@SuppressWarnings("serial")
@Table(name = "devel_field_name")
public class FieldName extends BaseEntity {
    /**
     * ID:主键
     */
    @Id
    @Column(name = "fn_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 字段图标:字段定义的图标URL地址
     */
    @Column(name = "fn_icon")
    private String icon;

    /**
     * 适用范围:字段定义的适用范围路径~自定义分隔符
     */
    @Column(name = "fn_path")
    private String path;

    /**
     * 字段名称:字段定义的枚举类型名称~符合变量命名规则，相同X路径下不能重复
     */
    @Column(name = "fn_name")
    private String name;

    /**
     * 类型释义:字段定义的类型的解释与含义说明
     */
    @Column(name = "fn_parse")
    private String parse;

    /**
     * 是否只读:字段定义的枚举取值是否只读模式？~~只读模式则所有取值也为制度模式，并且不允许增减取值列表
     */
    @Column(name = "fn_readonly")
    private Boolean readonly;

    /**
     * 取值Key前缀:字段枚举取值的Key前缀
     */
    @Column(name = "fn_key_prefix")
    private String keyPrefix;

    /**
     * 取值Key后缀:字段枚举取值的Key后缀
     */
    @Column(name = "fn_key_suffix")
    private String keySuffix;

    /**
     * 取值Key类型:字段枚举取值的Key命名类型~枚举(关联FieldValueKeyNamingType的取值)
     */
    @Column(name = "fn_key_namingtype")
    private Integer keyNamingtype;

    /**
     * 取值Value方式:字段枚举取值的Value依据方式~枚举(关联FieldValueValueBy的取值)
     */
    @Column(name = "fn_value_by")
    private Integer valueBy;

    /**
     * 取值类型:字段的取值类型~枚举(关联FieldDataType的取值)
     */
    @Column(name = "fn_datatype")
    private Integer datatype;

    /**
     * 取值单位:字段的取值单位~枚举(关联FieldDataUnit的取值)
     */
    @Column(name = "fn_dataunit")
    private Integer dataunit;

    /**
     * 删除:信息是否已被删除？
     */
    @Column(name = "fn_deleted")
    private Boolean deleted;

    /**
     * 类型描述:字段定义的类型的描述信息~~即字段定义的注释
     */
    @Column(name = "fn_desc")
    private String desc;

    /**
     * 获取ID:主键
     *
     * @return fn_id - ID:主键
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
     * 获取字段图标:字段定义的图标URL地址
     *
     * @return fn_icon - 字段图标:字段定义的图标URL地址
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置字段图标:字段定义的图标URL地址
     *
     * @param icon 字段图标:字段定义的图标URL地址
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取适用范围:字段定义的适用范围路径~自定义分隔符
     *
     * @return fn_path - 适用范围:字段定义的适用范围路径~自定义分隔符
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置适用范围:字段定义的适用范围路径~自定义分隔符
     *
     * @param path 适用范围:字段定义的适用范围路径~自定义分隔符
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取字段名称:字段定义的枚举类型名称~符合变量命名规则，相同X路径下不能重复
     *
     * @return fn_name - 字段名称:字段定义的枚举类型名称~符合变量命名规则，相同X路径下不能重复
     */
    public String getName() {
        return name;
    }

    /**
     * 设置字段名称:字段定义的枚举类型名称~符合变量命名规则，相同X路径下不能重复
     *
     * @param name 字段名称:字段定义的枚举类型名称~符合变量命名规则，相同X路径下不能重复
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取类型释义:字段定义的类型的解释与含义说明
     *
     * @return fn_parse - 类型释义:字段定义的类型的解释与含义说明
     */
    public String getParse() {
        return parse;
    }

    /**
     * 设置类型释义:字段定义的类型的解释与含义说明
     *
     * @param parse 类型释义:字段定义的类型的解释与含义说明
     */
    public void setParse(String parse) {
        this.parse = parse;
    }

    /**
     * 获取是否只读:字段定义的枚举取值是否只读模式？~~只读模式则所有取值也为制度模式，并且不允许增减取值列表
     *
     * @return fn_readonly - 是否只读:字段定义的枚举取值是否只读模式？~~只读模式则所有取值也为制度模式，并且不允许增减取值列表
     */
    public Boolean getReadonly() {
        return readonly;
    }

    /**
     * 设置是否只读:字段定义的枚举取值是否只读模式？~~只读模式则所有取值也为制度模式，并且不允许增减取值列表
     *
     * @param readonly 是否只读:字段定义的枚举取值是否只读模式？~~只读模式则所有取值也为制度模式，并且不允许增减取值列表
     */
    public void setReadonly(Boolean readonly) {
        this.readonly = readonly;
    }

    /**
     * 获取取值Key前缀:字段枚举取值的Key前缀
     *
     * @return fn_key_prefix - 取值Key前缀:字段枚举取值的Key前缀
     */
    public String getKeyPrefix() {
        return keyPrefix;
    }

    /**
     * 设置取值Key前缀:字段枚举取值的Key前缀
     *
     * @param keyPrefix 取值Key前缀:字段枚举取值的Key前缀
     */
    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    /**
     * 获取取值Key后缀:字段枚举取值的Key后缀
     *
     * @return fn_key_suffix - 取值Key后缀:字段枚举取值的Key后缀
     */
    public String getKeySuffix() {
        return keySuffix;
    }

    /**
     * 设置取值Key后缀:字段枚举取值的Key后缀
     *
     * @param keySuffix 取值Key后缀:字段枚举取值的Key后缀
     */
    public void setKeySuffix(String keySuffix) {
        this.keySuffix = keySuffix;
    }

    /**
     * 获取取值Key类型:字段枚举取值的Key命名类型~枚举(关联FieldValueKeyNamingType的取值)
     *
     * @return fn_key_namingtype - 取值Key类型:字段枚举取值的Key命名类型~枚举(关联FieldValueKeyNamingType的取值)
     */
    public Integer getKeyNamingtype() {
        return keyNamingtype;
    }

    /**
     * 设置取值Key类型:字段枚举取值的Key命名类型~枚举(关联FieldValueKeyNamingType的取值)
     *
     * @param keyNamingtype 取值Key类型:字段枚举取值的Key命名类型~枚举(关联FieldValueKeyNamingType的取值)
     */
    public void setKeyNamingtype(Integer keyNamingtype) {
        this.keyNamingtype = keyNamingtype;
    }

    /**
     * 获取取值Value方式:字段枚举取值的Value依据方式~枚举(关联FieldValueValueBy的取值)
     *
     * @return fn_value_by - 取值Value方式:字段枚举取值的Value依据方式~枚举(关联FieldValueValueBy的取值)
     */
    public Integer getValueBy() {
        return valueBy;
    }

    /**
     * 设置取值Value方式:字段枚举取值的Value依据方式~枚举(关联FieldValueValueBy的取值)
     *
     * @param valueBy 取值Value方式:字段枚举取值的Value依据方式~枚举(关联FieldValueValueBy的取值)
     */
    public void setValueBy(Integer valueBy) {
        this.valueBy = valueBy;
    }

    /**
     * 获取取值类型:字段的取值类型~枚举(关联FieldDataType的取值)
     *
     * @return fn_datatype - 取值类型:字段的取值类型~枚举(关联FieldDataType的取值)
     */
    public Integer getDatatype() {
        return datatype;
    }

    /**
     * 设置取值类型:字段的取值类型~枚举(关联FieldDataType的取值)
     *
     * @param datatype 取值类型:字段的取值类型~枚举(关联FieldDataType的取值)
     */
    public void setDatatype(Integer datatype) {
        this.datatype = datatype;
    }

    /**
     * 获取取值单位:字段的取值单位~枚举(关联FieldDataUnit的取值)
     *
     * @return fn_dataunit - 取值单位:字段的取值单位~枚举(关联FieldDataUnit的取值)
     */
    public Integer getDataunit() {
        return dataunit;
    }

    /**
     * 设置取值单位:字段的取值单位~枚举(关联FieldDataUnit的取值)
     *
     * @param dataunit 取值单位:字段的取值单位~枚举(关联FieldDataUnit的取值)
     */
    public void setDataunit(Integer dataunit) {
        this.dataunit = dataunit;
    }

    /**
     * 获取删除:信息是否已被删除？
     *
     * @return fn_deleted - 删除:信息是否已被删除？
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
     * 获取类型描述:字段定义的类型的描述信息~~即字段定义的注释
     *
     * @return fn_desc - 类型描述:字段定义的类型的描述信息~~即字段定义的注释
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置类型描述:字段定义的类型的描述信息~~即字段定义的注释
     *
     * @param desc 类型描述:字段定义的类型的描述信息~~即字段定义的注释
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
}