package com.luka.r18.service.impl;

import com.luka.r18.entity.ComicViewEntity;
import com.luka.r18.entity.response_object.ComicView;
import com.luka.r18.mappers.ComicViewMapper;
import com.luka.r18.service.ComicViewService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

@Service("comicViewService")
public class ComicViewServiceImpl implements ComicViewService {
    @Resource
    private ComicViewMapper comicViewMapper;

    @Override
    public ComicViewEntity queryById(String uuid) {
        return this.comicViewMapper.queryById(uuid);
    }

    @Override
    public Page<ComicView> queryByPage(ComicViewEntity comicViewEntity, PageRequest pageRequest) {
        long total = this.comicViewMapper.count(comicViewEntity);
        return new PageImpl<>(this.comicViewMapper.queryAllByLimit(comicViewEntity, pageRequest), pageRequest, total);
    }

    @Override
    public ComicViewEntity insert(ComicViewEntity comicViewEntity) {
        this.comicViewMapper.insert(comicViewEntity);
        return comicViewEntity;
    }

    @Override
    public ComicViewEntity update(ComicViewEntity comicViewEntity) {
        this.comicViewMapper.update(comicViewEntity);
        return this.queryById(comicViewEntity.getUuid());
    }

    @Override
    public boolean deleteById(String uuid) {
        return this.comicViewMapper.deleteById(uuid) > 0;
    }
}
