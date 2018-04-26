package com.jsfd.microservice.server.rest;

import com.jsfd.microservice.member.api.dto.MemberDTO;
import com.jsfd.microservice.member.api.member.MemberApiService;

/**
 * @program: microservice-parent
 * @description: MemberApiController
 * @author: jackchen
 * @create: 2018-04-26 23:30
 **/
public class MemberApiController implements MemberApiService {
    @Override
    public MemberDTO getMemberByMemberId(Long memberId) {
        return null;
    }
}