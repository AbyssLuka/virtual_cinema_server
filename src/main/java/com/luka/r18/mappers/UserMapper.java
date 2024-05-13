package com.luka.r18.mappers;

import com.luka.r18.entity.UserEntity;
import com.luka.r18.entity.response_object.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface UserMapper {

    UserEntity selectUserByName(String username);

    UserInfo selectUserInfoByName(String username);

    UserEntity queryById(Integer id);

    UserEntity selectUserByUuid(String uuid);

    long count(UserEntity userEntity);

    int insert(UserEntity userEntity);

    int insertBatch(@Param("entities") List<UserEntity> entities);

    int insertOrUpdateBatch(@Param("entities") List<UserEntity> entities);

    int update(UserEntity userEntity);

    int deleteById(Integer id);

    int haveNameOrEMali(String username,String email);

}

