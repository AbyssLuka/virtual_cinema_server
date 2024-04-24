package com.luka.r18.entity.response_object;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FileListData {

    private String fileName;
    private String parentFolder;
    private String fileUuid;
    private String fileType;
    private String fileSize;
    private String lastEditTime;

}
