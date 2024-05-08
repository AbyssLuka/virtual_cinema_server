package com.luka.r18.entity.response_object;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class UserInfo {

    private String email = "";
    private String phone = "";
    private String address = "";
    private String info = "";
    private String username = "";
    private String profilePhoto = "";
    private Integer sexType = 0;
    private Date createTime = new Date();

}
