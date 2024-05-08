package com.luka.r18.mappers;

import com.luka.r18.entity.ComicViewEntity;
import com.luka.r18.entity.response_object.ComicView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Mapper
public interface ComicViewMapper {

    ComicViewEntity queryById(String uuid);

    List<ComicView> queryAllByLimit(@Param("ComicViewEntity") ComicViewEntity comicViewEntity, @Param("pageable") Pageable pageable);

    long count(@Param("ComicViewEntity")ComicViewEntity comicViewEntity);

    int insert(@Param("entity")ComicViewEntity comicViewEntity);

    int insertBatch(@Param("entities") List<ComicViewEntity> entities);

    int insertOrUpdateBatch(@Param("entities") List<ComicViewEntity> entities);

    int update(@Param("ComicViewEntity")ComicViewEntity comicViewEntity);

    int deleteById(String uuid);

}

