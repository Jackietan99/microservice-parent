

#   简述Summary
此项目是由SpringCloud的体系构建的Raw模板(下载后更改配置即用),整合了Mybatis,Redis,RabbitMq,WebSocket,(ToBeContinued).


每个业务服务采用独立的MYSQL数据库
1. 服务注册、发现: eureka
2. 配置管理:spring config , spring security
3. 集群容错: hystrix
4. API网关: zuul
5. 服务负载:feign+ribbon
6. API文档:swagger (待实现)
7. 消息队列:rabbitmq (待实现,构建初期暂时用线程池)
8. 项目缓存: redis
10. 链路跟踪:zipkin (待实现)

#   个人观点KeyPoint?
1.很多项目本身MVC简单服务调用链已经满足,Po主膜拜很多大神项目后,稍加改动如 :
    Member 领域最简单调用 

   利用关系来满足上下约束规范

2.单个业务module只需要一个启动项

 #  概念图
 ![image](https://github.com/Jackietan99/microservice-parent/blob/master/keypointflow.PNG?raw=true)



#   各模块介绍
| 模块名称        | 端口   |  简介  |
| --------   | -----:  | :----:  |
| microservice-global-register      | 8761   |   服务注册中心，提供服务注册、发现功能    |   
| microservice-global-auth        |  --   |   权限业务，结合spring-security   |
| microservice-global-gateway        |    8080    |  提供配置config(定时schedualing,线程池ThreadPool,页面WebSocket) ,filter(ZuulFilter) ,Remote(各业务模块入口) |    
| microservice-global-core       |    --    |  BaseController,Utils,Model,自定义异常等 |
| microservice-member        |    --    |  member模块service,mapper,pojo  |
| microservice-member-api        |    --    |  member模块Remote接口和DTO  |
| microservice-member-server       |    8082    |  member模块启动项  |


# 使用

更改各领域中的配置后,先后启动 register,member,gateway.

#未完(TODO)


po主还在摸索springcloud项目 ,虚心请教各位.
更多功能, 后续会加上.

勉励