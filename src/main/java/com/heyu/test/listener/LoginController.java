package com.heyu.test.listener;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    /**
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request){
        HttpSession session = request.getSession();
        return "index";
    }
}
