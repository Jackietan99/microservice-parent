package com.jsfd.microservice.auth.pojo;


import com.jsfd.core.entity.BusineEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 认证用户
 */
public class User extends BusineEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	// fields
	/** 用户名 unique . */
	private String username;

	/** 用户令牌 unique . */
	private String token;

	/** 手机号码 unique . */
	private String mobile;

	/** 邮箱 unique . */
	private String email;

	/** 密码. */
	private String password;

	/** 昵称. */
	private String nickName;

	/** 语言. */
	private String language;

	/** 状态 0停用 1启用. */
	private Integer statu;

	/** 截止期限 . */
	private Date deadline;

	/** 最后登录IP . */
	private String loginIp;

	/** 最后登录时间 . */
	private Date lastLogin;

	/** 描述. */
	private String descn;

	/** 用户类型 . */
	private Integer userType;

	// association
	/** 引用外部用户对象ID */
	private String userRefId;

	// collections
	/** 角色与认证用户多对多连接关系. */
	private Set<RoleUser> roleUsers = new HashSet<RoleUser>(0);

	/** 权限与认证用户多对多连接关系. */
	private Set<PermUser> permUsers = new HashSet<PermUser>(0);

	// models
	/** 包含角色. */
	private List<Role> roles;

	/** 包含权限. */
	private List<Perm> perms;
	/**用户在线状态，1为在线，0为离线*/
	private Integer onLineStatus;

	public Integer getOnLineStatus() {
		return onLineStatus;
	}

	public void setOnLineStatus(Integer onLineStatus) {
		this.onLineStatus = onLineStatus;
	}

	public User() {
		super();
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatu() {
		return this.statu;
	}

	public void setStatu(Integer statu) {
		this.statu = statu;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Date getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

	public String getUserRefId() {
		return userRefId;
	}

	public void setUserRefId(String userRefId) {
		this.userRefId = userRefId;
	}

	public Set<RoleUser> getRoleUsers() {
		return roleUsers;
	}

	public void setRoleUsers(Set<RoleUser> roleUsers) {
		this.roleUsers = roleUsers;
	}

	public Set<PermUser> getPermUsers() {
		return permUsers;
	}

	public void setPermUsers(Set<PermUser> permUsers) {
		this.permUsers = permUsers;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Perm> getPerms() {
		return this.perms;
	}

	public void setPerms(List<Perm> perms) {
		this.perms = perms;
	}

}
