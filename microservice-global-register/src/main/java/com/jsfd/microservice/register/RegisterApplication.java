package com.jsfd.microservice.register;

/**
 * @program: microservice-parent
 * @description: RegisterStartup
 * @author: jackchen
 * @create: 2018-04-17 20:28
 **/

@SpringBootApplication
@EnableEurekaServer
public class RegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterApplication.class, args);
    }
}