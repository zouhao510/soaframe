package org.soaframe.common.util.sign;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Digest {
	public static String digest(String aValue) {
		return digest(aValue, "UTF-8");
	}

	public static String digest(String aValue, String encoding) {
		aValue = aValue.trim();

		byte[] value;
		try {
			value = aValue.getBytes(encoding);
		} catch (UnsupportedEncodingException var6) {
			value = aValue.getBytes();
		}

		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException var5) {
			var5.printStackTrace();
			return null;
		}

		return ConvertUtils.toHex(md.digest(value));
	}

}