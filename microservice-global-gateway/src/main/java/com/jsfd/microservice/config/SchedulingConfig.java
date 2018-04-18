package com.jsfd.microservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulingConfig {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired 
    private WebsocketService websocketService;
	
	/** 系统当前时间 .*/
	@Scheduled(cron = "0 0/1 * * * ?") // 每1分钟执行一次
    public void sendSystemCurrentTimeMillis() {
        logger.debug("########################## 系统当前时间定时任务启动,每1分钟执行一次 ##########################");
        //String.valueOf(System.currentTimeMillis())
        WsResponse wsResponse = WsResponse.successSysdate(System.currentTimeMillis());
        websocketService.sendTopicDateTimeMessage(wsResponse);
    }
}
