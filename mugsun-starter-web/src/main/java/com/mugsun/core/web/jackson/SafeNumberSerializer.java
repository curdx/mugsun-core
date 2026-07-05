package com.mugsun.core.web.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.NumberSerializer;

import java.io.IOException;

/**
 * 大整数序列化：数值超出 JS 安全整数范围（±2^53）时输出为字符串，规避雪花主键在前端丢失精度；
 * 范围内的数值仍按数字输出，不影响分页总数、状态码等常规字段。
 */
public class SafeNumberSerializer extends NumberSerializer {

	/** JS 安全整数上界 2^53 */
	private static final long JS_MAX_SAFE = 1L << 53;
	/** JS 安全整数下界 -2^53 */
	private static final long JS_MIN_SAFE = -(1L << 53);

	public static final SafeNumberSerializer INSTANCE = new SafeNumberSerializer(Number.class);

	public SafeNumberSerializer(Class<? extends Number> rawType) {
		super(rawType);
	}

	@Override
	public void serialize(Number value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		long longValue = value.longValue();
		if (longValue < JS_MIN_SAFE || longValue > JS_MAX_SAFE) {
			gen.writeString(value.toString());
		} else {
			super.serialize(value, gen, provider);
		}
	}
}
