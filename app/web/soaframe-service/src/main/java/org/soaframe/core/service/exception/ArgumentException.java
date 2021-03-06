package org.soaframe.core.service.exception;

/**
 * @Description: 请求参数错误
 * @author zouhao
 * @date 2017年7月17日 下午2:05:52
 * 
 */
public class ArgumentException extends RuntimeException {

	private static final long serialVersionUID = 8629871530536011636L;

	private int errorCode;
	private String errorMsg;

	public ArgumentException(CodeEnum codeEnum) {
		this.errorCode = codeEnum.getCode();
		this.errorMsg = codeEnum.getMsg();
	}

	public ArgumentException(CodeEnum codeEnum, String errorMsg) {
		this.errorCode = codeEnum.getCode();
		this.errorMsg = errorMsg;
	}

	public ArgumentException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
