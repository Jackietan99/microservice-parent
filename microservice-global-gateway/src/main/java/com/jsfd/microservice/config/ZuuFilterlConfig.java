package com.jsfd.microservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuuFilterlConfig {
	/** 将TokenFilter加入到请求拦截队列. */
	@Bean
	public TokenZuulFilter tokenFilter() {
		return new TokenZuulFilter();
	}
}
