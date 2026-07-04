package com.mugsun.core.tool.api;

/**
 * 统一响应状态码
 */
public enum ResultCode {

	SUCCESS(200, "操作成功"),
	FAILURE(400, "操作失败"),
	UNAUTHORIZED(401, "请求未授权"),
	FORBIDDEN(403, "没有访问权限"),
	NOT_FOUND(404, "资源不存在"),
	SERVER_ERROR(500, "服务器内部异常");

	private final int code;
	private final String msg;

	ResultCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
