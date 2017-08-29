package org.soaframe.rpc.service.impl.service;

import org.apache.log4j.Logger;
import org.soaframe.core.service.exception.CodeEnum;
import org.soaframe.core.service.exception.ServiceException;
import org.soaframe.rpc.service.api.StockService;
import org.soaframe.rpc.service.resp.RpcResp;

import com.alibaba.dubbo.config.annotation.Service;

@Service
public class StockServiceImpl implements StockService {

	private final Logger log = Logger.getLogger(StockServiceImpl.class);

	@Override
	public RpcResp outStock(String product, Integer num) {

		if (num >= 2) {
			log.error(String.format("商品：%s 库存不足：%d 件", product, num));
			throw new ServiceException(CodeEnum.ERROR_SERVICE_DEFAULT, "商品库存不足");
		}
		log.info(String.format("商品：%s 出库：%d 件成功", product, num));
		return RpcResp.OK();
	}

}
