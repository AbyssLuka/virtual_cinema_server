package com.luka.r18.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

@Data
public class CollectionEntity implements Serializable {

    private static final long serialVersionUID = 751078978239888832L;
    private Integer id;
    private String uuid;
    private String userUuid;
    private String viewUuid;
    private Date createTime;

}

