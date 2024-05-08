package com.luka.r18.mappers;

import com.luka.r18.entity.UserEntity;
import com.luka.r18.entity.response_object.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface UserDataMapper {

    UserEntity selectUserByName(String username);

    UserInfo selectUserInfoByName(String username);

    UserEntity queryById(Integer id);

    long count(UserEntity userData);

    int insert(UserEntity userData);

    int insertBatch(@Param("entities") List<UserEntity> entities);

    int insertOrUpdateBatch(@Param("entities") List<UserEntity> entities);

    int update(UserEntity userData);

    int deleteById(Integer id);

    int haveNameOrEMali(String username,String email);

}

