package com.heyu.test.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        System.out.println("首页登录");
        ApplicationContext context = new ClassPathXmlApplicationContext();
        context.getBean("");
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String username,String password){
        System.out.println("开始验证登录信息,username " + username );
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","01d7f40760960e7bd9443513f22ab9af");
        try {

            subject.login(token);
            return "index";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "login";

    }

    @RequestMapping("/index")
    public String index(){
        System.out.println("登录成功");
        return "index";
    }

    @RequestMapping("/403")
    public String error(){
        System.out.println("请先登录");
        return "403";
    }

    @RequiresRoles("admin")
    @RequestMapping("list")
    public String list(){
        return "list";
    }
}
