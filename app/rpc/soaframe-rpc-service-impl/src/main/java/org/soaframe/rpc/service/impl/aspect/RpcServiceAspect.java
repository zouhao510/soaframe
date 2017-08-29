package org.soaframe.rpc.service.impl.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soaframe.core.service.exception.ArgumentException;
import org.soaframe.core.service.exception.BizException;
import org.soaframe.core.service.exception.CodeEnum;
import org.soaframe.core.service.exception.ServiceException;
import org.soaframe.rpc.service.resp.RpcResp;
import org.springframework.stereotype.Component;

/**
 * @Description: rpc接口实现切面日志
 * @author zouhao
 * @date 2017年8月26日 下午1:37:02
 * 
 */
@Aspect
@Component
public class RpcServiceAspect {

	private static final Logger digestLogger = LoggerFactory.getLogger("SERVICE-FACADE-DIGEST");
	private static final Logger defaultLogger = LoggerFactory.getLogger("SERVICE-FACADE-DEFAULT");

	@Pointcut("execution(public * org.soaframe.rpc.service.impl.service.*.*(..))")
	private void allMethod() {
	}

	@Around("allMethod()")
	public Object doAround(ProceedingJoinPoint call) throws Throwable {
		MethodSignature signature = (MethodSignature) call.getSignature();
		Method method = signature.getMethod();
		RpcResp baseRes = (RpcResp) method.getReturnType().newInstance();
		String[] classNameArray = method.getDeclaringClass().getName().split("\\.");
		String methodName = classNameArray[classNameArray.length - 1] + "." + method.getName();
		String params = buildParamsDefault(call);

		Date startDate = new Date();
		try {
			defaultLogger.info("[SERVICE_FACADE_REQUEST]," + methodName + ",soaframe," + params);
			baseRes = (RpcResp) call.proceed();
			defaultLogger.info("[SERVICE_FACADE_RESPONSE],"
					+ ToStringBuilder.reflectionToString(baseRes, ToStringStyle.SHORT_PREFIX_STYLE));
			return baseRes;
		} catch (ArgumentException e) {
			baseRes.setCode(e.getErrorCode());
			baseRes.setMsg(e.getErrorMsg());
			defaultLogger.error("参数异常");
			return baseRes;
		} catch (ServiceException e) {
			baseRes.setCode(e.getErrorCode());
			baseRes.setMsg(e.getErrorMsg());
			defaultLogger.error("服务处理异常");
			return baseRes;
		} catch (BizException e) {
			baseRes.setCode(e.getErrorCode());
			baseRes.setMsg(e.getErrorMsg());
			defaultLogger.error("业务处理异常");
			return baseRes;
		} catch (Exception e) {
			baseRes.setCode(CodeEnum.ERROR_APPLICATION_DEFAULT.getCode());
			baseRes.setMsg(CodeEnum.ERROR_APPLICATION_DEFAULT.getMsg());
			defaultLogger.error("未知异常: " + e.getMessage(), e);
			return baseRes;
		} finally {
			defaultLogger.info("[SERVICE_FACADE_RESPONSE]," + methodName + ",soaframe," + baseRes);
			long runTimes = new Date().getTime() - startDate.getTime();
			digestLogger.info("[SERVICE_FACADE_RESPONSE]," + methodName + ",soaframe,," + runTimes + "ms,"
					+ (baseRes.getCode() == 0 ? 0 : 1) + "," + baseRes.getMsg());
		}
	}

	private String buildParamsDefault(ProceedingJoinPoint call) {
		String params = "[";
		for (int i = 0; i < call.getArgs().length; i++) {
			Object obj = call.getArgs()[i];
			if (i != call.getArgs().length - 1) {
				params += obj + "&";
			} else {
				params += obj + "]";
			}
		}
		return params;
	}

}
