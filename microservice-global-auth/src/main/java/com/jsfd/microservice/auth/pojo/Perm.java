package com.jsfd.microservice.auth.pojo;


import com.jsfd.core.entity.BusineEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 权限
 * @author admin
 *
 */
public class Perm extends BusineEntity {

	private static final long serialVersionUID = 1L;

	// fields
	/** 权限编码. */
	private String code;

	/** 权限名. */
	private String name;

	/** 排序. */
	private Integer priority;

	/** 图标. */
	private String iconCls;

	/** 描述. */
	private String descn;

	// association
	/** 所属权限类型ID. */
	private PermType permType;

	// collections
	/** 资源与权限多对多关系 */
	private Set<AccessPerm> accessPerms = new HashSet<AccessPerm>(0);

	/** 权限与角色多对多连接关系. */
	private Set<PermRole> permRoles = new HashSet<PermRole>(0);

	/** 权限与认证用户多对多连接关系. */
	private Set<PermUser> permUsers = new HashSet<PermUser>(0);

	// models
	/** 包含访问资源. */
	private List<Access> accesses;
	/** 包含角色. */
	private List<Role> roles;
	/** 包含访问资源. */
	private List<User> users;
	
	private String accessIds;

	public Perm() {

	}

	public Perm(Long id) {
		setId(id);
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
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

	public PermType getPermType() {
		return permType;
	}

	public void setPermType(PermType permType) {
		this.permType = permType;
	}

	public Set<PermUser> getPermUsers() {
		return permUsers;
	}

	public void setPermUsers(Set<PermUser> permUsers) {
		this.permUsers = permUsers;
	}

	public Set<PermRole> getPermRoles() {
		return permRoles;
	}

	public void setPermRoles(Set<PermRole> permRoles) {
		this.permRoles = permRoles;
	}

	public Set<AccessPerm> getAccessPerms() {
		return accessPerms;
	}

	public void setAccessPerms(Set<AccessPerm> accessPerms) {
		this.accessPerms = accessPerms;
	}

	public List<Access> getAccesses() {
		return accesses;
	}

	public void setAccesses(List<Access> accesses) {
		this.accesses = accesses;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void addAccessPerm(AccessPerm accessPerm) {
		getAccessPerms().add(accessPerm);
	}
	public String getAccessIds() {
		return accessIds;
	}

	public void setAccessIds(String accessIds) {
		this.accessIds = accessIds;
	}
}
