package com.luka.r18.service.impl;

import com.luka.r18.entity.TagEntity;
import com.luka.r18.mappers.TagMapper;
import com.luka.r18.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

@Service("tagService")
public class TagServiceImpl implements TagService {
    @Resource
    private TagMapper tagMapper;

    @Override
    public TagEntity queryByUuid(String uuid) {
        return this.tagMapper.queryByUuid(uuid);
    }

    @Override
    public TagEntity queryById(Integer id) {
        return this.tagMapper.queryById(id);
    }

    @Override
    public Page<TagEntity> queryByPage(TagEntity tagEntity, PageRequest pageRequest) {
        long total = this.tagMapper.count(tagEntity);
        return new PageImpl<>(this.tagMapper.queryAllByLimit(tagEntity, pageRequest), pageRequest, total);
    }

    @Override
    public TagEntity insert(TagEntity tagEntity) {
        this.tagMapper.insert(tagEntity);
        return tagEntity;
    }

    @Override
    public TagEntity update(TagEntity tagEntity) {
        this.tagMapper.update(tagEntity);
        return this.queryById(tagEntity.getId());
    }

    @Override
    public boolean deleteById(Integer id) {
        return this.tagMapper.deleteById(id) > 0;
    }
}
