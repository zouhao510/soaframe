package org.soaframe.rpc.service.resp;

/**
 * @Description: rpc层统一错误响应码
 * @author zouhao
 * @date 2017年8月26日 下午1:12:07
 * 
 */
public enum RpcRespStatus {

	OK(0, "OK"), ERROR_DEFAULT(1, "服务异常");

	private final int code;
	private final String msg;

	RpcRespStatus(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static RpcRespStatus getRpcRespStatus(int code) {
		for (RpcRespStatus status : values()) {
			if (status.getCode() == code) {
				return status;
			}
		}
		return null;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
