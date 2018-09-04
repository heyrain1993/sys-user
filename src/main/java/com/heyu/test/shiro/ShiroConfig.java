package com.heyu.test.shiro;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 结合spring，shiro完成web的认证和授权
 */
@Configuration
public class ShiroConfig {

    @Bean
    public DefaultSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(definedRealm());
        securityManager.setCacheManager(cacheManager());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    public DefinedRealm definedRealm(){
        DefinedRealm realm = new DefinedRealm();
        return realm;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager());
        shiroFilter.setLoginUrl("/login");//设置登录URL
        shiroFilter.setUnauthorizedUrl("/403");//设置未授权URL
        shiroFilter.setSuccessUrl("/index");//设置登录成功后跳转URL

        //设置拦截的路径
        Map<String,String> filterChain = new LinkedHashMap<>();
        filterChain.put("/logout","logout");
        filterChain.put("/css/**","anon");
        filterChain.put("/fonts/**","anon");
        filterChain.put("/images/**","anon");
        filterChain.put("/js/**","anon");
        filterChain.put("/login","anon");
        filterChain.put("/**","authc");//设置在最后，按照顺序判断，如果匹配则返回
        shiroFilter.setFilterChainDefinitionMap(filterChain);

        return shiroFilter;
    }

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
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }

    /**
     * shiro生命周期处理器
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultWebSessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO());
        return sessionManager;
    }

    @Bean
    public SessionDAO sessionDAO(){
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
        return sessionDAO;
    }

    @Bean
    public EhCacheManager cacheManager(){
        EhCacheManager cacheManager = new EhCacheManager();
        return cacheManager;
    }
}
