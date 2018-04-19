package com.jsfd.microservice.auth.pojo;


import com.jsfd.core.entity.BaseEntity;

/**
 * 权限与角色关联
 * @author admin
 *
 */
public class PermRole extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// association
	/** 所属权限 . */
	private Perm perm;

	/** 所属角色 . */
	private Role role;

	public PermRole() {

	}

	public PermRole(Role role, Perm perm) {
		setRole(role);
		setPerm(perm);
	}

	public Perm getPerm() {
		return this.perm;
	}

	public void setPerm(Perm perm) {
		this.perm = perm;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
