package com.jsfd.core.web.model;

import java.util.Map;

public class AjaxResult {
	
	private static final String OK = "200";
	private static final String ERROR = " 500";
	
	/** 返回HTTP状态码代码. */
	private String code;;
	/** 返回结果数据. */
	private Object data;
	/** 返回结果消息. */
	private String message;
	/** 附加属性. */
	private Map<String, Object> attributes;

	public AjaxResult success() {
		this.code = OK;
		return this;
	}

	public AjaxResult success(Object data) {
		this.success();
		this.data = data;
		return this;
	}
	
	public AjaxResult success(Object data,String message) {
		this.success(data);
		this.message = message;
		return this;
	}
	
	public AjaxResult addAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
		return this;
	}

	public AjaxResult failure() {
		this.code = ERROR;
		return this;
	}

	public AjaxResult failure(String message) {
		this.failure();
		this.message = message;
		return this;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

}
