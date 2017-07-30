package org.soaframe.rpc.service.api;

import org.soaframe.rpc.service.pojo.PayResult;

/**
 * 支付接口
 */
public interface IPayService {

    public PayResult pay(double money);

}
