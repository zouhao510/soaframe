package org.soaframe.web.exception;

import javax.servlet.http.HttpServletResponse;

import org.soaframe.manager.exception.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = { ServiceException.class })
	// @ExceptionHandler // 处理所有异常
	@ResponseBody // 在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
	public MyExceptionResponse exceptionHandler(ServiceException e, HttpServletResponse response) {
		MyExceptionResponse resp = new MyExceptionResponse();
		resp.setCode(300);
		resp.setMsg(e.getMsg());
		return resp;
	}
}
