package com.jsfd.microservice.auth.pojo.assist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ScopeTypeEnum {
	/** 0后台. */
	DEFAULT("默认"),
	/** 1系统管理. */
	ADMIN("系统管理"),
	/** 2代理管理. */
	AGENT("代理管理"),
	/** 3会员管理. */
	MEMBER("会员管理"),
	/** 4后台. */
	CMS("cms管理"),
	/** 5后台. */
	MOBILE("移动管理"),
	/** 6前台. */
	FRONT("前台管理");
	
	private String title;
	
	public String getValue() {
		return String.valueOf(this);
	}

	private ScopeTypeEnum(String title) {
		this.setTitle(title);
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	} 
	
	/**
	 * 获取所有实例,封装为Map
	 * 
	 * @return
	 */
	public static List<Map<String, String>> getValues() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		for (ScopeTypeEnum type : ScopeTypeEnum.values()) {
			map = new HashMap<String, String>();
			map.put("value", String.valueOf(type));
			map.put("title", type.getTitle());
			list.add(map);
		}
		return list;
	}

}
