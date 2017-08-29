package org.soaframe.rpc.service.resp;

import java.io.Serializable;

/**
 * @Description: rpc层统一基础响应类,只允许基于该类扩展
 * @author zouhao
 * @date 2017年8月26日 下午1:15:18
 * 
 */
public class RpcResp implements Serializable {

	private static final long serialVersionUID = 1263887601848628050L;

	private int code;
	private String msg;

	public RpcResp() {
	}

	public static RpcResp OK() {
		return new RpcResp(RpcRespStatus.OK);
	}

	public RpcResp(RpcRespStatus status) {
		this.code = status.getCode();
		this.setMsg(status.getMsg());
	}

	public RpcResp(RpcRespStatus status, String msg) {
		this.code = status.getCode();
		this.setMsg(msg);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
