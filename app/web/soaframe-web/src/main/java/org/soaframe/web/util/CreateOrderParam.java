package org.soaframe.web.util;

import java.io.Serializable;

import org.soaframe.manager.share.util.CommonProduct;

import com.alibaba.fastjson.JSON;

/**
 * @Description: 创建订单post提交参数
 * @author zouhao
 * @date 2017年7月30日 下午3:33:30
 * 
 */
public class CreateOrderParam implements Serializable {

	private static final long serialVersionUID = -8521330206335258682L;
	private String account;
	private Double money;
	private String payMethod;
	private CommonProduct product;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public CommonProduct getProduct() {
		return product;
	}

	public void setProduct(CommonProduct product) {
		this.product = product;
	}

	public static void main(String[] args) {
		CreateOrderParam param = new CreateOrderParam();
		param.setAccount("DST_1000");
		param.setMoney(100.98);
		param.setPayMethod("支付宝");
		CommonProduct product = new CommonProduct();
		product.setName("太平鸟男装EX-23SDSSDF");
		product.setNum(1);
		product.setPrice(368.00);
		product.setUrl("http://taobao.com/product/ex-23SDSSDF");
		param.setProduct(product);
		System.out.println(JSON.toJSONString(param));
	}

}
