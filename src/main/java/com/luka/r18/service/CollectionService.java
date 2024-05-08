package com.luka.r18.service;

import com.luka.r18.entity.CollectionEntity;
import com.luka.r18.entity.response_object.CollectionView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CollectionService {

    CollectionEntity queryById(Integer id);

    Page<CollectionView> queryByPage(CollectionEntity collectionEntity, PageRequest pageRequest);

    CollectionEntity insert(CollectionEntity collectionEntity);

    CollectionEntity update(CollectionEntity collectionEntity);

    boolean deleteById(Integer id);

    CollectionEntity selectByEntity(CollectionEntity collectionEntity);
}
