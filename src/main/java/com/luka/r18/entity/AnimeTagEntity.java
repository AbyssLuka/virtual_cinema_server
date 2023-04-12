package com.luka.r18.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (AnimeTag)实体类
 *
 * @author makejava
 * @since 2022-09-21 00:25:09
 */
public class AnimeTagEntity implements Serializable {
    private static final long serialVersionUID = -11981937054013802L;

    private Integer id;
    private String uuid;
    private String tagName;
    private String info;
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

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

