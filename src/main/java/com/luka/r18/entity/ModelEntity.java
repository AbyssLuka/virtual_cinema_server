package com.luka.r18.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

@Data
public class ModelEntity implements Serializable {
    private static final long serialVersionUID = -30222419415256437L;
    private String uuid;
    private String type;
    private String fileName;
    private Date createTime;

}

