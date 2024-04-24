package com.luka.r18.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (AnimeTag)实体类
 *
 * @author makejava
 * @since 2022-09-21 00:25:09
 */
@Data
public class AnimeTagEntity implements Serializable {
    private static final long serialVersionUID = -11981937054013802L;
    private Integer id;
    private String uuid;
    private String tagName;
    private String info;
    private Date createTime;

}

