package com.commerceerp.system.controller;

import com.commerceerp.system.service.TokenService;
import com.commerceerp.system.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/6/10/010.
 */
@Controller
@RequestMapping("")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    //获取授权码
    @RequestMapping(value = "/wish/authorization_code")
    @ResponseBody
    public JsonResult saveToken(String code){

        return tokenService.saveToken(code);
    }

}
