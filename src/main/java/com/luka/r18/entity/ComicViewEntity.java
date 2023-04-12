package com.luka.r18.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (ComicView)实体类
 *
 * @author makejava
 * @since 2022-11-09 16:27:39
 */
public class ComicViewEntity implements Serializable {
    private static final long serialVersionUID = 247952208817808316L;

    private Integer id;
    private String uuid;
    private String pathUuid;
    private String info;
    private Integer clicks;
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

    public void setClick(Integer clicks) {
        this.clicks = clicks;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ComicViewEntity{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", pathUuid='" + pathUuid + '\'' +
                ", info='" + info + '\'' +
                ", clicks=" + clicks +
                ", createTime=" + createTime +
                '}';
    }
}

