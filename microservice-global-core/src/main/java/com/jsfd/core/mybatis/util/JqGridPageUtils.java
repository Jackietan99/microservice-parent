package com.jsfd.core.mybatis.util;

import java.util.Map;

public class JqGridPageUtils {
	
	public static final String page_key = "page";
	public static final String page_rows = "rows";
	
	public static int getPage(Map<String, Object> parameterMap) {
		int pageNum = 1;
		if(parameterMap.get(page_key) != null) {
			try {
				pageNum = Integer.valueOf((String) parameterMap.get(page_key));
			} catch (Exception e) {
				pageNum = 1;
			}
		}
		return pageNum;
	}

	public static int getRows(Map<String, Object> parameterMap) {
		int pageSize = 15;
		if(parameterMap.get(page_rows) != null) {
			try {
				pageSize = Integer.valueOf((String) parameterMap.get(page_rows));
			} catch (Exception e) {
				pageSize = 15;
			}
		}
		return pageSize;
	}
}
