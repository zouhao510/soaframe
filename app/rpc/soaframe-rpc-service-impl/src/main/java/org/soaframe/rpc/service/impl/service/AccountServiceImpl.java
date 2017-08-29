package org.soaframe.rpc.service.impl.service;

import org.soaframe.common.dal.dataobject.AccountDO;
import org.soaframe.rpc.service.api.AccountService;
import org.soaframe.rpc.service.resp.RpcDataResp;
import org.soaframe.rpc.service.resp.RpcRespStatus;

import com.alibaba.dubbo.config.annotation.Service;

@Service
public class AccountServiceImpl implements AccountService {

	// @Autowired
	// private AccountDAO accountDAO;// 注入数据层

	@Override
	public RpcDataResp<AccountDO> findByAccount(String account) {

		// 从数据库层查询，此处模拟
		// return accountDAO.findByAccount(account);

		AccountDO accountDO = new AccountDO();
		accountDO.setAccount(account);
		accountDO.setMoney(100.0);

		RpcDataResp<AccountDO> resp = new RpcDataResp<AccountDO>(RpcRespStatus.OK, accountDO);
		return resp;

	}

}
