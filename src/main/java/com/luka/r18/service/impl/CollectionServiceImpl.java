package com.luka.r18.service.impl;

import com.luka.r18.entity.CollectionEntity;
import com.luka.r18.entity.response_object.CollectionView;
import com.luka.r18.mappers.CollectionMapper;
import com.luka.r18.service.CollectionService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

@Service("collectionService")
public class CollectionServiceImpl implements CollectionService {
    @Resource
    private CollectionMapper collectionMapper;

    @Override
    public CollectionEntity queryById(Integer id) {
        return this.collectionMapper.queryById(id);
    }

    @Override
    public Page<CollectionView> queryByPage(CollectionEntity collectionEntity, PageRequest pageRequest) {
        long total = this.collectionMapper.count(collectionEntity);
        return new PageImpl<>(this.collectionMapper.queryAllByLimit(collectionEntity, pageRequest), pageRequest, total);
    }

    @Override
    public CollectionEntity insert(CollectionEntity collectionEntity) {
        this.collectionMapper.insert(collectionEntity);
        return collectionEntity;
    }

    @Override
    public CollectionEntity update(CollectionEntity collectionEntity) {
        this.collectionMapper.update(collectionEntity);
        return this.queryById(collectionEntity.getId());
    }

    @Override
    public boolean deleteById(Integer id) {
        return this.collectionMapper.deleteById(id) > 0;
    }

    @Override
    public CollectionEntity selectByEntity(CollectionEntity collectionEntity) {
        return collectionMapper.selectByEntity(collectionEntity);
    }
}
