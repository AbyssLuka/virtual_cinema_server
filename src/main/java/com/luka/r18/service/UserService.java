package com.luka.r18.service;

import com.luka.r18.entity.UserEntity;
import com.luka.r18.entity.response_object.UserInfo;

import java.util.Map;

public interface UserService {

    UserEntity selectUserByName(String userName);

    UserInfo selectUserInfoByName(String userName);

    UserEntity selectUserByUuid(String uuid);

    int insert(UserEntity userEntity);

    int update(UserEntity userEntity);

    String renderMailPage(Map<String, Object> map);

    int haveNameOrEMali(String userName,String email);

}
