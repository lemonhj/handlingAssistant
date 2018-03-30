package com.beigebigdata.bdCourt.ca.api.entity;

import com.septinary.common.core.basic.dto.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author lin.tb lin.maple.leaf@gmail.com
 * @ClassName: Member
 * @Description:会员类
 * @date 16/10/21
 */
@Table(name = "t_member")
public class Member extends BaseEntity {
    /**
     * 会员id
     */
    @Id
    @Column(name = "mem_id")
    private Long memId;

    /**
     * 会员code
     */
    @Column(name = "mem_code")
    private String memCode;

    @Column(name = "mem_host")
    private String memHost;

    @Column(name = "mem_username")
    private String memUsername;

    @Column(name = "mem_password")
    private String memPassword;

    @Column(name = "mem_secretkey")
    private String memSecretkey;

    @Column(name = "mem_type")
    private Integer memType;

    @Column(name = "mem_state")
    private Integer memState;

    @Column(name = "mem_create")
    private Date memCreate;

    @Column(name = "mem_update")
    private Date memUpdate;

    @Column(name = "mem_deleted")
    private Boolean memDeleted;

    /**
     * 会员电话
     */
    @Column(name = "mem_tel")
    private String memTel;

    /**
     * 获取会员id
     *
     * @return mem_id - 会员id
     */
    public Long getMemId() {
        return memId;
    }

    /**
     * 设置会员id
     *
     * @param memId 会员id
     */
    public void setMemId(Long memId) {
        this.memId = memId;
    }

    /**
     * 获取会员code
     *
     * @return mem_code - 会员code
     */
    public String getMemCode() {
        return memCode;
    }

    /**
     * 设置会员code
     *
     * @param memCode 会员code
     */
    public void setMemCode(String memCode) {
        this.memCode = memCode;
    }

    /**
     * @return mem_host
     */
    public String getMemHost() {
        return memHost;
    }

    /**
     * @param memHost
     */
    public void setMemHost(String memHost) {
        this.memHost = memHost;
    }

    /**
     * @return mem_username
     */
    public String getMemUsername() {
        return memUsername;
    }

    /**
     * @param memUsername
     */
    public void setMemUsername(String memUsername) {
        this.memUsername = memUsername;
    }

    /**
     * @return mem_password
     */
    public String getMemPassword() {
        return memPassword;
    }

    /**
     * @param memPassword
     */
    public void setMemPassword(String memPassword) {
        this.memPassword = memPassword;
    }

    /**
     * @return mem_secretkey
     */
    public String getMemSecretkey() {
        return memSecretkey;
    }

    /**
     * @param memSecretkey
     */
    public void setMemSecretkey(String memSecretkey) {
        this.memSecretkey = memSecretkey;
    }

    /**
     * @return mem_type
     */
    public Integer getMemType() {
        return memType;
    }

    /**
     * @param memType
     */
    public void setMemType(Integer memType) {
        this.memType = memType;
    }

    /**
     * @return mem_state
     */
    public Integer getMemState() {
        return memState;
    }

    /**
     * @param memState
     */
    public void setMemState(Integer memState) {
        this.memState = memState;
    }

    /**
     * @return mem_create
     */
    public Date getMemCreate() {
        return memCreate;
    }

    /**
     * @param memCreate
     */
    public void setMemCreate(Date memCreate) {
        this.memCreate = memCreate;
    }

    /**
     * @return mem_update
     */
    public Date getMemUpdate() {
        return memUpdate;
    }

    /**
     * @param memUpdate
     */
    public void setMemUpdate(Date memUpdate) {
        this.memUpdate = memUpdate;
    }

    /**
     * @return mem_deleted
     */
    public Boolean getMemDeleted() {
        return memDeleted;
    }

    /**
     * @param memDeleted
     */
    public void setMemDeleted(Boolean memDeleted) {
        this.memDeleted = memDeleted;
    }

    /**
     * 获取会员电话
     *
     * @return mem_tel - 会员电话
     */
    public String getMemTel() {
        return memTel;
    }

    /**
     * 设置会员电话
     *
     * @param memTel 会员电话
     */
    public void setMemTel(String memTel) {
        this.memTel = memTel;
    }
}