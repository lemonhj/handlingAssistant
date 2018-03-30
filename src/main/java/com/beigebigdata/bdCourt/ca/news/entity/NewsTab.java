package com.beigebigdata.bdCourt.ca.news.entity;

import com.septinary.common.core.basic.dto.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_news_tab")
public class NewsTab extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 页签的名字
     */
    @Column(name = "tab_name")
    private String tabName;

    /**
     * 页签显示的顺序
     */
    @Column(name = "show_order")
    private Float showOrder;

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
     * 是否删除
     */
    @Column(name = "is_delete")
    private Boolean isDelete;

    /**
     * 是否显示
     */
    @Column(name = "is_show")
    private Boolean isShow;

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
     * 获取页签的名字
     *
     * @return tab_name - 页签的名字
     */
    public String getTabName() {
        return tabName;
    }

    /**
     * 设置页签的名字
     *
     * @param tabName 页签的名字
     */
    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    /**
     * 获取页签显示的顺序
     *
     * @return show_order - 页签显示的顺序
     */
    public Float getShowOrder() {
        return showOrder;
    }

    /**
     * 设置页签显示的顺序
     *
     * @param showOrder 页签显示的顺序
     */
    public void setShowOrder(Float showOrder) {
        this.showOrder = showOrder;
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
}