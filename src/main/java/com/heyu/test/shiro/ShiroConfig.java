package com.heyu.test.shiro;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 结合spring，shiro完成web的认证和授权
 */
@Configuration
public class ShiroConfig {

   /* @Bean
    public DefaultSecurityManager securityManager(){
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(definedRealm());
        return securityManager;
    }

    @Bean
    public DefinedRealm definedRealm(){
        DefinedRealm realm = new DefinedRealm();
        return realm;
    }*/

    /**
     * Spring的一个bean,有advisor决定对那些类实现AOP代理
     * 配置通过注解完成权限和角色验证
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisor = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisor.setProxyTargetClass(true);
        return defaultAdvisor;
    }

    /**
     * shiro类的实现Advisor的类，开启shiro的注解支持
     * 内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor
     * 拦截@RequiredRoles,@RequiredPermission注解的方法
     * @return
     */
   /* @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }*/
}
