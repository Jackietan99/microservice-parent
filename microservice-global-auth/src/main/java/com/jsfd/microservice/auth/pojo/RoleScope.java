package com.jsfd.microservice.auth.pojo;

import com.lottery.core.entity.BaseEntity;

import java.io.Serializable;

/**
 * 模块与角色关联
 * @author admin
 *
 */
public class RoleScope extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// association
	/** 所属角色ID. */
	private Role role;

	/** 所属系统模块ID. */
	private Scope scope;
	
	public RoleScope() {
		super();
	}

	public RoleScope(Role role, Scope scope) {
		this.role = role;
		this.scope = scope;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Scope getScope() {
		return this.scope;
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}

}
