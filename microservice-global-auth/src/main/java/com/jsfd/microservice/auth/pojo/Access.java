package com.jsfd.microservice.auth.pojo;


import com.jsfd.core.entity.BusineEntity;
import com.jsfd.core.entity.able.Treeable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 访问资源
 * @author admin
 *
 */
public class Access extends BusineEntity implements Treeable<Long> {

	private static final long serialVersionUID = 1L;

	// fields
	/** 资源编码. */
	private String code;
	
	/** 资源名称. */
	private String name;

	/** 资源类型：如# 0:URL;1:METHOD . */
	private Integer accessType;

	/** 资源值：type=0时value=request.servletPath;type=1时value=classfullname.method . */
	private String value;

	/** 图标. */
	private String iconCls;

	/** 排序. */
	private Integer priority;

	/** 描述. */
	private String descn;

	/** 链接打开方式. */
	private Short openMode;

	/** 森林中树根名. */
	private String treeRootName;

	/** 层级---方便无限级树操作 */
	private Integer level = 0;

	// association
	/** 所属父资源. */
	private Access parent;

	/** 作用域 . */
	private Scope scope;

	// collections
	/** 包含子资源 . Collections.emptySet(); */
	private Set<Access> childs = new HashSet<Access>(0);

	/** 资源与权限多对多关系. */
	private Set<AccessPerm> accessPerms = new HashSet<AccessPerm>(0);

	// models
	
	/** 包含权限.Collections.emptyList() */
	private List<Perm> perms;

	private Integer childSize = 0;
/**/	
	public Access() {

	}

	public Access(Long id) {
		setId(id);
	}

	@Override
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getDescn() {
		return this.descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

	@Override
	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Short getOpenMode() {
		return this.openMode;
	}

	public void setOpenMode(Short openMode) {
		this.openMode = openMode;
	}

	@Override
	public String getTreeRootName() {
		return this.treeRootName;
	}

	public void setTreeRootName(String treeRootName) {
		this.treeRootName = treeRootName;
	}

	@Override
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Scope getScope() {
		return scope;
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}

	public Access getParent() {
		return parent;
	}

	public void setParent(Access parent) {
		this.parent = parent;
	}

	public Set<Access> getChilds() {
		return childs;
	}

	public void setChilds(Set<Access> childs) {
		this.childs = childs;
	}

	public Set<AccessPerm> getAccessPerms() {
		return accessPerms;
	}

	public void setAccessPerms(Set<AccessPerm> accessPerms) {
		this.accessPerms = accessPerms;
	}

	@Override
	public Long getParentId() {
		if (getParent() != null) {
			return getParent().getId();
		}
		return null;
	}

	@Override
	public boolean isRoot() {
		return (getParent() == null);
	}

	@Override
	public boolean isLeaf() {
		// return (this.getChilds().size()==0);
		return childSize == 0;
	}

	public Integer getChildSize() {
		return childSize;
	}

	public void setChildSize(Integer childSize) {
		this.childSize = childSize;
	}

	public List<Perm> getPerms() {
		return perms;
	}

	public void setPerms(List<Perm> perms) {
		this.perms = perms;
	}

	public Integer getAccessType() {
		return accessType;
	}

	public void setAccessType(Integer accessType) {
		this.accessType = accessType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
