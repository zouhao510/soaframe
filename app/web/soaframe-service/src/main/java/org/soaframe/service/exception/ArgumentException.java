package org.soaframe.service.exception;

/**
 * @Description: 请求参数错误
 * @author zouhao
 * @date 2017年7月17日 下午2:05:52
 * 
 */
public class ArgumentException extends RuntimeException {

	private static final long serialVersionUID = 8629871530536011636L;

	private Integer errorCode;
	private String errorMessage;

	public ArgumentException(CodeEnum codeEnum) {
		this.errorCode = codeEnum.getCode();
		this.errorMessage = codeEnum.getMsg();
	}

	public ArgumentException(CodeEnum codeEnum, String errorMessage) {
		this.errorCode = codeEnum.getCode();
		this.errorMessage = errorMessage;
	}

	public ArgumentException(Integer errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
