package com.luka.r18.service;

import com.luka.r18.entity.UserDataEntity;
import com.luka.r18.entity.response_object.UserInfo;

import java.util.Map;

public interface UserDataService {

    UserDataEntity selectUserByName(String userName);

    UserInfo selectUserInfoByName(String userName);

    int insert(UserDataEntity userData);

    int update(UserDataEntity userData);

    String renderMailPage(Map<String, Object> map);

    int haveNameOrEMali(String userName,String email);

}
