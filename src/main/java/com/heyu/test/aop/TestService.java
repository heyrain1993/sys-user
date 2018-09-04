package com.heyu.test.aop;

import com.heyu.test.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestService {

    @Autowired
    private LogService logService;

    @Test
    public void testInsert(){
        logService.insert();
    }

    @Test
    public void testUpdate(){
        logService.update();
    }

    @Test
    public void testQuery(){
        logService.query();
    }
}
