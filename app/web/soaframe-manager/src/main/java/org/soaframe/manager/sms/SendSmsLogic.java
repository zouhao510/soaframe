package org.soaframe.manager.sms;

import org.soaframe.dao.pojo.SmsResponse;
import org.soaframe.manager.exception.ServiceException;
import org.soaframe.rpc.service.api.IPayService;
import org.soaframe.rpc.service.pojo.PayResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 发送短信逻辑类
 * @author zouhao
 * @date 2017年7月9日 下午4:25:04
 * 
 */

@Service
public class SendSmsLogic {

	@Autowired
	private IPayService payService;

	public SmsResponse sendSms(String phone, String content) {

		try {
			int result = 1 / 0;
		} catch (Exception e) {
			throw new ServiceException("divide by zero");
		}

		SmsResponse smsResp = new SmsResponse();
		smsResp.setCode(0);
		smsResp.setMessage("发送成功");
		smsResp.setData(content);

		return smsResp;
	}

	public String smsSuccessCallBack(String phone) {

		PayResult result = payService.pay(2.0d);

		result.setMessage(phone);

		return result.getMessage();
	}

}
