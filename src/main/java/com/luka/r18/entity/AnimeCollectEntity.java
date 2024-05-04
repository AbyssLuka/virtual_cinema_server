package com.luka.r18.entity;

import com.luka.r18.util.CustomUtil;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (AnimeCollect)实体类
 *
 * @author Luka
 * @since 2022-08-30 05:03:58
 */
@Data
public class AnimeCollectEntity implements Serializable {

    private static final long serialVersionUID = 751078978239888832L;
    private Integer id;
    private String uuid;
    private String userUuid;
    private String viewUuid;
    private Date createTime;

}

