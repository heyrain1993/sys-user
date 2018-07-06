package com.heyu.test.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.heyu.test.Application;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;


/**
 * 测试三种Realm的认证方式
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class LoginController {

    @Autowired
    private DefinedRealm definedRealm;

    @Autowired
    private TestAnnotation testAnnotation;

    /**
     * 测试IniRealm认证方式
     */
    @Test
    public void testIni(){
        //创建securityManager
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //设置realm
        securityManager.setRealm(new IniRealm("classpath:shiro-config.ini"));
        //绑定securityManager到securityUtils
        SecurityUtils.setSecurityManager(securityManager);
        //创建登录实体
        Subject subject = SecurityUtils.getSubject();
        //创建登录token
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","123456");
        //登录
        subject.login(token);

        System.out.println("是否认证通过" + subject.isAuthenticated());
        System.out.println("是否有角色role1" + subject.hasRole("role1"));
        System.out.println("是否有权限permission1" + subject.isPermitted("permission1"));
    }

    /**
     * 测试JDBCRealm认证方式
     * JDBCRealm和IniRealm的区别是一个从数据库获取用户信息，一个从配置文件中获取用户信息
     * 注意：jdbcRealm默认表名和表字段，建表是不能随意取名
     */
    @Test
    public void testJdbc(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("driverClassName","com.mysql.jdbc.Driver");
        map.put("url","jdbc:mysql://127.0.0.1:3306/rdsweb?characterEncoding=utf-8");
        map.put("username","root");
        map.put("password","123456");

        try {
            //创建数据源
            DruidDataSource druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(map);
            JdbcRealm jdbcRealm = new JdbcRealm();
            jdbcRealm.setDataSource(druidDataSource);

            //创建securityManager
            DefaultSecurityManager securityManager = new DefaultSecurityManager();
            //设置realm
            securityManager.setRealm(jdbcRealm);
            //绑定securityManager到securityUtils
            SecurityUtils.setSecurityManager(securityManager);
            //创建登录实体
            Subject subject = SecurityUtils.getSubject();
            //创建登录token
            UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","123456");
            //登录
            subject.login(token);

            System.out.println("是否认证通过" + subject.isAuthenticated());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试自定义Realm方式认证
     * 自定义Realm认证时，可以根据自己表设计来获取用户信息
     */
    @Test
    public void testDefined(){
        //创建securityManager
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //设置realm
        securityManager.setRealm(definedRealm);
        //绑定securityManager到securityUtils
        SecurityUtils.setSecurityManager(securityManager);
        //创建登录实体
        Subject subject = SecurityUtils.getSubject();
        //创建登录token
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","01d7f40760960e7bd9443513f22ab9af");
        //登录
        subject.login(token);
        System.out.println("是否认证通过 " + subject.isAuthenticated());
        System.out.println("是否有角色admin " + subject.hasRole("admin"));
        System.out.println("是否有权限permission1 " + subject.isPermitted("permission1"));
        testAnnotation.testAnnontationPermission();
    }



    public void testHasPermission(Subject subject){
        if (subject.isPermitted("update")){
            System.out.println("权限验证通过");
        }
    }
}
