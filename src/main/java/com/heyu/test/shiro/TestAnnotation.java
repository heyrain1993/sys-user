package com.heyu.test.shiro;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Component;

@Component
public class TestAnnotation {

    @RequiresRoles("ff")
    public void testAnnontationPermission(){
        System.out.println("权限验证通过");
    }
}
