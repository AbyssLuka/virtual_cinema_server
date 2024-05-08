package com.luka.r18.mappers;

import com.luka.r18.entity.VideoInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;


@Mapper
public interface VideoInfoMapper {

    VideoInfoEntity queryById(Integer id);

    String querySubtitlePath(String videoUuid);

    List<VideoInfoEntity> queryAllByLimit(VideoInfoEntity videoInfoEntity, @Param("pageable") Pageable pageable);

    long count(VideoInfoEntity videoInfoEntity);

    int insert(VideoInfoEntity videoInfoEntity);

    int insertBatch(@Param("entities") List<VideoInfoEntity> entities);

    int insertOrUpdateBatch(@Param("entities") List<VideoInfoEntity> entities);

    int update(VideoInfoEntity videoInfoEntity);

    int deleteById(Integer id);

}

