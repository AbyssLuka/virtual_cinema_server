package com.luka.r18.entity.response_object;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class VideoView {

    private String uuid;
    private String title;
    private String pathUuid = "";
    private String info = "";
    private Integer clicks;
    private Date createTime;
    private List<FileListData> fileList;

}

