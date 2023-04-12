package com.luka.r18.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (AnimeTagMap)实体类
 *
 * @author makejava
 * @since 2022-09-21 00:38:36
 */
public class AnimeTagMapEntity implements Serializable {
    private static final long serialVersionUID = -68656109690631645L;

    private Integer id;
    private String uuid;
    /**
     * anime_view table
     */
    private String animeViewUuid;
    /**
     * anime_tag table
     */
    private String tagUuid;
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

    public String getAnimeViewUuid() {
        return animeViewUuid;
    }

    public void setAnimeViewUuid(String animeViewUuid) {
        this.animeViewUuid = animeViewUuid;
    }

    public String getTagUuid() {
        return tagUuid;
    }

    public void setTagUuid(String tagUuid) {
        this.tagUuid = tagUuid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

