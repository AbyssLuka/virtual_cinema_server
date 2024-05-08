package com.luka.r18.mappers;

import com.luka.r18.entity.VideoViewEntity;
import com.luka.r18.entity.response_object.VideoView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Mapper
public interface VideoViewMapper {

    VideoViewEntity queryById(Integer id);


    List<VideoView> queryAllByLimit(
            @Param("videoView") VideoViewEntity videoViewEntity,
            @Param("pageable") Pageable pageable,
            @Param("keyword")  String keyword);


    long count( @Param("videoView") VideoViewEntity videoViewEntity,
                @Param("keyword")  String keyword);

    int insert(VideoViewEntity videoViewEntity);

    int insertBatch(@Param("entities") List<VideoViewEntity> entities);

    int insertOrUpdateBatch(@Param("entities") List<VideoViewEntity> entities);

    int update(VideoViewEntity videoViewEntity);

    int deleteById(Integer id);

    int isEmptyByUuid(@Param("uuid") String uuid);

}

