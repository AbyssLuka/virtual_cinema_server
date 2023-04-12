package com.luka.r18.service.impl;

import com.luka.r18.entity.AnimeFileEntity;
import com.luka.r18.entity.response_object.VideoData;
import com.luka.r18.mappers.AnimeFileMapper;
import com.luka.r18.service.AnimeFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.print.Pageable;
import java.util.List;

@Service
public class AnimeFileServiceImpl implements AnimeFileService {

    @Resource
    private AnimeFileMapper animeFileMapper;

    @Override
    public List<AnimeFileEntity> selectAnimeFileByType(String type) {
        return animeFileMapper.selectAnimeFileByType(type);
    }

    @Override
    public List<AnimeFileEntity> selectAnimeFileByFileName(String fileName) {
        return animeFileMapper.selectAnimeFileByFileName(fileName);
    }

    @Override
    public AnimeFileEntity selectFileByUuid(String uuid) {
        return animeFileMapper.selectFileByUuid(uuid);
    }

    @Override
    public VideoData selectVideoByUuid(String uuid) {
        return animeFileMapper.selectVideoByUuid(uuid);
    }

    @Override
    public List<AnimeFileEntity> selectAnimeByParentFolder(String parentfolder) {
        return animeFileMapper.selectAnimeByParentFolder(parentfolder);
    }

    @Override
    public List<AnimeFileEntity> selectAnimeByNameKeyword(String keywords) {
        return animeFileMapper.selectAnimeByNameKeyword(keywords);
    }





    @Override
    public List<AnimeFileEntity> queryAllByLimit(AnimeFileEntity animeFileEntity, Pageable pageable) {
        return animeFileMapper.queryAllByLimit(animeFileEntity, pageable);
    }

    @Override
    public AnimeFileEntity queryById(Integer id) {
        return this.animeFileMapper.queryById(id);
    }


    @Override
    public AnimeFileEntity insert(AnimeFileEntity animeFileEntity) {
        this.animeFileMapper.insert(animeFileEntity);
        return animeFileEntity;
    }


    @Override
    public AnimeFileEntity update(AnimeFileEntity animeFileEntity) {
        this.animeFileMapper.update(animeFileEntity);
        return this.queryById(animeFileEntity.getId());
    }


    @Override
    public boolean deleteById(Integer id) {
        return this.animeFileMapper.deleteById(id) > 0;
    }


}
