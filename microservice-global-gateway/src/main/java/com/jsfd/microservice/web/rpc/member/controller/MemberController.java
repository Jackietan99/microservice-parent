package com.jsfd.microservice.web.rpc.member.controller;

import com.jsfd.core.controller.BaseController;
import com.jsfd.microservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: microservice-parent
 * @description: MemberController
 * @author: jackchen
 * @create: 2018-04-25 23:26
 **/

@RestController
@RequestMapping("/member")
public class MemberController extends BaseController{



    @Autowired
    private MemberService memberService;

}