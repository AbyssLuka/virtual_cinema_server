package com.luka.r18.service;

import com.luka.r18.entity.FileEntity;
import com.luka.r18.entity.response_object.VideoData;

import java.awt.print.Pageable;
import java.util.List;

public interface FileService {

    List<FileEntity> selectFileByType(String type);

    List<FileEntity> selectFileByFileName(String filename);

    FileEntity selectFileByUuid(String uuid);

    VideoData selectVideoByUuid(String uuid);

    List<FileEntity> selectFileByParentFolder(String parentfolder);

    List<FileEntity> selectFileByNameKeyword(String keywords);

    List<FileEntity> queryAllByLimit(FileEntity fileEntity, Pageable pageable);

    FileEntity queryById(Integer id);

    FileEntity insert(FileEntity fileEntity);

    FileEntity update(FileEntity fileEntity);

    boolean deleteById(Integer id);

}
