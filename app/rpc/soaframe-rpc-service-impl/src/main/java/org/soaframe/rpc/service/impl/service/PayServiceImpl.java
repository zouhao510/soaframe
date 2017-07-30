package org.soaframe.rpc.service.impl.service;

import org.apache.log4j.Logger;
import org.soaframe.rpc.service.api.PayService;

import com.alibaba.dubbo.config.annotation.Service;

@Service
public class PayServiceImpl implements PayService {

	private final Logger log = Logger.getLogger(PayServiceImpl.class);

	@Override
	public void pay(String orderCode, String payMethod, double money) {

		log.info(String.format("支付订单号：%s ,采用支付方式：%s , 工支付：%f 成功", orderCode, payMethod, money));
	}

}
