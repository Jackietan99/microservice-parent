package com.jsfd.microservice.remote;

import com.jsfd.microservice.member.api.member.MemberApiService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @program: microservice-parent
 * @description: Rpc Service
 * @author: jackchen
 * @create: 2018-04-25 23:39
 **/


@FeignClient(value = "MICROSERVICE-MEMBER-SERVER",fallback = MemberRpcServiceFallback.class)
public class MemberRpcService extends MemberApiService {
}