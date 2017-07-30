package org.soaframe.manager.share;

import org.soaframe.manager.share.util.CommonProduct;

/**
 * @Description: 订单业务逻辑接口定义
 * @author zouhao
 * @date 2017年7月9日 下午4:25:04
 * 
 */

public interface OrderManager {

	/**
	 * @Description:创建订单
	 * @author zouhao
	 * @param account
	 * @param money
	 * @param payMethod
	 * @param product
	 * @return
	 */
	void crateOrder(String account, double money, String payMethod, CommonProduct product);

	/**
	 * 提交订单
	 * 
	 * @Description:TODO
	 * @author zouhao
	 * @param account
	 * @param payMethod
	 * @param money
	 * @return
	 */
	void commitOrder(String account, String payMethod, double money, CommonProduct product);
}
