package com.jsfd.microservice.auth.pojo.assist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 显示类型 0显示 1隐藏. */
public enum DisplayTypeEnum {
	/** 0 显示. */
	DISPLAY("显示"),
	
	/** 1隐藏 . */
	NONE("隐藏");
	
	private String title;
	
	public String getValue() {
		return String.valueOf(this);
	}

	private DisplayTypeEnum(String title) {
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
		for (DisplayTypeEnum type : DisplayTypeEnum.values()) {
			map = new HashMap<String, String>();
			map.put("value", String.valueOf(type));
			map.put("title", type.getTitle());
			list.add(map);
		}
		return list;
	}
}
