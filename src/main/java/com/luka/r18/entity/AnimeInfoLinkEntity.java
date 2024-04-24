package com.luka.r18.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (AnimeInfoLink)实体类
 *
 * @author makejava
 * @since 2022-07-24 09:55:53
 */
@Data
public class AnimeInfoLinkEntity implements Serializable {
    private static final long serialVersionUID = -84347717305283968L;
    private Integer id = 0;
    private String uuid = "";
    private String videoUuid = "";
    private String coverUuid = "";
    private String subtitleUuid = "";
    private String info = "";

}

