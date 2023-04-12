package com.luka.r18.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (UserData)实体类
 *
 * @author makejava
 * @since 2022-07-06 23:53:58
 */
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public Integer getSexType() {
        return sexType;
    }

    public void setSexType(Integer sexType) {
        this.sexType = sexType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

