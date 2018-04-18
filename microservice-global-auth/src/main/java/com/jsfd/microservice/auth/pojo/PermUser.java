package com.jsfd.microservice.auth.pojo;

import com.lottery.core.entity.BaseEntity;

import java.io.Serializable;

/**
 * 权限与认证用户关联
 * @author admin
 *
 */
public class PermUser extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// association
	/** 所属权限 . */
	private Perm perm;

	/** 所属认证用户. */
	private User userStatus;

	public Perm getPerm() {
		return this.perm;
	}

	public void setPerm(Perm perm) {
		this.perm = perm;
	}

	public User getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(User userStatus) {
		this.userStatus = userStatus;
	}

}
