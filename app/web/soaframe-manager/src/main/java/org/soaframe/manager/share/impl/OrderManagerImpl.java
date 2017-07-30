package org.soaframe.manager.share.impl;

import java.util.UUID;

import org.jboss.logging.Logger;
import org.soaframe.common.dal.dataobject.AccountDO;
import org.soaframe.manager.share.OrderManager;
import org.soaframe.manager.share.util.CommonProduct;
import org.soaframe.rpc.service.api.AccountService;
import org.soaframe.rpc.service.api.PayService;
import org.soaframe.rpc.service.api.StockService;
import org.soaframe.service.exception.ArgumentException;
import org.soaframe.service.exception.CodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 订单业务逻辑类
 * @author zouhao
 * @date 2017年7月9日 下午4:25:04
 * 
 */

@Service
public class OrderManagerImpl implements OrderManager {

	private static Logger log = Logger.getLogger(OrderManagerImpl.class);

	@Autowired
	private AccountService accountService;// 账户中心rpc服务

	@Autowired
	private PayService payService;// 支付中心rpc服务

	@Autowired
	private StockService stockService;// 仓库中心rpc服务

	@Override
	public void crateOrder(String account, double money, String payMethod, CommonProduct product) {

		// 调用用户中心服务,查看账户类型，及账户优惠信息
		AccountDO accountDO = accountService.findByAccount(account);

		if (null == accountDO) {
			log.error(String.format("账户不存在：%s"));
			throw new ArgumentException(CodeEnum.ERROR_PARAM_DEFAULT, "账户不存在");
		}

		commitOrder(account, payMethod, money, product);

	}

	@Override
	public void commitOrder(String account, String payMethod, double money, CommonProduct product) {

		// 调用stock库存服务
		stockService.outStock(product.getName(), product.getNum());

		// 调用支付中心服务
		String orderCode = UUID.randomUUID().toString();
		payService.pay(orderCode, payMethod, money);

	}

}
