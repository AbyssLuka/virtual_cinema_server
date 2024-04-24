package com.luka.r18.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (UserData)实体类
 *
 * @author makejava
 * @since 2022-07-06 23:53:58
 */
@Data
public class UserDataEntity implements Serializable {
    private static final long serialVersionUID = 400907383764128976L;

    private Integer id;
    private String uuid;
    private String salt = "";
    private String email = "";
    private String phone = "";
    private String address = "";
    private String info = "";
    private String username = "";
    private String password = "";
    private String profilePhoto = "";
    /**
     * [未知,蓝孩子,铝孩子,扶她,药娘,伪娘,外星人,触手怪,福瑞]
     */
    private Integer sexType = 0;
    private Date createTime = new Date();

}

