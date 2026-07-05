package com.mugsun.core.web.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import com.mugsun.core.web.handler.GlobalExceptionHandler;
import com.mugsun.core.web.jackson.SafeNumberModule;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web 基座自动装配：注册全局异常处理、大整数序列化模块与 Sa-Token 注解鉴权拦截器
 */
@AutoConfiguration
public class MugsunWebAutoConfiguration implements WebMvcConfigurer {

	@Bean
	public GlobalExceptionHandler globalExceptionHandler() {
		return new GlobalExceptionHandler();
	}

	@Bean
	public SafeNumberModule safeNumberModule() {
		return new SafeNumberModule();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 开启 Sa-Token 注解鉴权（@SaCheckLogin / @SaCheckPermission 等）
		registry.addInterceptor(new SaInterceptor()).addPathPatterns("/**");
	}
}
