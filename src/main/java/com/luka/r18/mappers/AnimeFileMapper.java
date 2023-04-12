package com.luka.r18.mappers;

import com.luka.r18.entity.AnimeFileEntity;
import com.luka.r18.entity.response_object.VideoData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.awt.print.Pageable;
import java.util.List;

@Mapper
public interface AnimeFileMapper {

    List<AnimeFileEntity> selectAnimeFileByType(@Param("type") String type);


    List<AnimeFileEntity> selectAnimeFileByFileName(String fileName);

    AnimeFileEntity selectFileByUuid(String uuid);

    VideoData selectVideoByUuid(String uuid);

    List<AnimeFileEntity> selectAnimeByParentFolder(String parentfolder);

    AnimeFileEntity queryById(Integer id);

    List<AnimeFileEntity> selectAnimeByNameKeyword(String keywords);


    List<AnimeFileEntity> queryAllByLimit(AnimeFileEntity animeFileEntity, @Param("pageable") Pageable pageable);

    long count(AnimeFileEntity animeFileEntity);

    int insert(AnimeFileEntity animeFileEntity);

    int insertBatch(@Param("entities") List<AnimeFileEntity> entities);

    int insertOrUpdateBatch(@Param("entities") List<AnimeFileEntity> entities);

    int update(AnimeFileEntity animeFileEntity);

    int deleteById(Integer id);

}

