package com.luka.r18.entity;

import com.luka.r18.util.CustomUtil;

import java.util.Date;
import java.io.Serializable;

/**
 * (AnimeCollect)实体类
 *
 * @author makejava
 * @since 2022-08-30 05:03:58
 */
public class AnimeCollectEntity implements Serializable {
    private static final long serialVersionUID = 751078978239888832L;
    
    private Integer id;
    private String uuid;
    private String userUuid;
    private String viewUuid;
    private Date createTime;

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

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getViewUuid() {
        return viewUuid;
    }

    public void setViewUuid(String viewUuid) {
        this.viewUuid = viewUuid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

