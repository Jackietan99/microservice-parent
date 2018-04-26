package com.jsfd.microservice.remote;

import com.jsfd.microservice.member.api.member.MemberApiService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "MICROSERVICE-MEMBER-SERVER",fallback = MemberRpcServiceFallBack.class)
public interface MemberRpcService extends MemberApiService {
}
