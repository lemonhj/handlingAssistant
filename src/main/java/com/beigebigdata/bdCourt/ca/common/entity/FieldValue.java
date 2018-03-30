package com.beigebigdata.bdCourt.ca.common.entity;

import com.septinary.common.core.basic.dto.BaseEntity;

import javax.persistence.*;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: FieldValue
 * @Description:
 * @date 16/10/20
 */
@SuppressWarnings("serial")
@Table(name = "devel_field_value")
public class FieldValue extends BaseEntity {
    /**
     * ID:主键
     */
    @Id
    @Column(name = "fv_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 枚举字段:取值所属的枚举字段~外键FieldName.ID
     */
    @Column(name = "fv_field")
    private Long field;

    /**
     * 取值排序:字段取值的排列序号
     */
    @Column(name = "fv_order")
    private Float order;

    /**
     * 取值图标:字段取值的图标URL地址
     */
    @Column(name = "fv_icon")
    private String icon;

    /**
     * 取值标识:字段取值的标识~符合变量命名规则，相同field下不能重复
     */
    @Column(name = "fv_key")
    private String key;

    /**
     * 枚举取值:字段取值~存储为字符串~根据field数据类型进行相应转换
     */
    @Column(name = "fv_value")
    private String value;

    /**
     * 取值释义:字段取值的解释与含义说明
     */
    @Column(name = "fv_parse")
    private String parse;

    /**
     * 是否只读:字段取值是否只读模式？~~只读模式不能变更key、value值
     */
    @Column(name = "fv_readonly")
    private Boolean readonly;

    /**
     * 删除:信息是否已被删除？
     */
    @Column(name = "fv_deleted")
    private Boolean deleted;

    /**
     * 附加信息:字段取值的额外的附加信息~自定义格式~用于传递数据
     */
    @Column(name = "fv_extra")
    private String extra;

    /**
     * 取值描述:字段取值的详细描述信息~~即字段取值的注释
     */
    @Column(name = "fv_desc")
    private String desc;

    /**
     * 获取ID:主键
     *
     * @return fv_id - ID:主键
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
     * 获取枚举字段:取值所属的枚举字段~外键FieldName.ID
     *
     * @return fv_field - 枚举字段:取值所属的枚举字段~外键FieldName.ID
     */
    public Long getField() {
        return field;
    }

    /**
     * 设置枚举字段:取值所属的枚举字段~外键FieldName.ID
     *
     * @param field 枚举字段:取值所属的枚举字段~外键FieldName.ID
     */
    public void setField(Long field) {
        this.field = field;
    }

    /**
     * 获取取值排序:字段取值的排列序号
     *
     * @return fv_order - 取值排序:字段取值的排列序号
     */
    public Float getOrder() {
        return order;
    }

    /**
     * 设置取值排序:字段取值的排列序号
     *
     * @param order 取值排序:字段取值的排列序号
     */
    public void setOrder(Float order) {
        this.order = order;
    }

    /**
     * 获取取值图标:字段取值的图标URL地址
     *
     * @return fv_icon - 取值图标:字段取值的图标URL地址
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置取值图标:字段取值的图标URL地址
     *
     * @param icon 取值图标:字段取值的图标URL地址
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取取值标识:字段取值的标识~符合变量命名规则，相同field下不能重复
     *
     * @return fv_key - 取值标识:字段取值的标识~符合变量命名规则，相同field下不能重复
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置取值标识:字段取值的标识~符合变量命名规则，相同field下不能重复
     *
     * @param key 取值标识:字段取值的标识~符合变量命名规则，相同field下不能重复
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取枚举取值:字段取值~存储为字符串~根据field数据类型进行相应转换
     *
     * @return fv_value - 枚举取值:字段取值~存储为字符串~根据field数据类型进行相应转换
     */
    public String getValue() {
        return value;
    }
    public Integer value() {
    	try {
    		return Integer.valueOf(this.value);
    	} catch (NumberFormatException nfe) {
    		return 0; // 通常，0表示未定义的枚举取值。
    	}
    }

    /**
     * 设置枚举取值:字段取值~存储为字符串~根据field数据类型进行相应转换
     *
     * @param value 枚举取值:字段取值~存储为字符串~根据field数据类型进行相应转换
     */
    public void setValue(String value) {
        this.value = value;
    }
    public void setValueInt(Integer value) {
    	this.value = String.valueOf(value);
    }

    /**
     * 获取取值释义:字段取值的解释与含义说明
     *
     * @return fv_parse - 取值释义:字段取值的解释与含义说明
     */
    public String getParse() {
        return parse;
    }

    /**
     * 设置取值释义:字段取值的解释与含义说明
     *
     * @param parse 取值释义:字段取值的解释与含义说明
     */
    public void setParse(String parse) {
        this.parse = parse;
    }

    /**
     * 获取是否只读:字段取值是否只读模式？~~只读模式不能变更key、value值
     *
     * @return fv_readonly - 是否只读:字段取值是否只读模式？~~只读模式不能变更key、value值
     */
    public Boolean getReadonly() {
        return readonly;
    }

    /**
     * 设置是否只读:字段取值是否只读模式？~~只读模式不能变更key、value值
     *
     * @param readonly 是否只读:字段取值是否只读模式？~~只读模式不能变更key、value值
     */
    public void setReadonly(Boolean readonly) {
        this.readonly = readonly;
    }

    /**
     * 获取删除:信息是否已被删除？
     *
     * @return fv_deleted - 删除:信息是否已被删除？
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
     * 获取附加信息:字段取值的额外的附加信息~自定义格式~用于传递数据
     *
     * @return fv_extra - 附加信息:字段取值的额外的附加信息~自定义格式~用于传递数据
     */
    public String getExtra() {
        return extra;
    }

    /**
     * 设置附加信息:字段取值的额外的附加信息~自定义格式~用于传递数据
     *
     * @param extra 附加信息:字段取值的额外的附加信息~自定义格式~用于传递数据
     */
    public void setExtra(String extra) {
        this.extra = extra;
    }

    /**
     * 获取取值描述:字段取值的详细描述信息~~即字段取值的注释
     *
     * @return fv_desc - 取值描述:字段取值的详细描述信息~~即字段取值的注释
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置取值描述:字段取值的详细描述信息~~即字段取值的注释
     *
     * @param desc 取值描述:字段取值的详细描述信息~~即字段取值的注释
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
}