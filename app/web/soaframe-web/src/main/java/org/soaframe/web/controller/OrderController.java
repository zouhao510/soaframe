package org.soaframe.web.controller;

import org.soaframe.core.service.exception.ArgumentException;
import org.soaframe.core.service.exception.CodeEnum;
import org.soaframe.manager.share.OrderManager;
import org.soaframe.web.resp.WebBaseResp;
import org.soaframe.web.util.CreateOrderParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.StringUtils;

/**
 * @Description: 订单相关控制类
 * @author zouhao
 * @date 2017年7月9日 下午4:34:21
 * 
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderManager orderManager;

	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public WebBaseResp<String> createOrder(@RequestBody CreateOrderParam param) {

		if (StringUtils.isBlank(param.getAccount()) || StringUtils.isBlank(param.getPayMethod())
				|| null == param.getProduct() || null == param.getMoney()) {
			throw new ArgumentException(CodeEnum.ERROR_PARAM_EMPTY);
		}
		orderManager.crateOrder(param.getAccount(), param.getMoney(), param.getPayMethod(), param.getProduct());
		return new WebBaseResp<String>(CodeEnum.OK);
	}

}
