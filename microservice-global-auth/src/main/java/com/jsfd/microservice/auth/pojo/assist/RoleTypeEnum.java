package com.jsfd.microservice.auth.pojo.assist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统模块类型 .
 */
public enum RoleTypeEnum {
	/**  0默认角色. */
	ROLE_CODE_DEFAULT("默认"),
	
	/**  1管理角色. */
	ROLE_CODE_SUPER_ADMIN("超级管理员"),
	
	/** 2代理角色 . */
	ROLE_CODE_SYSTEM_ADMIN("系统管理角色"),
	
	/** 3顶级代理角色 . */
	ROLE_CODE_SUPER_AGENT("系统管理角色"),
	
	/** 4顶级代理角色 . */
	ROLE_CODE_COMMON_AGENT("普通代理角色"),
	
	/** 5会员角色  . */
	ROLE_CODE_MEMBER("应用角色");
	
	private String title;

	private RoleTypeEnum(String title) {
		this.setTitle(title);
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	} 
	
	public String getValue() {
		return String.valueOf(this);
	}
	/**
	 * 获取所有实例,封装为Map
	 */
	public static List<Map<String, String>> getValues() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		for (RoleTypeEnum type : RoleTypeEnum.values()) {
			map = new HashMap<String, String>();
			map.put("value", String.valueOf(type));
			map.put("title", type.getTitle());
			list.add(map);
		}
		return list;
	}

}
