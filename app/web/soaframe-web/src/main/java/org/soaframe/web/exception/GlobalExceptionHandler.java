package org.soaframe.web.exception;

import javax.servlet.http.HttpServletResponse;

import org.soaframe.service.exception.ArgumentException;
import org.soaframe.service.exception.CodeEnum;
import org.soaframe.service.exception.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 全局异常处理类
 * @author zouhao
 * @date 2017年7月30日 上午10:34:03
 * 
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	// @ExceptionHandler // 处理所有异常
	@ExceptionHandler(value = { ServiceException.class })
	@ResponseBody // 在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
	public ExceptionResponse exceptionHandler(ServiceException e, HttpServletResponse response) {
		return new ExceptionResponse(CodeEnum.ERROR_SERVICE_DEFAULT, e.getErrorMessage());
	}

	@ExceptionHandler(value = { ArgumentException.class })
	@ResponseBody
	public ExceptionResponse exceptionHandler(ArgumentException e, HttpServletResponse response) {
		return new ExceptionResponse(CodeEnum.ERROR_PARAM_DEFAULT, e.getErrorMessage());
	}
}
