package org.soaframe.web.controller;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.soaframe.core.service.exception.ArgumentException;
import org.soaframe.core.service.exception.CodeEnum;
import org.soaframe.core.service.impl.RedisLock;
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

	@Autowired
	private RedisLock redisLock;

	private int num = 0;

	private String product = "201763243";

	Random random = new Random();

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

	@ResponseBody
	@RequestMapping(value = "/testRedisLock", method = RequestMethod.GET)
	public WebBaseResp<String> testRedisLock() {

		ExecutorService es = Executors.newFixedThreadPool(20);
		for (int i = 0; i < 20; i++) {
			es.execute(new Runnable() {
				@Override
				public void run() {
					int temp = random.nextInt(5);
					System.out.println(Thread.currentThread().getName() + "加前: + " + num);
					operateNum(product, num);
					System.out.println(Thread.currentThread().getName() + "加后: + " + temp + " = " + num);
				}

			});
		}
		return new WebBaseResp<String>(CodeEnum.OK);
	}

	private void operateNum(String product, int num) {
		try {
			redisLock.lock(product, 30000, 60000);
			System.out.println(Thread.currentThread().getName() + ": 获取锁成功");
			int temp = random.nextInt(5);
			num += temp;
			System.out.println(Thread.currentThread().getName() + ": + " + temp);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("释放锁");
			redisLock.unlock(product);
		}
	}

}
