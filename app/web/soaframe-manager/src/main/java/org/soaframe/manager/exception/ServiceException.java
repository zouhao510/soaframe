package org.soaframe.manager.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -5166886425590209142L;

	private Integer code;

	private String msg;

	public ServiceException(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public ServiceException(String msg) {
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
