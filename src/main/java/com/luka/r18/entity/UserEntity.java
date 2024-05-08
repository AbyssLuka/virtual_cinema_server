package com.luka.r18.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

@Data
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 400907383764128976L;

    private Integer id;
    private String uuid;
    private String salt = "";
    private String email = "";
    private String info = "";
    private String username = "";
    private String password = "";
    private String profilePhoto = "";
    private Integer sexType = 0;
    private Date createTime = new Date();

}

