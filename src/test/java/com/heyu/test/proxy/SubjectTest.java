package com.heyu.test.proxy;

import com.heyu.test.Application;
import com.heyu.test.shiro.dao.SysRoleDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class SubjectTest {

    @Autowired
    private SysRoleDao sysRoleDao;

    /*@Autowired
    private Client client;*/

    /*@Test
    public void test(){
        client.test();
    }*/

}
