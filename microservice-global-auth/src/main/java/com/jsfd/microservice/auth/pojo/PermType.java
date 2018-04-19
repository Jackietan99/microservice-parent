package com.jsfd.microservice.auth.pojo;


import com.jsfd.core.entity.BusineEntity;

/**
 * 权限类型
 * @author admin
 *
 */
public class PermType extends BusineEntity {

	private static final long serialVersionUID = 1L;
	// fields
	/** 类型名. */
	private String name;

	/** 显示类型 0显示 1隐藏. */
	private Integer displayType;

	/** 排序. */
	private Integer priority;

	/** 描述. */
	private String descn;
	
	public PermType() {
		
	}

	public PermType(Long id) {
		this.id = id;
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

	public Integer getDisplayType() {
		return displayType;
	}

	public void setDisplayType(Integer displayType) {
		this.displayType = displayType;
	}
	
	public String getDescn() {
		return this.descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

}
