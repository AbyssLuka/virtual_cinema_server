package com.luka.r18.service;

import com.luka.r18.entity.ComicViewEntity;
import com.luka.r18.entity.response_object.ComicView;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ComicViewService {

    ComicViewEntity queryById(String uuid);

    Page<ComicView> queryByPage(ComicViewEntity comicViewEntity, PageRequest pageRequest);

    ComicViewEntity insert( ComicViewEntity comicViewEntity);

    ComicViewEntity update(ComicViewEntity comicViewEntity);

    boolean deleteById(String uuid);

}
