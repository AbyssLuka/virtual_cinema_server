package com.luka.r18.controller;

import com.luka.r18.entity.AnimeTagEntity;
import com.luka.r18.service.impl.AnimeTagServiceImpl;
import com.luka.r18.util.CustomUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * (AnimeTag)表控制层
 *
 * @author makejava
 * @since 2022-09-21 00:29:11
 */
@RestController
@RequestMapping("tag")
public class AnimeTagController {

    @Resource
    AnimeTagServiceImpl animeTagService;

    @RequestMapping(path = {"add"})
    public String add(String tagName){
        AnimeTagEntity animeTagEntity = new AnimeTagEntity();
        animeTagEntity.setUuid(CustomUtil.getUUID());
        animeTagEntity.setTagName(tagName);
        animeTagEntity.setInfo("");
        animeTagEntity.setCreateTime(new Date());
        animeTagEntity = animeTagService.insert(animeTagEntity);
        if (animeTagEntity!=null){
            return CustomUtil.toJson(200,"");
        }else {
            return CustomUtil.toJson(400,"error");
        }
    }
}

