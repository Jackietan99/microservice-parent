package com.jsfd.microservice;

/**
 * @program: microservice-parent
 * @description: GateWay startup
 * @author: jackchen
 * @create: 2018-04-17 20:23
 **/

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableZuulProxy
@ComponentScan(basePackages={"com.jsfd.*"})
@MapperScan("com.jsfd.*")
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }


}