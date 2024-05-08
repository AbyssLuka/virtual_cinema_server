package com.luka.r18.service.impl;

import com.luka.r18.entity.TagMapEntity;
import com.luka.r18.mappers.TagMapMapper;
import com.luka.r18.service.TagMapService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

@Service("tagMapService")
public class TagMapServiceImpl implements TagMapService {
    @Resource
    private TagMapMapper tagMapMapper;

    @Override
    public TagMapEntity queryTag(String animeViewUuid) {
        return this.tagMapMapper.queryTag(animeViewUuid);
    }

    @Override
    public TagMapEntity queryById(Integer id) {
        return this.tagMapMapper.queryById(id);
    }

    @Override
    public Page<TagMapEntity> queryByPage(TagMapEntity tagMapEntity, PageRequest pageRequest) {
        long total = this.tagMapMapper.count(tagMapEntity);
        return new PageImpl<>(this.tagMapMapper.queryAllByLimit(tagMapEntity, pageRequest), pageRequest, total);
    }

    @Override
    public TagMapEntity insert(TagMapEntity tagMapEntity) {
        this.tagMapMapper.insert(tagMapEntity);
        return tagMapEntity;
    }

    @Override
    public TagMapEntity update(TagMapEntity tagMapEntity) {
        this.tagMapMapper.update(tagMapEntity);
        return this.queryById(tagMapEntity.getId());
    }

    @Override
    public boolean deleteById(Integer id) {
        return this.tagMapMapper.deleteById(id) > 0;
    }
}
