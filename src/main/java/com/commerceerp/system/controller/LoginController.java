package com.commerceerp.system.controller;

import com.commerceerp.system.service.LoginService;
import com.commerceerp.system.util.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/5/13/013.
 */
@Controller
@RequestMapping("/sys")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){

        return "login";
    }

    //登录请求
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult doLogin(String loginname, String password){
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(loginname, password);
            SecurityUtils.getSubject().login(token);
            return new JsonResult(0,"登录成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(400,"用户名或密码错误");
        }
    }

    @RequestMapping(value = "/index")
    public String index(){

        return "index";
    }

}
