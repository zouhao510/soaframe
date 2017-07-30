package org.soaframe.rpc.service.api;

import org.soaframe.common.dal.dataobject.AccountDO;

/**
 * @Description: 账户中心rpc服务
 * @author zouhao
 * @date 2017年7月30日 上午11:18:20
 * 
 */
public interface AccountService {

	AccountDO findByAccount(String account);

}
