package com.luka.r18.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

@Data
public class TagMapEntity implements Serializable {
    private static final long serialVersionUID = -68656109690631645L;

    private Integer id;
    private String uuid;
    private String viewUuid;
    private String tagUuid;
    private Date createTime;

}

