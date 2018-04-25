package com.jsfd.microservice.member.api.member;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface MemberApiService {


    /** 获取会员 .*/
    @RequestMapping(value = "/getMemberByMemberId/{memberId}", method = RequestMethod.GET)
    MemberDTO getMemberByMemberId(@PathVariable("memberId") Long memberId);
}
