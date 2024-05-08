package com.luka.r18.service;

import com.luka.r18.entity.UserEntity;
import com.luka.r18.entity.response_object.UserInfo;

import java.util.Map;

public interface UserService {

    UserEntity selectUserByName(String userName);

    UserInfo selectUserInfoByName(String userName);

    int insert(UserEntity userData);

    int update(UserEntity userData);

    String renderMailPage(Map<String, Object> map);

    int haveNameOrEMali(String userName,String email);

}
