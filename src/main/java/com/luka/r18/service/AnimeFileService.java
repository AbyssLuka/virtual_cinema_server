package com.luka.r18.service;

import com.luka.r18.entity.AnimeFileEntity;
import com.luka.r18.entity.response_object.VideoData;

import java.awt.print.Pageable;
import java.util.List;

/**
 * (AnimeFile)表服务接口
 *
 * @author makejava
 * @since 2022-03-22 04:28:20
 */
public interface AnimeFileService {

    List<AnimeFileEntity> selectAnimeFileByType(String type);

    List<AnimeFileEntity> selectAnimeFileByFileName(String filename);

    AnimeFileEntity selectFileByUuid(String uuid);

    VideoData selectVideoByUuid(String uuid);

    List<AnimeFileEntity> selectAnimeByParentFolder(String parentfolder);

    List<AnimeFileEntity> selectAnimeByNameKeyword(String keywords);




    List<AnimeFileEntity> queryAllByLimit(AnimeFileEntity animeFileEntity, Pageable pageable);

    AnimeFileEntity queryById(Integer id);

    AnimeFileEntity insert(AnimeFileEntity animeFileEntity);

    AnimeFileEntity update(AnimeFileEntity animeFileEntity);

    boolean deleteById(Integer id);

}
