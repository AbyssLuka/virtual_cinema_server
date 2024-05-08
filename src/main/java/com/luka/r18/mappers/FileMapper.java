package com.luka.r18.mappers;

import com.luka.r18.entity.FileEntity;
import com.luka.r18.entity.response_object.VideoData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.awt.print.Pageable;
import java.util.List;

@Mapper
public interface FileMapper {

    List<FileEntity> selectFileByType(@Param("type") String type);

    List<FileEntity> selectFileByFileName(String fileName);

    FileEntity selectFileByUuid(String uuid);

    VideoData selectVideoByUuid(String uuid);

    List<FileEntity> selectFileByParentFolder(String parentfolder);

    FileEntity queryById(Integer id);

    List<FileEntity> selectFileByNameKeyword(String keywords);


    List<FileEntity> queryAllByLimit(FileEntity fileEntity, @Param("pageable") Pageable pageable);

    long count(FileEntity fileEntity);

    int insert(FileEntity fileEntity);

    int insertBatch(@Param("entities") List<FileEntity> entities);

    int insertOrUpdateBatch(@Param("entities") List<FileEntity> entities);

    int update(FileEntity fileEntity);

    int deleteById(Integer id);

}

