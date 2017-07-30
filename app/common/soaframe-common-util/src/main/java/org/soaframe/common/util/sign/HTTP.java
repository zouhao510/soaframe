package org.soaframe.common.util.sign;

public enum HTTP {
	GET("GET"), POST("POST"),;

	private final String method;

	private HTTP(String method) {
		this.method = method;
	}

	public static HTTP validate(String method) {
		for (HTTP m : HTTP.values()) {
			if (m.method().equals(method)) {
				return m;
			}
		}
		throw new IllegalArgumentException("invalid http method - " + method);
	}

	public String method() {
		return method;
	}
}
