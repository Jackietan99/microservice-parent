package com.jsfd.microservice.web.rpc.member.controller;

import com.jsfd.core.controller.BaseController;
import com.jsfd.core.web.model.AjaxResult;
import com.jsfd.microservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    public AjaxResult hello(HttpServletRequest request, HttpServletResponse response , Model model){

        String hello ="hello";
        return super.success(hello);

    }

}