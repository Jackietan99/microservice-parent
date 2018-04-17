package com.jsfd.microservice.server;

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
        springApplication.run(MemberApplication.class, args);
    }
}