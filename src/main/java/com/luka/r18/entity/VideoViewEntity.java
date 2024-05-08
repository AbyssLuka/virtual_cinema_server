package com.luka.r18.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

@Data
public class VideoViewEntity implements Serializable {
    private static final long serialVersionUID = 908056965186469531L;

    private Integer id;
    private String uuid;
    private String pathUuid  = "";
    private String info = "";
    private Integer clicks = -1;
    private Date createTime;

}

