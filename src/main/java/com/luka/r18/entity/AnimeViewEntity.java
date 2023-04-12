package com.luka.r18.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (AnimeView)实体类
 *
 * @author makejava
 * @since 2022-07-15 03:26:49
 */
public class AnimeViewEntity implements Serializable {
    private static final long serialVersionUID = 908056965186469531L;

    private Integer id;
    private String uuid;
    /**
     * anime_file table
     */
    private String pathUuid  = "";
    private String info = "";
    private Integer clicks = -1;
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

    public String getPathUuid() {
        return pathUuid;
    }

    public void setPathUuid(String pathUuid) {
        this.pathUuid = pathUuid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

