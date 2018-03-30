package com.beigebigdata.bdCourt.ca.api.entity;


import com.septinary.common.core.basic.dto.BaseEntity;
import javax.persistence.*;
import java.util.Date;

@Table(name = "t_banner")
public class Banner extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 图片地址
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 是否显示
     */
    @Column(name = "is_show")
    private Boolean isShow;

    /**
     * 是否删除
     */
    @Column(name = "is_delete")
    private Boolean isDelete;

    /**
     * 点击后触发的时间类型
     */
    @Column(name = "action_type")
    private Integer actionType;

    /**
     * 触发的跳转地址
     */
    @Column(name = "action_url")
    private String actionUrl;

    /**
     * 轮播图的类型（0：新闻资讯）
     */
    @Column(name = "banner_type")
    private Integer bannerType;

    /**
     * 显示位置
     */
    @Column(name = "show_order")
    private Float showOrder;

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
     * 获取图片地址
     *
     * @return img_url - 图片地址
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置图片地址
     *
     * @param imgUrl 图片地址
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取是否显示
     *
     * @return is_show - 是否显示
     */
    public Boolean getIsShow() {
        return isShow;
    }

    /**
     * 设置是否显示
     *
     * @param isShow 是否显示
     */
    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    /**
     * 获取是否删除
     *
     * @return is_delete - 是否删除
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否删除
     *
     * @param isDelete 是否删除
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取点击后触发的时间类型
     *
     * @return action_type - 点击后触发的时间类型
     */
    public Integer getActionType() {
        return actionType;
    }

    /**
     * 设置点击后触发的时间类型
     *
     * @param actionType 点击后触发的时间类型
     */
    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    /**
     * 获取触发的跳转地址
     *
     * @return action_url - 触发的跳转地址
     */
    public String getActionUrl() {
        return actionUrl;
    }

    /**
     * 设置触发的跳转地址
     *
     * @param actionUrl 触发的跳转地址
     */
    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    /**
     * 获取轮播图的类型（0：新闻资讯）
     *
     * @return banner_type - 轮播图的类型（0：新闻资讯）
     */
    public Integer getBannerType() {
        return bannerType;
    }

    /**
     * 设置轮播图的类型（0：新闻资讯）
     *
     * @param bannerType 轮播图的类型（0：新闻资讯）
     */
    public void setBannerType(Integer bannerType) {
        this.bannerType = bannerType;
    }

    /**
     * 获取显示位置
     *
     * @return show_order - 显示位置
     */
    public Float getShowOrder() {
        return showOrder;
    }

    /**
     * 设置显示位置
     *
     * @param showOrder 显示位置
     */
    public void setShowOrder(Float showOrder) {
        this.showOrder = showOrder;
    }
}