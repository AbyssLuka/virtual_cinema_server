package com.luka.r18.service;

import com.luka.r18.entity.TagMapEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface TagMapService {

    TagMapEntity queryTag(String fileEntity);

    TagMapEntity queryById(Integer id);

    Page<TagMapEntity> queryByPage(TagMapEntity tagMapEntity, PageRequest pageRequest);

    TagMapEntity insert(TagMapEntity tagMapEntity);

    TagMapEntity update(TagMapEntity tagMapEntity);

    boolean deleteById(Integer id);

}
