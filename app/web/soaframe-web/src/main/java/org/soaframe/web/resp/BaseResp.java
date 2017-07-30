package org.soaframe.web.resp;

import java.io.Serializable;

import org.soaframe.service.exception.CodeEnum;

/**
 * @Description: web层基础响应类
 * @author zouhao
 * @date 2017年7月30日 上午10:36:48
 * 
 * @param <T>
 */
public class BaseResp<T> implements Serializable {

	private static final long serialVersionUID = 7450186561550824949L;

	private Integer code;

	private String msg;

	private T data;

	public BaseResp(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public BaseResp(CodeEnum codeEnum) {
		this.code = codeEnum.getCode();
		this.msg = codeEnum.getMsg();
	}

	public BaseResp(CodeEnum codeEnum, String msg) {
		this.code = codeEnum.getCode();
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
