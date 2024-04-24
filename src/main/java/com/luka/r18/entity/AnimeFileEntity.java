package com.luka.r18.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (AnimeFile)实体类
 *
 * @author makejava
 * @since 2022-03-22 04:28:18
 */
@Data
public class AnimeFileEntity implements Serializable {
    private static final long serialVersionUID = 176534116495074535L;
    private Integer id;
    private String uuid;
    private String fileName = "";
    private String parentFolder = "";
    private String type = "";
    private String md5 = "";
    private String fileSize = "";
    private String info = "";
    private String absolutePath = "";
    private Date createTime = new Date();
    private Date lastEditTime = new Date();

}

