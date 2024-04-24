package com.luka.r18.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (AnimeTagMap)实体类
 *
 * @author makejava
 * @since 2022-09-21 00:38:36
 */
@Data
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

}

