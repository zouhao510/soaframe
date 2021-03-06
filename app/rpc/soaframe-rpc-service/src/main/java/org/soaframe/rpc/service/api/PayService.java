package org.soaframe.rpc.service.api;

import org.soaframe.rpc.service.resp.RpcResp;

/**
 * @Description: 支付中心rpc服务
 * @author zouhao
 * @date 2017年7月30日 上午11:18:20
 * 
 */
public interface PayService {

	/**
	 * @Description:根据订单编号支付
	 * @author zouhao
	 * @param orderCode
	 * @param payMethod
	 * @param money
	 */
	RpcResp pay(String orderCode, String payMethod, double money);

}
