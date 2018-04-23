package com.heyu.sysuser.Controller;

import com.heyu.sysuser.entity.SysUser;
import com.heyu.sysuser.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SysUserController {

   /* @Autowired
    private SysUserDao sysUserRepository;*/

    @Autowired
    private SysUserService sysUserService;

    @GetMapping(value = "users/{loginName}")
    @ResponseBody
    public List<SysUser> getUsers(@PathVariable(value = "loginName") String loginName){
        //return sysUserService.selectByLoginName(loginName);
        return null;
    }

   /* @PostMapping(value = "users")
    public SysUser insertSysUser(@RequestBody SysUser sysUser){
        return sysUserRepository.save(sysUser);
    }

    @GetMapping(value = "insert")
    public String insert(Model model){
        System.out.println("============");
        model.addAttribute("books",sysUserRepository.findAll());
        return "index";
    }*/
}
