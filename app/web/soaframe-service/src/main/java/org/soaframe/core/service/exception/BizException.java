package org.soaframe.core.service.exception;

/**
 * @Description: 逻辑业务异常
 * @author zouhao
 * @date 2017年8月26日 下午1:25:10
 * 
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = 8629871530536011636L;

	private int errorCode;
	private String errorMsg;

	public BizException(CodeEnum codeEnum) {
		this.errorCode = codeEnum.getCode();
		this.errorMsg = codeEnum.getMsg();
	}

	public BizException(CodeEnum codeEnum, String errorMsg) {
		this.errorCode = codeEnum.getCode();
		this.errorMsg = errorMsg;
	}

	public BizException(int errorCode, String errorMsg) {
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
