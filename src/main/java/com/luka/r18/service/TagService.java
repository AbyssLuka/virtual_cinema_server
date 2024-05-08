package com.luka.r18.service;

import com.luka.r18.entity.TagEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface TagService {

    TagEntity queryByUuid(String uuid);

    TagEntity queryById(Integer id);

    Page<TagEntity> queryByPage(TagEntity tagEntity, PageRequest pageRequest);

    TagEntity insert(TagEntity tagEntity);

    TagEntity update(TagEntity tagEntity);

    boolean deleteById(Integer id);

}
