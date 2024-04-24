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
    /**
     * [未知,蓝孩子,铝孩子,扶她,药娘,伪娘,外星人,触手怪,福瑞]
     */
    private Integer sexType = 0;
    private Date createTime = new Date();

}
