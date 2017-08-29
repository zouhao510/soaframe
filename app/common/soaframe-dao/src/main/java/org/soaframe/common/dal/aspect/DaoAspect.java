package org.soaframe.common.dal.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: DAO请求拦截,打印摘要日志，耗时插座100ms的
 * @author zouhao
 * @date 2017年8月15日 下午4:18:24
 * 
 */
@Aspect
@Component
public class DaoAspect {
	private static final Logger logger = LoggerFactory.getLogger("DAL-DIGEST");

	@Pointcut("execution(* org.soaframe.common.dal.dao.*.*(..))")
	private void allMethod() {
	}

	@Around("allMethod()")
	public Object doAround(ProceedingJoinPoint call) throws Throwable {
		MethodSignature signature = (MethodSignature) call.getSignature();
		Method method = signature.getMethod();

		String[] classNameArray = method.getDeclaringClass().getName().split("\\.");
		String methodName = classNameArray[classNameArray.length - 1] + "." + method.getName();

		Object obj = null;

		long current = System.currentTimeMillis();
		try {
			obj = call.proceed();
		} catch (Exception e) {
			throw e;
		} finally {
			long useTime = System.currentTimeMillis() - current;
			// 只记录耗时超过100ms的
			if (useTime > 100) {
				logger.info(String.format("DAO Method:%s,uses time :%sms", methodName, useTime));
			}
		}
		return obj;
	}

}
