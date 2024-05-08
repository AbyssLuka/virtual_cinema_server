package com.luka.r18.service.impl;

import com.luka.r18.entity.FileEntity;
import com.luka.r18.entity.response_object.VideoData;
import com.luka.r18.mappers.FileMapper;
import com.luka.r18.service.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.print.Pageable;
import java.util.List;

@Service("fileService")
public class FileServiceImpl implements FileService {

    @Resource
    private FileMapper fileMapper;

    @Override
    public List<FileEntity> selectFileByType(String type) {
        return fileMapper.selectFileByType(type);
    }

    @Override
    public List<FileEntity> selectFileByFileName(String fileName) {
        return fileMapper.selectFileByFileName(fileName);
    }

    @Override
    public FileEntity selectFileByUuid(String uuid) {
        return fileMapper.selectFileByUuid(uuid);
    }

    @Override
    public VideoData selectVideoByUuid(String uuid) {
        return fileMapper.selectVideoByUuid(uuid);
    }

    @Override
    public List<FileEntity> selectFileByParentFolder(String parentfolder) {
        return fileMapper.selectFileByParentFolder(parentfolder);
    }

    @Override
    public List<FileEntity> selectFileByNameKeyword(String keywords) {
        return fileMapper.selectFileByNameKeyword(keywords);
    }


    @Override
    public List<FileEntity> queryAllByLimit(FileEntity fileEntity, Pageable pageable) {
        return fileMapper.queryAllByLimit(fileEntity, pageable);
    }

    @Override
    public FileEntity queryById(Integer id) {
        return this.fileMapper.queryById(id);
    }


    @Override
    public FileEntity insert(FileEntity fileEntity) {
        this.fileMapper.insert(fileEntity);
        return fileEntity;
    }


    @Override
    public FileEntity update(FileEntity fileEntity) {
        this.fileMapper.update(fileEntity);
        return this.queryById(fileEntity.getId());
    }


    @Override
    public boolean deleteById(Integer id) {
        return this.fileMapper.deleteById(id) > 0;
    }


}
