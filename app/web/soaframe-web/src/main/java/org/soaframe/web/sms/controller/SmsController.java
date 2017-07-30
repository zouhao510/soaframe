package org.soaframe.web.sms.controller;

import org.soaframe.dao.pojo.SmsResponse;
import org.soaframe.manager.exception.ServiceException;
import org.soaframe.manager.exception.StatusParam;
import org.soaframe.manager.sms.SendSmsLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: sms短信相关控制类
 * @author zouhao
 * @date 2017年7月9日 下午4:34:21
 * 
 */
@Controller
@RequestMapping("/sms")
public class SmsController {

	@Autowired
	private SendSmsLogic sendSmsLogic;

	/**
	 * @Description:向指定手机发送短信
	 * @author zouhao
	 * @param phone
	 * @param content
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/send")
	private ResponseEntity<SmsResponse> sendSms(String phone, String content) throws ServiceException {

		SmsResponse smsResponse = sendSmsLogic.sendSms(phone, content);

		return new ResponseEntity<SmsResponse>(smsResponse, HttpStatus.OK);
	}

	/**
	 * @Description:短信发送成功回调接口测试
	 * @author zouhao
	 * @param phone
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/success")
	private ResponseEntity<String> successCallBack(String phone) {

		String rpcResult = sendSmsLogic.smsSuccessCallBack(phone);

		return new ResponseEntity<String>(rpcResult, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping("/success")
	private ResponseEntity<String> successCallBack(@RequestParam StatusParam param) {

		String rpcResult = sendSmsLogic.smsSuccessCallBack(param.getAccount());

		return new ResponseEntity<String>(rpcResult, HttpStatus.OK);
	}

}
