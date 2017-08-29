package org.soaframe.web.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.soaframe.core.service.exception.ArgumentException;
import org.soaframe.core.service.exception.BizException;
import org.soaframe.core.service.exception.CodeEnum;
import org.soaframe.core.service.exception.ServiceException;
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

	private static final Logger log = Logger.getLogger(GlobalExceptionHandler.class);

	// @ExceptionHandler // 处理所有异常
	@ExceptionHandler(value = { ServiceException.class })
	@ResponseBody // 在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
	public ExceptionResponse exceptionHandler(ServiceException e, HttpServletResponse response) {
		log.error("服务异常", e);
		return new ExceptionResponse(CodeEnum.ERROR_SERVICE_DEFAULT, e.getErrorMsg());
	}

	@ExceptionHandler(value = { ArgumentException.class })
	@ResponseBody
	public ExceptionResponse exceptionHandler(ArgumentException e, HttpServletResponse response) {
		log.error("参数异常", e);
		return new ExceptionResponse(CodeEnum.ERROR_PARAM_DEFAULT, e.getErrorMsg());
	}

	@ExceptionHandler(value = { BizException.class })
	@ResponseBody // 在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
	public ExceptionResponse exceptionHandler(BizException e, HttpServletResponse response) {
		log.error("业务异常", e);
		return new ExceptionResponse(CodeEnum.ERROR_BIZ_DEFAULT, e.getErrorMsg());
	}

	// 运行时异常
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public ExceptionResponse runtimeExceptionHandler(RuntimeException e) {
		log.error("未知异常", e);
		return new ExceptionResponse(CodeEnum.UNKNOWN_EXCEPTION);
	}

	// 空指针异常
	@ExceptionHandler(NullPointerException.class)
	@ResponseBody
	public ExceptionResponse nullPointerExceptionHandler(NullPointerException e) {
		log.error("未知异常", e);
		return new ExceptionResponse(CodeEnum.UNKNOWN_EXCEPTION);
	}

	// 类型转换异常
	@ExceptionHandler(ClassCastException.class)
	@ResponseBody
	public ExceptionResponse classCastExceptionHandler(ClassCastException e) {
		log.error("未知异常", e);
		return new ExceptionResponse(CodeEnum.UNKNOWN_EXCEPTION);
	}

	// IO异常
	@ExceptionHandler(IOException.class)
	@ResponseBody
	public ExceptionResponse iOExceptionHandler(IOException e) {
		log.error("未知异常", e);
		return new ExceptionResponse(CodeEnum.UNKNOWN_EXCEPTION);
	}

	// 未知方法异常
	@ExceptionHandler(NoSuchMethodException.class)
	@ResponseBody
	public ExceptionResponse noSuchMethodExceptionHandler(NoSuchMethodException e) {
		log.error("未知异常", e);
		return new ExceptionResponse(CodeEnum.UNKNOWN_EXCEPTION);
	}

	// 数组越界异常
	@ExceptionHandler(IndexOutOfBoundsException.class)
	@ResponseBody
	public ExceptionResponse indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException e) {
		log.error("未知异常", e);
		return new ExceptionResponse(CodeEnum.UNKNOWN_EXCEPTION);
	}
}
