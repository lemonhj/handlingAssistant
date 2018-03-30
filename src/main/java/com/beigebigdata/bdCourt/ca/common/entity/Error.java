package com.beigebigdata.bdCourt.ca.common.entity;

import com.septinary.common.core.basic.dto.BaseEntity;

import javax.persistence.*;

@SuppressWarnings("serial")
@Table(name = "devel_error")
public class Error extends BaseEntity {
    /**
     * ID:主键
     */
    @Id
    @Column(name = "err_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 错误图标:错误信息图标URL地址
     */
    @Column(name = "err_icon")
    private String icon;

    /**
     * 错误图片:错误信息图片URL地址
     */
    @Column(name = "err_img")
    private String img;

    /**
     * 错误类型:错误信息的所属类型~枚举(关联ErrorType的取值)
     */
    @Column(name = "err_type")
    private Integer type;

    /**
     * 错误编号:错误信息的编号~满足一定规则，相同type下不能重复~可使用CodeMaker生成
     */
    @Column(name = "err_code")
    private String code;

    /**
     * 错误含义:错误信息的含义~~即是对错误码的文字说明
     */
    @Column(name = "err_info")
    private String info;

    /**
     * 删除:信息是否已被删除？
     */
    @Column(name = "err_deleted")
    private Boolean deleted;

    /**
     * 错误备注:错误信息的默认备注~~通常携带错误的详情描述信息
     */
    @Column(name = "err_memo")
    private String memo;

    /**
     * 获取ID:主键
     *
     * @return err_id - ID:主键
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
     * 获取错误图标:错误信息图标URL地址
     *
     * @return err_icon - 错误图标:错误信息图标URL地址
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置错误图标:错误信息图标URL地址
     *
     * @param icon 错误图标:错误信息图标URL地址
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取错误图片:错误信息图片URL地址
     *
     * @return err_img - 错误图片:错误信息图片URL地址
     */
    public String getImg() {
        return img;
    }

    /**
     * 设置错误图片:错误信息图片URL地址
     *
     * @param img 错误图片:错误信息图片URL地址
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * 获取错误类型:错误信息的所属类型~枚举(关联ErrorType的取值)
     *
     * @return err_type - 错误类型:错误信息的所属类型~枚举(关联ErrorType的取值)
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置错误类型:错误信息的所属类型~枚举(关联ErrorType的取值)
     *
     * @param type 错误类型:错误信息的所属类型~枚举(关联ErrorType的取值)
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取错误编号:错误信息的编号~满足一定规则，相同type下不能重复~可使用CodeMaker生成
     *
     * @return err_code - 错误编号:错误信息的编号~满足一定规则，相同type下不能重复~可使用CodeMaker生成
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置错误编号:错误信息的编号~满足一定规则，相同type下不能重复~可使用CodeMaker生成
     *
     * @param code 错误编号:错误信息的编号~满足一定规则，相同type下不能重复~可使用CodeMaker生成
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取错误含义:错误信息的含义~~即是对错误码的文字说明
     *
     * @return err_info - 错误含义:错误信息的含义~~即是对错误码的文字说明
     */
    public String getInfo() {
        return info;
    }

    /**
     * 设置错误含义:错误信息的含义~~即是对错误码的文字说明
     *
     * @param info 错误含义:错误信息的含义~~即是对错误码的文字说明
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * 获取删除:信息是否已被删除？
     *
     * @return err_deleted - 删除:信息是否已被删除？
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
     * 获取错误备注:错误信息的默认备注~~通常携带错误的详情描述信息
     *
     * @return err_memo - 错误备注:错误信息的默认备注~~通常携带错误的详情描述信息
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 设置错误备注:错误信息的默认备注~~通常携带错误的详情描述信息
     *
     * @param memo 错误备注:错误信息的默认备注~~通常携带错误的详情描述信息
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }
}