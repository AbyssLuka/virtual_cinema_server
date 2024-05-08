package com.luka.r18.service;

import com.luka.r18.entity.VideoViewEntity;
import com.luka.r18.entity.response_object.VideoView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface VideoViewService {

    VideoViewEntity queryById(Integer id);

    Page<VideoView> queryByPage(VideoViewEntity videoViewEntity, PageRequest pageRequest, String keyword);

    VideoViewEntity insert(VideoViewEntity videoViewEntity);

    VideoViewEntity update(VideoViewEntity videoViewEntity);

    boolean deleteById(Integer id);

    boolean isEmptyByUuid(String uuid);


}
