package com.luka.r18.service.impl;

import com.luka.r18.entity.VideoInfoEntity;
import com.luka.r18.mappers.VideoInfoMapper;
import com.luka.r18.service.VideoInfoService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

@Service("videoInfoService")
public class VideoInfoServiceImpl implements VideoInfoService {
    @Resource
    private VideoInfoMapper videoInfoMapper;

    @Override
    public VideoInfoEntity queryById(Integer id) {
        return this.videoInfoMapper.queryById(id);
    }

    @Override
    public String querySubtitlePath(String videoUuid) {
        return videoInfoMapper.querySubtitlePath(videoUuid);
    }

    @Override
    public Page<VideoInfoEntity> queryByPage(VideoInfoEntity videoInfoEntity, PageRequest pageRequest) {
        long total = this.videoInfoMapper.count(videoInfoEntity);
        return new PageImpl<>(this.videoInfoMapper.queryAllByLimit(videoInfoEntity, pageRequest), pageRequest, total);
    }

    @Override
    public VideoInfoEntity insert(VideoInfoEntity videoInfoEntity) {
        this.videoInfoMapper.insert(videoInfoEntity);
        return videoInfoEntity;
    }

    @Override
    public VideoInfoEntity update(VideoInfoEntity videoInfoEntity) {
        this.videoInfoMapper.update(videoInfoEntity);
        return this.queryById(videoInfoEntity.getId());
    }

    @Override
    public boolean deleteById(Integer id) {
        return this.videoInfoMapper.deleteById(id) > 0;
    }
}
