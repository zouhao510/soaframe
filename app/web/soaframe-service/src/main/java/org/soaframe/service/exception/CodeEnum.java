package org.soaframe.service.exception;

/**
 * @Description: 错误码
 * @author zouhao
 * @date 2017年7月30日 上午11:40:43
 * 
 */
public enum CodeEnum {

	OK(0, "OK"),

	ERROR_APPLICATION_DEFAULT(10000, "系统异常"),

	ERROR_PARAM_DEFAULT(20000, "参数错误"),

	ERROR_PARAM_EMPTY(20001, "参数不能为空"),

	ERROR_SERVICE_DEFAULT(30000, "服务异常");

	private final int code;
	private final String msg;

	CodeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public int getCode() {
		return code;
	}

}
