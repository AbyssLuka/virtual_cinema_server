package com.luka.r18.service.impl;

import com.luka.r18.entity.VideoViewEntity;
import com.luka.r18.entity.response_object.VideoView;
import com.luka.r18.mappers.VideoViewMapper;
import com.luka.r18.service.VideoViewService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

@Service("videoViewService")
public class VideoViewServiceImpl implements VideoViewService {
    @Resource
    private VideoViewMapper videoViewMapper;

    @Override
    public VideoViewEntity queryById(Integer id) {
        return this.videoViewMapper.queryById(id);
    }

    @Override
    public Page<VideoView> queryByPage(VideoViewEntity videoViewEntity, PageRequest pageRequest, String keyword) {
        long total = this.videoViewMapper.count(videoViewEntity, keyword);
        return new PageImpl<>(this.videoViewMapper.queryAllByLimit(videoViewEntity, pageRequest, keyword), pageRequest, total);
    }

    @Override
    public VideoViewEntity insert(VideoViewEntity videoViewEntity) {
        this.videoViewMapper.insert(videoViewEntity);
        return videoViewEntity;
    }

    @Override
    public VideoViewEntity update(VideoViewEntity videoViewEntity) {
        this.videoViewMapper.update(videoViewEntity);
        return this.queryById(videoViewEntity.getId());
    }

    @Override
    public boolean deleteById(Integer id) {
        return this.videoViewMapper.deleteById(id) > 0;
    }

    @Override
    public boolean isEmptyByUuid(String uuid) {
        return this.videoViewMapper.isEmptyByUuid(uuid) > 0;
    }
}
