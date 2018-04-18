package com.jsfd.core.exception;

public class BussinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	// 业务代码
	private int bizCode;
	// 错误信息
	private String message;

	public BussinessException(int bizCode, String message) {
		super(message);
		this.bizCode = bizCode;
		this.message = message;
	}

	public BussinessException(String message) {
		super(message);
		this.bizCode = -1;
		this.message = message;
	}	
	public int getBizCode() {
		return bizCode;
	}

	public void setBizCode(int bizCode) {
		this.bizCode = bizCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
