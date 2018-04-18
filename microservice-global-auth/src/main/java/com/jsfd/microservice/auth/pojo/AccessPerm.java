package com.jsfd.microservice.auth.pojo;

import com.lottery.core.entity.BaseEntity;

/**
 * 权限与访问资源关联
 * @author admin
 *
 */
public class AccessPerm extends BaseEntity implements Comparable<AccessPerm> {

	private static final long serialVersionUID = 1L;

	// association
	/** 所属权限 . */
	private Perm perm;

	/** 所属访问资源 . */
	private Access access;

	/** 操作类型：0#所有;1#增加;2#删除;3#更新;4#查询 */
	private Integer operateType = 0;

	public AccessPerm() {

	}

	public AccessPerm(Perm perm, Access access) {
		setPerm(perm);
		setAccess(access);
	}

	public Perm getPerm() {
		return this.perm;
	}

	public void setPerm(Perm perm) {
		this.perm = perm;
	}

	public Access getAccess() {
		return this.access;
	}

	public void setAccess(Access access) {
		this.access = access;
	}

	@Override
	public int compareTo(AccessPerm o) {
		/*
		 * if(this.getId() > o.getId()){ return 1; }else if(this.getId() < o.getId()){
		 * return -1; } return 0;
		 */
		if (this.getId() == null || o.getId() == null) {
			return -1;
		}

		if (this.getId().equals(o.getId())) {
			return 0;
		}

		if ((this.getAccess().getId().equals(o.getAccess().getId())) && (this.getPerm().getId().equals(o.getPerm().getId()))) {
			return 0;
		}

		return this.getId() > o.getId() ? 1 : -1;
	}

	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}
}
