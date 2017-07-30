package org.soaframe.manager.share.util;

import java.io.Serializable;

/**
 * @Description: 商品结算通用描述
 * @author zouhao
 * @date 2017年7月30日 上午10:52:43
 * 
 */
public class CommonProduct implements Serializable {

	private static final long serialVersionUID = -1808429861256057625L;

	private String name;// 名称

	private Double price;// 价格

	private String url;// 商品网站地址

	private Integer num;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
