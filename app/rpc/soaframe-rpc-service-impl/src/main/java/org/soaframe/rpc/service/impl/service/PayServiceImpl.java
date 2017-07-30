package org.soaframe.rpc.service.impl.service;

import org.soaframe.manager.sms.SendSmsLogic;
import org.soaframe.rpc.service.api.IPayService;
import org.soaframe.rpc.service.pojo.PayResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * dubbo 支付服务提供者
 */
@Component
@Service
public class PayServiceImpl implements IPayService {

	@Autowired
	private SendSmsLogic sendSmsLogic;

	public PayResult pay(double money) {

		String callBack = sendSmsLogic.smsSuccessCallBack("xxxxxxxx");
		PayResult result = new PayResult();
		result.setCode(1);
		result.setMessage(callBack);
		return result;
	}

}
