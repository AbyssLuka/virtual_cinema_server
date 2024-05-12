package com.luka.r18.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager DefaultWebSecurityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JWTFilter());
        factoryBean.setFilters(filterMap);
        //设置安全管理器
        factoryBean.setSecurityManager(DefaultWebSecurityManager);
        //配置过滤器
        /*
        anon:无需认证
        authc：必须认证
        user：必须记住我
        perms：拥有某个资源权限
        role：拥有某个角色权限
         */
        Map<String, String> filterRuleMap = new HashMap<>();
        filterRuleMap.put("/user/profile_photo","anon");
        filterRuleMap.put("/user/activation/**","anon");
        filterRuleMap.put("/user/kaptcha","anon");
        filterRuleMap.put("/user/login","anon");
        filterRuleMap.put("/user/signup","anon");
        filterRuleMap.put("/user/sendEmailCode","anon");
        filterRuleMap.put("/model","anon");
        filterRuleMap.put("/file/**","anon");
        filterRuleMap.put("/anime/**","anon");
        filterRuleMap.put("/**", "jwt");
        factoryBean.setLoginUrl("/login");
//        factoryBean.setUnauthorizedUrl("/401");
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建UserRealm
    @Bean("userRealm")
    public UserRealm userRealm() {
//        UserRealm userRealm = new UserRealm();
//        userRealm.setAuthenticationTokenClass(ShiroToken.class);
        return new UserRealm();
    }

}
