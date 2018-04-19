package com.jsfd.microservice.auth.pojo;


import com.jsfd.core.entity.BaseEntity;

/**
 * 群组与认证用户关联
 * @author admin
 *
 */
public class GroupUser extends BaseEntity {
	private static final long serialVersionUID = 1L;

	// association
	/** 所属群组. */
	private Group group;

	/** 所属认证用户. */
	private User userStatus;

	public GroupUser() {

	}

	public GroupUser(User userStatus, Group group) {
		setGroup(group);
		setUserStatus(userStatus);
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public User getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(User userStatus) {
		this.userStatus = userStatus;
	}

}
