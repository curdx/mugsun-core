package com.mugsun.core.tool.exception;

import com.mugsun.core.tool.api.ResultCode;

/**
 * 业务异常
 */
public class ServiceException extends RuntimeException {

	private final ResultCode resultCode;

	public ServiceException(String message) {
		super(message);
		this.resultCode = ResultCode.FAILURE;
	}

	public ServiceException(ResultCode resultCode) {
		super(resultCode.getMsg());
		this.resultCode = resultCode;
	}

	public ResultCode getResultCode() {
		return resultCode;
	}
}
