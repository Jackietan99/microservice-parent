package com.jsfd.microservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableScheduling
public class ThreadPoolConfig {
	@Bean(name = "customThreadPoolTaskExecutor")
	ThreadPoolTaskExecutor customThreadPoolTaskExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		Integer corePoolSize = 10; //线程池维护线程的最少数量
		Integer keepAliveSeconds = 200; //允许的空闲时间
		Integer maxPoolSize = 20; //线程池维护线程的最大数量
		Integer queueCapacity = 100; //线程池所使用的缓冲队列
		threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
		threadPoolTaskExecutor.setKeepAliveSeconds(keepAliveSeconds);
		threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
		threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
		//线程池对拒绝任务(无线程可用)的处理策略,ThreadPoolExecutor.CallerRunsPolicy策略 ,调用者的线程会执行该任务,如果执行器已关闭,则丢弃.
		RejectedExecutionHandler callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy(); 
		
		threadPoolTaskExecutor.setRejectedExecutionHandler(callerRunsPolicy); 
		return threadPoolTaskExecutor;
	}
	
}
