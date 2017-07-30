package org.soaframe.dao.pojo;

/**
 * @Description: 发送短信响应内容
 * @author zouhao
 * @date 2017年7月9日 下午4:32:01
 * 
 */
public class SmsResponse {

	private int code;
	private String message;
	private Object data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
