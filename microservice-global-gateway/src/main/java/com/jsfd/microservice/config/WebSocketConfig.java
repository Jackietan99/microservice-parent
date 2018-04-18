package com.jsfd.microservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
	@Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        //注册一个STOMP协议服务端点/socket，来接收客户端的连接，开启SockJS协议支持。
    	stompEndpointRegistry.addEndpoint("/socket").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
    	//配置消息代理为 topic queue 客户端订阅服务地址的前缀
        registry.enableSimpleBroker("/topic","/queue");
        //服务端接收地址的前缀,所有目的地址以/ws开头的消息都将会路由到带有@MessageMapping注解的控制器方法中,而不会发布到代理队列或主题中
        registry.setApplicationDestinationPrefixes("/ws");
    }

}
