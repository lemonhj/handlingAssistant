package com.beigebigdata.bdCourt.ca.news.entity;

import com.septinary.common.core.basic.dto.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_member_news")
public class MemberNews extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "mem_id")
    private Long memId;

    /**
     * 新闻id
     */
    @Column(name = "news_id")
    private Long newsId;

    /**
     * 收藏时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否删除
     */
    @Column(name = "is_delete")
    private Boolean isDelete;

    /**
     * 删除时间
     */
    @Column(name = "delete_time")
    private Date deleteTime;

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
     * 获取用户id
     *
     * @return mem_id - 用户id
     */
    public Long getMemId() {
        return memId;
    }

    /**
     * 设置用户id
     *
     * @param memId 用户id
     */
    public void setMemId(Long memId) {
        this.memId = memId;
    }

    /**
     * 获取新闻id
     *
     * @return news_id - 新闻id
     */
    public Long getNewsId() {
        return newsId;
    }

    /**
     * 设置新闻id
     *
     * @param newsId 新闻id
     */
    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    /**
     * 获取收藏时间
     *
     * @return create_time - 收藏时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置收藏时间
     *
     * @param createTime 收藏时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
     * 获取删除时间
     *
     * @return delete_time - 删除时间
     */
    public Date getDeleteTime() {
        return deleteTime;
    }

    /**
     * 设置删除时间
     *
     * @param deleteTime 删除时间
     */
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
}