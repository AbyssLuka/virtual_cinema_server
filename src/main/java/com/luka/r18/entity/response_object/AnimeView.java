package com.luka.r18.entity.response_object;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * (AnimeView)实体类
 *
 * @author makejava
 * @since 2022-07-15 03:26:49
 */
@Setter
@Getter
public class AnimeView {

    private String uuid;
    private String title;
    /**
     * anime_file table
     */
    private String pathUuid = "";
    private String info = "";
    private Integer clicks;
    private Date createTime;
    private List<FileListData> fileList;
}

