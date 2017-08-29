package org.soaframe.web.aspect;

import java.lang.reflect.Field;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soaframe.core.service.exception.ArgumentException;
import org.soaframe.core.service.exception.BizException;
import org.soaframe.core.service.exception.CodeEnum;
import org.soaframe.core.service.exception.ServiceException;
import org.soaframe.web.resp.WebBaseResp;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Description: 对所有的web请求接口切面日志，打出请求参数，执行时间，响应结果等
 * @author zouhao
 * @date 2017年8月26日 下午1:48:44
 * 
 */
@Aspect
@Component
public class WebControllerAspect {

	private static final Logger digestLogger = LoggerFactory.getLogger("WEB-SERVICE-DIGEST");
	private static final Logger defaultLogger = LoggerFactory.getLogger("WEB-SERVICE-DEFAULT");

	@Pointcut("execution(* org.soaframe.web.controller.*.*(..))")
	private void allMethod() {
	}

	@Around("allMethod()")
	public Object doAround(ProceedingJoinPoint call) throws Throwable {

		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();
		String uri = request.getRequestURI();
		Object[] args = call.getArgs();
		WebBaseResp resp = new WebBaseResp();
		String params = buildParamsDefault(call);
		String token = request.getParameter("token");
		String account = request.getParameter("account");
		try {
			if (StringUtils.isEmpty(token)) {
				Field filed = args[0].getClass().getDeclaredField("token");
				if (null != filed) {
					filed.setAccessible(true);
					Object object = filed.get(args[0]);
					if (null != object) {
						token = object.toString();
					}
				}
			}
		} catch (Exception e) {
			token = "";
		}
		try {
			if (StringUtils.isEmpty(account)) {
				Field filed = args[0].getClass().getDeclaredField("account");
				if (null != filed) {
					filed.setAccessible(true);
					Object object = filed.get(args[0]);
					if (null != object) {
						account = object.toString();
					}
				}

			}
		} catch (Exception e) {
			account = "";
		}

		Date startDate = new Date();
		try {
			defaultLogger.info("[WEB-SERVICE_REQUEST]" + uri + ",soaframe," + params);
			resp = (WebBaseResp) call.proceed();
			defaultLogger.info("[WEB-SERVICE_RESPONSE]"
					+ ToStringBuilder.reflectionToString(resp, ToStringStyle.SHORT_PREFIX_STYLE));
		} catch (ArgumentException e) {
			defaultLogger.error("参数异常");
			throw e;
		} catch (ServiceException e) {
			defaultLogger.error("服务处理异常");
			resp.setCode(e.getErrorCode());
			resp.setMsg(e.getErrorMsg());
			return resp;
		} catch (BizException e) {
			defaultLogger.error("业务处理异常");
			resp.setCode(e.getErrorCode());
			resp.setMsg(e.getErrorMsg());
			return resp;
		} catch (Exception e) {
			defaultLogger.error("未知异常: " + e.getMessage(), e);
			resp.setCode(CodeEnum.ERROR_APPLICATION_DEFAULT.getCode());
			resp.setMsg(CodeEnum.ERROR_APPLICATION_DEFAULT.getMsg());
			return resp;
		} finally {
			defaultLogger.info("[WEB-SERVICE_RESPONSE]," + uri + ",soaframe," + resp);
			long runTimes = new Date().getTime() - startDate.getTime();
			digestLogger.info("[WEB-SERVICE_RESPONSE]," + uri + "," + token + "," + account + "," + runTimes + "ms,"
					+ (resp.getCode() == 0 ? 0 : 1 + ",")
					+ ToStringBuilder.reflectionToString(resp.getData(), ToStringStyle.SIMPLE_STYLE));
			return resp;
		}
	}

	private String buildParamsDefault(ProceedingJoinPoint call) {
		String params = "";
		for (int i = 0; i < call.getArgs().length; i++) {
			Object obj = call.getArgs()[i];
			if (i != call.getArgs().length - 1) {
				params += obj + "";
			} else {
				params += obj + "";
			}
		}
		return params;
	}
}
