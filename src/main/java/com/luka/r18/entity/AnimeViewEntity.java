package com.luka.r18.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (AnimeView)实体类
 *
 * @author makejava
 * @since 2022-07-15 03:26:49
 */
@Data
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

}

