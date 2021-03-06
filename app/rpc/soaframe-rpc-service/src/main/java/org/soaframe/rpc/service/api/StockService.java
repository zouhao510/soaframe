package org.soaframe.rpc.service.api;

import org.soaframe.rpc.service.resp.RpcResp;

/**
 * @Description: 库存中心rpc服务
 * @author zouhao
 * @date 2017年7月30日 上午11:18:20
 * 
 */
public interface StockService {

	/**
	 * @Description:从仓库中出库指定数量产品
	 * @author zouhao
	 * @param product
	 * @param num
	 * @return
	 */
	RpcResp outStock(String product, Integer num);
}
