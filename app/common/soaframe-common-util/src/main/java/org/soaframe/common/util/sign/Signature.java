package org.soaframe.common.util.sign;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.TreeMap;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

/**
 * @Description: 使用阿里云签名方式
 * @author zouhao
 * @date 2017年7月30日 下午4:50:39
 * 
 */
public class Signature {
	private final static String CHARSET_UTF8 = "utf8";
	private final static String ALGORITHM = "UTF-8";
	private final static String SEPARATOR = "&";

	// 第一步
	public static Map<String, String> splitQueryString(String url)
			throws URISyntaxException, UnsupportedEncodingException {
		URI uri = new URI(url);
		String query = uri.getQuery();
		final String[] pairs = query.split("&");
		TreeMap<String, String> queryMap = new TreeMap<String, String>();
		for (String pair : pairs) {
			final int idx = pair.indexOf("=");
			final String key = idx > 0 ? pair.substring(0, idx) : pair;
			if (!queryMap.containsKey(key)) {
				queryMap.put(key, URLDecoder.decode(pair.substring(idx + 1), CHARSET_UTF8));
			}
		}
		return queryMap;
	}

	public static String generate(String method, Map<String, String> parameter, String accessKeySecret)
			throws Exception {
		String signString = generateSignString(method, parameter);
		System.out.println("signString---" + signString);
		byte[] signBytes = hmacSHA1Signature(accessKeySecret + "&", signString);
		String signature = newStringByBase64(signBytes);
		System.out.println("signature---" + signature);
		if ("POST".equals(method))
			return signature;
		return URLEncoder.encode(signature, "UTF-8");

	}

	// 第二步 构造的规范化字符串按照规则构造成待签名的字符串
	public static String generateSignString(String httpMethod, Map<String, String> parameter) throws IOException {
		TreeMap<String, String> sortParameter = new TreeMap<String, String>();
		sortParameter.putAll(parameter);

		String canonicalizedQueryString = UrlUtil.generateQueryString(sortParameter, true);
		if (null == httpMethod) {
			throw new RuntimeException("httpMethod can not be empty");
		}

		/**
		 * 构造待签名的字符串
		 */
		StringBuilder stringToSign = new StringBuilder();
		stringToSign.append(httpMethod).append(SEPARATOR);
		stringToSign.append(percentEncode("/")).append(SEPARATOR);
		stringToSign.append(percentEncode(canonicalizedQueryString));

		return stringToSign.toString();
	}

	// 第三步计算代签名字符串的HMAC值

	public static byte[] hmacSHA1Signature(String secret, String baseString) throws Exception {
		if (isEmpty(secret)) {
			throw new IOException("secret can not be empty");
		}
		if (isEmpty(baseString)) {
			return null;
		}
		Mac mac = Mac.getInstance("HmacSHA1");
		SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(CHARSET_UTF8), ALGORITHM);
		mac.init(keySpec);
		return mac.doFinal(baseString.getBytes(CHARSET_UTF8));
	}

	private static boolean isEmpty(String str) {
		return (str == null || str.length() == 0);
	}

	// 第四步 按照Base64 编码规则把上面的 HMAC 值编码成字符串，即得到签名值（Signature）
	public static String newStringByBase64(byte[] bytes) throws UnsupportedEncodingException {
		if (bytes == null || bytes.length == 0) {
			return null;
		}
		return new String(new BASE64Encoder().encode(bytes));
	}

	public static String composeStringToSign(Map<String, String> queries) {
		String[] sortedKeys = (String[]) queries.keySet().toArray(new String[0]);
		Arrays.sort(sortedKeys);
		StringBuilder canonicalizedQueryString = new StringBuilder();
		for (String key : sortedKeys) {
			canonicalizedQueryString.append("&").append(percentEncode(key)).append("=")
					.append(percentEncode((String) queries.get(key)));
		}

		StringBuilder stringToSign = new StringBuilder();
		stringToSign.append("GET");
		stringToSign.append("&");
		stringToSign.append(percentEncode("/"));
		stringToSign.append("&");
		stringToSign.append(percentEncode(canonicalizedQueryString.toString().substring(1)));

		return stringToSign.toString();
	}

	public static String percentEncode(String value) {
		try {
			return value == null ? null
					: URLEncoder.encode(value, CHARSET_UTF8).replace("+", "%20").replace("*", "%2A").replace("%7E",
							"~");
		} catch (Exception e) {
		}
		return "";
	}

	/**
	 * get SignatureNonce
	 *
	 * @return
	 */
	public static String getUniqueNonce() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	/**
	 * get timestamp
	 *
	 * @param date
	 * @return
	 */
	public static String getISO8601Time() {
		Date nowDate = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		df.setTimeZone(new SimpleTimeZone(0, "GMT"));

		return df.format(nowDate);
	}

	// 第五步 将得到的签名值作为?Signature?参数添加到请求参数中，即完成对请求签名的过程。?
	public static String composeUrl(String endpoint, Map<String, String> queries) throws UnsupportedEncodingException {
		Map<String, String> mapQueries = queries;
		StringBuilder urlBuilder = new StringBuilder("");
		urlBuilder.append("http");
		urlBuilder.append("://").append(endpoint);
		if (-1 == urlBuilder.indexOf("?")) {
			urlBuilder.append("/?");
		}
		urlBuilder.append(concatQueryString(mapQueries));
		return urlBuilder.toString();
	}

	public static String concatQueryString(Map<String, String> parameters) throws UnsupportedEncodingException {
		if (null == parameters) {
			return null;
		}
		StringBuilder urlBuilder = new StringBuilder("");
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			String key = (String) entry.getKey();
			String val = (String) entry.getValue();
			urlBuilder.append(encode(key));
			if (val != null) {
				urlBuilder.append("=").append(encode(val));
			}
			urlBuilder.append("&");
		}

		int strIndex = urlBuilder.length();
		if (parameters.size() > 0) {
			urlBuilder.deleteCharAt(strIndex - 1);
		}
		return urlBuilder.toString();
	}

	public static String encode(String value) throws UnsupportedEncodingException {
		return URLEncoder.encode(value, "UTF-8");
	}

}