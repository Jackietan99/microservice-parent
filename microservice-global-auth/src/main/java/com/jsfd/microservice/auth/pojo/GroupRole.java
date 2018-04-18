package com.jsfd.microservice.auth.pojo;

import com.lottery.core.entity.BaseEntity;

/**
 * 群组与角色关联
 * @author admin
 *
 */
public class GroupRole extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// association
	/** 所属群组. */
	private Group group;

	/** 所属角色. */
	private Role role;

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
