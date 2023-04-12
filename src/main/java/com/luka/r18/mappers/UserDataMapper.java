package com.luka.r18.mappers;

import com.luka.r18.entity.UserDataEntity;
import com.luka.r18.entity.response_object.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (UserData)表数据库访问层
 *
 * @author makejava
 * @since 2022-07-06 23:56:21
 */
@Mapper
public interface UserDataMapper {

    UserDataEntity selectUserByName(String username);

    UserInfo selectUserInfoByName(String username);

    UserDataEntity queryById(Integer id);

    long count(UserDataEntity userData);

    int insert(UserDataEntity userData);

    int insertBatch(@Param("entities") List<UserDataEntity> entities);

    int insertOrUpdateBatch(@Param("entities") List<UserDataEntity> entities);

    int update(UserDataEntity userData);

    int deleteById(Integer id);

    int haveNameOrEMali(String username,String email);

}

