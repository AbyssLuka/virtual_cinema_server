package com.luka.r18.entity.response_object;

import java.util.Date;
import java.util.List;

public class ComicView {

    private Integer id;
    private String uuid;
    private String title;
    private String pathUuid = "";
    private String info = "";
    private Integer clicks;
    private Date createTime;
    private List<FileListData> fileList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPathUuid() {
        return pathUuid;
    }

    public void setPathUuid(String pathUuid) {
        this.pathUuid = pathUuid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<FileListData> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileListData> fileList) {
        this.fileList = fileList;
    }
}

