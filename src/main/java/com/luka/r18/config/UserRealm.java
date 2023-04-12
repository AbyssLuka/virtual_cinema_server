package com.luka.r18.config;

import com.luka.r18.entity.UserDataEntity;
import com.luka.r18.service.impl.UserDataServiceImpl;
import com.luka.r18.util.JWTToken;
import com.luka.r18.util.TokenUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

public class UserRealm extends AuthorizingRealm {

    @Resource
    UserDataServiceImpl userDataServiceImpl;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;

        try{

            String token = (String) authenticationToken.getCredentials();
            assert token != null;

            String userName = TokenUtil.getTokenClaim(token, "username");
            assert userName != null;


            UserDataEntity user = userDataServiceImpl.selectUserByName(userName);
            assert user != null;

            if (!TokenUtil.verify(token, userName, user.getPassword())) {
                return null;
            }

            return new SimpleAuthenticationInfo(token, token, this.getName());
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean supports(AuthenticationToken token){
        return token instanceof JWTToken;
    }
}
