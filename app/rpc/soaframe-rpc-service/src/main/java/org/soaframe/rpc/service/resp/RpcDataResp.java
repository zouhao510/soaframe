package org.soaframe.rpc.service.resp;

/**
 * @Description: 带响应结果数据的rpc响应类
 * @author zouhao
 * @date 2017年8月26日 下午2:30:00
 * 
 * @param <T>
 */
public class RpcDataResp<T> extends RpcResp {

	private static final long serialVersionUID = -3036185399075176170L;

	private T data;

	public RpcDataResp() {
	}

	public RpcDataResp(RpcRespStatus status) {
		super(status);
	}

	public RpcDataResp(RpcRespStatus status, T data) {
		super(status);
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
