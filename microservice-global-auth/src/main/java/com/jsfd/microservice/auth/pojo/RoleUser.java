package com.jsfd.microservice.auth.pojo;

import com.lottery.core.entity.BaseEntity;

import java.io.Serializable;

/**
 * 角色与认证用户关联
 * @author admin
 *
 */
public class RoleUser extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// association
	/** 所属角色. */
	private Role role;

	/** 所属认证用户. */
	private User user;

	public RoleUser() {

	}

	public RoleUser(User userStatus, Role role) {
		setRole(role);
		setUser(userStatus);
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
