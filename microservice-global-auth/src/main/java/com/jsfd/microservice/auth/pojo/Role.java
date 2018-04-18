package com.jsfd.microservice.auth.pojo;

import com.lottery.core.entity.BusineEntity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 角色
 * @author admin
 *
 */
public class Role extends BusineEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	// fields
	/** 角色编码. */
	private String code;

	/** 角色名. */
	private String name;

	/** 排序. */
	private Integer priority;

	/** 图标. */
	private String iconCls;

	/** 描述. */
	private String descn;

	// collections
	/** 角色与用户多对多连接关系. */
	private Set<RoleUser> roleUsers = new HashSet<RoleUser>(0);

	/** 权限与角色多对多连接关系. */
	private Set<PermRole> permRoles = new HashSet<PermRole>(0);

	// models
	/** 角色包含权限. */
	private List<Perm> perms;
	/** 角色包含认证用户. */
	private List<User> users;
	
	private String permIds;

	public Role() {

	}

	public Role(Long id) {
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
		return this.descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

	public Set<RoleUser> getRoleUsers() {
		return roleUsers;
	}

	public void setRoleUsers(Set<RoleUser> roleUsers) {
		this.roleUsers = roleUsers;
	}

	public Set<PermRole> getPermRoles() {
		return permRoles;
	}

	public void setPermRoles(Set<PermRole> permRoles) {
		this.permRoles = permRoles;
	}

	public List<Perm> getPerms() {
		return perms;
	}

	public void setPerms(List<Perm> perms) {
		this.perms = perms;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getPermIds() {
		return permIds;
	}

	public void setPermIds(String permIds) {
		this.permIds = permIds;
	}

}
