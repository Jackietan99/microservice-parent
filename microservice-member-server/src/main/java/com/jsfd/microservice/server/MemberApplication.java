package com.jsfd.microservice.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: microservice-parent
 * @description: MemberServer Startup
 * @author: jackchen
 * @create: 2018-04-17 20:44
 **/

@SpringBootApplication
@ComponentScan(basePackages={"com.jsfd.microservice.member.*"})
@MapperScan("com.jsfd.microservice.member.*")
public class MemberApplication {

    public static void main(String []args){
        SpringApplication.run(MemberApplication.class, args);
    }
}