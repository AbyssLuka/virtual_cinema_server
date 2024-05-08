package com.luka.r18.service.impl;

import com.luka.r18.entity.UserEntity;
import com.luka.r18.entity.response_object.UserInfo;
import com.luka.r18.mappers.UserDataMapper;
import com.luka.r18.service.UserService;
import com.luka.r18.util.MailClient;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDataMapper userMapper;

    @Resource
    private TemplateEngine templateEngine;

    @Resource
    private MailClient mailClient;

    @Override
    public UserEntity selectUserByName(String userName) {
        return userMapper.selectUserByName(userName);
    }

    @Override
    public UserInfo selectUserInfoByName(String userName) {
        return userMapper.selectUserInfoByName(userName);
    }

    @Override
    public String renderMailPage(Map<String, Object> map) {
        //渲染邮箱页面
        Context context = new Context();
        context.setVariables(map);
        return templateEngine.process("/mail/RegisterMail", context);
    }


    public void sendActivateMessage(String username, String url, String email) {
        Map<String, Object> map = new HashMap<>();
        map.put("url", url);
        map.put("userName", username);
        String content = renderMailPage(map);
        mailClient.sendMail(email, "账号激活", content);

    }

    @Override
    public int haveNameOrEMali(String userName, String email) {
        return userMapper.haveNameOrEMali(userName, email);
    }

    @Override
    public int insert(UserEntity userEntity) {
        return userMapper.insert(userEntity);
    }

    @Override
    public int update(UserEntity userData) {
        return userMapper.update(userData);
    }


}
