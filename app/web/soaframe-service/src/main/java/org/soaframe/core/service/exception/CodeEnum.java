package org.soaframe.core.service.exception;

/**
 * @Description: 对外统一错误码
 * @author zouhao
 * @date 2017年7月30日 上午11:40:43
 * 
 */
public enum CodeEnum {

	OK(0, "OK"),

	ERROR_APPLICATION_DEFAULT(1000, "系统异常"),

	ERROR_PARAM_DEFAULT(2000, "参数错误"),

	ERROR_PARAM_EMPTY(2001, "参数不能为空"),

	ERROR_BIZ_DEFAULT(3000, "业务异常"),

	ERROR_SERVICE_DEFAULT(4000, "服务异常"),

	// 未捕获异常
	UNKNOWN_EXCEPTION(5000, "未知异常");

	private final int code;
	private final String msg;

	CodeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static CodeEnum getCodeEnum(int code) {
		for (CodeEnum status : values()) {
			if (status.getCode() == code) {
				return status;
			}
		}
		return null;
	}

	public String getMsg() {
		return msg;
	}

	public int getCode() {
		return code;
	}

}
