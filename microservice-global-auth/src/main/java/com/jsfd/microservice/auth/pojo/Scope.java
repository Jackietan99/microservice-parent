package com.jsfd.microservice.auth.pojo;

import com.lottery.core.entity.BusineEntity;

import java.io.Serializable;

/**
 * 系统模块
 * @author admin
 *
 */
public class Scope extends BusineEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 模块编码. */
	private String code;

	/** 模块名. */
	private String name;

	/** 类型. 0、后台;1、前台 ;2、app . */
	private Integer scopeType;

	/** 排序. */
	private Integer priority;

	/** 图标. */
	private String iconCls;

	/** 描述. */
	private String descn;
	
	public Scope() {
		
	}

	public Scope(Long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
		this.iconCls = iconCls;
	}

	public String getDescn() {
		return this.descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

	public Integer getScopeType() {
		return scopeType;
	}

	public void setScopeType(Integer scopeType) {
		this.scopeType = scopeType;
	}

}
