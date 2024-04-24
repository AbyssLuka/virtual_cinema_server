package com.luka.r18.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (ComicView)实体类
 *
 * @author makejava
 * @since 2022-11-09 16:27:39
 */
@Data
public class ComicViewEntity implements Serializable {
    private static final long serialVersionUID = 247952208817808316L;

    private Integer id;
    private String uuid;
    private String pathUuid;
    private String info;
    private Integer clicks;
    private Date createTime;

}

