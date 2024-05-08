package com.luka.r18.service;

import com.luka.r18.entity.VideoInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface VideoInfoService {

    VideoInfoEntity queryById(Integer id);

    String querySubtitlePath(String videoUuid);

    Page<VideoInfoEntity> queryByPage(VideoInfoEntity videoInfoEntity, PageRequest pageRequest);

    VideoInfoEntity insert(VideoInfoEntity videoInfoEntity);

    VideoInfoEntity update(VideoInfoEntity videoInfoEntity);

    boolean deleteById(Integer id);

}
