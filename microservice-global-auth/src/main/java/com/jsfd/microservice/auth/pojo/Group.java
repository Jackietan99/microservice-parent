package com.jsfd.microservice.auth.pojo;


import com.jsfd.core.entity.BusineEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 群组
 * @author admin
 *
 */
public class Group extends BusineEntity {

	private static final long serialVersionUID = 1L;
	// fields
	/** 群组编码. */
	private String code;

	/** 群组名称. */
	private String name;

	/** 排序. */
	private Integer priority;

	/** 图标. */
	private String iconCls;

	/** 描述. */
	private String descn;

	// collections
	/** 群组与认证用户多对多连接关系. */
	private Set<GroupUser> groupUsers = new HashSet<GroupUser>(0);

	/** 群组与角色多对多关系. */
	private Set<GroupRole> groupRoles = new HashSet<GroupRole>(0);

	// models
	/** 包含角色. */
	private List<Role> roles;

	/** 包含用户. */
	private List<User> user;

	public Group() {

	}

	public Group(Long id) {
		setId(id);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getIconCls() {
		return this.iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls == null ? null : iconCls.trim();
	}

	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

	public Set<GroupUser> getGroupUsers() {
		return groupUsers;
	}

	public void setGroupUsers(Set<GroupUser> groupUsers) {
		this.groupUsers = groupUsers;
	}

	public Set<GroupRole> getGroupRoles() {
		return groupRoles;
	}

	public void setGroupRoles(Set<GroupRole> groupRoles) {
		this.groupRoles = groupRoles;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

}
