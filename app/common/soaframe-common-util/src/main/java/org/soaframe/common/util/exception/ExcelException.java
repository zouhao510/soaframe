package org.soaframe.common.util.exception;

/**
 * @Description: excel处理异常
 * @author zouhao
 * @date 2017年8月29日 上午9:48:10
 * 
 */
public class ExcelException extends RuntimeException {

	private static final long serialVersionUID = -7239817715930866633L;

	public ExcelException() {
	}

	public ExcelException(String message) {
		super(message);
	}

	public ExcelException(Throwable cause) {
		super(cause);
	}

	public ExcelException(String message, Throwable cause) {
		super(message, cause);
	}

}
