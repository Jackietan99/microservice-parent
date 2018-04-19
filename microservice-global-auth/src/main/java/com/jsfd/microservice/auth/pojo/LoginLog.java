package com.jsfd.microservice.auth.pojo;


import com.jsfd.core.entity.BusineEntity;

import java.io.Serializable;
import java.util.Date;

public class LoginLog  extends BusineEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 用户ID. */
    private Long userId;
    /** 用户名. */
    private String username;
    /** 登录时间. */
    private Date loginTime;
    /** 退出时间. */
    private Date logoutTime;
    /** 登录IP. */
    private String loginIp;
    /** 日志类型. */
    private Integer logType;
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Integer getLogType() {
        return logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }

}