package com.mugsun.core.web.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.mugsun.core.tool.api.R;
import com.mugsun.core.tool.api.ResultCode;
import com.mugsun.core.tool.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理：统一转为 R 响应，并对齐 HTTP 状态码
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/** 业务异常 */
	@ExceptionHandler(ServiceException.class)
	public R<Void> handleService(ServiceException e) {
		return R.fail(e.getResultCode(), e.getMessage());
	}

	/** 参数校验异常 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<R<Void>> handleValid(MethodArgumentNotValidException e) {
		FieldError fieldError = e.getBindingResult().getFieldError();
		String msg = fieldError != null ? fieldError.getDefaultMessage() : "参数校验失败";
		return ResponseEntity.badRequest().body(R.fail(msg));
	}

	/** 未登录 → 401 */
	@ExceptionHandler(NotLoginException.class)
	public ResponseEntity<R<Void>> handleNotLogin(NotLoginException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(R.fail(ResultCode.UNAUTHORIZED));
	}

	/** 无权限/无角色 → 403 */
	@ExceptionHandler({NotPermissionException.class, NotRoleException.class})
	public ResponseEntity<R<Void>> handleNoPermission(RuntimeException e) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(R.fail(ResultCode.FORBIDDEN));
	}

	/** 兜底 → 500 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<R<Void>> handleException(Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(R.fail(ResultCode.SERVER_ERROR));
	}
}
