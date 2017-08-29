package org.soaframe.web.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.soaframe.manager.share.util.CommonProduct;
import org.soaframe.rpc.service.resp.RpcDataResp;
import org.soaframe.rpc.service.resp.RpcResp;
import org.soaframe.rpc.service.resp.RpcRespStatus;
import org.soaframe.web.util.CreateOrderParam;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
public class WebTest {

	public static void main(String[] args) {
		RpcDataResp resp = new RpcDataResp(RpcRespStatus.OK);
		RpcResp r = (RpcResp) resp;
		System.out.println(JSON.toJSONString(r));
	}

	@Test
	public void testOrderCreate() {
		try {
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

			String strURL = "http://localhost:8989/order/create";
			String res = this.httpPost(strURL, JSON.toJSONString(param));
			JSONObject objs = JSON.parseObject(res);
			System.out.println(res);
			Integer code = objs.getInteger("code");
			Assert.assertEquals(code.intValue(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String httpGet(String path) {
		BufferedReader in = null;
		StringBuilder result = new StringBuilder();
		try {
			// GET请求直接在链接后面拼上请求参数
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			// Get请求不需要DoOutPut
			conn.setDoOutput(false);
			conn.setDoInput(true);
			// 设置连接超时时间和读取超时时间
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			// 连接服务器
			conn.connect();
			// 取得输入流，并使用Reader读取
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result.toString();
	}

	private String httpPost(String strURL, String params) {
		try {
			BufferedReader bufferedReader = null;
			StringBuffer result = new StringBuffer();

			URL url = new URL(strURL);// 创建连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST"); // 设置请求方式
			connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
			connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
			connection.connect();
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
			out.append(params);
			out.flush();
			out.close();

			// 接受连接返回参数
			bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line);
			}
			bufferedReader.close();
			return result.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 向指定URL发送GET方法的请求
	 *
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

}
