package com.jsfd.microservice.auth.pojo.assist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统模块类型.0根用户. 1后台用户. 2代理用户 .3会员用户 .
 */
public enum UserTypeEnum {
	/** 0根用户. */
	ROOT("根用户"),
	
	/**  1管理用户. */
	ADMIN("管理用户"),
	
	/** 2代理用户 . */
	AGENT("代理用户"),
	
	/** 3会员用户  . */
	MEMBER("应用用户");
	
	private String title;

	private UserTypeEnum(String title) {
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
		for (UserTypeEnum type : UserTypeEnum.values()) {
			map = new HashMap<String, String>();
			map.put("value", String.valueOf(type));
			map.put("title", type.getTitle());
			list.add(map);
		}
		return list;
	}

}
