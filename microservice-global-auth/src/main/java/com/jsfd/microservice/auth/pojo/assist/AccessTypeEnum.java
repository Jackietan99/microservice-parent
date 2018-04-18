package com.jsfd.microservice.auth.pojo.assist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资源类型.0URL.1METHOD
 */
public enum AccessTypeEnum {
	/** 0URL. */
	URL("url"),
	
	/** 1METHOD . */
	METHOD("method");
	
	private String title;
	
	public String getValue() {
		return String.valueOf(this);
	}

	private AccessTypeEnum(String title) {
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
		for (AccessTypeEnum type : AccessTypeEnum.values()) {
			map = new HashMap<String, String>();
			map.put("value", String.valueOf(type));
			map.put("title", type.getTitle());
			list.add(map);
		}
		return list;
	}

}
