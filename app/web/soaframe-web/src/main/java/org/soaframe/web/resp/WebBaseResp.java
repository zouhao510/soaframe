package org.soaframe.web.resp;

import java.io.Serializable;

import org.soaframe.core.service.exception.CodeEnum;

/**
 * @Description: web层基础响应类
 * @author zouhao
 * @date 2017年7月30日 上午10:36:48
 * 
 * @param <T>
 */
public class WebBaseResp<T> implements Serializable {

	private static final long serialVersionUID = 7450186561550824949L;

	private int code;

	private String msg;

	private T data;

	public WebBaseResp() {

	}

	public WebBaseResp(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public WebBaseResp(CodeEnum codeEnum) {
		this.code = codeEnum.getCode();
		this.msg = codeEnum.getMsg();
	}

	public WebBaseResp(CodeEnum codeEnum, String msg) {
		this.code = codeEnum.getCode();
		this.msg = msg;
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
