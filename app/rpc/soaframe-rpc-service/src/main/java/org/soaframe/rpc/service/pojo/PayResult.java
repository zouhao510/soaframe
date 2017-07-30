package org.soaframe.rpc.service.pojo;

import java.io.Serializable;

/**
 * 支付响应
 */
public class PayResult implements Serializable {

    private static final long serialVersionUID = 5319846586155625613L;

    private int code;

    private String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
