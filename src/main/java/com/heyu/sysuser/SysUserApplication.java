package com.heyu.sysuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 开启组件扫描和自动配置
 */
@SpringBootApplication
@MapperScan(value = "com.heyu.sysuser.dao")
public class SysUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysUserApplication.class, args);
    }
}
