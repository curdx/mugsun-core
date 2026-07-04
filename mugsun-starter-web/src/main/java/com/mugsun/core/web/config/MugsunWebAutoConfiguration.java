package com.mugsun.core.web.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import com.mugsun.core.web.handler.GlobalExceptionHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web 基座自动装配：注册全局异常处理与 Sa-Token 注解鉴权拦截器
 */
@AutoConfiguration
public class MugsunWebAutoConfiguration implements WebMvcConfigurer {

	@Bean
	public GlobalExceptionHandler globalExceptionHandler() {
		return new GlobalExceptionHandler();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 开启 Sa-Token 注解鉴权（@SaCheckLogin / @SaCheckPermission 等）
		registry.addInterceptor(new SaInterceptor()).addPathPatterns("/**");
	}
}
