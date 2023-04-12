package com.luka.r18.service.impl;

import com.luka.r18.entity.UserDataEntity;
import com.luka.r18.entity.response_object.UserInfo;
import com.luka.r18.mappers.UserDataMapper;
import com.luka.r18.service.UserDataService;
import com.luka.r18.util.MailClient;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserDataServiceImpl implements UserDataService {

    @Resource
    private UserDataMapper userDataMapper;

    @Resource
    private TemplateEngine templateEngine;

    @Resource
    private MailClient mailClient;

    @Override
    public UserDataEntity selectUserByName(String userName) {
        return userDataMapper.selectUserByName(userName);
    }

    @Override
    public UserInfo selectUserInfoByName(String userName) {
        return userDataMapper.selectUserInfoByName(userName);
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
        return userDataMapper.haveNameOrEMali(userName, email);
    }

    @Override
    public int insert(UserDataEntity userDataEntity) {
        return userDataMapper.insert(userDataEntity);
    }

    @Override
    public int update(UserDataEntity userData) {
        return userDataMapper.update(userData);
    }


}
