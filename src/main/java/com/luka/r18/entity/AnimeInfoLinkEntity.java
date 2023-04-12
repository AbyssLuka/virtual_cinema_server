package com.luka.r18.entity;

import java.io.Serializable;

/**
 * (AnimeInfoLink)实体类
 *
 * @author makejava
 * @since 2022-07-24 09:55:53
 */
public class AnimeInfoLinkEntity implements Serializable {
    private static final long serialVersionUID = -84347717305283968L;

    private Integer id = 0;
    private String uuid = "";
    private String videoUuid = "";
    private String coverUuid = "";
    private String subtitleUuid = "";
    private String info = "";

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

    public String getVideoUuid() {
        return videoUuid;
    }

    public void setVideoUuid(String videoUuid) {
        this.videoUuid = videoUuid;
    }

    public String getCoverUuid() {
        return coverUuid;
    }

    public void setCoverUuid(String coverUuid) {
        this.coverUuid = coverUuid;
    }

    public String getSubtitleUuid() {
        return subtitleUuid;
    }

    public void setSubtitleUuid(String subtitleUuid) {
        this.subtitleUuid = subtitleUuid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}

