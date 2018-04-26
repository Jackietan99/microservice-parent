package com.jsfd.microservice.service;

import com.jsfd.microservice.remote.MemberRpcService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: microservice-parent
 * @description: MemberService
 * @author: jackchen
 * @create: 2018-04-25 23:30
 **/
public class MemberService {

    @Autowired
    private MemberRpcService memberRpcService;
}