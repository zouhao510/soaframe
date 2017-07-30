package org.soaframe.web.exception;

import org.soaframe.service.exception.CodeEnum;
import org.soaframe.web.resp.BaseResp;

/**
 * @Description: 异常处理统一响应类
 * @author zouhao
 * @date 2017年7月30日 上午10:38:47
 * 
 * @param <T>
 */
public class ExceptionResponse extends BaseResp {

	private static final long serialVersionUID = -5180575286817919769L;

	public ExceptionResponse(CodeEnum codeEnum, String msg) {
		super(codeEnum, msg);
	}

}
