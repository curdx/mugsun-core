package com.mugsun.core.web.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;

import java.math.BigInteger;

/**
 * 大整数序列化模块：为 Long / long / BigInteger 注册超精度转字符串逻辑，随 Jackson 全局生效。
 */
public class SafeNumberModule extends SimpleModule {

	public SafeNumberModule() {
		super(SafeNumberModule.class.getName());
		addSerializer(Long.class, SafeNumberSerializer.INSTANCE);
		addSerializer(Long.TYPE, SafeNumberSerializer.INSTANCE);
		addSerializer(BigInteger.class, SafeNumberSerializer.INSTANCE);
	}
}
