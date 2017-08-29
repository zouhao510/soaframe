package org.soaframe.web.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.soaframe.core.service.exception.ArgumentException;
import org.soaframe.core.service.exception.BizException;
import org.soaframe.core.service.exception.ServiceException;
import org.springframework.stereotype.Component;

/**
 * @Description: 针对manager和service层方法切面的日志aop
 * @author zouhao
 * @date 2017年8月26日 下午1:47:57
 * 
 */
@Aspect
@Component
public class GlobalMethodAspect {

	private static final Logger defaultLogger = Logger.getLogger(GlobalMethodAspect.class);

	@Pointcut(value = "within(org.soaframe.manager.share.impl..*)")
	private void managerAllMethod() {
	}

	@Pointcut(value = "within(org.soaframe.core.service.impl..*)")
	private void serviceAllMethod() {
	}

	@Around(value = "managerAllMethod()")
	private Object managerAround(ProceedingJoinPoint call) throws Throwable {
		MethodSignature signature = (MethodSignature) call.getSignature();
		Method method = signature.getMethod();
		String[] classNameArray = method.getDeclaringClass().getName().split("\\.");
		String methodName = classNameArray[classNameArray.length - 1] + "." + method.getName();
		Object object = null;

		Date startDate = new Date();
		try {
			object = call.proceed();
		} catch (ArgumentException e) {
			defaultLogger.error("参数异常");
			throw e;
		} catch (ServiceException e) {
			defaultLogger.error("服务处理异常");
			throw e;
		} catch (BizException e) {
			defaultLogger.error("业务处理异常");
			throw e;
		} catch (Exception e) {
			defaultLogger.error("未知异常: " + e.getMessage(), e);
			throw e;
		} finally {
			long runTimes = new Date().getTime() - startDate.getTime();
			defaultLogger.info("[MANAGER_RESPONSE]" + methodName + ",marketsms," + runTimes + "ms");
		}
		return object;
	}

	@Around(value = "serviceAllMethod()")
	private Object serviceAround(ProceedingJoinPoint call) throws Throwable {
		MethodSignature signature = (MethodSignature) call.getSignature();
		Method method = signature.getMethod();
		String[] classNameArray = method.getDeclaringClass().getName().split("\\.");
		String methodName = classNameArray[classNameArray.length - 1] + "." + method.getName();
		Object object = null;

		Date startDate = new Date();
		try {
			object = call.proceed();
		} catch (ArgumentException e) {
			defaultLogger.error("参数异常");
			throw e;
		} catch (ServiceException e) {
			defaultLogger.error("服务处理异常");
			throw e;
		} catch (BizException e) {
			defaultLogger.error("业务处理异常");
			throw e;
		} catch (Exception e) {
			defaultLogger.error("未知异常: " + e.getMessage());
			throw e;
		} finally {
			long runTimes = new Date().getTime() - startDate.getTime();
			defaultLogger.info("[SERVICE_RESPONSE]" + methodName + ",marketsms," + runTimes + "ms");
		}
		return object;
	}
}
