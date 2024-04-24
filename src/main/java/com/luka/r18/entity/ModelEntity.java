package com.luka.r18.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Model)实体类
 *
 * @author Luka
 * @since 2024-02-05 04:26:22
 */
@Data
public class ModelEntity implements Serializable {
    private static final long serialVersionUID = -30222419415256437L;
    private String uuid;
    private String type;
    private String fileName;
    private Date createTime;

}

